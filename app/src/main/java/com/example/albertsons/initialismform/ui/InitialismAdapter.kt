package com.example.albertsons.initialismform.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsons.R
import com.example.albertsons.initialismform.extensions.inflate
import com.example.albertsons.initialismform.models.ui.InitialismUiModel

class InitialismAdapter : RecyclerView.Adapter<BaseInitialismViewHolder<InitialismUiModel>>() {

    var items:List<InitialismUiModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(InitialismUiModel.InitialismViewType.values()[viewType]) {
        InitialismUiModel.InitialismViewType.ITEM -> InitialismTitleViewHolder(parent.inflate(R.layout.title_view))
        InitialismUiModel.InitialismViewType.SUB_ITEM -> RailsSubItemViewHolder(parent.inflate(R.layout.sub_item_view))
    } as BaseInitialismViewHolder<InitialismUiModel>

    override fun onBindViewHolder(holder: BaseInitialismViewHolder<InitialismUiModel>, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemViewType(position: Int) = if(items[position] is InitialismUiModel.InitialismItem) InitialismUiModel.InitialismViewType.ITEM .ordinal else InitialismUiModel.InitialismViewType.SUB_ITEM.ordinal

    override fun getItemCount() = items.size
}