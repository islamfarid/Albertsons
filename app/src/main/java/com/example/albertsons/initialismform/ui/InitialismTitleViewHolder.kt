package com.example.albertsons.initialismform.ui

import android.view.View
import android.widget.TextView
import com.example.albertsons.R
import com.example.albertsons.initialismform.models.ui.InitialismUiModel

class InitialismTitleViewHolder(view: View) :
    BaseInitialismViewHolder<InitialismUiModel.InitialismItem>(view) {

    private val tvTitle by lazy {
        view.findViewById<TextView>(R.id.tv_title)
    }

    override fun bind(item: InitialismUiModel.InitialismItem) {
        tvTitle.text = item.sf
    }
}