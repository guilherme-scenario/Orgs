package com.gvfs.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.gvfs.orgs.databinding.FormularioImagemBinding
import com.gvfs.orgs.extensions.tentaCarregarImagem

class FormularioImagemDialog(private val context: Context) {

    fun mostraDialog(
        urlPadrao: String? = null,
        quandoImagemCarregada: (imagem: String) -> Unit
    ) {
        FormularioImagemBinding
            .inflate(LayoutInflater.from(context)).apply {
                urlPadrao?.let {
                    formularioImageview.tentaCarregarImagem(it)
                    formularioImagemUrl.setText(it)
                }

                formularioImagemBotaoCarregar.setOnClickListener {
                    val url = formularioImagemUrl.text.toString()
                    formularioImageview.tentaCarregarImagem(url)
                }

                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") { _, _ ->
                        val url = formularioImagemUrl.text.toString()
                        quandoImagemCarregada(url)
                    }
                    .setNegativeButton("Cancelar") {_, _ ->

                    }
                    .show()
            }




    }
}