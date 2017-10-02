package com.dayanidhid.jussave.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dayanidhid.jussave.R;
import com.dayanidhid.jussave.Remainders;

import java.util.ArrayList;

/**
 * Created by HP on 10/1/2017.
 */

public class RemRecyclerView extends RecyclerView.Adapter<RemRecyclerView.ViewHolder> {
    private int listItemLayout5;
   public ArrayList<RemAdapter> itemList5;
    public ArrayList<RemAdapter> selectedlist;
    Context mcontext;
    public RemRecyclerView(Context context, int listItemLayout, ArrayList<RemAdapter> itemList, ArrayList<RemAdapter> selectedlist1)
    {
        this.listItemLayout5=listItemLayout;
        this.itemList5=itemList;
        this.selectedlist=selectedlist1;
        this.mcontext=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout5, parent, false);
        final ViewHolder myViewHolder3 = new ViewHolder(view);
        return myViewHolder3;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        if(selectedlist!=null) {
            if (selectedlist.contains(itemList5.get(position))) {
                holder.ll.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.list_item_selected_state));
            } else {
                holder.ll.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.list_item_normal_state));
            }
//              Code to disable multiple share
//            if(selectedlist.size()==1)
//            {
//                Remainders.share.setVisibility(View.VISIBLE);
//            }
//            else {
//                Remainders.share.setVisibility(View.INVISIBLE);
//            }
        }
        holder.title.setText(itemList5.get(position).getName());
    }

    @Override
    public int getItemCount()
    {
        return itemList5==null?0:itemList5.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public LinearLayout ll;
        public TextView title;
        public ViewHolder(View itemView)
        {
            super(itemView);
            ll=(LinearLayout)itemView.findViewById(R.id.linearLayout);
            title=(TextView)itemView.findViewById(R.id.title);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
