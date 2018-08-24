package com.example.carlosvilhena.gerenciaroteador;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Requisicao {
    public static HttpURLConnection connection;

    public static void login(){
        String urlFinal = "/goform/webLogin";
        String urlParameters = "User=" + "user" + "&Passwd=" + "user1234";
        conexao(urlFinal, urlParameters);
    }

    public static void segurancaFiberHome(String ssid, String senha){
        String urlFinal = "/goform/APSecurity";
        String urlParameters = "ssidIndex=1&SSIDEnable=enable&bssid_num=1&ssidName=" + ssid +
                "&secure_WMM=1&security_mode=WPAPSK&security_shared_mode=WEP&wep_default_key=1&WEP1=&WEP1Select=" +
                "1&WEP2=&WEP2Select=1&WEP3=&WEP3Select=1&WEP4=&WEP4Select=1&cipher=1&passphrase=" + senha + "&PreAuthentication=1&RadiusServerIP=" +
                "0&RadiusServerPort=1812&RadiusServerSecret=ralink&RadiusServerSessionTimeout=&RadiusServerIdleTimeout=&access_mode=0&addmac=";
        conexao(urlFinal, urlParameters);
    }

    public static void wifiFiberHome(String banda, String canal){
        String urlFinal = "/goform/wirelessBasic";
        String urlParameters = "radiohiddenButton=2&radio_on=1&wirelessmode=4&select_regDomain=0&Fre_band=" + banda + "&sz11aChannel=" + canal + "&guard_inter=0&ISOLATIONEnable=1";
        conexao(urlFinal, urlParameters);
    }

    public static void reboot(){
        String urlFinal = "/goform/reboot";
        String urlParameters = "n/a";
        conexao (urlFinal, urlParameters);
    }

    public static void wifiTPLink(){
        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    URL url = new URL("http://" + "192.168.0.1" + "/cgi?2&2&2");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");

                    String urlParameters2 = "[LAN_WLAN#1,1,0,0,0,0#0,0,0,0,0,0]0,10\nSSID=ViaRealTeste2\nStandard=n\n"+
                            "AutoChannelEnable=1\nChannel=10\nX_TP_Bandwidth=Auto\nEnable=1\nSSIDAdvertisementEnabled=1\n"+
                            "WMMEnable=1\nX_TP_FragmentThreshold=2346\nRegulatoryDomain=BR \n[LAN_WLAN_MULTISSID#1,1,0,0,0,0#0,0,0,0,0,0]1,1\n"+
                            "multiSSIDEnable=0\n[LAN_WLAN_WDSBRIDGE#1,1,0,0,0,0#0,0,0,0,0,0]2,1\nBridgeEnable=0";

                    connection.setUseCaches(true);
                    connection.setReadTimeout(150000);
                    connection.setConnectTimeout(150000);
                    connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setRequestProperty("Transfer-Encoding" , "chunked");
                    connection.setDoOutput(true);
                    DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());

                    dStream.writeBytes(urlParameters2);

                    System.out.println(urlParameters2);
                    System.out.println(connection.getResponseCode());

                    dStream.flush();
                    dStream.close();

                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void conexao(final String urlFinal, final String urlParameters) {
        new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    URL url = new URL("http://" + Fabricante.address + urlFinal);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");

                    connection.setUseCaches(true);
                    connection.setReadTimeout(150000);
                    connection.setConnectTimeout(150000);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setDoOutput(true);
                    DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());

                    dStream.writeBytes(urlParameters);

                    System.out.println(urlParameters);
                    System.out.println(connection.getResponseCode());

                    dStream.flush();
                    dStream.close();

                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
