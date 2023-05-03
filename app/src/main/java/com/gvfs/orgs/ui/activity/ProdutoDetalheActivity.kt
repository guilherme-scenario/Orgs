package com.gvfs.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gvfs.orgs.R
import com.gvfs.orgs.dao.ProdutosDao
import com.gvfs.orgs.databinding.ActivityDetalheProdutoBinding
import com.gvfs.orgs.extensions.formataParaMoedaBrasileira
import com.gvfs.orgs.extensions.tentaCarregarImagem
import com.gvfs.orgs.model.Produto

class ProdutoDetalheActivity : AppCompatActivity(R.layout.activity_detalhe_produto) {

    private val dao = ProdutosDao()
    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    private var url :String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<Produto>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            preencheCampos(produtoCarregado)
        } ?: finish()
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            detalheProdutoImagem.tentaCarregarImagem(produtoCarregado.imagem)
            detalheProdutoNome.text = produtoCarregado.nome
            detalheProdutoDescricao.text = produtoCarregado.descricao
            detalheProdutoValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }

}