package studybos.com.studybos2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import studybos.com.studybos2.data.AnswerInfo;
import studybos.com.studybos2.data.AnswerInfo2;
import studybos.com.studybos2.data.ChatMessage;
import studybos.com.studybos2.data.ChatMessage2;
import studybos.com.studybos2.data.HeadPortraitPack;
import studybos.com.studybos2.data.LiveMessage;
import studybos.com.studybos2.data.LoginId;
import studybos.com.studybos2.data.LoginInfo;
import studybos.com.studybos2.data.ProblemPack;
import studybos.com.studybos2.data.ProblemPack2;
import studybos.com.studybos2.data.RegisterMessage;
import studybos.com.studybos2.data.RequestCode;

/**
 * Created by 孙守财 on 2018/10/6.
 * 把服务设置成系统级别的服务
 */

public class SocketService extends Service {
    //socket的值可以有登陆界面输入登录成功后设置，或者有开机自登陆设置
    private Socket socket = null;
    private  int responseCode;
    private boolean isExit;
    private static SocketService singleSS = new SocketService();

    //采用单例设计模式
    private SocketService(){
    }

    public static SocketService getInstance(){
        return singleSS;
    }

    //开机执行的代码
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
                ObjectOutputStream oo = new ObjectOutputStream(socket.getOutputStream());
                oo.writeObject(li);
                //读取响应码
                BufferedReader bw = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                responseCode = bw.read();

                //创建socket并且完成登录以后，判断登录是否成功，开启接收器
                if(responseCode == 0x03){
                    setIsExit(false);
                    receiver();
                }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            //当服务被销毁是关闭资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket()
    {
        if(socket!=null) {
            return this.socket;
        }else{
            return null;
        }
    }

    public  void setIsExit(boolean isExit)
    {
        this.isExit = isExit;
    }

    public void setSocket(Socket socket){
        if(this.socket==null) {
            this.socket = socket;
            setIsExit(false);
            receiver();
        }
    }

    public int getResponseCode(){
        //通过这个来判断要不要进行登录
        return this.responseCode;
    }

    public void setResponseCode(int responseCode){
        //该函数主要用与退出账户登陆时使用
        this.responseCode = responseCode;
    }

    //当退出账户登陆的时候
    public void onExit(){
        //设置为登录状态
        setResponseCode(-1);
        //结束接收器
        setIsExit(true);
        //发送一个退出登录的申请包,关闭socket，并且删除上一次登录的记录
        // TODO: 2018/10/7

    }

