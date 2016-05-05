package com.yan.doorcontrol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private String isChecked = "";
    private static final String YES = "yes";
    private static final String NO = "no";
    protected int finishCount = 0;

    private EditText etLoginNamne;
    private EditText etpassword;
    private Button btnLogin;
    private CheckBox remember_me;
    private String loginNameValue, passwordValue;
    private String loginName;
    private String password;
    private SharedPreferences sp;

    private int retCode;

    private MyApplication myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_login);

        etLoginNamne = (EditText) findViewById(R.id.etloginName);
        etpassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        remember_me = (CheckBox) findViewById(R.id.remember_me);
        sp = this.getSharedPreferences("loginFrom", Context.MODE_PRIVATE);

        isChecked = sp.getString("isChecked", NO);
        if (isChecked.equals(YES)) {
            loginNameValue = sp.getString("loginName", "");
            passwordValue = sp.getString("password", "");
            etLoginNamne.setText(loginNameValue);
            etpassword.setText(passwordValue);
        }
        Editor editor = sp.edit();
        editor.putString(loginNameValue, etLoginNamne.toString());
        editor.putString(passwordValue, etpassword.toString());
        editor.commit();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginName = etLoginNamne.getText().toString();
                password = etpassword.getText().toString();

                if (loginName.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
               new Thread() {
                    public void run() {
                        login();
                        getInfo();
                    }
                }.start();
            }
        });
    }

    //登录
    public void login() {

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    retCode = response.getInt("login");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //如果返回值为1
                if (retCode == 1) {
                    remenber();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finishActivity();
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(LoginActivity.this, "服务器连接失败，请重新尝试!", Toast.LENGTH_SHORT).show();
                Log.e(TAG, volleyError.getMessage(), volleyError);
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        String JSONDateUrl = "http://192.168.19.49/Door/login.php?loginName=" + loginName + "&password=" + password;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, JSONDateUrl, null, listener, errorListener) {
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(1000,1,1.0f));
        requestQueue.add(jsonObjectRequest);
    }

    //获取登陆用户的信息
    public void getInfo() {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG, volleyError.getMessage(), volleyError);
            }
        };
        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                myApp = (MyApplication) getApplication();
                // 将每一个数组再转换成Json对象
                String mId = null,mLoginName = null, mRealName = null, mMobilephone = null, mIMEI = null;
                try {
                    JSONObject jsonObject = response.getJSONObject(0);
                    mId = jsonObject.getString("_id");
                    mLoginName = jsonObject.getString("loginName");
                    mRealName = jsonObject.getString("realName");
                    mMobilephone = jsonObject.getString("mobilephone");
                    mIMEI = jsonObject.getString("IMEI");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myApp.set_id(mId);
                myApp.setLoginName(mLoginName);
                myApp.setRealName(mRealName);
                myApp.setMobilephone(mMobilephone);
                myApp.setIMEI(mIMEI);

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        String JSONDateUrl = "http://192.168.19.49/Door/user.php?loginName=" + loginName + "&password=" + password;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(JSONDateUrl, listener, errorListener) {
        };
        requestQueue.add(jsonArrayRequest);
    }

    //监听记住用户名密码多选框按钮事件
    public void remenber() {
        if (remember_me.isChecked()) {
            if (sp == null) {
                sp = getSharedPreferences("loginForm", MODE_PRIVATE);
            }
            Editor edit = sp.edit();
            edit.putString("loginName", etLoginNamne.getText().toString());
            edit.putString("password", etpassword.getText().toString());
            edit.putString("isChecked", YES);
            edit.commit();
        } else if (!remember_me.isChecked()) {
            if (sp == null) {
                sp = getSharedPreferences("loginForm", MODE_PRIVATE);
            }
            Editor edit = sp.edit();
            edit.putString("isChecked", NO);
            edit.commit();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        finishCount = 0;
        return super.dispatchTouchEvent(ev);
    }

    public void finishActivity() {
        super.finish();
    }

    @Override
    public void finish() {
        finishCount++;
        if (finishCount == 1) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        } else if (finishCount == 2) {
            super.finish();
        }
    }

}

