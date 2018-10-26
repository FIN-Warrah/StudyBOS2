package studybos.com.studybos2;

import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 机械革命 on 2018/8/21.
 */

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    private List<Help> mHelpList;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView askerImage;
        TextView askerId;
        TextView title;
        TextView content;
        ImageView image;

        public ViewHolder(View view){
            super(view);
            askerImage=(CircleImageView)view.findViewById(R.id.help_item_asker_image);
            askerId=(TextView)view.findViewById(R.id.help_item_asker_id);
            title=(TextView)view.findViewById(R.id.help_item_title);
            content=(TextView)view.findViewById(R.id.help_item_content);
            image=(ImageView)view.findViewById(R.id.help_item_image);
        }
    }

    public HelpAdapter(List<Help> helpList){
        mHelpList=helpList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.help_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Help help=mHelpList.get(position);
        /*holder.askerImage.setImageResource(help.getAskerImageId());*/
        /*byte[] b=help.getImgByte();
        int length=b.length;
        holder.image.setImageBitmap(BitmapFactory.decodeByteArray(b,0,length));*/
        holder.askerId.setText(help.getAskerId());
        holder.title.setText(help.getTitle());
        holder.content.setText(help.getContent());
        /*holder.image.setImageResource(help.getImageId());*/

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
        return mHelpList.size();
    }
}
