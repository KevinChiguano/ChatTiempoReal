package com.proyecto.chattiemporeal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.proyecto.chattiemporeal.R
import com.proyecto.chattiemporeal.databinding.ActivityMainBinding
import com.proyecto.chattiemporeal.ui.fragments.ChatFragment
import com.proyecto.chattiemporeal.ui.utilities.FragmentsManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var messagesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Firebase
        database = FirebaseDatabase.getInstance()
        messagesRef = database.reference.child("messages")

        // Cargar el fragmento de chat
        FragmentsManager().replaceFragment(
            supportFragmentManager,
            binding.frmContainer.id,
            ChatFragment()
        )
    }

}