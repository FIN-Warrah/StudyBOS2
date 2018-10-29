package studybos.com.studybos2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.Calendar;

import studybos.com.studybos2.data.LoginId;
import studybos.com.studybos2.data.ProblemPack;

public class AddQuestion extends AppCompatActivity {

    private String pros[] = {"哲学","经济学","文学","经济学","历史学","理学","工学","医学","管理学","艺术学"};

    public static final int TAKE_PHOTO=1;
    private Uri imageUri;

    private Button backButton;
    private Button photoButton;
    private Button sendButton;
    private ImageView img;
    private EditText editText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        if (Build.VERSION.SDK_INT>=21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        backButton=(Button)findViewById(R.id.add_question_back_button);
        photoButton=(Button)findViewById(R.id.add_question_take_photo_button);
        sendButton=(Button)findViewById(R.id.add_question_submit);
        img=(ImageView)findViewById(R.id.add_question_image);
        editText=(EditText)findViewById(R.id.add_question_edit);
        spinner=(Spinner)findViewById(R.id.choosePro);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddQuestion.this,HelpActivity.class);
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
        /*photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage=new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(AddQuestion.this,"studybos.com.studybos2.fileprovider",outputImage);
                }else{
                    imageUri=Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });*/
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String problem=editText.getText().toString();
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH)+1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                int second = calendar.get(Calendar.SECOND);
                String time=Integer.toString(year)+Integer.toString(month)+Integer.toString(day)+Integer.toString(hour)+Integer.toString(minute)+Integer.toString(second);
                String asker= LoginId.getLoginId();
                String professoin=spinner.getSelectedItem().toString();


                ProblemPack problemPack=new ProblemPack();
                img.setDrawingCacheEnabled(true);
                Bitmap bitmap=img.getDrawingCache();
                img.setDrawingCacheEnabled(false);
                if (bitmap!=null){
                    int bytes=bitmap.getByteCount();
                    ByteBuffer buf = ByteBuffer.allocate(bytes);
                    bitmap.copyPixelsToBuffer(buf);
                    byte[] byteArray = buf.array();
                    problemPack.setImageBytes(byteArray);
                }
                problemPack.setAskerId(asker);
                problemPack.setAsktime(time);
                problemPack.setProfession(professoin);
                problemPack.setProblem(problem);
                problemPack.setProHash((problem+time).hashCode());
                ObjectOutputStream oos = null;
                try {
                    oos = new ObjectOutputStream(SocketService.getInstance().getSocket().getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    oos.writeObject(problemPack);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*switch (requestCode){
            case TAKE_PHOTO:
                if(requestCode==RESULT_OK){
                    try{
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        img.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }*/

        try{
        if(requestCode==0)
        {
            super.onActivityResult(requestCode, resultCode, data);
            Bundle Extras = data.getExtras();
            Bitmap mBitmap = (Bitmap)Extras.get("data");
            img.setImageBitmap(mBitmap);
        }
        else
        {
            Toast.makeText(this, "您没有拍摄照片！"+requestCode, Toast.LENGTH_SHORT).show();
        }}catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    private class SpinnerAdapter extends ArrayAdapter<String> {
        Context context;
        String[] items = new String[]{};

        public SpinnerAdapter(final Context context,
                              final int textViewResourceId, final String[] objects) {
            super(context, textViewResourceId, objects);
            this.items = objects;
            this.context = context;
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {

            //convertview是用来填充到spinner中的布局
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                //加载系统布局文件为view
                convertView = inflater.inflate(
                        android.R.layout.simple_spinner_item, parent, false);
            }

            TextView tv = (TextView) convertView
                    .findViewById(android.R.id.text1);
            tv.setText(items[position]);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.BLUE);
            tv.setTextSize(24);
            return convertView;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(
                        android.R.layout.simple_spinner_item, parent, false);
            }

            // android.R.id.text1 is default text view in resource of the android.
            // android.R.layout.simple_spinner_item is default layout in resources of android.

            TextView tv = (TextView) convertView
                    .findViewById(android.R.id.text1);
            tv.setText(items[position]);
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.BLUE);
            tv.setTextSize(24);
            return convertView;
        }
    }

}
