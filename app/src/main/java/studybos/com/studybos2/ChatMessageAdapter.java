package studybos.com.studybos2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import studybos.com.studybos2.data.ChatMessage;
import studybos.com.studybos2.data.ChatMessage2;
import studybos.com.studybos2.data.LoginId;

/**
 * Created by 机械革命 on 2018/10/18.
 */

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ViewHolder> {

    private List<ChatMessage2> mChatMessage2List;

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView lefttimetext;
        TextView leftmessagetext;
        TextView righttimetext;
        TextView rightmessagetext;

        public ViewHolder(View view){
            super(view);
            leftLayout=(LinearLayout)view.findViewById(R.id.message_item_left_layout);
            rightLayout=(LinearLayout)view.findViewById(R.id.message_item_right_layout);
            lefttimetext=(TextView)view.findViewById(R.id.message_item_left_time);
            leftmessagetext=(TextView)view.findViewById(R.id.message_item_left_message);
            righttimetext=(TextView)view.findViewById(R.id.message_item_right_time);
            rightmessagetext=(TextView)view.findViewById(R.id.message_item_right_message);
        }
    }

    public ChatMessageAdapter(List<ChatMessage2> chatMessage2List){
        mChatMessage2List=chatMessage2List;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_message_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChatMessage2 chatMessage2=mChatMessage2List.get(position);
        if(chatMessage2.getGetter().equals(LoginId.getLoginId())){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.lefttimetext.setText(chatMessage2.getDate());
            holder.leftmessagetext.setText(chatMessage2.getMess());
        }else if (chatMessage2.getSender().equals(LoginId.getLoginId())){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.righttimetext.setText(chatMessage2.getDate());
            holder.rightmessagetext.setText(chatMessage2.getMess());
        }
    }

    @Override
    public int getItemCount() {
        return mChatMessage2List.size();
    }
}
