package studybos.com.studybos2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import studybos.com.studybos2.data.AnswerInfo;
import studybos.com.studybos2.data.AnswerInfo2;
import studybos.com.studybos2.data.HelpList;
import studybos.com.studybos2.data.LoginId;
import studybos.com.studybos2.data.ProblemNumber;
import studybos.com.studybos2.util.InitUtil;

public class HelpContent extends AppCompatActivity {

    private List<AnswerInfo2> helpContentList=new ArrayList<>();
    private Button backButton;
    private EditText editText;
    private Button sendButton;
    private Button photoButton;
    private ImageView img;

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
        editText=(EditText)findViewById(R.id.help_content_edittext);
        sendButton=(Button)findViewById(R.id.help_content_send_button);
        photoButton=(Button)findViewById(R.id.help_content_image_button);
        img=(ImageView)findViewById(R.id.help_content_imageview);

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
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //关闭系统相机的view，然后把数据传回当前view
                    startActivityForResult(intent, 0);
                }
                catch(Exception e)
                {
                    Log.e("Exception", e.getMessage());
                }
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnswerInfo answerInfo=new AnswerInfo();
                answerInfo.setAnswerId(LoginId.getLoginId());
                answerInfo.setAnswer(editText.getText().toString());
                answerInfo.setProblemHash(HelpList.getHelpList().get(ProblemNumber.getProblemNumber()).getNumber());
                img.setDrawingCacheEnabled(true);
                Bitmap bitmap=img.getDrawingCache();
                img.setDrawingCacheEnabled(false);
                if (bitmap!=null){
                    int bytes=bitmap.getByteCount();
                    ByteBuffer buf = ByteBuffer.allocate(bytes);
                    bitmap.copyPixelsToBuffer(buf);
                    byte[] byteArray = buf.array();
                    answerInfo.setAnswerImg(byteArray);
                }
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String time=Integer.toString(year)+Integer.toString(month)+Integer.toString(day)+Integer.toString(hour)+Integer.toString(minute)+Integer.toString(second);
                answerInfo.setAnswerTime(time);
                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(SocketService.getInstance().getSocket().getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    oos.writeObject(answerInfo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //设置recyclerview
        /*helpContentList= InitUtil.initHelp(new Help[] {new Help(0)});
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.help_content_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HelpAdapter adapter=new HelpAdapter(helpContentList);
        recyclerView.setAdapter(adapter);*/

        helpContentList=InitUtil.initAnswerInfo();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.help_content_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AnswerAdapter adapter=new AnswerAdapter(helpContentList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
            if(requestCode==0)
            {
                super.onActivityResult(requestCode, resultCode, data);
                Bundle Extras = data.getExtras();
                Bitmap mBitmap = (Bitmap)Extras.get("data");
                img.setImageBitmap(mBitmap);
                Toast.makeText(this, "拍摄成功！"+requestCode, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "您没有拍摄照片！"+requestCode, Toast.LENGTH_SHORT).show();
            }}catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
