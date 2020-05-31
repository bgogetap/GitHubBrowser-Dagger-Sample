package com.sample.githubbrowser.home.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.githubbrowser.home.databinding.RepoItemBinding

class HomeRepoAdapter(
    private val onRepoSelected: (repoOwner: String, repoName: String) -> Unit
) : RecyclerView.Adapter<HomeRepoAdapter.RepoItemViewHolder>() {

    private val data: MutableList<RepoItem> = mutableListOf()

    fun setRepoItems(repoItems: List<RepoItem>) {
        data.clear()
        data.addAll(repoItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        val binding = RepoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return RepoItemViewHolder(binding, onRepoSelected)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RepoItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class RepoItemViewHolder(
        private val binding: RepoItemBinding,
        onRepoSelected: (repoOwner: String, repoName: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private var repoItem: RepoItem? = null

        init {
            itemView.setOnClickListener {
                repoItem?.let { repo -> onRepoSelected(repo.ownerName, repo.name) }
            }
        }

        fun bind(repoItem: RepoItem) {
            this.repoItem = repoItem
            binding.repoName.text = repoItem.name
            binding.repoDescription.text = repoItem.description
            binding.starsCount.text = "${repoItem.starsCount}"
            binding.forkCount.text = "${repoItem.forkCount}"
        }
    }
}