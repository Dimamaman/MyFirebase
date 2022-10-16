package com.example.myfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myfirebase.databinding.FragmentReadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadFragment : Fragment(R.layout.fragment_read) {
    private lateinit var binding: FragmentReadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReadBinding.bind(view)

        binding.apply {
            readdataBtn.setOnClickListener {
                val userName = etusername.text.toString()

                if (userName.isNotEmpty()) {
                    readUser(userName)
                } else {
                    Toast.makeText(requireContext(), "Field empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun readUser(userName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(userName).get().addOnSuccessListener {
            if (it.exists()) {
                val firstName = it.child("firstName").value
                val lastName = it.child("lastName").value
                val age = it.child("age").value

                binding.apply {
                    etusername.text.clear()
                    tvFirstName.text = firstName.toString()
                    tvLastName.text = lastName.toString()
                    tvAge.text = age.toString()
                }
                Toast.makeText(requireContext(), "Successfully read", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "User dosn't exit", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}