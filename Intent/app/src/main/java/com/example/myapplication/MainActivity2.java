package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.myapplication.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity2.this,binding.btnAction);
                popupMenu.inflate(R.menu.layout_popup_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent;
                        switch(menuItem.getItemId()){
                            case R.id.miDial:
                                intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:555-1234"));
                                startActivity(intent);
                                break;
                            case R.id.miWebSearch:
                                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                                intent.putExtra(SearchManager.QUERY,"straight hitting golf clubs");
                                startActivity(intent);
                                break;
                            case R.id.miSend:
                                //Tạo một intent với hành động Action_send và loại dữ liệu (data type) là text/plain
                                intent = new Intent();
                                intent.setAction(Intent.ACTION_SEND);
                                intent.setType("text/plain");

                                // Đặt dữ liệu (data) cho Intent
                                intent.putExtra(Intent.EXTRA_TEXT,"Hello, this is the message.");
                                // Đặt danh mục (categoryu) cho Intent
                                intent.addCategory(Intent.CATEGORY_DEFAULT);
                                // Đặt thành phần (component) cho Intent
                                intent.setComponent(new ComponentName("com.example.myapplication",
                                        "com.example.myapplication.GreetingActivity"));
                                startActivity(intent);
                                break;
                            case R.id.miMessage:
                                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:5551234"));
                                intent.putExtra("sms body", "are we playing golf next Saturday");
                                startActivity(intent);
                                break;
                            case R.id.miPictures:
                                intent = new Intent();
                                intent.setType("image/pictures/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivity(intent);
                                break;
                            case R.id.miContacts:
                                String myData = "content://contacts/people/";
                                intent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse(myData));
                                startActivity(intent);
                                break;
                            case R.id.miContactsId:
                                String myData2 = "content://contacts/people/8";
                                intent = new Intent(Intent.ACTION_VIEW,Uri.parse(myData2));
                                startActivity(intent);
                                break;
                            case R.id.miEditContact:
                                String myData3 = "content://contacts/people/8";
                                intent = new Intent(Intent.ACTION_EDIT,Uri.parse(myData3));
                                startActivity(intent);
                                break;
                            case R.id.miMapAddress:
                                intent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("geo:0,0?q=1860+east+18th+street+cleveland+oh"));
                                startActivity(intent);
                                break;
                            case R.id.miMapCoordinates:
                                intent = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("geo:41.5020952,-81.6789717"));
                                startActivity(intent);
                                break;
                            case R.id.miMusic:
                                intent = new Intent("android.intent.action.MUSIC_PLAYER");
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });

                popupMenu.show();
            }
        });

//        Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:555-1234"));
//        startActivity(call);
    }
}