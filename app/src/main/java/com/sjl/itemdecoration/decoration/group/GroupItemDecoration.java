package com.sjl.itemdecoration.decoration.group;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * GroupItemDecoration
 *
 * @author 沈建林
 * @date 2018/11/27
 */
public class GroupItemDecoration extends RecyclerView.ItemDecoration {
    private int headHeight;
    private int dividerHeight;
    private GroupCallback groupCallback;

    public GroupItemDecoration(int headHeight, int dividerHeight, GroupCallback groupCallback) {
        this.headHeight = headHeight;
        this.dividerHeight = dividerHeight;
        this.groupCallback = groupCallback;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (groupCallback != null) {
            int position = parent.getChildAdapterPosition(view);
            GroupBean groupBean = groupCallback.getGroupBean(position);
            if (groupBean != null) {
                outRect.top = groupBean.getTop();
            }
        }
    }
}
