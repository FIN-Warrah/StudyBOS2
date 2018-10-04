package studybos.com.studybos2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {
    public BootCompleteReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"开机",Toast.LENGTH_SHORT).show();
        Intent serviceIntent=new Intent(context,SocketService.class);
        context.startService(serviceIntent);
    }
}
