package com.sjl.itemdecoration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.sjl.itemdecoration.adapter.StringAdapter;
import com.sjl.itemdecoration.util.Util;
import com.sjl.libdecoration.GridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class GridItemDecorationActivity extends Activity {
    private EditText etSize;
    private EditText etSpanCount;
    private RecyclerView recyclerViewGridBorderW;
    private RecyclerView recyclerViewGridNoBorderW;
    private RecyclerView recyclerViewGridBorderM;
    private RecyclerView recyclerViewGridNoBorderM;

    private List<String> list = new ArrayList<>();
    private int spanCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item_decoration);

        initView();

    }

    StringAdapter adapterW;
    StringAdapter adapterM;

    private void initView() {
        etSize = findViewById(R.id.et_size);
        etSpanCount = findViewById(R.id.et_span_count);
        initAdapter();
        findViewById(R.id.btn_build).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build();
            }
        });
        findViewById(R.id.btn_build).performClick();
    }

    private void build() {
        spanCount = Integer.parseInt(etSpanCount.getText().toString());
        list.clear();
        list.addAll(Util.buildList(Integer.parseInt(etSize.getText().toString())));
        recyclerViewGridBorderW.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridNoBorderW.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridBorderM.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridNoBorderM.setLayoutManager(new GridLayoutManager(this, spanCount));
        adapterW.notifyDataSetChanged();
        adapterM.notifyDataSetChanged();
    }

    private void initAdapter() {

        adapterW = new StringAdapter(R.layout.item_string_w, list);
        recyclerViewGridBorderW = findViewById(R.id.recycler_view_grid_border_w);
        recyclerViewGridBorderW.setAdapter(adapterW);
        recyclerViewGridBorderW.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridBorderW.addItemDecoration(getItemDecoration(true));

        recyclerViewGridNoBorderW = findViewById(R.id.recycler_view_grid_no_border_w);
        recyclerViewGridNoBorderW.setAdapter(adapterW);
        recyclerViewGridNoBorderW.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridNoBorderW.addItemDecoration(getItemDecoration(false));

        adapterM = new StringAdapter(R.layout.item_string_m, list);
        recyclerViewGridBorderM = findViewById(R.id.recycler_view_grid_border_m);
        recyclerViewGridBorderM.setAdapter(adapterM);
        recyclerViewGridBorderM.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridBorderM.addItemDecoration(getItemDecoration(true));

        recyclerViewGridNoBorderM = findViewById(R.id.recycler_view_grid_no_border_m);
        recyclerViewGridNoBorderM.setAdapter(adapterM);
        recyclerViewGridNoBorderM.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerViewGridNoBorderM.addItemDecoration(getItemDecoration(false));
    }


    public GridItemDecoration getItemDecoration(boolean hasBorder) {
        GridItemDecoration gridItemDecorationNoBorder = new GridItemDecoration(this);
        gridItemDecorationNoBorder.setHasBorder(hasBorder);
        gridItemDecorationNoBorder.setVerticalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        gridItemDecorationNoBorder.setHorizontalDivider(ContextCompat.getDrawable(this, R.drawable.bg_black));
        return gridItemDecorationNoBorder;
    }
}