    public void receiver(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //当没有退出当前账户时，就一直接收
                ObjectInputStream oIS= null;
                try {
                    oIS = new ObjectInputStream(getSocket().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while(!isExit){
                    //接收数据包，存到数据库
                    // TODO: 2018/10/7
                    try {

                        Object obj=oIS.readObject();
                        dealObject(obj);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void dealObject(Object obj){
        if(obj instanceof AnswerInfo){
            AnswerInfo answerInfo =(AnswerInfo)obj;
            AnswerInfo2 answerInfo2ToLoad =new AnswerInfo2();
            answerInfo2ToLoad.setAnswer(answerInfo.getAnswer());
            answerInfo2ToLoad.setAnswerId(answerInfo.getAnswerId());
            answerInfo2ToLoad.setAnswerTime(answerInfo.getAnswerTime());
            answerInfo2ToLoad.setProblemHash(answerInfo.getProblemHash());
            answerInfo2ToLoad.save();
        }else if (obj instanceof ChatMessage){
            ChatMessage chatMessage =(ChatMessage)obj;
            ChatMessage2 chatMessage2=new ChatMessage2();
            chatMessage2.setDate(chatMessage.getDate());
            chatMessage2.setSender(chatMessage.getSender());
            chatMessage2.setMess(chatMessage.getMess());
            chatMessage2.setGetter(chatMessage.getGetter());
            if((chatMessage2.getGetter()).equals(LoginId.getLoginId())||(chatMessage2.getSender()).equals(LoginId.getLoginId())){
                ChatMessage2 chatMessage2ToLoad =new ChatMessage2();
                chatMessage2ToLoad.setDate(chatMessage2.getDate());
                chatMessage2ToLoad.setGetter(chatMessage2.getGetter());
                chatMessage2ToLoad.setMess(chatMessage2.getMess());
                chatMessage2ToLoad.setSender(chatMessage2.getSender());
                chatMessage2ToLoad.save();
            }
        }else if (obj instanceof HeadPortraitPack){
            HeadPortraitPack headPortraitPack=(HeadPortraitPack)obj;
            HeadPortraitPack headPortraitPackToLoad=new HeadPortraitPack();
            headPortraitPackToLoad.setFormat(headPortraitPack.getFormat());
            headPortraitPackToLoad.setPortraitName(headPortraitPack.getPortraitName());
            headPortraitPackToLoad.setUserId(headPortraitPack.getUserId());
            headPortraitPackToLoad.setImageByte(headPortraitPack.getImageByte());
            headPortraitPackToLoad.save();
        }else if (obj instanceof LiveMessage){
            LiveMessage liveMessage=(LiveMessage)obj;
            LiveMessage liveMessageToLoad=new LiveMessage();
            liveMessageToLoad.setUserId(liveMessage.getUserId());
            liveMessageToLoad.setRoomId(liveMessage.getRoomId());
            liveMessageToLoad.setRoomMaster(liveMessage.getRoomMaster());
            liveMessageToLoad.setSubject(liveMessage.getSubject());
            liveMessageToLoad.save();
        }else if (obj instanceof LoginInfo){
            LoginInfo loginInfo=(LoginInfo)obj;
            LoginInfo loginInfoToLoad=new LoginInfo();
            loginInfoToLoad.setUserId(loginInfo.getUserId());
            loginInfoToLoad.setPassword(loginInfo.getPassword());
            loginInfoToLoad.save();
        }else if (obj instanceof ProblemPack){
            ProblemPack problemPack =(ProblemPack)obj;
            ProblemPack2 problemPack2ToLoad =new ProblemPack2();
            problemPack2ToLoad.setAskerId(problemPack.getAskerId());
            problemPack2ToLoad.setAsktime(problemPack.getAsktime());
            problemPack2ToLoad.setImageBytes(problemPack.getImageBytes());
            problemPack2ToLoad.setProblem(problemPack.getProblem());
            problemPack2ToLoad.setProfession(problemPack.getProfession());
            problemPack2ToLoad.setProHash(problemPack.getProHash());
            problemPack2ToLoad.setProject(problemPack.getProject());
            problemPack2ToLoad.save();
        }else if (obj instanceof RegisterMessage){
            RegisterMessage registerMessage=(RegisterMessage)obj;
            RegisterMessage registerMessageToLoad=new RegisterMessage();
            registerMessageToLoad.setProfession(registerMessage.getProfession());
            registerMessageToLoad.setGrade(registerMessage.getGrade());
            registerMessageToLoad.setIdentity(registerMessage.getIdentity());
            registerMessageToLoad.setPassword(registerMessage.getPassword());
            registerMessageToLoad.setPhoneNum(registerMessage.getPhoneNum());
            registerMessageToLoad.setQq(registerMessage.getQq());
            registerMessageToLoad.setUserId(registerMessage.getUserId());
            registerMessageToLoad.setWeiXin(registerMessage.getWeiXin());
            registerMessageToLoad.save();
        }else if (obj instanceof RequestCode){
            RequestCode requestCode=(RequestCode)obj;
            RequestCode requestCodeToLoad=new RequestCode();
            requestCodeToLoad.setRequestCode(requestCode.getRequestCode());
            requestCodeToLoad.save();
        }
    }

}
