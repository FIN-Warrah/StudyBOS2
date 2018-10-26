package studybos.com.studybos2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class LiveActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Button callDrawerButton;
    private NavigationView navigationView;
    private Button helpButton;
    private Button friendsButton;

    private LiveCard[] liveCards={new LiveCard("高等数学详解","Warrah","高等数学",8000,R.drawable.blue,R.drawable.icon_image)};
    private List<LiveCard> liveCardList=new ArrayList<>();
    private LiveCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        Intent intent0=new Intent(this,NotificationService.class);
        startService(intent0);

        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //初始化控件
        mDrawerLayout=(DrawerLayout)findViewById(R.id.live_drawer_layout);
        callDrawerButton=(Button)findViewById(R.id.live_call_menu_button);
        navigationView=(NavigationView)findViewById(R.id.live_nav_view);
        helpButton=(Button)findViewById(R.id.live_help);
        friendsButton=(Button)findViewById(R.id.live_friends);

        //点击按钮呼出菜单栏
        callDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //初始化直播卡片
        for(int i=0;i<50;i++){
            liveCardList.add(liveCards[0]);
        }

        //加载recyclerview
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.live_recyclerview);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new LiveCardAdapter(liveCardList);
        recyclerView.setAdapter(adapter);


        //按钮点击事件
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LiveActivity.this,HelpActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LiveActivity.this,FriendsActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //菜单选项点击事件
        navigationView.setCheckedItem(R.id.home_passage);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_passage:
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.personal_center:
                        Intent intent=new Intent(LiveActivity.this,PersonalCenterActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_asks:
                        intent=new Intent(LiveActivity.this,MyAsksAvtivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_answers:
                        intent=new Intent(LiveActivity.this,MyAnswersActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_settings:
                        intent=new Intent(LiveActivity.this,SettingsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.search:
                        intent=new Intent(LiveActivity.this,SearchActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    //按back关闭菜单
    @Override
    public void onBackPressed() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.live_drawer_layout);
        if (mDrawerLayout.isDrawerOpen(findViewById(R.id.live_nav_view)))
            mDrawerLayout.closeDrawers();
        else
            super.onBackPressed();
    }

    @Override
    protected void onPause() {
        //设置返回无动画
        overridePendingTransition(0, 0);
        super.onPause();
    }
}
