package studybos.com.studybos2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import studybos.com.studybos2.data.Subject;

/**
 * Created by 机械革命 on 2018/8/26.
 */

public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ViewHolder> {

    private Context mContext;
    private List<Choose> mChooseList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View chooseView;
        TextView chooseText;

        public ViewHolder(View view){
            super(view);
            chooseView=view;
            chooseText=(TextView) view.findViewById(R.id.choose_text);
        }
    }

    public ChooseAdapter(List<Choose> chooseList){
        mChooseList=chooseList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.chooseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Choose choose=mChooseList.get(position);
                Subject.setSubject(choose.getChoose());
                Toast.makeText(v.getContext(),choose.getChoose(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Choose choose=mChooseList.get(position);
        holder.chooseText.setText(choose.getChoose());
    }

    @Override
    public int getItemCount() {
        return mChooseList.size();
    }
}
