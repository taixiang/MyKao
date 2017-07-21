package com.kaoyan.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kaoyan.R;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.utils.ImgManager;

import butterknife.BindView;

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


    public static BannerItemFragment newInstance(String url) {
        BannerItemFragment fragment = new BannerItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("banner_url",url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @BindView(R.id.ivBanner)
    ImageView ivBanner;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_banner;
    }

    @Override
    protected void init() {
        String url = getArguments().getString("banner_url");
        ImgManager.loadImage(mActivity,url,ivBanner);
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
}
