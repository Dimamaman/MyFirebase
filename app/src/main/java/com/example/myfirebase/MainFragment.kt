package com.example.myfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myfirebase.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.apply {
            btnSaveUser.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_saveFragment)

            }

            btnRetrieveUser.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_readFragment)
            }

            btnUpdateUser.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_updateFragment)
            }

            btnDeleteUser.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_deleteFragment)
            }
        }
    }
}