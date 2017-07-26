package com.kaoyan.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.kaoyan.R;
import com.kaoyan.base.BaseFragment;




import com.kaoyan.database.User;
import com.kaoyan.database.UserDao;
import com.kaoyan.utils.CommonUtil;
import com.kaoyan.utils.GreenDaoManager;
import com.kaoyan.utils.ImgManager;
import com.kaoyan.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by tx on 2017/7/21.
 */

public class BannerItemFragment extends BaseFragment {

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_banner,null);
//    }

    private String imgUrl;
    private UserDao userDao;

    public static BannerItemFragment newInstance(String url) {
        BannerItemFragment fragment = new BannerItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("banner_url",url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.ivBanner)
    ImageView ivBanner;
    @BindView(R.id.container)
    LinearLayout container;
    private int i;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_banner;
    }

    @OnClick(R.id.insert)
    void inser(){
        i++;
        User user = new User(null,i+""," "+i);
        userDao.insert(user);
        LogUtil.i("userList ===  insert ");
    }

    @OnClick(R.id.delete)
    void delete(){
        List<User> user = userDao.queryBuilder().where(UserDao.Properties.Id.eq("1")).build().list();
        if(user != null){
            userDao.delete(user.get(0));
            LogUtil.i("userList ===  delete ");
        }
    }

    @OnClick(R.id.update)
    void update(){
        List<User> user = userDao.queryBuilder().where(UserDao.Properties.Name.eq("2")).build().list();
        if(user != null){
            user.get(0).setName("33333");
            userDao.update(user.get(0));
            LogUtil.i("userList ===  update ");
        }

    }

    @OnClick(R.id.find)
    void find(){
        List<User> list = userDao.queryBuilder().orderDesc(UserDao.Properties.Id).list();
        LogUtil.i("userList === "+list.toString());

        list.get(0).setName("4444444");
        userDao.update(list.get(0));
//        GreenDaoManager.getInstance(mActivity).getDaosession().clear();
        List<User> list2 = userDao.queryBuilder().orderDesc(UserDao.Properties.Id).list();
        LogUtil.i("userList2 === "+list.toString());

    }

    @Override
    protected void init() {
//        String url = getArguments().getString("banner_url");
//        ImgManager.loadImage(mActivity,url,ivBanner);
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mIsMulti = false;
        LogUtil.i(" bannerItem  init ");
        userDao = GreenDaoManager.getInstance(mActivity).getDaosession().getUserDao();

//        Rect outRect = new Rect();
//        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) container.getLayoutParams();
//        params.height = outRect.bottom - outRect.top;

//        LogUtil.i("  params  height=  "+params.height+"  window height"+CommonUtil.getWidthAndHeight(mActivity)[1]);

    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
}
