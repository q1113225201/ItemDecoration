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
        RecyclerView recyclerViewGrid = findViewById(R.id.recycler_view_grid);
        recyclerViewGrid.setAdapter(adapter);
        recyclerViewGrid.setLayoutManager(new GridLayoutManager(this, 3));
        GridItemDecoration gridItemDecoration = new GridItemDecoration(this);
        gridItemDecoration.setVerticalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        gridItemDecoration.setHorizontalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));

        GridDividerItemDecoration gridDividerItemDecoration = new GridDividerItemDecoration(200, Color.BLACK);
        recyclerViewGrid.addItemDecoration(gridItemDecoration);
    }
}
