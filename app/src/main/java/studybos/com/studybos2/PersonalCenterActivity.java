package studybos.com.studybos2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalCenterActivity extends AppCompatActivity {

    private Button backButton;
    private Button changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);

        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //初始化控件
        backButton=(Button)findViewById(R.id.personal_center_back);
        changeButton=(Button)findViewById(R.id.personal_center_change_message);

        //设置按钮点击事件
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PersonalCenterActivity.this,LiveActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PersonalCenterActivity.this,ChangeMessageActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PersonalCenterActivity.this,LiveActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }
}
