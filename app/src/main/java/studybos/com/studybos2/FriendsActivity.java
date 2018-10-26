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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import studybos.com.studybos2.util.InitUtil;

public class FriendsActivity extends AppCompatActivity {

    private List<FriendNew> friendNewList=new ArrayList<>();
    private List<Choose> chooseList=new ArrayList<>();

    private DrawerLayout mDrawerLayout;
    private Button callDrawerButton;
    private NavigationView navigationView;
    private Button liveButton;
    private Button helpButton;
    private Button messageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //初始化控件
        mDrawerLayout=(DrawerLayout)findViewById(R.id.friends_drawer_layout);
        callDrawerButton=(Button)findViewById(R.id.friends_call_menu_button);
        navigationView=(NavigationView)findViewById(R.id.friends_nav_view);
        liveButton=(Button)findViewById(R.id.friends_live);
        helpButton=(Button)findViewById(R.id.friends_help);
        messageButton=(Button)findViewById(R.id.friends_chat);


        //设置recyclerview
        /*initFriends();*/
        friendNewList=InitUtil.initFriendsNew();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.friends_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FriendNewAdapter adapter=new FriendNewAdapter(friendNewList);

        recyclerView.setAdapter(adapter);

        /*chooseList=InitUtil.initChooses(new Choose[] {new Choose("关注"),new Choose("粉丝"),new Choose("好友"),new Choose("陌生人")});
        RecyclerView recyclerView1=(RecyclerView)findViewById(R.id.friends_choose_recycler_view);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        ChooseAdapter adapter1=new ChooseAdapter(chooseList);
        recyclerView1.setAdapter(adapter1);*/

        //点击按钮呼出菜单栏
        callDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //按钮点击事件
        liveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FriendsActivity.this,LiveActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FriendsActivity.this,HelpActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FriendsActivity.this,SendMessageActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
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
                        Intent intent=new Intent(FriendsActivity.this,PersonalCenterActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_asks:
                        intent=new Intent(FriendsActivity.this,MyAsksAvtivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_answers:
                        intent=new Intent(FriendsActivity.this,MyAnswersActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_settings:
                        intent=new Intent(FriendsActivity.this,SettingsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.search:
                        intent=new Intent(FriendsActivity.this,SearchActivity.class);
                        startActivity(intent);
                    default:
                        return false;
                }
            }
        });
    }

    //按back关闭菜单
    @Override
    public void onBackPressed() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.friends_drawer_layout);
        if (mDrawerLayout.isDrawerOpen(findViewById(R.id.friends_nav_view)))
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

    //初始化朋友
    /*private void initFriends(){
        for (int i=0;i<20;i++){
            Friend friend=new Friend(i);
            friend.setImageId(R.drawable.icon_image);
            friend.setId("Warrah");
            friend.setLastMessage("Yes");
            friendList.add(friend);
        }
    }*/
}
