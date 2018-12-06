package com.sjl.itemdecoration.decoration;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sjl.itemdecoration.R;

/**
 * 覆盖分割
 *
 * @author 沈建林
 * @date 2018/12/6
 */
public class OverItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = 1;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(childView);
            c.save();
            if (position < 3) {
                c.drawBitmap(BitmapFactory.decodeResource(parent.getResources(), R.mipmap.recommend), 0, childView.getTop(), null);
            }
            c.restore();
        }
    }
}
