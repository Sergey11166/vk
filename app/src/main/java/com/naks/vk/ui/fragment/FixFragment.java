package com.naks.vk.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.List;

//http://ideaventure.blogspot.ru/2014/10/nested-retained-fragment-lost-state.html
public class FixFragment extends Fragment {

    private FragmentManager retainedChildFragmentManager;
    private FragmentHostCallback currentHost;
    private Field mHostField;

    {
        //Prepare the reflections to manage hided fields
        try {
            Class fragmentImplClass = Class.forName("android.support.v4.app.FragmentManagerImpl");
            mHostField = fragmentImplClass.getDeclaredField("mHost");
            mHostField.setAccessible(true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("FragmentManagerImpl is renamed due to the " +
                    "change of Android SDK, this workaround doesn't work any more. " +
                    "See the issue at " +
                    "https://code.google.com/p/android/issues/detail?id=74222", e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("FragmentManagerImpl.mHost is found due to the " +
                    "change of Android SDK, this workaround doesn't work any more. " +
                    "See the issue at " +
                    "https://code.google.com/p/android/issues/detail?id=74222", e);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    protected FragmentManager childFragmentManager() {
        if (retainedChildFragmentManager == null) {
            retainedChildFragmentManager = getChildFragmentManager();
        }
        return retainedChildFragmentManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (retainedChildFragmentManager != null) {
            //restore the last retained child fragment manager to the new
            //created fragment
            try {
                //Copy the mHost(Activity) to retainedChildFragmentManager
                currentHost = (FragmentHostCallback) mHostField.get(getFragmentManager());

                Field childFMField = Fragment.class.getDeclaredField("mChildFragmentManager");
                childFMField.setAccessible(true);
                childFMField.set(this, retainedChildFragmentManager);

                refreshHosts(getFragmentManager());
            } catch (Exception e) {
                Log.w(e.getMessage(), e);
            }
            //Refresh children fragment's hosts
        } else {
            //If the child fragment manager has not been retained yet, let it hold the internal
            //child fragment manager as early as possible. This can prevent child fragment
            //manager from missing to be set and then retained, which could happen when
            //OS kills activity and restarts it. In this case, the delegate fragment restored
            //but childFragmentManager() may not be called so mRetainedChildFragmentManager is
            //yet set. If the fragment is rotated, the state of child fragment manager will be
            //lost since mRetainedChildFragmentManager hasn't set to be retained by the OS.
            retainedChildFragmentManager = getChildFragmentManager();
        }
    }

    private void refreshHosts(FragmentManager fragmentManager) throws IllegalAccessException {
        if (fragmentManager != null) {
            replaceFragmentManagerHost(fragmentManager);
        }

        //replace host(activity) of fragments already added
        assert fragmentManager != null;
        List<Fragment> frags = fragmentManager.getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null) {
                    try {
                        //Copy the mHost(Activity) to retainedChildFragmentManager
                        Field mHostField = Fragment.class.getDeclaredField("mHost");
                        mHostField.setAccessible(true);
                        mHostField.set(f, currentHost);
                    } catch (Exception e) {
                        Log.w(e.getMessage(), e);
                    }
                    if (f.getChildFragmentManager() != null) {
                        refreshHosts(f.getChildFragmentManager());
                    }
                }
            }
        }
    }

    //replace host(activity) of the fragment manager so that new fragments it creates will be attached
    //with correct activity
    private void replaceFragmentManagerHost(FragmentManager fragmentManager) throws IllegalAccessException {
        if (currentHost != null) {
            mHostField.set(fragmentManager, currentHost);
        }
    }
}
