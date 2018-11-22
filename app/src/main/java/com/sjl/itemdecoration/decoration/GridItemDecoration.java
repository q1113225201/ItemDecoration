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
    private boolean hasBorder = false;

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
        int itemCount = parent.getAdapter().getItemCount();
        //列表中位置
        int position = parent.getChildAdapterPosition(view);
        //列数
        int spanCount = getSpanCount(parent);

        //左右偏移
        //行中位置
        int indexHorizontal = position % spanCount;
        //纵向分割线宽度
        int dividerWidth = mVerticalDivider.getIntrinsicWidth();
        //有边界  itemWidth = ( 列数 + 1 ) * 分割线宽度 / 列数
        //无边界  itemWidth = ( 列数 - 1 ) * 分割线宽度 / 列数
        //每个item内分割线占用的宽度
        int itemDividerWidth = (spanCount + (hasBorder ? 1 : -1)) * dividerWidth / spanCount;
        int left;
        int right;
        if (hasBorder) {
            //有边框
            // left = ( 行中位置 + 1 ) * 分割线宽度 - 行中位置 * 每个item内分割线占用的宽度
            left = (indexHorizontal + 1) * dividerWidth - indexHorizontal * itemDividerWidth;
            //right = 每个item内分割线占用的宽度 - left
            right = itemDividerWidth - left;
        } else {
            //无边框
            //left = 行中位置 * ( 分割线宽度 - 每个item内分割线占用的宽度 )
            left = indexHorizontal * (dividerWidth - itemDividerWidth);
            //right = 每个item内分割线占用的宽度 - left
            right = itemDividerWidth - left;
        }
        left += parent.getPaddingLeft();
        right += parent.getPaddingLeft();

        //上下偏移
        //横向分割线高度
        int dividerHeight = mHorizontalDivider.getIntrinsicHeight();
        int top;
        int bottom;
        if (hasBorder) {
            //有边框
            top = isFirstRow(position, spanCount) ? dividerHeight : (dividerHeight / 2);
            bottom = isLastRow(position, itemCount, spanCount) ? dividerHeight : (dividerHeight / 2);
        } else {
            //无边框
            top = isFirstRow(position, spanCount) ? 0 : (dividerHeight / 2);
            bottom = isLastRow(position, itemCount, spanCount) ? 0 : (dividerHeight / 2);
        }
        outRect.set(left, top, right, bottom);
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

        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        int bottom;
        int top;
        int itemCount = parent.getAdapter().getItemCount();
        int spanCount = getSpanCount(parent);
        int itemWidth = (parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight()) / spanCount;
        int dividerHeight = mVerticalDivider.getIntrinsicHeight();
        int position;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            position = parent.getChildAdapterPosition(child);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            left = mBounds.left;
            right = left + itemWidth;
            top = mBounds.bottom - dividerHeight / 2;
            if (isLastRow(position, itemCount, spanCount)) {
                //最后一行，有边界需要完整分割线高度，没边界减掉
                top += hasBorder ? -dividerHeight / 2 : dividerHeight / 2;
            }
            if (position >= itemCount - spanCount && !hasBorder) {
                //最后几个，且没有边框
                bottom = top;
            } else {
                bottom = top + dividerHeight;
            }
            mVerticalDivider.setBounds(left, top, right, bottom);
            mVerticalDivider.draw(canvas);

            if (isFirstRow(position, spanCount) && hasBorder) {
                //第一行且有边界，需要最上面一条
                top = mBounds.top;
                bottom = top + dividerHeight;
                mVerticalDivider.setBounds(left, top, right, bottom);
                mVerticalDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    private boolean isLastItem(int position, int itemCount) {
        return position + 1 == itemCount;
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int itemCount = parent.getAdapter().getItemCount();
        int spanCount = getSpanCount(parent);
        int childCount = parent.getChildCount();
        int itemWidth = (parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight()) / spanCount;
        int dividerWidth = mHorizontalDivider.getIntrinsicWidth();
        int itemDividerWidth = (spanCount + (hasBorder ? 1 : -1)) * dividerWidth / spanCount;
        int position;


        int top;
        int bottom;
        int right;
        int left;

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            position = parent.getChildAdapterPosition(child);
            top = mBounds.top;
            bottom = mBounds.bottom;
            if (!hasBorder) {
                if (position + spanCount == itemCount) {
                    //最后一个item上面那个
                    bottom += mVerticalDivider.getIntrinsicHeight() / 2;
                } else if ((itemCount % spanCount != 0) && (itemCount % spanCount < position % spanCount) && (position > itemCount - spanCount)) {
                    bottom -= mVerticalDivider.getIntrinsicHeight() / 2;
                }
            }
            //行中位置
            int indexHorizontal = position % spanCount;
            if (hasBorder) {
                left = mBounds.left - (itemDividerWidth - dividerWidth) * indexHorizontal;
            } else {
                left = mBounds.left - (dividerWidth - indexHorizontal * (dividerWidth - itemDividerWidth));
            }
            right = left + dividerWidth;
            //画左边纵向分割线
            mVerticalDivider.setBounds(left, top, right, bottom);
            mVerticalDivider.draw(canvas);
            if (hasBorder && isLastColumn(position, spanCount, itemCount)) {
                //最后一列
                left = left + itemWidth - (itemDividerWidth - dividerWidth);
                right = left + dividerWidth;
                mVerticalDivider.setBounds(left, top, right, bottom);
                mVerticalDivider.draw(canvas);
            }
        }
        canvas.restore();
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
    private boolean isLastRow(int position, int itemCount, int spanCount) {
        //当前个数小于总个数
        //并且
        //item个数正好排成N行spanCount，且当前位置>=总item-每行个数
        //或
        //item个数多了一部分((item+1)%spanCount)，且当前位置在多出来部分
        return position < itemCount
                && ((itemCount % spanCount == 0 && position >= itemCount - spanCount)
                || (itemCount % spanCount >= itemCount - position));
    }

    /**
     * 第一列
     */
    private boolean isFirstColumn(int position, int spanCount) {
        return position % spanCount == 0;
    }

    /**
     * 最后一列
     */
    private boolean isLastColumn(int position, int spanCount, int itemCount) {
        return ((position + 1) % spanCount == 0) || (position + 1 == itemCount);
    }

    public void setVerticalDivider(Drawable verticalDivider) {
        this.mVerticalDivider = verticalDivider;
    }

    public void setHorizontalDivider(Drawable horizontalDivider) {
        this.mHorizontalDivider = horizontalDivider;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
    }
}
