package com.gvfs.orgs.extensions

import android.widget.ImageView
import coil.load
import com.gvfs.orgs.R

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro) //se for null
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}
