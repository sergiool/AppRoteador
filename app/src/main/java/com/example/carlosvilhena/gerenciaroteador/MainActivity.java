package com.example.carlosvilhena.gerenciaroteador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity{
    public static Fabricante fab = new Fabricante();
    public static String marca = "";
    Roteador r;
    // Inicia a tela já pegando os dados do roteador.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab.descobreDados(getApplicationContext());
        Fabricante.readFromFile(getAssets());
    }

    // Verifica o modelo do roteador e redireciona para a respectiva tela de configuração de segurança.
    public void clicouSeguranca(View view){
        fab.descobreDados(getApplicationContext());
        Fabricante.readFromFile(getAssets());
        if (Fabricante.ret.equals("TP-LINK")){
            marca = "TP-LINK";
            r = new TPLink();
            Toast.makeText(getApplicationContext(), "As configurações para este roteador ainda estão em desenvolvimento.", Toast.LENGTH_LONG).show();
        }
        else if (Fabricante.ret.equals("FiberHome")) {
            marca = "FiberHome";
            r = new FiberHome();
        }
        else
            Toast.makeText(getApplicationContext(), "Roteador não suportado pelo aplicativo.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Seguranca.class);
        startActivity(intent);
    }

    // Verifica o modelo do roteador e redireciona para a respectiva tela de configuração wireless.
    public void clicouWireless(View view) {
        fab.descobreDados(getApplicationContext());
        Fabricante.readFromFile(getAssets());
        if (Fabricante.ret.equals("TP-LINK")){
            marca = "TP-LINK";
            Toast.makeText(getApplicationContext(), "As configurações para este roteador ainda estão em desenvolvimento.", Toast.LENGTH_LONG).show();
        }
        else if (Fabricante.ret.equals("FiberHome")) {
            marca = "FiberHome";
        }
        else
            Toast.makeText(getApplicationContext(), "Roteador não suportado pelo aplicativo.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Wireless.class);
        startActivity(intent);
    }

    // Verifica o modelo do roteador e redireciona para a respectiva tela que exibe o status da rede e permite reiniciar o roteador.
    public void clicouAvancado(View view) {
        fab.descobreDados(getApplicationContext());
        Fabricante.readFromFile(getAssets());
        if (Fabricante.ret.equals("TP-LINK")){
            marca = "TP-LINK";
            Toast.makeText(getApplicationContext(), "As configurações para este roteador ainda estão em desenvolvimento.", Toast.LENGTH_LONG).show();
        }
        else if (Fabricante.ret.equals("FiberHome")) {
            marca = "FiberHome";
        }
        else
            Toast.makeText(getApplicationContext(), "Roteador não suportado pelo aplicativo.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Avancado.class);
        startActivity(intent);
    }
}