package com.example.b3;

import android.annotation.SuppressLint;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.GridView;

public class MainActivity extends Activity {
//    TextView selection;
//    String[] items = {
//            "Android",
//            "IPhone",
//            "WindowsMobile",
//            "Blackberry",
//            "WebOS",
//            "Ubuntu",
//            "Windows7",
//            "Max OS X"
//    };
//
//    @Override
//    public void onCreate(Bundle icicle) {
//        super.onCreate(icicle);
//        setContentView(R.layout.activity_main);
//
//        selection = (TextView) findViewById(R.id.selection);
//        GridView gv = (GridView) findViewById(R.id.grid);
//
//        ArrayAdapter<String> aa = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                items
//        );
//
//        gv.setAdapter(aa);
//        gv.setOnItemClickListener(this);
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//        selection.setText(items[position]);
//    }

    //    TextView selection;
//    Spinner spinner;
//    String[] items = {"Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        selection = findViewById(R.id.selection);
//        spinner = findViewById(R.id.spinner);
//        spinner.setOnItemSelectedListener(this);
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
//        String selectedItem = items[position];
//        selection.setText("Selected: " + selectedItem);
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//        selection.setText("Nothing selected");
//    }

    // Array of strings...
    String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mobileArray);
        ListView listView = findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);
    }
}
