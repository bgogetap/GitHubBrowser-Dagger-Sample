package com.sample.githubbrowser.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.githubbrowser.home.databinding.RepoItemBinding

class HomeRepoAdapter : RecyclerView.Adapter<HomeRepoAdapter.RepoItemViewHolder>() {

    private val data: MutableList<RepoItem> = mutableListOf()

    fun setRepoItems(repoItems: List<RepoItem>) {
        data.clear()
        data.addAll(repoItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val binding = RepoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return RepoItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class RepoItemViewHolder(private val binding: RepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repoItem: RepoItem) {
            binding.repoName.text = repoItem.name
            binding.repoDescription.text = repoItem.description
            binding.starsCount.text = "${repoItem.starsCount}"
            binding.forkCount.text = "${repoItem.forkCount}"
        }
    }
}