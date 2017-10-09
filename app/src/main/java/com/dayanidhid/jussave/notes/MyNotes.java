package com.dayanidhid.jussave.notes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dayanidhid.jussave.Adapters.AlertDialogHelper;
import com.dayanidhid.jussave.Adapters.NoteRecyclerView;
import com.dayanidhid.jussave.Adapters.NotesAdapter;
import com.dayanidhid.jussave.GetInput.InputNote;
import com.dayanidhid.jussave.HomeActivity;
import com.dayanidhid.jussave.R;
import com.dayanidhid.jussave.Adapters.RecyclerTouchListener;
import com.dayanidhid.jussave.Adapters.RecyclerViewAdapter;
import com.dayanidhid.jussave.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyNotes extends AppCompatActivity implements AlertDialogHelper.AlertDialogListener {
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<NotesAdapter> list2 = new ArrayList<NotesAdapter>();
    public ArrayList<NotesAdapter> multilist2;
    public StringBuilder stringBuilder;
    private boolean status = true;
    private boolean isMultiSelect = false;
    NoteRecyclerView noteRecyclerView;
    AlertDialogHelper alertDialogHelper;
    String userChoosenTask;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbarCustom)
    Toolbar toolbarCustom;

    @BindView(R.id.delete)
    ImageView delete1;

    static ImageView share1;

    @BindView(R.id.count)
    TextView count;

    @BindView(R.id.empty)
    LinearLayout linearLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindDrawable(R.drawable.ic_back)
    Drawable ic_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        share1 = findViewById(R.id.share);
        alertDialogHelper = new AlertDialogHelper(this);
        toolbar.setNavigationIcon(ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 100; i++) {
            list2.add(new NotesAdapter("Title:" + i, "Type:" + "img"));
        }
        if (list2.isEmpty())
        {
            mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
            List<String> input = new ArrayList<>();

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
                    } else {

//                        Toast.makeText(MyNotes.this, "Single Click on position        :" + position,
//                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onLongClick(View view, int position) {

//                    Toast.makeText(MyNotes.this, "Long press on position :" + position,
//                            Toast.LENGTH_LONG).show();
                    onSelectItem();
                    if (!isMultiSelect) {
                        multilist2 = new ArrayList<NotesAdapter>();
                        isMultiSelect = true;
                    }
                    multiselect(position);
                }
            }));
        }

    public void onSelectItem()
    {
        status = false;
//        toolbarCustom = (Toolbar) findViewById(R.id.toolbarCustom);
        toolbar.setVisibility(View.GONE);
        toolbarCustom.setVisibility(View.VISIBLE);
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
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        } else if (id == R.id.action_aboutUs){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view){
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

    @Override
    public void onBackPressed() {
        if (status == false)
        {
            toolbarHide();
            noteRecyclerView.itemList6 = list2;
            noteRecyclerView.selectedlist2.clear();
            isMultiSelect=false;
            noteRecyclerView.notifyDataSetChanged();
            //Toast.makeText(this, ""+multilist2.size(), Toast.LENGTH_SHORT).show();

        } else {
            super.onBackPressed();
            finish();
        }
    }

    public void multiselect(int position)
    {
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

    public void refreshAdapter()
    {
        noteRecyclerView.selectedlist2 = multilist2;
        noteRecyclerView.itemList6 = list2;
        noteRecyclerView.notifyDataSetChanged();
    }

    @Override
    public void onPositiveClick(int from) {
        if (from == 1)
        {
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
        toolbar.setVisibility(View.VISIBLE);
        toolbarCustom.setVisibility(View.GONE);
        status = true;
    }

    public static void setShareVisibility(int visibility){
        share1.setVisibility(visibility);
    }

}

