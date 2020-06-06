package com.sample.githubbrowser.home.list

import com.sample.githubbrowser.poweradapter.RecyclerItem

data class NumberItem(val number: Int) : RecyclerItem {
    override fun getId(): Long {
        return number.toLong()
    }
}