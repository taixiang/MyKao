package com.kaoyan.module.me;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.api.RetrofitService;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.event.LoginEvent;
import com.kaoyan.model.BaseItem;
import com.kaoyan.model.ReleaseItem;
import com.kaoyan.module.StickyActivity;
import com.kaoyan.module.StickyActivity2;
import com.kaoyan.module.Test2Activity;
import com.kaoyan.utils.ImgManager;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.widget.CardPagerAdapter;
import com.kaoyan.widget.LoadingDialog;
import com.kaoyan.widget.ShadowTransformer;
import com.kaoyan.widget.SlideFromBottomPopup;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tx on 2017/8/1.
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.iv_circle)
    CircleImageView imageView;
    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.pager)
    ViewPager pager;

    @BindView(R.id.iv)
    ImageView iv;
    private List<String> list = new ArrayList<>();
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected void init() {
        ImgManager.loadImage(mActivity,"http://m.iisbn.com/images_side/1.jpg",imageView);
        EventBus.getDefault().register(this);
        LogUtil.i(" EventBus  "+EventBus.getDefault().toString());
        list.add("http://m.iisbn.com/images_side/11_11.jpg");
        list.add("http://m.iisbn.com/images_side/1.jpg");
//        list.add("http://m.iisbn.com/images_side/5.jpg");
//        list.add("http://m.iisbn.com/images_side/6.jpg");
//        list.add("http://m.iisbn.com/images_side/2.jpg");
        pager.setAdapter(new UlViewPagerAdapter());
        pager.setPageMargin(50);
        pager.setOffscreenPageLimit(3);
//        pager.setPageMargin(-30);
//        RequestBody requestBody = RequestBody.create()
//        MultipartBody.Part body = MultipartBody.Part.createFormData();
    }

    private class UlViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.adapter_ulviewpager,null);
            ImageView imageView = view.findViewById(R.id.iv);
            ImgManager.loadImage(mActivity,list.get(position),imageView);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(" EventBus  "+EventBus.getDefault().toString());

        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.iv_circle)
    void startImg(){
//        Intent intent = new Intent(mActivity, ImageGridActivity.class);
//        startActivityForResult(intent, 1);

//        Intent intent = new Intent(mActivity, Test2Activity.class);
//        startActivity(intent);

//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,1);

        SlideFromBottomPopup popup = new SlideFromBottomPopup(mActivity);
        popup.showPopupWindow();

//        Intent intent = new Intent(mActivity, StickyActivity2.class);
//        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null){
            LogUtil.i(" imageUrl ==  onActivityResult");

            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);

            LogUtil.i(" imageitem === " + images.toString());
            LogUtil.i(" imageitem === size " + images.size());
            LogUtil.i(" imageitem === path "  + images.get(0).path);

            ImgManager.loadImage(mActivity,new File(images.get(0).path),iv);
            File file = new File(images.get(0).path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"),file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("picture",file.getName(),requestBody);
//            RetrofitService.commonApi.upload(body).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .compose(this.<BaseItem<ReleaseItem>>bindToLife())
//                    .subscribe(new Subscriber<BaseItem<ReleaseItem>>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable  e) {
//                            LogUtil.i(" e=== "+e.toString());
//                        }
//
//                        @Override
//                        public void onNext(BaseItem<ReleaseItem> releaseItemBaseItem) {
//                            LogUtil.i(" releaseItemBaseItem "+releaseItemBaseItem.toString());
//                        }
//                    });

//            String path =  getAbsolutePath(mActivity,data.getData());
//            File file = new File(path);
//            LogUtil.i(" file path == "+path);
//            ImgManager.loadImage(mActivity,file,iv);
//            Intent intent = new Intent("com.android.camera.action.CROP");
//            intent.setDataAndType(data.getData(), "image/*");
//            intent.putExtra("crop", "true");
//            intent.putExtra("aspectX", 1);
//            intent.putExtra("aspectY", 0.7);
//            intent.putExtra("outputX", 1080);
//            intent.putExtra("outputY", 756);
//            intent.putExtra("return-data", true);
//            startActivityForResult(intent, 3);


        }else if(requestCode == 3 && data != null){
            String path =  getAbsolutePath(mActivity,data.getData());
            File file = new File(path);
            LogUtil.i(" file path == "+path);
            ImgManager.loadImage(mActivity,file,iv);
        }
    }

    @Subscribe
    public void LoginEvent(LoginEvent loginEvent){
        LogUtil.i(" loginSuccess ");
    }

    public String getAbsolutePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}