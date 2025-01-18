package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import java.util.Set;


import com.brother.sdk.lmprinter.Channel;

import com.brother.sdk.lmprinter.PrinterSearcher;

import android.content.Context;
import android.content.Intent;

import android.app.Activity;


import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.brother.sdk.lmprinter.PrinterDriverGenerateResult;
import com.brother.sdk.lmprinter.PrinterDriverGenerator;
import com.brother.sdk.lmprinter.OpenChannelError;

import com.brother.sdk.lmprinter.PrinterDriver;

import com.brother.sdk.lmprinter.PrinterModel;

import com.brother.sdk.lmprinter.setting.PJPrintSettings;
import com.brother.sdk.lmprinter.setting.PJPaperSize;

import com.brother.sdk.lmprinter.PrintError;


import java.io.File;

import com.google.gson.Gson;




public class Hello extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {

            String name = data.getString(0);
            String message = "Hello, " + name;
            callbackContext.success(message);

            return true;
        }
        else if (action.equals("scan")) {

            Context context = this.cordova.getActivity().getApplicationContext();

            ArrayList<Channel> printers = PrinterSearcher.startBluetoothSearch(context).getChannels();

            Gson gson = new Gson();
            String printersJSON = gson.toJson(printers);

            callbackContext.success(printersJSON);

            // BluetoothManager bluetoothManager = context.getSystemService(BluetoothManager.class);
            // BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
            // if (bluetoothAdapter == null) {
            //     // Device doesn't support Bluetooth
            // }

            // if (!bluetoothAdapter.isEnabled()) {
            //     Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //     int REQUEST_ENABLE_BT = 1;
            //     Activity activity = this.cordova.getActivity();
            //     activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            // }

            // Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

            // String pairedDevicesString = "";
            // if (pairedDevices.size() > 0) {
            //     // There are paired devices. Get the name and address of each paired device.
            //     for (BluetoothDevice device : pairedDevices) {
            //         String deviceName = device.getName();
            //         String deviceHardwareAddress = device.getAddress(); // MAC address
            //         pairedDevicesString += deviceName + ' ' + deviceHardwareAddress + ' '; 
            //     }
            // }

            // callbackContext.success(pairedDevicesString);

            return true;
        }
        else if (action.equals("print")) {
            String macAddress = data.getString(0);
            String path = data.getString(1);

            Context context = this.cordova.getActivity().getApplicationContext();
            BluetoothManager bluetoothManager = context.getSystemService(BluetoothManager.class);
            BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();

            Channel channel = Channel.newBluetoothChannel(macAddress, bluetoothAdapter);
            
            PrinterDriverGenerateResult result = PrinterDriverGenerator.openChannel(channel);
            if (result.getError().getCode() != OpenChannelError.ErrorCode.NoError) {
                callbackContext.error("Error - Open Channel: " + result.getError().getCode());
                return true;
            }

            // File dir = context.getExternalFilesDir(null);
            // File dir = context.getFilesDir();
            // File file = new File(dir, path);


            File dir = context.getExternalFilesDir(null);
            File file = new File(dir, path);


            // callbackContext.success(file.getAbsolutePath());


            PrinterDriver printerDriver = result.getDriver();
            PJPrintSettings printSettings = new PJPrintSettings(PrinterModel.PJ_863); // PJ_883 for prod

            printSettings.setPaperSize(PJPaperSize.newPaperSize(PJPaperSize.PaperSize.Letter));
            printSettings.setWorkPath(dir.toString());

            // PrintError printError = printerDriver.printImage(file.toString(), printSettings);
            // PrintError printError = printerDriver.printPDF(file.getAbsolutePath(), printSettings);

            PrintError printError = printerDriver.printPDF(file.getAbsolutePath(), printSettings);

            if (printError.getCode() != PrintError.ErrorCode.NoError) {
                Gson gson = new Gson();
                callbackContext.error("Error - Print: " + gson.toJson(printError));
            }
            else {
                callbackContext.success("Success - Print");
            }         
            
            printerDriver.closeChannel();

            return true;
        }
        else {
            
            return false;

        }
    }
}
