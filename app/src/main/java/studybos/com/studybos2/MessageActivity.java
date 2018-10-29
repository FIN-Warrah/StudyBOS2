package studybos.com.studybos2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import studybos.com.studybos2.data.ChatMessage;
import studybos.com.studybos2.data.ChatMessage2;
import studybos.com.studybos2.data.FriendNewList;
import studybos.com.studybos2.data.FriendNewPosition;
import studybos.com.studybos2.util.InitUtil;

public class MessageActivity extends AppCompatActivity {

    private Button backButton;
    private List<ChatMessage2> chatMessage2List=new ArrayList<>();
    private Button sendButton;
    private EditText messageEdit;

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
        sendButton=(Button)findViewById(R.id.message_send);
        messageEdit=(EditText)findViewById(R.id.message_message);

        //设置按钮点击事件
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MessageActivity.this,LiveActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=messageEdit.getText().toString();
                SharedPreferences pref=getSharedPreferences("loginMessage",MODE_PRIVATE);
                String sender=pref.getString("account","");
                int friendNumber= FriendNewPosition.getFriendNewPosition();
                FriendNew friendNew=FriendNewList.getFriendNewArrayList().get(friendNumber);
                String getter=friendNew.getId();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String time=Integer.toString(year)+Integer.toString(month)+Integer.toString(day)+Integer.toString(hour)+Integer.toString(minute)+Integer.toString(second);
                ChatMessage chatMessage=new ChatMessage();
                chatMessage.setSender(sender);
                chatMessage.setDate(time);
                chatMessage.setMess(message);
                chatMessage.setGetter(getter);
                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(SocketService.getInstance().getSocket().getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    oos.writeObject(chatMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //设置recyclerview
        chatMessage2List= InitUtil.initChatMessage();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.message_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ChatMessageAdapter adapter=new ChatMessageAdapter(chatMessage2List);
        recyclerView.setAdapter(adapter);
    }
}
