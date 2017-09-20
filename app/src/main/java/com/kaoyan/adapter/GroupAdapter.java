package com.kaoyan.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.model.Group;
import com.kaoyan.model.User;
import com.kaoyan.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tx on 2017/9/14.
 */

public class GroupAdapter extends BaseExpandableListAdapter {

    private List<SparseBooleanArray> booleanArrays = new ArrayList<>();
    SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();

    SparseArray<SparseBooleanArray> sa = new SparseArray<>();

    Context context;
    List<Group> list;

    public GroupAdapter(Context context, List<Group> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.expand_view, null);
            holder = new GroupHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (GroupHolder) convertView.getTag();
        }
        //设置数据
        Group group = getGroup(groupPosition);
        holder.groupName.setText(group.groupName);
        holder.groupOnline.setText(group.getOnlineCount()+"/"+getChildrenCount(groupPosition));
        holder.groupName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray groupArray = sa.get(groupPosition);
                if(groupArray != null){
                    if(groupArray.size() == list.get(groupPosition).list.size()){
                        //子项全未选
                        sa.delete(groupPosition);
                    }else {
                        //子项全选
                        for(int i=0;i<list.get(groupPosition).list.size();i++){
                            groupArray.put(i,true);
                        }
                    }
                }else {
                    //子项全选
                    groupArray = new SparseBooleanArray();
                    for(int i=0;i<list.get(groupPosition).list.size();i++){
                        groupArray.put(i,true);
                    }
                    sa.put(groupPosition,groupArray);
                }
                LogUtil.i("sa ========"+sa.toString());
                notifyDataSetChanged();
            }
        });
        SparseBooleanArray groupArray = sa.get(groupPosition);
        if(groupArray != null && groupArray.size() == list.get(groupPosition).list.size()){
            //父项选中
        }else {
            //父项未选中
        }


        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if(convertView == null){
            convertView = View.inflate(context, R.layout.expand_item_view, null);
            holder = new ChildHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ChildHolder) convertView.getTag();
        }
        //设置数据
        User user = getGroup(groupPosition).getChild(childPosition);
//        holder.img.setImageResource(user.getImgId());
        holder.nickName.setText(user.getNickName());
        holder.online.setText(user.isOnline()?"[在线]":"[离线]");
        holder.sign.setText(user.getSign());
        holder.nickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.i("groupPosition==="+groupPosition+"  childPosition===="+childPosition);
                SparseBooleanArray boolItem = sa.get(groupPosition);
                if(boolItem != null ){
                    if(boolItem.get(childPosition)){
                        boolItem.delete(childPosition);
                    }else {
                        boolItem.put(childPosition,true);
                    }
                }else {
                    boolItem = new SparseBooleanArray();
                    boolItem.put(childPosition,true);
                    sa.put(groupPosition,boolItem);
                    LogUtil.i("boolItem3333 ========"+boolItem.toString());
                }
                //子项全选上
                if(boolItem.size() == list.get(groupPosition).list.size()){

                    notifyDataSetChanged();
                }
                LogUtil.i("sa ========"+sa.toString());
            }
        });

        SparseBooleanArray groupArray = sa.get(groupPosition);
        if(groupArray != null && groupArray.size() == list.get(groupPosition).list.size()){
            //父项选中

        }else {
            //父项未选中

        }

        return convertView;

    }

    class GroupHolder{
        TextView groupName;
        TextView groupOnline;

        public GroupHolder(View convertView){
            groupName = (TextView) convertView.findViewById(R.id.groupName);
            groupOnline = (TextView) convertView.findViewById(R.id.groupOnline);

        }
    }
    class ChildHolder{
        ImageView img;
        TextView nickName;
        TextView online;
        TextView sign;

        public ChildHolder(View convertView){
            img = (ImageView) convertView.findViewById(R.id.img);
            nickName = (TextView) convertView.findViewById(R.id.nickName);
            online = (TextView) convertView.findViewById(R.id.online);
            sign = (TextView) convertView.findViewById(R.id.sign);

        }

    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).getChildCount();
    }

    @Override
    public Group getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public User getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChild(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
