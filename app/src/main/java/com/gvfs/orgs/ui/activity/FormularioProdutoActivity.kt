package com.gvfs.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gvfs.orgs.R
import com.gvfs.orgs.dao.ProdutosDao
import com.gvfs.orgs.databinding.ActivityFormularioProdutoBinding
import com.gvfs.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    private val dao = ProdutosDao()
    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()

            dao.adiciona(produtoNovo)
            finish()

        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()

        val campoDescricao =  binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()

        val campoValor = binding.activityFormularioProdutoValor
        val valorEmTexto = campoValor.text.toString()

        val valor = if (valorEmTexto.isEmpty() || valorEmTexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmTexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
    }

}