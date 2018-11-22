package com.sjl.itemdecoration;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sjl.itemdecoration.adapter.StringAdapter;
import com.sjl.itemdecoration.decoration.GridDividerItemDecoration;
import com.sjl.itemdecoration.decoration.GridItemDecoration;
import com.sjl.itemdecoration.util.Util;

public class GridItemDecorationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item_decoration);

        initView();
    }

    private void initView() {
        int spanCount = 4;
        StringAdapter adapterW = new StringAdapter(R.layout.item_string_w,Util.buildList());
        RecyclerView recyclerViewGridBorderW = findViewById(R.id.recycler_view_grid_border_w);
        recyclerViewGridBorderW.setAdapter(adapterW);
        recyclerViewGridBorderW.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridBorderW.addItemDecoration(getItemDecoration(true));

        RecyclerView recyclerViewGridNoBorderW = findViewById(R.id.recycler_view_grid_no_border_w);
        recyclerViewGridNoBorderW.setAdapter(adapterW);
        recyclerViewGridNoBorderW.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridNoBorderW.addItemDecoration(getItemDecoration(false));

        StringAdapter adapterM = new StringAdapter(R.layout.item_string_m,Util.buildList());
        RecyclerView recyclerViewGridBorderM = findViewById(R.id.recycler_view_grid_border_m);
        recyclerViewGridBorderM.setAdapter(adapterM);
        recyclerViewGridBorderM.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridBorderM.addItemDecoration(getItemDecoration(true));

        RecyclerView recyclerViewGridNoBorderM = findViewById(R.id.recycler_view_grid_no_border_m);
        recyclerViewGridNoBorderM.setAdapter(adapterM);
        recyclerViewGridNoBorderM.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridNoBorderM.addItemDecoration(getItemDecoration(false));
    }

    public GridItemDecoration getItemDecoration(boolean hasBorder){
        GridItemDecoration gridItemDecorationNoBorder = new GridItemDecoration(this);
        gridItemDecorationNoBorder.setHasBorder(hasBorder);
        gridItemDecorationNoBorder.setVerticalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        gridItemDecorationNoBorder.setHorizontalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        return gridItemDecorationNoBorder;
    }
}
