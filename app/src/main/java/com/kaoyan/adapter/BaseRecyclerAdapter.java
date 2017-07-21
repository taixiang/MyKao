package com.kaoyan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by tx on 2017/7/17.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder>{
    private static final String TAG = "BaseRecyclerAdapter--->";
    private static final int TYPE_HEAD = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOT = 2;
//    private boolean isScrolling = true;  //false表示滑动，true表示不滑动
    private View headerView,footerView;  //头和尾的view
    private List<T> lists;   //传入的集合。
    private int totalList;
    private int itemLayoutId; //设置Layout的id。
    private Context cxt;
    private OnItemViewClickListener onItemViewClickListener;
    public interface OnItemViewClickListener{
        //设置item中某个view的点击事件
        void onItemViewClick(RecyclerViewHolder view, int position);
    }
    public void setonItemViewClickListener(OnItemViewClickListener listener){
        this.onItemViewClickListener = listener;
    }
    public BaseRecyclerAdapter(Context context,List<T> list,int itemLayoutId){
        this.lists = list;
        this.itemLayoutId = itemLayoutId;
        this.cxt = context;

    }
    public void setFooterView(View view){
        this.footerView = view;
    }
    public void setHeaderView(View view){
        this.headerView = view;
    }

    /**
     * 创建RecyclerView里面的itemView。
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEAD && headerView !=null){
            return new RecyclerViewHolder(headerView);
        }
        if(viewType == TYPE_FOOT && footerView!=null){
            return new RecyclerViewHolder(footerView);
        }
        View view = LayoutInflater.from(cxt).inflate(itemLayoutId,parent,false);
        return new RecyclerViewHolder(view);
    }

    /**
     *  绑定每一个itemView的具体数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        if(getItemViewType(position) != TYPE_ITEM){
            //如果不是正常的Item，就不去绑定数据
            return;
        }
        Log.i(TAG,"bindView---"+position);
        //抽象方法，让子类去具体的实现
        int p = getRealPosition(position);
        bindConvert(holder,p,lists.get(p));
        if(onItemViewClickListener!=null){
            onItemViewClickListener.onItemViewClick(holder,p);
        }
    }
    private int getRealPosition(int position){
        return headerView ==null ? position : position-1;
    }
    public abstract void bindConvert(RecyclerViewHolder holder, int position, T item);

    /**
     * 判断有多少个list.size()
     * @return
     */
    @Override
    public int getItemCount() {
        if(lists == null){
            return 0;
        }
        if(headerView!=null && footerView!=null){
            //头尾都不为空
            totalList =  lists.size()+2;
        }else if(headerView ==null && footerView ==null){
            //头尾都为空
            totalList = lists.size();
        }else{
            //头尾有一个不为空
            totalList = lists.size()+1;
        }
        return totalList;
    }

    @Override
    public int getItemViewType(int position) {

        if(position == 0 && headerView !=null){
            //表示第一个item，并且headerView不为空
            return TYPE_HEAD;
        }
        if(position+1 == getItemCount() && footerView !=null){
            //表示最好一个item，并且footerView不为空
            return TYPE_FOOT;
        }
        return TYPE_ITEM;
    }
}