package com.example.aplicativo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public ProdutoDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long inserir(Produto produto) {
        ContentValues values = new ContentValues();
        values.put("produto", produto.getProduto());
        values.put("unidade", produto.getUnidade());
        values.put("quantidade", produto.getQuantidade());
        return banco.insert("produto", null, values);
    }

    public List<Produto> obterTodos() {
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor = banco.query("produto", new String[]{"id", "produto", "unidade", "quantidade"},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Produto a = new Produto();
            a.setId(cursor.getInt(0));
            a.setProduto(cursor.getString(1));
            a.setUnidade(cursor.getString(2));
            a.setQuantidade(cursor.getString(3));
            produtos.add(a);
        }
        return produtos;
    }

    public void excluir(Produto a){
        banco.delete("produto","id=?", new String[]{a.getId().toString()});
    }

    public void atualizar (Produto produto){
        ContentValues values = new ContentValues();
        values.put("produto", produto.getProduto());
        values.put("unidade", produto.getUnidade());
        values.put("quantidade", produto.getQuantidade());
        banco.update("produto",values, "id=?", new String[]{produto.getId().toString()});
    }
}
