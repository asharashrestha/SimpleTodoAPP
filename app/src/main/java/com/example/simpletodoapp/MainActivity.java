package com.example.simpletodoapp;

import android.os.FileUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));

    Button btnAdd;
    EditText etitems;
    RecyclerView rvItems;
    itemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        etitems = findViewById(R.id.etitems);
        rvItems = findViewById(R.id.rvItems);

        items = new ArrayList<>();
        items.add("Buy Christmas Presents");
        items.add("Shovel the snow");
        items.add("Decorate the Christmas Tree");
        items.add("Clean up the house");

        itemsAdapter.OnLongClickListener onLongClickListener = new itemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int i) {
                //Delete the items
                items.remove(i);
                //Notify the adapter
                itemsAdapter.notifyItemRemoved(i);
                Toast.makeText(getApplicationContext(),"An Item was removed", Toast.LENGTH_SHORT).show();


            }
        };

        itemsAdapter = new itemsAdapter(items, onLongClickListener);
        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = etitems.getText().toString();
                items.add(todoItem);
                itemsAdapter.notifyItemInserted(items.size() -1);
                etitems.setText("");
                Toast.makeText(getApplicationContext(),"Item was added", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private File getDataFile() {
        return new File(getFilesDir(), "data.txt");
    }
    // This fxn will load itesma by reading every line of the data file.
    private void loadItems() {
        items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
    }
}
