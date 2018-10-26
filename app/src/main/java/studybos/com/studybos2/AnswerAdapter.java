package studybos.com.studybos2;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import studybos.com.studybos2.data.AnswerInfo2;

/**
 * Created by 机械革命 on 2018/10/14.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    private List<AnswerInfo2> mAnswerInfoList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView answertItemId;
        TextView answerItemTime;
        TextView answerItemAnswer;
        ImageView answerItemImage;

        public ViewHolder(View view){
            super(view);
            answertItemId=(TextView)view.findViewById(R.id.answer_item_id);
            answerItemTime=(TextView)view.findViewById(R.id.answer_item_time);
            answerItemAnswer=(TextView)view.findViewById(R.id.answer_item_answer);
            answerItemImage=(ImageView)view.findViewById(R.id.answer_item_image);
        }
    }

    public AnswerAdapter(List<AnswerInfo2> answerInfoList){
        mAnswerInfoList=answerInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AnswerInfo2 answerInfo=mAnswerInfoList.get(position);
        holder.answertItemId.setText(answerInfo.getAnswerId());
        holder.answerItemTime.setText(answerInfo.getAnswerTime());
        holder.answerItemAnswer.setText(answerInfo.getAnswer());
        /*byte[] b=answerInfo.getAnswerImg();
        int length=b.length;
        holder.answerItemImage.setImageBitmap(BitmapFactory.decodeByteArray(b,0,length));*/
    }

    @Override
    public int getItemCount() {
        return mAnswerInfoList.size();
    }
}
