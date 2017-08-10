package com.kaoyan.module.me;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaoyan.R;
import com.kaoyan.base.BaseFragment;
import com.kaoyan.event.LoginEvent;
import com.kaoyan.module.Test2Activity;
import com.kaoyan.utils.ImgManager;
import com.kaoyan.utils.LogUtil;
import com.kaoyan.widget.LoadingDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by tx on 2017/8/1.
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.iv_circle)
    CircleImageView imageView;
    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.iv)
    ImageView iv;
    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected void init() {
        ImgManager.loadImage(mActivity,"http://m.iisbn.com/images_side/1.jpg",imageView);
        EventBus.getDefault().register(this);
        LogUtil.i(" EventBus  "+EventBus.getDefault().toString());

//        RequestBody requestBody = RequestBody.create()
//        MultipartBody.Part body = MultipartBody.Part.createFormData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(" EventBus  "+EventBus.getDefault().toString());

        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.iv_circle)
    void startImg(){
        Intent intent = new Intent(mActivity, ImageGridActivity.class);
        startActivityForResult(intent, 1);

//        Intent intent = new Intent(mActivity, Test2Activity.class);
//        startActivity(intent);
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,1);
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
