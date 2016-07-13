package com.naks.vk.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.naks.vk.R;
import com.naks.vk.api.domain.VKApiItem;
import com.naks.vk.api.domain.VKApiNews;
import com.naks.vk.ui.widget.NewsImagesContentView;
import com.naks.vk.ui.widget.NewsTextContentView;
import com.vk.sdk.api.model.VKApiCommunityFull;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKAttachments;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int VISIBLE_THRESHOLD = 10;

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;
    private static final int VIEW_TYPE_ERROR_LOAD_PAGE = 3;

    private int lastVisibleItem, totalItemCount;
    private boolean isLoading;
    private boolean isShowError;

    private Context context;
    private VKApiNews data;
    private OnNewsItemClickListener newsItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private MenuItemClickListener menuItemClickListener;
    private List<Integer> expandedPositions;

    public NewsRecyclerAdapter(Context context) {
        this.context = context;
        this.expandedPositions = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_ITEM:
                return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.news_item, parent, false));
            case VIEW_TYPE_PROGRESS:
                return new ProgressViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.progress_item, parent, false));
            case VIEW_TYPE_ERROR_LOAD_PAGE:
                return new ErrorViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.error_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ProgressViewHolder) return;

        if (holder instanceof ErrorViewHolder) {
            ((ErrorViewHolder)holder).button.setOnClickListener(v -> {
                isShowError = false;
                notifyItemChanged(getItemCount() - 1);
                onLoadMoreListener.onLoadMore();
            });
            return;
        }

        ItemViewHolder vh = (ItemViewHolder) holder;
        vh.item = data.items.get(position);

        int sourceId = vh.item.source_id;
        if (sourceId > 0) {
            vh.user = findUsersByItem(sourceId);
        } else {
            vh.group = findGroupByItem(sourceId);
        }
        assert vh.user != null;
        assert vh.group != null;

        Glide.with(context.getApplicationContext())
                .load(sourceId > 0 ? vh.user.photo_100 : vh.group.photo_100)
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(vh.headImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory
                                .create(context.getResources(), resource);
                        drawable.setCircular(true);
                        vh.headImage.setImageDrawable(drawable);
                    }
                });

        vh.owner.setText(sourceId > 0 ? vh.user.first_name + " " + vh.user.last_name :
                vh.group.name);

        String date = String.valueOf(vh.item.date);
        // TODO: 28.06.2016: Implement pars date
        vh.date.setText(date);

        vh.ownerLayout.setOnClickListener(view -> {
            Toast.makeText(context, "clickOnHeader", Toast.LENGTH_SHORT).show();
            // TODO: 28.06.2016: Implement showing owner (profile or group)
        });

        vh.popupButton.setOnClickListener(view -> showPopupMenu(vh.item, view));

        vh.text.setExpanded(expandedPositions.contains(position));
        vh.text.setText(vh.item.text, position);
        vh.text.setOnTextClickListener(v -> onItemClick(v, vh));
        vh.text.setOnExpandClickListener(pos -> expandedPositions.add(pos));

        List<VKApiPhoto> images = new ArrayList<>();
        for (VKAttachments.VKApiAttachment attachment : vh.item.attachments) {
            if (attachment.getType().equals("photo")) {
                images.add(((VKApiPhoto) attachment));
            }
        }
        vh.images.setImages(images);

        vh.v.setOnClickListener(v -> onItemClick(v, vh));
    }

    private void onItemClick(View v, ItemViewHolder vh) {
        if (v.getTag() != null) {
            v.setTag(null);
            return;
        }
        Toast.makeText(context, "onNewsClick", Toast.LENGTH_SHORT).show();
        newsItemClickListener.onClick(vh.item, vh.user, vh.group);
    }

    @Override
    public int getItemCount() {
        return data == null || data.items == null ? 0 : data.items.size();
    }

    public VKApiNews getData() {
        return data;
    }

    public void setData(VKApiNews data) {
        expandedPositions.clear();
        this.data = data;
        notifyDataSetChanged();
    }

    public void addData(VKApiNews data) {
        if (this.data == null) {
            this.data = data;
            return;
        }
        this.data.items.remove(getItemCount() - 1);
        this.data.items.addAll(data.items);

        for (VKApiUserFull user : data.profiles) {
            if (!this.data.profiles.contains(user)) {
                this.data.profiles.add(user);
            }
        }

        for (VKApiCommunityFull group : data.groups) {
            if (!this.data.groups.contains(group)) {
                this.data.groups.add(group);
            }
        }
        notifyDataSetChanged();
        isLoading = false;
    }

    private VKApiUserFull findUsersByItem(int sourceId) {
        for (VKApiUserFull user : data.profiles) {
            if(user.id == sourceId) return user;
        }
        return null;
    }

    private VKApiCommunityFull findGroupByItem(int sourceId) {
        for (VKApiCommunityFull group : data.groups) {
            if(group.id == -sourceId) return group;
        }
        return null;
    }

    public void initOnScrollListener(RecyclerView recyclerView) {

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                        data.items.add(null);
                        notifyItemInserted(getItemCount() - 1);
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void showErrorLoadPage() {
        isShowError = true;
        notifyItemChanged(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        VKApiItem item = data.items.get(position);
        if (item != null) {
            return VIEW_TYPE_ITEM;
        } else {
            return isShowError ? VIEW_TYPE_ERROR_LOAD_PAGE : VIEW_TYPE_PROGRESS;
        }
    }

    private void showPopupMenu(VKApiItem item, View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.popup_menu_news_item);
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            menuItemClickListener.onItemClick(context, item, menuItem.getItemId());
            return true;
        });
        popupMenu.show();
    }

    public void setOnNewsItemClickListener(OnNewsItemClickListener listener) {
        newsItemClickListener = listener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        onLoadMoreListener = listener;
    }

    public void setMenuItemClickListener(MenuItemClickListener listener) {
        menuItemClickListener = listener;
    }

    public interface OnNewsItemClickListener {
        void onClick(VKApiItem item, VKApiUserFull user, VKApiCommunityFull community);
    }

    public interface MenuItemClickListener {
        void onItemClick(Context context, VKApiItem item, int menuItemId);
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public final View v;

        @BindView(R.id.ownerLayout) View ownerLayout;
        @BindView(R.id.headImage) ImageView headImage;
        @BindView(R.id.owner) TextView owner;
        @BindView(R.id.date) TextView date;
        @BindView(R.id.popupButton) ImageView popupButton;
        @BindView(R.id.newsTextView) NewsTextContentView text;
        @BindView(R.id.newsImagesView) NewsImagesContentView images;

        public VKApiItem item;
        public VKApiUserFull user;
        public VKApiCommunityFull group;
        public ItemViewHolder(View view) {
            super(view);
            v = view;
            ButterKnife.bind(this, view);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public final View v;

        public ProgressViewHolder(View view) {
            super(view);
            v = view;
            ButterKnife.bind(this, view);
        }
    }

    public static class ErrorViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button) Button button;

        public ErrorViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
