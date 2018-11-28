package com.sjl.itemdecoration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sjl.itemdecoration.adapter.StringAdapter;
import com.sjl.itemdecoration.decoration.group.GroupBean;
import com.sjl.itemdecoration.decoration.group.GroupCallback;
import com.sjl.itemdecoration.decoration.group.GroupItemDecoration;
import com.sjl.itemdecoration.util.Util;

import java.util.ArrayList;
import java.util.List;

public class GroupItemDecorationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_item_decoration);

        initAdapter();
    }

    private void initAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        List<String> list = Util.buildList();
        StringAdapter adapter = new StringAdapter(R.layout.item_string_m, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final List<GroupBean> groupBeanList = buildGroupList(list);

        GroupItemDecoration groupItemDecoration = new GroupItemDecoration(new GroupCallback() {
            @Override
            public GroupBean getGroupBean(int position) {
                //列表范围内返回对象
                if (position < groupBeanList.size()) {
                    return groupBeanList.get(position);
                }
                return null;
            }

            @Override
            public View getDividerView(int position) {
                GroupBean groupBean = getGroupBean(position);
                if (groupBean == null) {
                    return null;
                }
                View view;
                //根据分组状态返回不同分割线
                if (groupBean.isGroupHead()) {
                    view = View.inflate(GroupItemDecorationActivity.this, R.layout.layout_divider_head, null);
                    ((TextView) view.findViewById(R.id.tv_name)).setText(groupBean.getTitle());
                } else {
                    view = View.inflate(GroupItemDecorationActivity.this, R.layout.layout_divider, null);
                }
                return view;
            }
        });

        recyclerView.addItemDecoration(groupItemDecoration);
    }

    private List<GroupBean> buildGroupList(List<String> list) {
        int groupId = 0;
        int index = 0;
        View view = View.inflate(this, R.layout.layout_divider_head, null);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int headHeight = view.getMeasuredHeight();
        int top = 0;
        List<GroupBean> groupBeanList = new ArrayList<>();
        GroupBean groupBean;
        for (int i = 0; i < list.size(); i++) {
            if (i % 3 == 0) {
                groupId = i == 0 ? 0 : (groupId + 1);
                index = 0;
                top = headHeight;
            } else {
                index++;
                top = 1;
            }
            groupBean = new GroupBean();
            groupBean.setGroupId(groupId);
            groupBean.setIndex(index);
            groupBean.setTop(top);
            groupBean.setTitle("G" + groupId);
            groupBeanList.add(groupBean);
        }
        return groupBeanList;
    }
}
