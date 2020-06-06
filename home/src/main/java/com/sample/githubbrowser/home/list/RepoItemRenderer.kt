package com.sample.githubbrowser.home.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.githubbrowser.home.HomeViewModel
import com.sample.githubbrowser.home.R
import com.sample.githubbrowser.home.databinding.RepoItemBinding
import com.sample.githubbrowser.poweradapter.ItemRenderer
import javax.inject.Inject

class RepoItemRenderer @Inject constructor(
    private val homeViewModel: HomeViewModel
) : ItemRenderer<RepoItem> {

    override fun layoutRes() = R.layout.repo_item

    override fun createView(parent: ViewGroup): View {
        val binding = RepoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewBinder = RepoItemViewBinder(binding, homeViewModel)
        binding.root.tag = viewBinder
        return binding.root
    }

    override fun render(itemView: View, item: RepoItem) {
        (itemView.tag as RepoItemViewBinder).bind(item)
    }
}

class RepoItemViewBinder(
    private val binding: RepoItemBinding,
    private val homeViewModel: HomeViewModel
) {

    private var repoItem: RepoItem? = null

    init {
        binding.root.setOnClickListener {
            repoItem?.let { repo -> homeViewModel.onRepoSelected(repo.ownerName, repo.name) }
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