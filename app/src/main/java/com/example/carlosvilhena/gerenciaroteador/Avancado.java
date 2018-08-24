package com.example.carlosvilhena.gerenciaroteador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class Avancado extends AppCompatActivity {

    public static CookieManager cookieManager;
    public static String marca = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avancado);

        MainActivity.fab.descobreDados(getApplicationContext());

        cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);

        TextView tv = findViewById(R.id.textView8);
        tv.setText("IP: "+Fabricante.address+"\n\n"+"MAC: "+Fabricante.mac+"\n\n"+"Fabricante: "+Fabricante.readFromFile(getAssets()));

        Requisicao.login();
    }

    // Reinicia o roteador
    public void clicouReiniciar(View view) {
        if (Fabricante.ret.equals("TP-LINK")){
            marca = "TP-LINK";
            Toast.makeText(getApplicationContext(), "As configurações para este roteador ainda estão em desenvolvimento.", Toast.LENGTH_LONG).show();
        }
        else if (Fabricante.ret.equals("FiberHome")) {
            marca = "FiberHome";
            Requisicao.reboot();
            Toast.makeText(getApplicationContext(), "Reiniciando o roteador.", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Roteador não suportado pelo aplicativo.", Toast.LENGTH_LONG).show();
    }
}