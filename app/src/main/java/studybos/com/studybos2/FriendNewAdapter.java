package studybos.com.studybos2;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import studybos.com.studybos2.data.FriendNewPosition;

/**
 * Created by 机械革命 on 2018/10/17.
 */

public class FriendNewAdapter extends RecyclerView.Adapter<FriendNewAdapter.ViewHolder> {
    private List<FriendNew> mFriendNewList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        View friendNewView;

        TextView idText;
        TextView messageText;
        TextView timeText;

        public ViewHolder(View view){
            super(view);
            friendNewView=view;
            idText=(TextView)view.findViewById(R.id.friend_new_item_id);
            messageText=(TextView)view.findViewById(R.id.friend_new_item_message);
            timeText=(TextView)view.findViewById(R.id.friend_new_item_time);
        }
    }

    public FriendNewAdapter(List<FriendNew> friendNewList){
        mFriendNewList=friendNewList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_new_item,parent,false);

        final ViewHolder holder=new ViewHolder(view);
        holder.friendNewView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                FriendNew friendNew=mFriendNewList.get(position);
                FriendNewPosition.setFriendNewPosition(position);
                Chatter.setPos(position);
                Toast.makeText(v.getContext(),"you clicked view "+friendNew.getId()+"    "+position,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(v.getContext(),MessageActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FriendNew friendNew=mFriendNewList.get(position);
        holder.idText.setText(friendNew.getId());
        holder.messageText.setText(friendNew.getMessage());
        holder.timeText.setText(friendNew.getTime());
    }

    @Override
    public int getItemCount() {
        return mFriendNewList.size();
    }
}
