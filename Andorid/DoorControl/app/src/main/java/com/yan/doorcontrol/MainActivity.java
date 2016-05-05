package com.yan.doorcontrol;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = LoginActivity.class.getSimpleName();
    protected int finishCount = 0;

    private TextView tvLoginName;
    private TextView tvRealName;
    private Button btnScan;

    private MyApplication MyApp;
    private SoundPool soundPool;
    private int audioErrorDevice, audioOpen, audioNoAccess, audioSuccess, audioNotValue;

    private String imei;
    private String code;
    private String rand;
    private int retCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        tvLoginName = (TextView) findViewById(R.id.tvLoginName);
        tvRealName = (TextView) findViewById(R.id.tvRealName);
        MyApp = (MyApplication) getApplication();
        tvLoginName.setText(MyApp.getLoginName());
        tvRealName.setText(MyApp.getRealName() + ",你好！");

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        audioErrorDevice = soundPool.load(this, R.raw.device, 1);
        audioOpen = soundPool.load(this, R.raw.open, 1);
        audioNoAccess = soundPool.load(this, R.raw.noaccess, 1);
        audioSuccess = soundPool.load(this, R.raw.success, 1);
        audioNotValue = soundPool.load(this, R.raw.notvalue, 1);

        btnScan = (Button) findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                imei = telephonyManager.getDeviceId();
                if (imei.equals(MyApp.getIMEI())) {
                    soundPool.play(audioOpen, 1, 1, 0, 0, 1);
                    Toast.makeText(MainActivity.this, "请扫描二维码开门！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, 0);
                } else {
                    soundPool.play(audioErrorDevice, 1, 1, 0, 0, 1);
                    Toast.makeText(MainActivity.this, "该设备不是您绑定的设备！", Toast.LENGTH_SHORT).show();
                }
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //取到扫描到的二维码的内容
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            String scanResultCode = scanResult;
            String scanResultRand = scanResult;
            //去掉随机数，取信息码
            if (scanResult.length() > 32) {
                code = scanResultCode.substring(0, scanResultCode.length() - 33);
                rand = scanResultRand.substring(scanResultRand.length() - 32,scanResultRand.length());
                new Thread() {
                    public void run() {
                        openDoor();
                    }
                }.start();
            } else {
                soundPool.play(audioNotValue, 1, 1, 0, 0, 1);
                Toast.makeText(MainActivity.this, "该二维码无效，请重新扫描!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void openDoor() {

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    retCode = response.getInt("open");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //如果返回值为1,则开门成功
                if (retCode == 1) {
                    soundPool.play(audioSuccess, 1, 1, 0, 0, 1);
                    Toast.makeText(MainActivity.this, "门已打开，请进!", Toast.LENGTH_SHORT).show();
                } else if (retCode == -1) {
                    soundPool.play(audioNotValue, 1, 1, 0, 0, 1);
                    Toast.makeText(MainActivity.this, "该二维码无效，请重新扫描!", Toast.LENGTH_LONG).show();
                } else if (retCode == 0) {
                    soundPool.play(audioNoAccess, 1, 1, 0, 0, 1);
                    Toast.makeText(MainActivity.this, "您没有打开此门的权限!", Toast.LENGTH_LONG).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "服务器连接失败，请重试或使用钥匙开门!", Toast.LENGTH_LONG).show();
                Log.e(TAG, volleyError.getMessage(), volleyError);
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String JSONDateUrl = "http://192.168.19.49/Door/opendoor.php?code=" + code + "&randNumber=" + rand + "&userId=" + MyApp.get_id();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, JSONDateUrl, null, listener, errorListener) {
        };
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            // 显示关于信息
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_loginout) {
            // 退出账号
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            super.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        finishCount = 0;
        return super.dispatchTouchEvent(ev);
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
