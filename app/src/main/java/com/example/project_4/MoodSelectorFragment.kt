package com.example.project_4.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project_4.R
import com.example.project_4.databinding.FragmentMoodSelectorBinding

class MoodSelectorFragment : Fragment() {

    private var _binding: FragmentMoodSelectorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoodSelectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.happyButton.setOnClickListener {
            navigateWithMood("happy")
        }

        binding.sadButton.setOnClickListener {
            navigateWithMood("sad")
        }

        binding.angryButton.setOnClickListener {
            navigateWithMood("angry")
        }

        binding.boredButton.setOnClickListener {
            navigateWithMood("bored")
        }
    }

    private fun navigateWithMood(mood: String) {
        val bundle = Bundle().apply {
            putString("selectedMood", mood)
        }
        findNavController().navigate(R.id.recipeListFragment, bundle)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
