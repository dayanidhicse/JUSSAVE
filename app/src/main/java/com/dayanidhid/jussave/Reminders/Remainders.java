package com.dayanidhid.jussave.Reminders;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dayanidhid.jussave.Adapters.AlertDialogHelper;
import com.dayanidhid.jussave.Adapters.RemAdapter;
import com.dayanidhid.jussave.Adapters.RemRecyclerView;
import com.dayanidhid.jussave.HomeActivity;
import com.dayanidhid.jussave.R;
import com.dayanidhid.jussave.Adapters.RecyclerTouchListener;
import com.dayanidhid.jussave.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class Remainders extends AppCompatActivity implements AlertDialogHelper.AlertDialogListener{
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar, toolbarCustom;
    private TextView count;
   static public ImageView delete,share;
    private ArrayList<RemAdapter> list=new ArrayList<RemAdapter>();
    public ArrayList<RemAdapter> multilist;
    public StringBuilder stringBuilder;
    boolean status=true;
    boolean isMultiSelect = false;
    RemRecyclerView remRecyclerView;
    AlertDialogHelper alertDialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainders);
        alertDialogHelper =new AlertDialogHelper(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        delete=(ImageView)findViewById(R.id.delete);
        share=(ImageView)findViewById(R.id.share);
        count=(TextView)findViewById(R.id.count);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.empty);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder=new StringBuilder();
                if(multilist!=null)
                {
                    for(int i=0;i<multilist.size();i++)
                    {
                     stringBuilder.append(""+multilist.get(i).getName()+"\n");


                    }
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
                    sendIntent.setType("text/*");
                    startActivity(sendIntent);
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(multilist.size()>=1) {
                    alertDialogHelper.showAlertDialog("", "Delete Contact", "DELETE", "CANCEL", 1, false);
                }
                else
                {
                    alertDialogHelper.showAlertDialog("", "Can't delete,No Selection", "", "Ok", 0, false);
                }
            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        //recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this,getResources().getConfiguration().orientation==1?2:3));
        for(int i=0;i<100;i++) {
            list.add(new RemAdapter("Test:"+i));
        }
        if(list.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        } else{
            linearLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        remRecyclerView=new RemRecyclerView(this,R.layout.rem_card,list,multilist);
        recyclerView.setAdapter(remRecyclerView);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                if(isMultiSelect)
                {
                    multiselect(position);
                }
                else
                    Toast.makeText(Remainders.this, "Single Click on position        :"+position,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position)
            {

                Toast.makeText(Remainders.this, "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
                onSelectItem();
                if (!isMultiSelect) {
                    multilist=new ArrayList<RemAdapter>();
                    isMultiSelect=true;
                }
                multiselect(position);
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_aboutUs){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onSelectItem()
    {
        status=false;
        toolbarCustom = (Toolbar) findViewById(R.id.toolbarCustom);
        toolbar.setVisibility(View.GONE);
        toolbarCustom.setVisibility(View.VISIBLE);
    }
    @Override
    public void onBackPressed()
    {
        if(status==false)
        {
//            toolbarCustom = (Toolbar) findViewById(R.id.toolbarCustom);
//            toolbar.setVisibility(View.VISIBLE);
//            toolbarCustom.setVisibility(View.GONE);
//            status=true;
            //startActivity(new Intent(this,Remainders.class));
            //finish();
            toolbarHide();
            remRecyclerView.itemList5=list;
            remRecyclerView.selectedlist.clear();
//            for(int i=0;i<multilist.size();i++)
//            {
//                remRecyclerView.selectedlist.
//            }
            isMultiSelect=false;

            remRecyclerView.notifyDataSetChanged();

        }
        else
        {
            super.onBackPressed();
            finish();
        }
    }
    public void multiselect(int position)
    {
        if (multilist.contains(list.get(position)))
            multilist.remove(list.get(position));
        else
            multilist.add(list.get(position));

        if (multilist.size() > 0)
            count.setText("" + multilist.size());
        else
            count.setText("");

        refreshAdapter();
    }

public void refreshAdapter()
{   remRecyclerView.selectedlist=multilist;
    remRecyclerView.itemList5=list;
    remRecyclerView.notifyDataSetChanged();
}
    @Override
    public void onPositiveClick(int from)
    {
        if(from==1)
        {
            if(multilist.size()>0)
            {
                for(int i=0;i<=multilist.size()-1;i++)
                {
                    remRecyclerView.itemList5.remove(multilist.get(i));
                }
                remRecyclerView.notifyDataSetChanged();
                toolbarHide();
            }
            multilist.clear();
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNegativeClick(int from) {

    }

    @Override
    public void onNeutralClick(int from)
    {

    }
    public void toolbarHide()
    {
            toolbarCustom = (Toolbar) findViewById(R.id.toolbarCustom);
            toolbar.setVisibility(View.VISIBLE);
            toolbarCustom.setVisibility(View.GONE);
            status=true;
    }

}
