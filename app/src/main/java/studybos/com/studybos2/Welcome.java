package studybos.com.studybos2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.litepal.LitePal;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (Build.VERSION.SDK_INT>=21){
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        LitePal.getDatabase();

        SharedPreferences pref=getSharedPreferences("loginMessage",MODE_PRIVATE);
        String account=pref.getString("account",null);
        String password=pref.getString("password",null);

        if(account==null||password==null){
            Intent intent=new Intent(Welcome.this,Login.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }else{
            Intent intent=new Intent(Welcome.this,LiveActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
        }
    }
}
