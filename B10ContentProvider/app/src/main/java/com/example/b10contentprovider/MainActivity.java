package com.example.b10contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void getContact(View view) {
        String strID = "";
        String strPhone = "";
        String strName = "";
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int idIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            int hasPhone = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            strID = cursor.getString(idIndex);
            strName = cursor.getString(nameIndex);
            if(cursor.getInt(hasPhone) > 0) {
                Cursor pCur = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{strID}, null
                );
                while (pCur.moveToNext()) {
                    int numberPhone = pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    strPhone = pCur.getString(numberPhone);
                }
            }
            Log.i("//====", "ID:" + strID);
            Log.i("//====", "Name:" + strName);
            Log.i("//====", "Phone:" + strPhone);
        }
    }
}