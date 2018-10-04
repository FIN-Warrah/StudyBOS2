package studybos.com.studybos2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketService extends Service {
    public SocketService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ObjectInputStream oIS=null;
        Object object=null;

        ApplicationUtil applicationUtil=(ApplicationUtil)SocketService.this.getApplication();
        try {
            applicationUtil.init();
            Socket socket=applicationUtil.getSocket();
            while (true){
                oIS=applicationUtil.getOIS();
                object=oIS.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*Socket socket= null;
        ObjectInputStream oIS=null;
        try {
            socket = new Socket("192.168.1.233", 9999);
            socket.setSoTimeout(10000);
            while(true) {
                oIS = new ObjectInputStream(socket.getInputStream());
                Object getObj = oIS.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
