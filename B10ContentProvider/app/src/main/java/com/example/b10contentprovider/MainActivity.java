package com.example.b10contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//    private static final String AUTH = "com.phucdepzai";
//    private static final String path = ""
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
    public void insertData(View view) {
        String name = "Phuc";
        String phoneNumber = "0989194097";

        // Step 1: Insert a new raw contact
        ContentValues rawContactValues = new ContentValues();
        Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, rawContactValues);

        // Step 2: Get the raw contact ID
        long rawContactId = ContentUris.parseId(rawContactUri);

        // Step 3: Insert contact data (display name) into ContactsContract.Data
        ContentValues contactValues = new ContentValues();
        contactValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        contactValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        contactValues.put(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, contactValues);

        // Insert the phone number data
        ContentValues phoneValues = new ContentValues();
        phoneValues.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        phoneValues.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        phoneValues.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNumber);
        phoneValues.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, phoneValues);

        Log.e("Insert", "Success");
    }


}