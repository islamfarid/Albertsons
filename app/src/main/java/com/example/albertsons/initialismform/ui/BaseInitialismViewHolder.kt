package com.example.albertsons.initialismform.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsons.initialismform.models.ui.InitialismUiModel

abstract class BaseInitialismViewHolder<T : InitialismUiModel>(view: View) :
    RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
}