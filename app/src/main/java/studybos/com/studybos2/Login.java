package studybos.com.studybos2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

import studybos.com.studybos2.data.LoginId;

public class Login extends AppCompatActivity {

    private EditText loginAccountEdit;
    private EditText loginPasswordEdit;
    private Button passwordStatusButton;
    private Button loginButton;
    private Button forgetPasswordButton;
    private Button registerButton;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //将状态栏与背景融合
        if (Build.VERSION.SDK_INT>=21){
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }




        //初始化控件
        loginAccountEdit=(EditText)findViewById(R.id.login_account_edit);
        loginPasswordEdit=(EditText)findViewById(R.id.login_password_edit);
        passwordStatusButton=(Button)findViewById(R.id.password_status_button);
        loginButton=(Button)findViewById(R.id.login_button);
        forgetPasswordButton=(Button)findViewById(R.id.forget_password_button);
        registerButton=(Button)findViewById(R.id.login_to_register);

        //设置密码隐藏与明文
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        editor=pref.edit();
        editor.putInt("passwordtype",1);
        loginPasswordEdit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        passwordStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pref.getInt("passwordtype",1)==1){
                    editor.putInt("passwordtype",0);
                    editor.apply();
                    loginPasswordEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }else if (pref.getInt("passwordtype",1)==0){
                    editor.putInt("passwordtype",1);
                    editor.apply();
                    loginPasswordEdit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        //设置按钮点击事件
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //登录进入功能界面
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account=loginAccountEdit.getText().toString();
                String password=loginPasswordEdit.getText().toString();

                SharedPreferences.Editor editor=getSharedPreferences("loginMessage",MODE_PRIVATE).edit();
                editor.putString("account","");
                editor.putString("password","");
                editor.apply();
                LoginId.setLoginId(account);

                SharedPreferences pref=getSharedPreferences("loginMessage",MODE_PRIVATE);
                String theAccount=pref.getString("account","");
                String thePassword=pref.getString("password","");

                if (account!=null&&password!=null){
                    Intent intent=new Intent(Login.this,LiveActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
