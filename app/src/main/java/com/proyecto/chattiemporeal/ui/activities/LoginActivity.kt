package com.proyecto.chattiemporeal.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import com.proyecto.chattiemporeal.databinding.ActivityLoginBinding
import com.proyecto.chattiemporeal.ui.utilities.UserData
import kotlinx.parcelize.Parcelize

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInWithEmailAndPassword(email, password)
            } else {
                Toast.makeText(this, "Por favor, ingresa correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        val matchedUser = userList.find { it.email == email && it.password == password }

        if (matchedUser != null) {
            val username = matchedUser.username
            val intent = Intent(this, UserListActivity::class.java)
            intent.putExtra("username", username)
            intent.putParcelableArrayListExtra("userList", ArrayList(userList))
            startActivity(intent)
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }

    private val userList = listOf(
        UserData("andres@gmail.com", "pass1", "Andres","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_Q0LnflfIEOFyVezV5_TdxCUrkjjce4R12PCjw_xfNlQLLnx2Sogj4H4XF0AjSQx42TI&usqp=CAU"),
        UserData("franklin@gmail.com", "pass2", "Franklin", "https://cdn.icon-icons.com/icons2/2468/PNG/512/user_kids_avatar_user_profile_icon_149314.png"),
        UserData("wendy@gmail.com", "pass3", "Wendy", "https://cdn.icon-icons.com/icons2/1999/PNG/512/avatar_people_person_profile_user_woman_icon_123359.png"),
        UserData("stalyn@gmail.com", "pass4", "Stalyn", "https://cdn.icon-icons.com/icons2/1999/PNG/512/avatar_man_people_person_profile_user_icon_123385.png"),
        UserData("kevin@gmail.com", "pass5", "Kevin", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpYRl15UVJC5M-i3WWga3AzehkcHnDt4uQTJoyulKMvj8W21f5MXkoFZsroUcWWO2DVG8&usqp=CAU")
    )


}