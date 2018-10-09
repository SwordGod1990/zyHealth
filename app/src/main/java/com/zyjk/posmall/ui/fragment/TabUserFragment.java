package com.zyjk.posmall.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageFragment;
import com.zyjk.posmall.window.PhotoPopupWindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Sword God on 2018/8/28.
 */

public class TabUserFragment extends BasePageFragment implements PhotoPopupWindow.OnItemClickListener {

    private PhotoPopupWindow popupWindow;
    private Bitmap head;                              // 头像Bitmap
    private static String path = "/sdcard/myHead/";   // sd路径

    @BindView(R.id.fragment_user_headPhoto_riv)
    ImageView fragment_user_headPhoto_riv;
    @BindView(R.id.frg_user_xrv)
    XRecyclerView mXRecyclerView;

    @Override
    public int getLayoutID() {
        return R.layout.fragment_user;
    }

    @Override
    public void initViews() {
        TitleSet();
        popupWindow = new PhotoPopupWindow(getContext());
    }

    /**
     * 标题设置
     */
    private void TitleSet() {

    }

    @Override
    public void registerListener() {
        popupWindow.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.fragment_user_headPhoto_riv})
    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_user_headPhoto_riv:
                //头像弹窗
                popupWindow.showAtLocation(getActivity().findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    /**
     * 弹窗点击事件
     *
     * @param v
     */
    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.window_circlePhoto_beatPhoto:
//                ToastUtil.toast("拍照");
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                popupWindow.dismiss();
                break;
            case R.id.window_circlePhoto_selectPhoto:
//                ToastUtil.toast("从相册中选择");
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                popupWindow.dismiss();
                break;
            case R.id.window_circlePhoto_cancel:
                popupWindow.dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());// 裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);// 保存在SD卡中
                        fragment_user_headPhoto_riv.setImageBitmap(head);// 用ImageView显示出来
                    }
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            Log.i("TAG", "头像路径：" + fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
