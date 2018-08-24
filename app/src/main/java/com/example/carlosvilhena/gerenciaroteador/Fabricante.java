package com.example.carlosvilhena.gerenciaroteador;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Fabricante{
    public static String address = "";
    public static String mac = "";
    public static String ssid = "";
    public static int canal = 0;
    public static String ret = "";
    //public static String potencia = "";

    // Descobre e define variáveis que guardam o IP da rede, o MAC Address do roteador, o SSID da rede e o canal de operação.
    public void descobreDados(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        DhcpInfo info = wifiManager.getDhcpInfo();
        address = Formatter.formatIpAddress(info.gateway);
        mac = wifiInfo.getBSSID().toUpperCase();
        ssid = wifiInfo.getSSID().replaceAll("\"","");
        //potencia = Integer.toString(wifiInfo.getRssi());
        canal = wifiInfo.getFrequency();
    }

    // Acessa uma lista de MAC addresses com seus respectivos fabricantes, identificando a marca do roteador da rede.
    public static String readFromFile(AssetManager asset) {
        try {
            InputStream inputStream = asset.open("macaddresses.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }

            ret=ret.substring(ret.indexOf(mac.substring(0,8))+8, ret.indexOf(mac.substring(0,8))+20);
            if (ret.equals("\tFiberhom\tFi")){
                ret="FiberHome";
            }
            else if (ret.equals("\tTp-LinkT\tTP")){
                ret="TP-LINK";
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }
}