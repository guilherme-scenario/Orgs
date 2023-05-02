package com.gvfs.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gvfs.orgs.R
import com.gvfs.orgs.dao.ProdutosDao
import com.gvfs.orgs.databinding.ActivityListaProdutosBinding
import com.gvfs.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos){

    private val dao = ProdutosDao();
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscTodos())

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        configuraRecyclerView()
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscTodos())
        configuraFab()
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
    }
}