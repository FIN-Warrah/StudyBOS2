package studybos.com.studybos2;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by 孙守财 on 2018/10/6.
 */

public class SocketService extends Service {
    //socket的值可以有登陆界面输入登录成功后设置，或者有开机自登陆设置
    private Socket socket = null;
    private  int responseCode;
    private static SocketService singleSS = new SocketService();

    //采用单例设计模式
    private SocketService(){
    }

    public static SocketService getInstance(){
        return singleSS;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //判断上次登陆成功后的文件是否存在,在服务开启时运行，该服务再开机运行时开启，然后一直保存下去
        File f = new File("自己指定的要存储文件的地址");
        if (!f.exists()) {
            socket = null;
            this.responseCode = -1;
        } else {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("存储的文件路径")));
                String line = null;
                LoginInfo li = new LoginInfo();
                line = br.readLine();
                //创建并且发送登录数据包
                li.setUserId(line.trim());
                line = br.readLine();
                li.setPassword(line.trim());
                socket = new Socket("", 9999);
                ObjectOutput oo = new ObjectOutputStream(socket.getOutputStream());
                oo.writeObject(li);
                //读取响应码
                BufferedReader bw = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                responseCode = bw.read();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public Socket getSocket()
    {
        if(socket!=null) {
            return this.socket;
        }else{
            return null;
        }
    }

    public void setSocket(Socket socket){
        if(socket==null)
            this.socket = socket;
    }

    public int getResponseCode(){
        //通过这个来判断要不要进行登录
        return this.responseCode;
    }
}