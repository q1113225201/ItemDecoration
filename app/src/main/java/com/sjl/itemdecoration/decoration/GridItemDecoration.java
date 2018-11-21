package com.sjl.itemdecoration.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * GridItemDecoration
 *
 * @author 林zero
 * @date 2018/11/20
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable mVerticalDivider;
    private Drawable mHorizontalDivider;

    private Rect mBounds = new Rect();
    private boolean hasBorder = true;

    public GridItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mVerticalDivider = a.getDrawable(0);
        mHorizontalDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        if (mVerticalDivider == null && mHorizontalDivider == null) {
            outRect.set(0, 0, 0, 0);
            return;
        }
        //列表中item个数
        int childCount = parent.getAdapter().getItemCount();
        //列表中位置
        int position = parent.getChildAdapterPosition(view);
        //列数
        int spanCount = getSpanCount(parent);

        //左右偏移
        //行中位置
        int indexHorizontal = position % spanCount;
        //纵向分割线宽度
        int dividerWidthHorizontal = mVerticalDivider.getIntrinsicWidth();
        //有边界  itemWidth = ( 列数 + 1 ) * 分割线宽度 / 列数
        //无边界  itemWidth = ( 列数 - 1 ) * 分割线宽度 / 列数
        //每个item内横向分割线占用的宽度
        int itemWidthHorizontal = (spanCount + (hasBorder ? 1 : -1)) * dividerWidthHorizontal / spanCount;
        int left;
        int right;
        if (hasBorder) {
            //有边框
            // left = ( 行中位置 + 1 ) * 分割线宽度 - 行中位置 * 每个item内横向分割线占用的宽度
            left = (indexHorizontal + 1) * dividerWidthHorizontal - indexHorizontal * itemWidthHorizontal;
            //right = 每个item内横向分割线占用的宽度 - left
            right = itemWidthHorizontal - left;
        } else {
            //无边框
            //left = 行中位置 * ( 分割线宽度 - 每个item内横向分割线占用的宽度 )
            left = indexHorizontal * (dividerWidthHorizontal - itemWidthHorizontal);
            //right = 每个item内横向分割线占用的宽度 - left
            right = itemWidthHorizontal - left;
        }
        left += parent.getPaddingLeft();
        right += parent.getPaddingLeft();

        //上下偏移
        int dividerHeightVertical = mHorizontalDivider.getIntrinsicWidth();
        int top;
        int bottom;
        if (hasBorder) {
            //有边框
            top = isFirstRow(position, spanCount) ? dividerHeightVertical : (dividerHeightVertical / 2);
            bottom = isLastRow(position, childCount, spanCount) ? dividerHeightVertical : (dividerHeightVertical / 2);
        } else {
            //无边框
            top = isFirstRow(position, spanCount) ? 0 : (dividerHeightVertical / 2);
            bottom = isLastRow(position, childCount, spanCount) ? 0 : (dividerHeightVertical / 2);
        }
        outRect.set(left, top, right, bottom);
    }

    /**
     * 第一行
     */
    private boolean isFirstRow(int position, int spanCount) {
        return position < spanCount;
    }

    /**
     * 最后一行
     */
    private boolean isLastRow(int position, int childCount, int spanCount) {
        return position >= childCount - spanCount;
    }

    /**
     * 获取列数
     */
    private int getSpanCount(RecyclerView parent) {
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        super.onDraw(c, parent, state);

//        drawVertical(c, parent);
//        drawHorizontal(c, parent);
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            final int top = bottom - mVerticalDivider.getIntrinsicHeight();
            mVerticalDivider.setBounds(left, top, right, bottom);
            mVerticalDivider.draw(canvas);
        }
        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            int position = parent.getChildAdapterPosition(child);
            final int right = mBounds.right + Math.round(child.getTranslationX());
//            final int right = parent.getLayoutManager().getDecoratedRight(child);
            final int left = right - mHorizontalDivider.getIntrinsicWidth();
            mHorizontalDivider.setBounds(left, top, right, bottom);
            mHorizontalDivider.draw(canvas);
        }
        canvas.restore();
    }

    public void setVerticalDivider(Drawable verticalDivider) {
        this.mVerticalDivider = verticalDivider;
    }

    public void setHorizontalDivider(Drawable horizontalDivider) {
        this.mHorizontalDivider = horizontalDivider;
    }
}
