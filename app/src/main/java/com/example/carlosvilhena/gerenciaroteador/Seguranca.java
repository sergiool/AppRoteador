package com.example.carlosvilhena.gerenciaroteador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class Seguranca extends AppCompatActivity {

    public static CookieManager cookieManager;
    Roteador r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguranca);
        MainActivity.fab.descobreDados(getApplicationContext());

        cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);

        //Requisicao.login();

        Requisicao.wifiTPLink();

        EditText et = findViewById(R.id.editText1);
        et.setText(MainActivity.fab.ssid);
    }

    // Verifica se o usuário digitou a senha nos dois campos, e se elas coincidem. Caso positivo, envia ao roteador as informações fornecidas.
    public void clicouSSID(View view) {

        EditText etSSID = findViewById(R.id.editText1);
        EditText etSenha = findViewById(R.id.editText2);
        EditText etSenha2 = findViewById(R.id.editText3);

        if (etSenha.getText().toString().equals("")||etSenha2.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Digite a senha para continuar.", Toast.LENGTH_LONG).show();
        }
        else if(etSenha.getText().toString().equals(etSenha2.getText().toString())){
            if (MainActivity.marca.equals("FiberHome")) {
                Requisicao.segurancaFiberHome(etSSID.getText().toString(), etSenha.getText().toString());
                r.trocaSSID(etSenha2.getText().toString());
            }
            else if (MainActivity.marca.equals("TP-LINK")){
                // em fase de testes
            }
            Toast.makeText(getApplicationContext(), "Aplicando alterações.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "As senhas digitadas não coincidem. Tente de novo.", Toast.LENGTH_LONG).show();
        }
    }
}