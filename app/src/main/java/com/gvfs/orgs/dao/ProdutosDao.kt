package com.gvfs.orgs.dao

import com.gvfs.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto("Cesta de Frutas", "Abacaxi, mação, bananas, ameixa, uvas e laranja", BigDecimal("19.99"))
        )
    }
}