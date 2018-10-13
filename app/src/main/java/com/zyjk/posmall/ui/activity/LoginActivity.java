package com.zyjk.posmall.ui.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.zyjk.posmall.R;
import com.zyjk.posmall.base.BasePageActivity;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * author : Sword God
 * e-mail : 156690858@qq.com
 * date   : 2018/9/2514:59
 * desc   : 登陆
 * version: 1.0
 */


public class LoginActivity extends BasePageActivity implements TextWatcher {

    @BindView(R.id.userName_et)
    EditText userNameEt;
    @BindView(R.id.clear_btn)
    Button clearBtn;

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void registerListener() {
        userNameEt.addTextChangedListener(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.clear_btn, R.id.window_btn})
    @Override
    public void viewsClick(View view) {
        switch (view.getId()) {
            case R.id.clear_btn:
                userNameEt.setText("");
                break;
            case R.id.window_btn:
                //弹窗

                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.i("TAG", "afterTextChanged: " + s);
        if (null != userNameEt.getText().toString() && !userNameEt.getText().toString().equals("")) {
            clearBtn.setVisibility(View.VISIBLE);
        } else {
            clearBtn.setVisibility(View.GONE);
        }
    }
}
