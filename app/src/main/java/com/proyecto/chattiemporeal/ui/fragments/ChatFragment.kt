package com.proyecto.chattiemporeal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.proyecto.chattiemporeal.R
import com.proyecto.chattiemporeal.databinding.FragmentChatBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var messagesRef: DatabaseReference
    private lateinit var messageList: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializar Firebase
        database = FirebaseDatabase.getInstance()
        messagesRef = database.reference.child("messages")

        messageList = mutableListOf()
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, messageList)
        binding.messageListView.adapter = adapter

        binding.sendButton.setOnClickListener {
            val message = binding.messageEditText.text.toString().trim()
            if (message.isNotEmpty()) {
                val newMessageRef = messagesRef.push()
                newMessageRef.setValue(message)
                binding.messageEditText.text.clear()
            }
        }

        // Escuchar cambios en la base de datos y actualizar el adaptador
        messagesRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(String::class.java)
                if (message != null) {
                    messageList.add(message)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // Implementar si es necesario
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // Implementar si es necesario
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Implementar si es necesario
            }

            override fun onCancelled(error: DatabaseError) {
                // Implementar si es necesario
            }
        })
    }
}