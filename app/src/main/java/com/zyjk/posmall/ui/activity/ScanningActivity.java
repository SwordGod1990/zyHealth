package com.zyjk.posmall.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.qrConstant;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Sword God on 2018/8/28.
 * 扫描
 */

public class ScanningActivity extends BaseActivity {


    @BindView(R.id.activity_scanning_result_tv)
    TextView activity_scanning_result_tv;
    @BindView(R.id.titleBar_center_tv)
    TextView titleBar_center_tv;

    @Override
    protected int getContentView() {
        return R.layout.activity_scanning;
    }

    @Override
    public void initViews() {
        titleBar_center_tv.setText("扫描");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.activity_scanning_btn, R.id.titleBar_left_iv})
    @Override
    public void processClick(View view) {
        switch (view.getId()) {
            case R.id.activity_scanning_btn:
                startQrCode();
                break;
            case R.id.titleBar_left_iv:
                finish();
                break;
        }
    }

    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(ScanningActivity.this, new String[]{Manifest.permission.CAMERA}, qrConstant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(ScanningActivity.this, CaptureActivity.class);
        startActivityForResult(intent, qrConstant.REQ_QR_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == qrConstant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(qrConstant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            activity_scanning_result_tv.setText(scanResult);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case qrConstant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(ScanningActivity.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
