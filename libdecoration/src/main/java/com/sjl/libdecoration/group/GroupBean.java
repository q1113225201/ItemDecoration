package com.sjl.libdecoration.group;

/**
 * GroupBean
 *
 * @author 林zero
 * @date 2018/11/27
 */
public class GroupBean {
    /**
     * 组标题
     */
    private String title;
    /**
     * 组id
     */
    private int groupId;
    /**
     * 组内位置
     */
    private int index;
    /**
     * 每个item上偏移
     */
    private int top;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public boolean isGroupHead() {
        return index == 0;
    }
}
