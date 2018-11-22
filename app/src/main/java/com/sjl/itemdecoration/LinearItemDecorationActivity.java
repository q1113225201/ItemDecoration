package com.sjl.itemdecoration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sjl.itemdecoration.adapter.StringAdapter;
import com.sjl.itemdecoration.util.Util;

public class LinearItemDecorationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_item_decoration);

        initView();
    }

    private void initView() {
        DividerItemDecoration dividerItemDecorationV = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecorationV.setDrawable(ContextCompat.getDrawable(this, R.drawable.bg_black));
        DividerItemDecoration dividerItemDecorationH = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        dividerItemDecorationH.setDrawable(ContextCompat.getDrawable(this, R.drawable.bg_black));
        //纵向
        StringAdapter adapterW = new StringAdapter(R.layout.item_string_w,Util.buildList());
        RecyclerView recyclerViewVerticalW = findViewById(R.id.recycler_view_vertical_w);
        recyclerViewVerticalW.setAdapter(adapterW);
        recyclerViewVerticalW.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewVerticalW.addItemDecoration(dividerItemDecorationV);
        //横向
        RecyclerView recyclerViewHorizontalW = findViewById(R.id.recycler_view_horizontal_w);
        recyclerViewHorizontalW.setAdapter(adapterW);
        recyclerViewHorizontalW.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHorizontalW.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        //纵向
        StringAdapter adapterM = new StringAdapter(R.layout.item_string_m,Util.buildList());
        RecyclerView recyclerViewVerticalM = findViewById(R.id.recycler_view_vertical_m);
        recyclerViewVerticalM.setAdapter(adapterM);
        recyclerViewVerticalM.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewVerticalM.addItemDecoration(dividerItemDecorationV);
        //横向
        RecyclerView recyclerViewHorizontalM = findViewById(R.id.recycler_view_horizontal_m);
        recyclerViewHorizontalM.setAdapter(adapterM);
        recyclerViewHorizontalM.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewHorizontalM.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    }
}
