package com.gvfs.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvfs.orgs.databinding.ProdutoItemBinding
import com.gvfs.orgs.extensions.tentaCarregarImagem
import com.gvfs.orgs.model.Produto
import com.gvfs.orgs.ui.activity.ListaProdutosActivity
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>,//proteger => manter a lista imutável no construtor
    var onItemClick: ((Produto) -> Unit)
        ): RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun vincula(produto: Produto) {
            val nome = binding.produtoItemNome
            nome.text = produto.nome
            val descricao = binding.produtoItemDescricao
            descricao.text = produto.descricao
            val valor = binding.produtoItemValor
            val valorEmMoeda: String = formataParaRealBrasileiro(produto.valor)
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

        private fun formataParaRealBrasileiro(valor: BigDecimal): String {
            val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            return formatador.format(valor)
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
        holder.itemView.setOnClickListener { onItemClick(produto) }
    }

    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }


}
