package com.gvfs.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gvfs.orgs.R
import com.gvfs.orgs.model.Produto
import com.gvfs.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        val nome = findViewById<TextView>(R.id.nome);
//        nome.text = "Cesta de Frutas"
//        val descricao = findViewById<TextView>(R.id.descricao);
//        descricao.text = "Laranja, manga e maçã"
//        val valor = findViewById<TextView>(R.id.valor);
//        valor.text = "19.99"
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = listOf(
            Produto(nome = "teste",
            descricao = "teste de descricao",
            valor = BigDecimal("19.99")
            ),
            Produto(nome = "teste 2",
                descricao = "teste de descricao 2",
                valor = BigDecimal("29.99")
            ),
            Produto(nome = "teste 3",
                descricao = "teste de descricao 3",
                valor = BigDecimal("39.99")
            )
        ))
//        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}