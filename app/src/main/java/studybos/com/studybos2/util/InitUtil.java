package studybos.com.studybos2.util;

import org.litepal.crud.DataSupport;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import studybos.com.studybos2.Choose;
import studybos.com.studybos2.Friend;
import studybos.com.studybos2.FriendNew;
import studybos.com.studybos2.Help;
import studybos.com.studybos2.R;
import studybos.com.studybos2.data.AnswerInfo2;
import studybos.com.studybos2.data.ChatMessage;
import studybos.com.studybos2.data.ChatMessage2;
import studybos.com.studybos2.data.FriendNewList;
import studybos.com.studybos2.data.FriendNewPosition;
import studybos.com.studybos2.data.HelpList;
import studybos.com.studybos2.data.LoginId;
import studybos.com.studybos2.data.ProblemNumber;
import studybos.com.studybos2.data.ProblemPack2;
import studybos.com.studybos2.data.Subject;

/**
 * Created by 机械革命 on 2018/8/23.
 */

public class InitUtil {

    public static List<Help> initHelp(Help[] helps){
        List<Help> helpList=new ArrayList<>();
        for (int i=0;i<20;i++){
            Help help=helps[0];
            help.setAskerImageId(R.drawable.icon_image);
            help.setAskerId("Warrah");
            help.setTitle("高数");
            help.setContent("这是一道高数题");
            help.setImageId(R.drawable.blue);
            helpList.add(help);
        }
        return helpList;
    }

    public static List<Friend> initFriends(Friend[] friends){
        List<Friend> friendList=new ArrayList<>();
        for (int i=0;i<20;i++){
            Friend friend=friends[0];
            friend.setImageId(R.drawable.icon_image);
            friend.setId("Warrah");
            friend.setLastMessage("Yes");
            friendList.add(friend);
        }
        return  friendList;
    }

    public static List<Choose> initChooses(Choose[] chooses){
        List<Choose> chooseList=new ArrayList<>();
        for (Choose choose:chooses){
            chooseList.add(choose);
        }
        return chooseList;
    }

    public static List<Help> initReadHelp(){
        String subject=Subject.getSubject();
        ArrayList<Help> helpList=new ArrayList<>();
        List<ProblemPack2> problemPack2List =DataSupport.findAll(ProblemPack2.class);
        for (ProblemPack2 p: problemPack2List){
            if(p.getProfession().equals(subject)||subject.equals("全部")){
                Help help=new Help(0);
                help.setAskerId(p.getAskerId());
                help.setImgByte(p.getImageBytes());
                help.setTitle(p.getProfession());
                help.setContent(p.getProblem());
                help.setNumber(p.getProHash());
                helpList.add(help);
            }
        }
        HelpList.setHelpList(helpList);
        return helpList;
    }

    public static List<Help> initMyAsks(){
        String id= LoginId.getLoginId();
        ArrayList<Help> helpList=new ArrayList<>();
        List<ProblemPack2> problemPack2List=DataSupport.findAll(ProblemPack2.class);
        for(ProblemPack2 p:problemPack2List){
            if (p.getAskerId().equals(id)){
                Help help=new Help(0);
                help.setAskerId(p.getAskerId());
                help.setImgByte(p.getImageBytes());
                help.setTitle(p.getProfession());
                help.setContent(p.getProblem());
                help.setNumber(p.getProHash());
                helpList.add(help);
            }
        }
        return helpList;
    }

    public static List<AnswerInfo2> initAnswerInfo(){
        int number= ProblemNumber.getProblemNumber();
        ArrayList<Help> helpList= HelpList.getHelpList();
        Help help=helpList.get(number);
        int hash=help.getNumber();
        List<AnswerInfo2> answerInfo2ArrayList=DataSupport.findAll(AnswerInfo2.class);
        List<AnswerInfo2> answerInfo2List=new ArrayList<>();
        for(AnswerInfo2 a:answerInfo2ArrayList){
            if(a.getProblemHash()==hash){
                answerInfo2List.add(a);
            }
        }
        return answerInfo2List;
    }

