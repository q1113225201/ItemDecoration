package com.sjl.itemdecoration.decoration.group;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 分组分割
 *
 * @author 林zero
 * @date 2018/11/27
 */
public class GroupItemDecoration extends RecyclerView.ItemDecoration {
    private GroupCallback groupCallback;

    public GroupItemDecoration(GroupCallback groupCallback) {
        this.groupCallback = groupCallback;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (groupCallback == null) {
            return;
        }
        int position = parent.getChildAdapterPosition(view);
        GroupBean groupBean = groupCallback.getGroupBean(position);
        if (groupBean != null) {
            //设置每个item的上边距
            outRect.top = groupBean.getTop();
        }else{
            //为null时上边距为0
            outRect.top = 0;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (groupCallback == null) {
            return;
        }
        //屏幕范围内item个数
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);
            GroupBean groupBean = groupCallback.getGroupBean(position);
            if (groupBean == null) {
                continue;
            }
            View dividerView = groupCallback.getDividerView(position);
            if(dividerView==null){
                continue;
            }
            //分割线高度
            int dividerHeight = groupBean.getTop();
            //分割线上边沿位置
            int top = childView.getTop() + parent.getPaddingTop()-dividerHeight;
            //计算设置每个获取到分割View的宽高
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dividerHeight);
            dividerView.setLayoutParams(layoutParams);
            dividerView.measure(View.MeasureSpec.makeMeasureSpec(right, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(dividerHeight, View.MeasureSpec.EXACTLY));
            //布局大小
            dividerView.layout(0, 0, right, dividerHeight);
            //必须加，否则可能出现获取到的bitmap为null
            dividerView.setDrawingCacheEnabled(true);
            dividerView.buildDrawingCache();
            //没有上面两句获取到的就是null
            Bitmap bitmap = dividerView.getDrawingCache();
            c.drawBitmap(bitmap, left, top, null);
        }
    }
}
