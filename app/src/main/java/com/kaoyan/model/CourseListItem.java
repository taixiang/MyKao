package com.kaoyan.model;

import com.kaoyan.module.courselist.CourseListFragment;

/**
 * Created by tx on 2017/8/3.
 */

public class CourseListItem {
    public CourseListFragment fragment;
    public int tag;
    public boolean isShow;

    public CourseListItem(CourseListFragment fragment, int tag,boolean isShow) {
        this.fragment = fragment;
        this.tag = tag;
        this.isShow = isShow;
    }
}
