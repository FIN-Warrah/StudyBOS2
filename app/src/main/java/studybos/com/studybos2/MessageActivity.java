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

import java.util.ArrayList;
import java.util.List;

import studybos.com.studybos2.util.InitUtil;

public class MessageActivity extends AppCompatActivity {

    private Button backButton;
    private List<Friend> friendList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //初始化控件
        backButton=(Button)findViewById(R.id.message_back_button);

        //设置按钮点击事件
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MessageActivity.this,LiveActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        //设置recyclerview
        friendList= InitUtil.initFriends(new Friend[] {new Friend(0)});
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.message_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FriendAdapter adapter=new FriendAdapter(friendList);
        recyclerView.setAdapter(adapter);
    }
}
