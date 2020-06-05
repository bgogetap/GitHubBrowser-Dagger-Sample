package com.sample.githubbrowser.poweradapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Provider

class PowerAdapter(
    private val renderers: Map<Class<out RecyclerItem>, Provider<ItemRenderer<out RecyclerItem>>>
) : RecyclerView.Adapter<PowerAdapterViewHolder>() {

    private val data = mutableListOf<RecyclerItem>()
    private val viewTypeToRenderer = mutableMapOf<Int, ItemRenderer<out RecyclerItem>>()

    init {
        setHasStableIds(true)
    }

    fun setData(data: List<RecyclerItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PowerAdapterViewHolder {
        val renderer =
            viewTypeToRenderer[viewType] ?: error("No renderer set for viewType: $viewType")
        @Suppress("UNCHECKED_CAST")
        return PowerAdapterViewHolder(renderer as ItemRenderer<RecyclerItem>, parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PowerAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemViewType(position: Int): Int {
        val renderer = renderers[data[position].javaClass]?.get()
            ?: error("No renderer for: ${data[position].javaClass}")
        val viewType = renderer.layoutRes()
        viewTypeToRenderer[viewType] = renderer
        return viewType
    }

    override fun getItemId(position: Int): Long {
        return data[position].getId()
    }
}