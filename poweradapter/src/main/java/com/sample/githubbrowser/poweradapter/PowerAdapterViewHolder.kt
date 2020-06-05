package com.sample.githubbrowser.poweradapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PowerAdapterViewHolder(
    private val itemRenderer: ItemRenderer<RecyclerItem>,
    parent: ViewGroup
) : RecyclerView.ViewHolder(itemRenderer.createView(parent)) {

    fun bind(recyclerItem: RecyclerItem) {
        itemRenderer.render(itemView, recyclerItem)
    }
}