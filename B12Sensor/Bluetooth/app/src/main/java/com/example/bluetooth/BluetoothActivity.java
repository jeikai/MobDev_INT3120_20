package com.example.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> discoveredDevicesAdapter;
    private ArrayList<String> discoveredDevicesList;

    private BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                String deviceName = device.getName();
                String deviceAddress = device.getAddress();
                String deviceInfo = deviceName + " - " + deviceAddress;
                discoveredDevicesList.add(deviceInfo);
                discoveredDevicesAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        ListView discoveredDevicesListView = findViewById(R.id.discoveredDevicesListView);
        discoveredDevicesList = new ArrayList<>();
        discoveredDevicesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, discoveredDevicesList);
        discoveredDevicesListView.setAdapter(discoveredDevicesAdapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Check if Bluetooth is supported on the device
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Request necessary permissions if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register the BroadcastReceiver to receive Bluetooth device discovery events
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(bluetoothReceiver, filter);

        // Enable Bluetooth if it is not already enabled
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED)
                return;
            startActivity(enableBtIntent);
        }

        // Start device discovery
        if (bluetoothAdapter.isEnabled()) {
            discoveredDevicesList.clear();
            discoveredDevicesAdapter.notifyDataSetChanged();
            bluetoothAdapter.startDiscovery();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Stop device discovery and unregister the BroadcastReceiver
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        bluetoothAdapter.cancelDiscovery();
        unregisterReceiver(bluetoothReceiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

//        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted, resume the Bluetooth operations
            onResume();
//        } else {
//            // Permission denied, show a message or handle it accordingly
//            Toast.makeText(this, "Permission denied. Cannot proceed.", Toast.LENGTH_SHORT).show();
//            finish();
//        }
    }
}
