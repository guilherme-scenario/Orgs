package com.gvfs.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.gvfs.orgs.R
import com.gvfs.orgs.dao.ProdutosDao
import com.gvfs.orgs.databinding.ActivityListaProdutosBinding
import com.gvfs.orgs.extensions.putExtra
import com.gvfs.orgs.model.Produto
import com.gvfs.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos) {

    private val dao = ProdutosDao();
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos()) {

    }

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
        adapter.onItemClick = { produto ->
            chamaDetalhes(produto)
        }
        recyclerView.adapter = adapter
    }

    private fun chamaDetalhes(produto: Produto) {
        val intent = Intent(this, ProdutoDetalheActivity::class.java)
        intent.putExtra("produto", produto)
        startActivity(intent)
    }
}