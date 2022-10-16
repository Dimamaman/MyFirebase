package com.example.myfirebase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myfirebase.databinding.FragmentSaveBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SaveFragment : Fragment(R.layout.fragment_save) {
    private lateinit var binding: FragmentSaveBinding
    private lateinit var database: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSaveBinding.bind(view)
        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.apply {
            registerBtn.setOnClickListener {
                val firstName1 = firstName.text.toString()
                val lastName1 = lastName.text.toString()
                val age1 = age.text.toString()
                val userName1 = userName.text.toString()

                if (firstName1.isNotEmpty() && lastName1.isNotEmpty() && age1.isNotEmpty() && userName1.isNotEmpty()) {
                    val user = User(firstName1, lastName1, age1, userName1)
                    database.child(userName1).setValue(user).addOnCompleteListener {
                        firstName.text.clear()
                        lastName.text.clear()
                        age.text.clear()
                        userName.text.clear()
                        Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_SHORT)
                            .show()
                    }.addOnFailureListener {
                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }else {
                    Toast.makeText(requireContext(), "Fields Empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}