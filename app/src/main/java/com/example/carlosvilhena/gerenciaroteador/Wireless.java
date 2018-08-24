package com.example.carlosvilhena.gerenciaroteador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class Wireless extends AppCompatActivity {

    public static CookieManager cookieManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wireless);
        MainActivity.fab.descobreDados(getApplicationContext());

        cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);

        Requisicao.login();
        canalWifi();
        Spinner spinner1 = findViewById(R.id.spinner);
        spinner1.setSelection(canalWifi() + 1);
        TextView tv = findViewById(R.id.textView17);
        String canalatual = Integer.toString(canalWifi()+1);
        tv.setText("Em uso atualmente: canal " + canalatual);
    }

    // Pega as informações fornecidas pelo usuário e as envia ao roteador.
    public void clicouCanal(View view) {
        Spinner spinner = findViewById(R.id.spinner);
        String canal = Integer.toString(spinner.getSelectedItemPosition());
        Spinner spinner2 = findViewById(R.id.spinner2);
        String banda = Integer.toString(spinner2.getSelectedItemPosition());

        if (MainActivity.marca.equals("FiberHome")) {
            Requisicao.wifiFiberHome(banda, canal);
            Toast.makeText(getApplicationContext(), "Aplicando alterações.", Toast.LENGTH_LONG).show();
        }
        else if (MainActivity.marca.equals("TP-LINK")){
            // em fase de testes
        }
    }

    // Verifica qual canal wireless está sendo utilizado pela rede em que o celular está conectado
    public int canalWifi() {
        int i = 0;
        switch (Fabricante.canal) {
            case 2412:
                i = 0;
                break;
            case 2417:
                i = 1;
                break;
            case 2422:
                i = 2;
                break;
            case 2427:
                i = 3;
                break;
            case 2432:
                i = 4;
                break;
            case 2437:
                i = 5;
                break;
            case 2442:
                i = 6;
                break;
            case 2447:
                i = 7;
                break;
            case 2452:
                i = 8;
                break;
            case 2457:
                i = 9;
                break;
            case 2462:
                i = 10;
                break;
            case 2467:
                i = 11;
                break;
            case 2472:
                i = 12;
                break;
            case 2477:
                i = 13;
                break;
        }
        return i;
    }
}