package com.example.aplicativo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText produto;
    private EditText unidade;
    private EditText quantidade;
    private ProdutoDAO dao;
    private Produto prod = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        produto = findViewById(R.id.editProduto);
        unidade = findViewById(R.id.editUnidade);
        quantidade = findViewById(R.id.editQuantidade);
        dao = new ProdutoDAO(this);

        Intent it = getIntent();
        if (it.hasExtra("prod")) {
            prod = (Produto) it.getSerializableExtra("prod");
            produto.setText((prod.getProduto()));
            unidade.setText((prod.getUnidade()));
            quantidade.setText(prod.getQuantidade());
        }

    }

    public void salvar(View view) {
        if (prod == null) {
            Produto prod = new Produto();
            prod.setProduto(produto.getText().toString());
            prod.setUnidade(unidade.getText().toString());
            prod.setQuantidade(quantidade.getText().toString());
            long id = dao.inserir(prod);
            Toast.makeText(this, "Produto inserido com Id: " + id, Toast.LENGTH_SHORT).show();
        } else {
            prod.setProduto(produto.getText().toString());
            prod.setUnidade(unidade.getText().toString());
            prod.setQuantidade(quantidade.getText().toString());
            dao.atualizar(prod);
            Toast.makeText(this, "Produto Atualizado", Toast.LENGTH_SHORT).show();
        }
    }

    public void navega(View v){
        Intent navega = new Intent(this, ListarProdutos.class);
        startActivity(navega);
    }
}