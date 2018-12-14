package com.sjl.libdecoration.group;

import android.view.View;

/**
 * GroupCallback
 *
 * @author 林zero
 * @date 2018/11/27
 */
public interface GroupCallback {
    /**
     * 获取每个item对应的组信息
     */
    GroupBean getGroupBean(int position);

    /***
     * 获取每个组员需要的分割View
     */
    View getDividerView(int position);
}
