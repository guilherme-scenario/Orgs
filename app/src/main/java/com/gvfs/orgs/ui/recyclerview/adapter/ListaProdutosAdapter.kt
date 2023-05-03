package com.gvfs.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvfs.orgs.databinding.ProdutoItemBinding
import com.gvfs.orgs.extensions.formataParaMoedaBrasileira
import com.gvfs.orgs.extensions.tentaCarregarImagem
import com.gvfs.orgs.model.Produto
import com.gvfs.orgs.ui.activity.ListaProdutosActivity
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>,
    var quandoClicaNoItem: (produto: Produto) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: Produto
        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
        }
        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = produto.valor.formataParaMoedaBrasileira();
            valor.text = valorEmMoeda

            val visibilidade = if (produto.imagem != null) {
/*                View.VISIBLE //default > sempre visível
                View.INVISIBLE // somente o container visível
                View.GONE // nada visível (perde o seu espaço no layout)*/
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibilidade

            binding.imageView.tentaCarregarImagem(produto.imagem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = produtos.size

    override fun onBindViewHolder(holder: ListaProdutosAdapter.ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
        holder.itemView.setOnClickListener {
            quandoClicaNoItem(produto)
        }
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }


}
