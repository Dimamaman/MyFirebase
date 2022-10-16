package com.example.myfirebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myfirebase.databinding.FragmentUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateFragment : Fragment(R.layout.fragment_update) {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)

        binding.apply {
            updateBtn.setOnClickListener {
                val userName = userName.text.toString()
                val firstName = firstName.text.toString()
                val lastName = lastname.text.toString()
                val age = age.text.toString()
                if (userName.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()) {
                    updateUser(userName, firstName, lastName, age)
                }else {
                    Toast.makeText(requireContext(), "Fields empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateUser(userName: String, firstName: String, lastName: String, age: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        val user = mapOf<String, String>(
            "firsName" to firstName,
            "lastName" to lastName,
            "age" to age
        )

        databaseReference.child(userName).updateChildren(user).addOnSuccessListener {
            binding.firstName.text.clear()
            binding.lastname.text.clear()
            binding.userName.text.clear()
            binding.age.text.clear()
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}