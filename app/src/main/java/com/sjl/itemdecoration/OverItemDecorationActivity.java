package com.sjl.itemdecoration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sjl.itemdecoration.adapter.StringAdapter;
import com.sjl.itemdecoration.util.Util;

public class OverItemDecorationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_item_decoration);

        initView();
    }

    private void initView() {
        StringAdapter adapterW = new StringAdapter(R.layout.item_string, Util.buildList());
        RecyclerView recyclerViewGridBorderW = findViewById(R.id.recycler_view);
        recyclerViewGridBorderW.setAdapter(adapterW);
        recyclerViewGridBorderW.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewGridBorderW.addItemDecoration(new OverItemDecoration());
    }
}
