package studybos.com.studybos2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.List;

import studybos.com.studybos2.util.InitUtil;

public class HelpContent extends AppCompatActivity {

    private List<Help> helpContentList=new ArrayList<>();
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_content);
        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //初始化控件
        backButton=(Button)findViewById(R.id.help_content_back_button);
        TextView config_hidden = (TextView) this.findViewById(R.id.config_hidden);

        //禁止软键盘自动弹出
        config_hidden.requestFocus();

        //设置按钮点击事件
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HelpContent.this,HelpActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        //设置recyclerview
        helpContentList= InitUtil.initHelp(new Help[] {new Help(0)});
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.help_content_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HelpAdapter adapter=new HelpAdapter(helpContentList);
        recyclerView.setAdapter(adapter);
    }
}
