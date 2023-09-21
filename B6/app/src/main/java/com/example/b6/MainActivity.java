package com.example.b6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.MenuItemCompat;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.btnAnchor);
        button2 = findViewById(R.id.btnCheckMe);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button1Clicked();
            }
        });

        // Bạn có thể thêm xử lý cho button2 ở đây nếu cần
    }

    private void button1Clicked() {
        // When user clicks on Button 1, create a PopupMenu and anchor it to Button 2.
        PopupMenu popup = new PopupMenu(this, button2);
        popup.inflate(R.menu.layout_popup_menu); // Corrected the resource name

        Menu menu = popup.getMenu();


        // Register Menu Item Click event.
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return menuItemClicked(item);
            }
        });

        // Show the PopupMenu.
        popup.show();
    }

    private boolean menuItemClicked(MenuItem item) {
        // Handle menu item click here.
        switch (item.getItemId()) {
            // Handle different menu items based on their IDs.
            // case R.id.menu_item_id:
            //    // Do something when a specific item is clicked.
            //    return true;
            default:
                return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.item1);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.item1:
//                Toast.makeText(MainActivity.this, "This is search icon", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.item2:
//                Toast.makeText(MainActivity.this, "This is item2 icon", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.item3:
//                Toast.makeText(MainActivity.this, "This is item3 icon", Toast.LENGTH_SHORT).show();
//                break;
//        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        menu.setHeaderTitle("Context Menu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.layout_context_menu, menu);
    }
}