package com.uz.todolistdemo;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    private final static String DATA_KEY = "data_key";
    ListView listTasks;
    EditText txtNewTask;
    ArrayAdapter adapter;
    ArrayList<String> data;
    private final static String TAG = "TodoListDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        if (savedInstanceState == null) {
            data = getData();
            if (data == null || data.isEmpty()) {
                data = new ArrayList<>();
            }

        } else {
            data = savedInstanceState.getStringArrayList(DATA_KEY);
        }

        txtNewTask = (EditText) findViewById(R.id.txtNewTask);
        listTasks = (ListView) findViewById(R.id.listNotes);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        listTasks.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_add_task:
                Log.d(TAG, "add task");
                addTask();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addTask() {
        String text = txtNewTask.getText().toString().trim();
        if(text.isEmpty()) return;
        data.add(text);
        txtNewTask.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList(DATA_KEY, data);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    private void saveData() {
        SharedPreferences.Editor editor = prefs.edit();
        Set<String> set = new HashSet<>();
        set.addAll(data);
        editor.putStringSet(DATA_KEY, set);
        editor.apply();
    }

    private ArrayList<String> getData() {
        data = new ArrayList<>();
        Set<String> set = prefs.getStringSet(DATA_KEY, null);
        if (set != null) {
            for (String s : set) {
                data.add(s);
            }
        }
        return data;
    }
}
