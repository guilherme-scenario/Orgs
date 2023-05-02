package com.gvfs.orgs.ui.activity

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.gvfs.orgs.R
import com.gvfs.orgs.dao.ProdutosDao
import com.gvfs.orgs.databinding.ActivityDetalheProdutoBinding
import com.gvfs.orgs.databinding.ActivityFormularioProdutoBinding
import com.gvfs.orgs.databinding.FormularioImagemBinding
import com.gvfs.orgs.extensions.tentaCarregarImagem
import com.gvfs.orgs.model.Produto
import com.gvfs.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ProdutoDetalheActivity : AppCompatActivity(R.layout.activity_detalhe_produto) {

    private val dao = ProdutosDao()
    private val binding by lazy {
        ActivityDetalheProdutoBinding.inflate(layoutInflater)
    }

    private var url :String? = null


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Detalhes do produto"

        val produto = intent.getParcelableExtra("produto", Produto::class.java)!!
        binding.detalheProdutoDescricao.setText(produto.descricao)
        binding.detalheProdutoNome.setText(produto.nome)
        binding.detalheProdutoValor.setText(formataParaRealBrasileiro(produto.valor))
        binding.detalheProdutoImagem.tentaCarregarImagem(produto.imagem)
    }

    private fun formataParaRealBrasileiro(valor: BigDecimal): String {
        val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        return formatador.format(valor)
    }

}