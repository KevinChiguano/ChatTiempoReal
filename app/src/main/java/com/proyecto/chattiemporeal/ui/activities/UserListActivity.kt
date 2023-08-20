package com.proyecto.chattiemporeal.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyecto.chattiemporeal.R
import com.proyecto.chattiemporeal.databinding.ActivityUserListBinding
import com.proyecto.chattiemporeal.ui.utilities.UserData
import com.proyecto.chattiemporeal.ui.utilities.UserListAdapter

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userName = intent.getStringExtra("username")
        val userList = intent.getParcelableArrayListExtra<UserData>("userList")

        val adapter = userList?.let {
            val userAdapter = UserListAdapter(it, userName ?: "")
            userAdapter.setName(userName ?: "") // Pasar el nombre al adaptador
            userAdapter
        }

        binding.userListRecyclerView.adapter = adapter
    }
}