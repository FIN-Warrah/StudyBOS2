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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import studybos.com.studybos2.data.ProblemNumber;
import studybos.com.studybos2.util.InitUtil;

public class HelpActivity extends AppCompatActivity {

    private List<Help> helpList=new ArrayList<>();
    private List<Choose> chooseHelpList=new ArrayList<>();

    private DrawerLayout mDrawerLayout;
    private Button callDrawerButton;
    private NavigationView navigationView;
    private Button liveButton;
    private Button friendsButton;
    private Button addQuestionbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //初始化控件
        mDrawerLayout=(DrawerLayout)findViewById(R.id.help_drawer_layout);
        callDrawerButton=(Button)findViewById(R.id.help_call_menu_button);
        navigationView=(NavigationView)findViewById(R.id.help_nav_view);
        liveButton=(Button)findViewById(R.id.help_live);
        friendsButton=(Button)findViewById(R.id.help_friends);
        addQuestionbutton=(Button)findViewById(R.id.help_add_question);

        //设置recyclerview
        chooseHelpList=InitUtil.initChooses(new Choose[] {new Choose("全部"),new Choose("哲学"),new Choose(" 经济学 "),new Choose("法学"),new Choose("文学"),new Choose(" 历史学 "),new Choose("理学"),new Choose("工学"),new Choose("医学"),new Choose(" 管理学 "),new Choose(" 艺术学 ")});
        RecyclerView recyclerView1=(RecyclerView)findViewById(R.id.help_choose_recycler_view);
        LinearLayoutManager layoutManager1=new LinearLayoutManager(this);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        ChooseAdapter adapter1=new ChooseAdapter(chooseHelpList);
        recyclerView1.setAdapter(adapter1);

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
                Intent intent=new Intent(HelpActivity.this,LiveActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HelpActivity.this,FriendsActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
        addQuestionbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HelpActivity.this,AddQuestion.class);
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
                        Intent intent=new Intent(HelpActivity.this,PersonalCenterActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_asks:
                        intent=new Intent(HelpActivity.this,MyAsksAvtivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_answers:
                        intent=new Intent(HelpActivity.this,MyAnswersActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.my_settings:
                        intent=new Intent(HelpActivity.this,SettingsActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.search:
                        intent=new Intent(HelpActivity.this,SearchActivity.class);
                        startActivity(intent);
                    default:
                        return false;
                }
            }
        });

        //设置recyclerview
        /*initHelp();*/
        /*helpList=InitUtil.initHelp(new Help[] {new Help(0)});*/
        helpList=InitUtil.initReadHelp();
        /*HelpList.setHelpList(helpList);*/
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.help_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HelpAdapter adapter=new HelpAdapter(helpList);

        //设置子项点击事件
        adapter.setOnItemClickListener(new HelpAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ProblemNumber.setProblemNumber(position);
                Toast.makeText(HelpActivity.this,"clicked"+position,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HelpActivity.this,HelpContent.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    //初始化recyclerview
    /*private void initHelp(){
        for(int i=0;i<10;i++){
            Help help=new Help(1);
            help.setAskerImageId(R.drawable.icon_image);
            help.setAskerId("Warrah");
            help.setTitle("高数");
            help.setContent("这是一道高数题");
            help.setImageId(R.drawable.blue);
            helpList.add(help);
            help=new Help(2);
            help.setAskerImageId(R.drawable.icon_image);
            help.setAskerId("Warrah2");
            help.setTitle("高数2");
            help.setContent("这是一道高数题2");
            help.setImageId(R.drawable.header_background);
            helpList.add(help);
        }
    }*/

    //按back关闭菜单
    @Override
    public void onBackPressed() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.help_drawer_layout);
        if (mDrawerLayout.isDrawerOpen(findViewById(R.id.help_nav_view)))
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
