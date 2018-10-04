package studybos.com.studybos2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 机械革命 on 2018/8/21.
 */

public class LiveCardAdapter extends RecyclerView.Adapter<LiveCardAdapter.ViewHolder> {

    private Context mContent;
    private List<LiveCard> mliveCardList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView liveCardImage;
        CircleImageView liverImage;
        TextView liveCardTitleText;
        TextView liveCardLiverIdText;
        TextView liveCardSubjectText;
        TextView liveCardAudienceNumberText;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            liveCardImage=(ImageView)view.findViewById(R.id.live_card_image);
            liverImage=(CircleImageView) view.findViewById(R.id.liver_image);
            liveCardTitleText=(TextView)view.findViewById(R.id.live_card_title);
            liveCardLiverIdText=(TextView)view.findViewById(R.id.live_card_liver);
            liveCardSubjectText=(TextView)view.findViewById(R.id.live_card_subject);
            liveCardAudienceNumberText=(TextView)view.findViewById(R.id.live_card_audience_number);
        }
    }

    public LiveCardAdapter(List<LiveCard> liveCardList){
        mliveCardList=liveCardList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContent==null){
            mContent=parent.getContext();
        }
        View view= LayoutInflater.from(mContent).inflate(R.layout.live_card_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LiveCard liveCard=mliveCardList.get(position);
        holder.liveCardTitleText.setText(liveCard.getTitle());
        holder.liveCardLiverIdText.setText(liveCard.getLiverid());
        holder.liveCardSubjectText.setText(liveCard.getSubject());
        holder.liveCardAudienceNumberText.setText(Integer.toString(liveCard.getAudienceNumber()));
        Glide.with(mContent).load(liveCard.getImageId()).into(holder.liveCardImage);
        Glide.with(mContent).load(liveCard.getLiverHeadImageId()).into(holder.liverImage);
    }

    @Override
    public int getItemCount() {
        return mliveCardList.size();
    }
}
