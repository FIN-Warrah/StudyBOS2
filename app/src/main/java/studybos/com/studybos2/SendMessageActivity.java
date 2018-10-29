package studybos.com.studybos2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import studybos.com.studybos2.data.ChatMessage;
import studybos.com.studybos2.data.ChatMessage2;
import studybos.com.studybos2.data.LoginId;

public class SendMessageActivity extends AppCompatActivity {

    private EditText id;
    private EditText message;
    private Button send;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //初始化控件
        id=(EditText)findViewById(R.id.send_message_to_who_edit);
        message=(EditText)findViewById(R.id.send_message_message);
        send=(Button)findViewById(R.id.send_message_send_button);
        back=(Button)findViewById(R.id.send_message_back_button);

        //点击
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SendMessageActivity.this,LiveActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //发送到服务器
                try {
                    ChatMessage chatMessage2 =new ChatMessage();
                    chatMessage2.setSender(LoginId.getLoginId());
                    chatMessage2.setGetter(id.getText().toString());
                    chatMessage2.setMess(message.getText().toString());


                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH)+1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int hour = calendar.get(Calendar.HOUR_OF_DAY);
                    int minute = calendar.get(Calendar.MINUTE);
                    int second = calendar.get(Calendar.SECOND);
                    String time=Integer.toString(year)+Integer.toString(month)+Integer.toString(day)+Integer.toString(hour)+Integer.toString(minute)+Integer.toString(second);

                    chatMessage2.setDate(time);

                    ObjectOutputStream oos = new ObjectOutputStream(SocketService.getInstance().getSocket().getOutputStream());
                    oos.writeObject(chatMessage2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast.makeText(SendMessageActivity.this,"已发送",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SendMessageActivity.this,LiveActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }
}
