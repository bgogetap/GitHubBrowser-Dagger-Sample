package com.sample.githubbrowser.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sample.githubbrowser.home.R
import com.sample.githubbrowser.poweradapter.ItemRenderer
import javax.inject.Inject

class NumberItemRenderer @Inject constructor() : ItemRenderer<NumberItem> {

    override fun layoutRes() = R.layout.number_item

    override fun createView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutRes(), parent, false)
    }

    override fun render(itemView: View, item: NumberItem) {
        (itemView as TextView).text = "${item.number}"
    }
}