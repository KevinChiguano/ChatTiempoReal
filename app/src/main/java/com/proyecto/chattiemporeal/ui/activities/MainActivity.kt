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


    private lateinit var database: FirebaseDatabase
    private lateinit var messagesRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("username")

        // Inicializar Firebase
        database = FirebaseDatabase.getInstance()
        messagesRef = database.reference.child("messages")

        // Cargar el fragmento de chat y pasar el nombre de usuario como argumento
        val chatFragment = ChatFragment()
        val bundle = Bundle()
        bundle.putString("username", username)
        chatFragment.arguments = bundle

        FragmentsManager().replaceFragment(
            supportFragmentManager,
            R.id.frm_container, // Usar directamente el ID del contenedor sin binding
            chatFragment
        )
    }

}