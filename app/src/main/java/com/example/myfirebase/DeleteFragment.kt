package com.example.myfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myfirebase.databinding.FragmentDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteFragment : Fragment(R.layout.fragment_delete) {
    private lateinit var binding: FragmentDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeleteBinding.bind(view)
        binding.apply {
            deleteBtn.setOnClickListener {
                val userName = etusername.text.toString()
                if (userName.isNotEmpty()) {
                    deleteUser(userName)
                }else {
                    Toast.makeText(requireContext(), "Field empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun deleteUser(userName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(userName).removeValue().addOnSuccessListener {
            binding.etusername.text.clear()
            Toast.makeText(requireContext(), "Saccessfully deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

}