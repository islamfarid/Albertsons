package com.example.albertsons.initialismform.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsons.R
import com.example.albertsons.initialismform.models.ui.InitialismUiModel

class RailsSubItemViewHolder(view: View) :
    BaseInitialismViewHolder<InitialismUiModel.InitialismSubItem>(view) {
    private val tvSubTitle by lazy {
        view.findViewById<TextView>(R.id.tv_sub_title)
    }

    override fun bind(item: InitialismUiModel.InitialismSubItem) {
        // This is not professional as we should consider localization; however for the task simplicity
        tvSubTitle.text = "Lf -> ${item.lf}        ${item.since}         ${item.vars.joinToString("*")}"
    }
}