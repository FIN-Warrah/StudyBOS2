package studybos.com.studybos2;

import android.app.Application;

import org.litepal.LitePalApplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by 机械革命 on 2018/10/4.
 */

public class ApplicationUtil extends LitePalApplication {

    public static final String ADDRESS = "192.168.1.233";
    public static final int PORT = 9999;

    private Socket socket;
    private ObjectInputStream oIS=null;
    private ObjectOutputStream oOS=null;

    public void init() throws IOException, Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //与服务器建立连接
                try {
                    socket = new Socket(ADDRESS, PORT);
                    oIS=new ObjectInputStream(socket.getInputStream());
                    oOS=new ObjectOutputStream(socket.getOutputStream());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public Socket getSocket(){
        return socket;
    }

    public void SetSocket(Socket socket){
        this.socket=socket;
    }

    public ObjectInputStream getOIS(){
        return oIS;
    }

    public void setOIS(ObjectInputStream oIS){
        this.oIS=oIS;
    }

    public ObjectOutputStream getOOS(){
        return oOS;
    }

    public void setOOS(ObjectOutputStream oOS){
        this.oOS=oOS;
    }
}
