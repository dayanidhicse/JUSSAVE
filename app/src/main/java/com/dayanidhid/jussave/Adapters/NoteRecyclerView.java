package com.dayanidhid.jussave.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dayanidhid.jussave.notes.MyNotes;
import com.dayanidhid.jussave.R;
import com.dayanidhid.jussave.Reminders.Remainders;

import java.util.ArrayList;

/**
 * Created by HP on 10/2/2017.
 */

public class NoteRecyclerView extends RecyclerView.Adapter<NoteRecyclerView.ViewHolder> {
    private int listItemLayout6;
    public ArrayList<NotesAdapter> itemList6;
    public ArrayList<NotesAdapter> selectedlist2;
    Context mcontext;
    public NoteRecyclerView(Context c,int listItemLayout,ArrayList<NotesAdapter> itemList, ArrayList<NotesAdapter> selectedlist){

        this.mcontext=c;
        this.itemList6=itemList;
        this.listItemLayout6=listItemLayout;
        this.selectedlist2=selectedlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout6, parent, false);
        final ViewHolder myViewHolder4 = new ViewHolder(view);
        return myViewHolder4;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.title.setText(itemList6.get(position).getTitle());
        holder.type.setText(itemList6.get(position).getType());
        if(selectedlist2!=null) {
            if (selectedlist2.contains(itemList6.get(position))) {
                holder.ll.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.list_item_selected_state));
            } else {
                holder.ll.setBackgroundColor(ContextCompat.getColor(mcontext, R.color.list_item_normal_state));
            }
            if(selectedlist2.size()==1)
            {
                MyNotes.setShareVisibility(View.VISIBLE);
//               MyNotes.share1.setVisibility(View.VISIBLE);
            }
            else {
                MyNotes.setShareVisibility(View.INVISIBLE);
//                MyNotes.share1.setVisibility(View.INVISIBLE);
            }
        }


    }

    @Override
    public int getItemCount()
    {
        return itemList6==null?0:itemList6.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public LinearLayout ll;
        public TextView title,type;
        public ViewHolder(View itemView)
        {
            super(itemView);
            ll=(LinearLayout)itemView.findViewById(R.id.ll);
            title=(TextView)itemView.findViewById(R.id.title);
            type=(TextView)itemView.findViewById(R.id.Type);
        }

        @Override
        public void onClick(View view)
        {

        }
    }
}
