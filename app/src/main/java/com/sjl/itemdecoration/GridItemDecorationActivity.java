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
        StringAdapter adapter = new StringAdapter(Util.buildList());
        RecyclerView recyclerViewGridBorder = findViewById(R.id.recycler_view_grid_border);
        recyclerViewGridBorder.setAdapter(adapter);
        recyclerViewGridBorder.setLayoutManager(new GridLayoutManager(this, 3));
        GridItemDecoration gridItemDecorationBorder = new GridItemDecoration(this);
        gridItemDecorationBorder.setHasBorder(true);
        gridItemDecorationBorder.setVerticalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        gridItemDecorationBorder.setHorizontalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        recyclerViewGridBorder.addItemDecoration(gridItemDecorationBorder);

        RecyclerView recyclerViewGridNoBorder = findViewById(R.id.recycler_view_grid_no_border);
        recyclerViewGridNoBorder.setAdapter(adapter);
        recyclerViewGridNoBorder.setLayoutManager(new GridLayoutManager(this, 3));
        GridItemDecoration gridItemDecorationNoBorder = new GridItemDecoration(this);
        gridItemDecorationNoBorder.setHasBorder(false);
        gridItemDecorationNoBorder.setVerticalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        gridItemDecorationNoBorder.setHorizontalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        recyclerViewGridNoBorder.addItemDecoration(gridItemDecorationNoBorder);
    }
}
