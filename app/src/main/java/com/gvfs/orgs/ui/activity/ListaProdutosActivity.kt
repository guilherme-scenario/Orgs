package com.gvfs.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gvfs.orgs.R
import com.gvfs.orgs.dao.ProdutosDao
import com.gvfs.orgs.databinding.ActivityListaProdutosBinding
import com.gvfs.orgs.model.Produto
import com.gvfs.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos) {

    private val dao = ProdutosDao();
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(binding.root)
        configuraFab()
        configuraRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())

    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            chamaFormulario()
        }
    }

    private fun chamaFormulario() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        recyclerView.adapter = adapter
        adapter.quandoClicaNoItem = {
            val intent = Intent(this, ProdutoDetalheActivity::class.java).apply {
                putExtra(CHAVE_PRODUTO, it)
            }
            startActivity(intent)
        }
    }
}