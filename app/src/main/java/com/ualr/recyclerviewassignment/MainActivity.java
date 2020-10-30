package com.ualr.recyclerviewassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import com.ualr.recyclerviewassignment.adapter.AdapterList;
import com.ualr.recyclerviewassignment.databinding.ActivityListMultiSelectionBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ualr.recyclerviewassignment.Utils.DataGenerator;
import com.ualr.recyclerviewassignment.model.Inbox;
import com.ualr.recyclerviewassignment.Utils.Tools;
import java.util.List;

// TODO 06. Detect click events on the list items. Implement a new method to toggle items' selection in response to click events
// TODO 07. Detect click events on the thumbnail located on the left of every list row when the corresponding item is selected.
//  Implement a new method to delete the corresponding item in the list
// TODO 08. Create a new method to add a new item on the top of the list. Use the DataGenerator class to create the new item to be added.

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFAB;
    private static final int TOP_POSITION = 0;
    private ActivityListMultiSelectionBinding binding;
    private AdapterList adapter;
    private List<Inbox> dataSource;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_multi_selection);
        initComponent();

        adapter = new AdapterList(this, dataSource);

        adapter.setOnItemClickListener(new AdapterList.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, Inbox obj, int position) {
                Log.d("Tapped", String.format("The user has tapped on %s", obj.getFrom()));
            }
        });
    }

    private void initComponent() {
        dataSource = DataGenerator.getInboxData(this);
        dataSource.addAll(DataGenerator.getInboxData(this));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // TODO 04. Define the layout of each item in the list
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter = new AdapterList(this, dataSource);
        binding.recyclerView.setAdapter(adapter);

        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO 10. Invoke the method created to a new item to the top of the list so it's
                //  triggered when the user taps the Floating Action Button
                //adapter.addItem(TOP_POSITION, dataSource.get(Tools.getEmailFromName()));
            }
        });


    }

}