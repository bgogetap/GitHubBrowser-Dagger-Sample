package com.sample.githubbrowser.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.githubbrowser.details.databinding.ScreenDetailsBinding

class RepoDetailsFragment : Fragment() {

    companion object {
        fun newInstance(repoOwner: String, repoName: String): RepoDetailsFragment {
            val args = Bundle().apply {
                putString("repo_name", repoName)
                putString("repo_owner", repoOwner)
            }
            return RepoDetailsFragment().apply { arguments = args }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ScreenDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
}