    public static List<Help> initMyAnswers(){
        String id=LoginId.getLoginId();
        int hash;
        List<AnswerInfo2> answerInfo2List=DataSupport.findAll(AnswerInfo2.class);
        List<ProblemPack2> problemPack2List=DataSupport.findAll(ProblemPack2.class);
        List<Help> helpList=new ArrayList<>();
        for(AnswerInfo2 a:answerInfo2List){
            if(a.getAnswerId().equals(id)){
                hash=a.getProblemHash();
                for(ProblemPack2 p:problemPack2List){
                    if(hash==p.getProHash()){
                        Help help=new Help(0);
                        help.setAskerId(p.getAskerId());
                        help.setImgByte(p.getImageBytes());
                        help.setTitle(p.getProfession());
                        help.setContent(p.getProblem());
                        help.setNumber(p.getProHash());
                        helpList.add(help);
                    }
                }
            }
        }
        return helpList;
    }

    public static List<FriendNew> initFriendsNew(){
        List<ChatMessage2> chatMessage2List =DataSupport.findAll(ChatMessage2.class);
        ArrayList<FriendNew> friendNewList=new ArrayList<>();
        int position=0;
        String date;
        int number=0;
        FriendNew friendNew=new FriendNew();
        for(ChatMessage2 c: chatMessage2List){
            if(friendNewList.size()==0){
                if(c.getSender().equals(LoginId.getLoginId())){
                    friendNew.setId(c.getGetter());
                }else if(c.getGetter().equals(LoginId.getLoginId())){
                    friendNew.setId(c.getSender());
                }
                friendNew.setMessage(c.getMess());
                friendNew.setTime(c.getDate());
                friendNewList.add(friendNew);
                number++;
            }else{
                for(FriendNew f:friendNewList){
                    if(c.getGetter().equals(f.getId())||c.getSender().equals(f.getId())){
                        if (c.getDate().compareTo(f.getTime())>0){
                            friendNew.setId(f.getId());
                            friendNew.setTime(c.getDate());
                            friendNew.setMessage(c.getMess());
                            friendNewList.set(number,friendNew);
                            number--;
                        }
                    }else{
                        if (c.getSender().equals(LoginId.getLoginId())){
                            friendNew.setId(c.getGetter());
                        }else if(c.getGetter().equals(LoginId.getLoginId())){
                            friendNew.setId(c.getSender());
                        }
                        friendNew.setMessage(c.getMess());
                        friendNew.setTime(c.getDate());
                        friendNewList.add(number,friendNew);
                    }
                    number++;
                }
            }
            number=0;
        }
        FriendNewList.setFriendNewArrayList(friendNewList);
        return friendNewList;
    }

    public static List<FriendNew> initfriendstest(){
        List<FriendNew> friendNewList=new ArrayList<>();
        FriendNew f=new FriendNew();
        f.setId("123");
        f.setTime("20181818");
        f.setMessage("message");
        friendNewList.add(f);
        return friendNewList;
    }

    public static List<ChatMessage2> initChatMessage(){
        int count=0;
        int number= FriendNewPosition.getFriendNewPosition();
        ArrayList<FriendNew>friendNewArrayList=FriendNewList.getFriendNewArrayList();
        FriendNew friendNew=friendNewArrayList.get(number);
        List<ChatMessage2> chatMessage2List=DataSupport.findAll(ChatMessage2.class);
        List<ChatMessage2> chatMessage2List1=new ArrayList<>();
        List<ChatMessage2> chatMessage2List2Init=new ArrayList<>();
        for(ChatMessage2 c:chatMessage2List){
            if ((c.getSender().equals(LoginId.getLoginId())&&c.getGetter().equals(friendNew.getId()))||(c.getGetter().equals(LoginId.getLoginId())&&c.getSender().equals(friendNew.getId()))){
                chatMessage2List1.add(c);
            }
        }
        for(ChatMessage2 c2:chatMessage2List1){
            if (chatMessage2List2Init.size()==0){
                chatMessage2List2Init.add(c2);
                count++;
            }else{
                for(ChatMessage2 c3:chatMessage2List2Init){
                    if(c2.getDate().compareTo(c3.getDate())>0){
                        count--;
                        chatMessage2List2Init.add(count,c2);
                    }
                }
                count=0;
            }
        }
        return chatMessage2List2Init;
    }
}
