package com.dayanidhid.jussave.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dayanidhid.jussave.Adapters.AlertDialogHelper;
import com.dayanidhid.jussave.Adapters.NoteRecyclerView;
import com.dayanidhid.jussave.Adapters.NotesAdapter;
import com.dayanidhid.jussave.GetInput.InputNote;
import com.dayanidhid.jussave.MainActivity;
import com.dayanidhid.jussave.R;
import com.dayanidhid.jussave.Adapters.RecyclerTouchListener;
import com.dayanidhid.jussave.Adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyNotes extends AppCompatActivity implements AlertDialogHelper.AlertDialogListener {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar, toolbarCustom;
    private TextView count;
    static public ImageView delete1, share1;
    private ArrayList<NotesAdapter> list2 = new ArrayList<NotesAdapter>();
    public ArrayList<NotesAdapter> multilist2;
    public StringBuilder stringBuilder;
    private boolean status = true;
    private boolean isMultiSelect = false;
    NoteRecyclerView noteRecyclerView;
    AlertDialogHelper alertDialogHelper;
    String userChoosenTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        delete1 = (ImageView) findViewById(R.id.delete);
        share1 = (ImageView) findViewById(R.id.share);
        count = (TextView) findViewById(R.id.count);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.empty);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setSupportActionBar(toolbar);
        alertDialogHelper = new AlertDialogHelper(this);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder = new StringBuilder();
                if (multilist2 != null) {
                    for (int i = 0; i < multilist2.size(); i++) {
                        stringBuilder.append("" + multilist2.get(i).getTitle() + "\n");


                    }
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, stringBuilder.toString());
                    sendIntent.setType("text/*");
                    startActivity(sendIntent);
                }
            }
        });
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogHelper.showAlertDialog("", "Delete Contact", "DELETE", "CANCEL", 1, false);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"Type Notes", "Camera", "Gallery"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MyNotes.this);
                builder.setTitle("Add Notes!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Type Notes")) {
                            userChoosenTask = "Type Notes";
                            startActivity(new Intent(getApplicationContext(), InputNote.class));

                        } else if (items[item].equals("Camera")) {
                            userChoosenTask = "Camera";
                        } else if (items[item].equals("Gallery")) {
                            userChoosenTask = "Gallery";
                        }
                    }
                });
                builder.show();
            }
        });
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        //recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 100; i++) {
            list2.add(new NotesAdapter("Title:" + i, "Type:" + "img"));
        }
        if (list2.isEmpty())
        {
            mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            List<String> input = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            input.add("Test" + i);
//        }// define an adapter

            if (input.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            } else {
                linearLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }

            noteRecyclerView = new NoteRecyclerView(MyNotes.this, R.layout.row_item, list2, multilist2);
            recyclerView.setAdapter(noteRecyclerView);
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                    recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, final int position) {
                    //Values are passing to activity & to fragment as well
                    if (isMultiSelect) {
                        multiselect(position);
                    } else
                        Toast.makeText(MyNotes.this, "Single Click on position        :" + position,
                                Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLongClick(View view, int position) {

                    Toast.makeText(MyNotes.this, "Long press on position :" + position,
                            Toast.LENGTH_LONG).show();
                    onSelectItem();
                    if (!isMultiSelect) {
                        multilist2 = new ArrayList<NotesAdapter>();
                        isMultiSelect = true;
                    }
                    multiselect(position);
                }
            }));
        }


//        @Override
//        public boolean onCreateOptionsMenu (Menu menu){
//            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.menu_main, menu);
//            return true;
//        }

//        @Override
//        public boolean onOptionsItemSelected (MenuItem item)
//        {
//            // Handle action bar item clicks here. The action bar will
//            // automatically handle clicks on the Home/Up button, so long
//            // as you specify a parent activity in AndroidManifest.xml.
//            int id = item.getItemId();
//
//            //noinspection SimplifiableIfStatement
//            if (id == R.id.action_settings) {
//                return true;
//            } else if (id == R.id.action_aboutUs) {
//                return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }

    public void onSelectItem()
    {
        status = false;
        toolbarCustom = (Toolbar) findViewById(R.id.toolbarCustom);
        toolbar.setVisibility(View.GONE);
        toolbarCustom.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (status == false) {
//            toolbarCustom = (Toolbar) findViewById(R.id.toolbarCustom);
//            toolbar.setVisibility(View.VISIBLE);
//            toolbarCustom.setVisibility(View.GONE);
//            status=true;
            //startActivity(new Intent(this,Remainders.class));
            //finish();
            toolbarHide();
            noteRecyclerView.itemList6 = list2;
            noteRecyclerView.selectedlist2.clear();
//            for(int i=0;i<multilist.size();i++)
//            {
//                noteRecyclerView.selectedlist.
//            }
            noteRecyclerView.notifyDataSetChanged();

        } else {
            super.onBackPressed();
            finish();
        }
    }

    public void multiselect(int position) {
        if (multilist2.contains(list2.get(position)))
            multilist2.remove(list2.get(position));
        else
            multilist2.add(list2.get(position));

        if (multilist2.size() > 0)
            count.setText("" + multilist2.size());
        else
            count.setText("");

        refreshAdapter();
    }

    public void refreshAdapter() {
        noteRecyclerView.selectedlist2 = multilist2;
        noteRecyclerView.itemList6 = list2;
        noteRecyclerView.notifyDataSetChanged();
    }

    @Override
    public void onPositiveClick(int from) {
        if (from == 1) {
            if (multilist2.size() > 0) {
                for (int i = 0; i <= multilist2.size() - 1; i++) {
                    noteRecyclerView.itemList6.remove(multilist2.get(i));
                }
                noteRecyclerView.notifyDataSetChanged();
                toolbarHide();
            }
            multilist2.clear();
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNegativeClick(int from) {

    }

    @Override
    public void onNeutralClick(int from) {

    }

    public void toolbarHide() {
        toolbarCustom = (Toolbar) findViewById(R.id.toolbarCustom);
        toolbar.setVisibility(View.VISIBLE);
        toolbarCustom.setVisibility(View.GONE);
        status = true;
    }

}

