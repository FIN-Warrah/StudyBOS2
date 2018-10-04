package studybos.com.studybos2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 机械革命 on 2018/8/21.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder>{

    private List<Friend> mFriendList;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView friendImage;
        TextView idText;
        TextView messageText;

        public ViewHolder(View view){
            super(view);
            friendImage=(CircleImageView)view.findViewById(R.id.friend_item_image);
            idText=(TextView)view.findViewById(R.id.friend_item_id);
            messageText=(TextView)view.findViewById(R.id.friend_item_message);
        }
    }

    public FriendAdapter(List<Friend> friendList){
        mFriendList=friendList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Friend friend=mFriendList.get(position);
        holder.friendImage.setImageResource(friend.getImageId());
        holder.idText.setText(friend.getId());
        holder.messageText.setText(friend.getLastMessage());

        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mFriendList.size();
    }
}
