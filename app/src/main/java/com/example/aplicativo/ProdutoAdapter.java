package com.example.aplicativo;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


import android.widget.BaseAdapter;

public class ProdutoAdapter extends BaseAdapter {

    private List<Produto> produtos;
    private Activity activity;

    public ProdutoAdapter(Activity activity, List<Produto> produtos){
        this.activity = activity;
        this.produtos = produtos;
    }
    @Override
    public int getCount() {
        return produtos.size();
    }
    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }
    @Override
    public long getItemId(int position) {
        return produtos.get(position).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item, viewGroup,false);
        TextView produto = v.findViewById(R.id.txt_produto);
        TextView unidade = v.findViewById(R.id.txt_unidade);
        TextView quantidade = v.findViewById(R.id.txt_quantidade);

        Produto a = produtos.get(i);

        produto.setText(a.getProduto());
        unidade.setText((a.getUnidade()));
        quantidade.setText((a.getQuantidade()));

        return v;
    }


}
