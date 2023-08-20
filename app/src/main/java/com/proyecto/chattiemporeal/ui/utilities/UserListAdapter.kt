package com.proyecto.chattiemporeal.ui.utilities

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.chattiemporeal.R
import com.proyecto.chattiemporeal.databinding.UserListItemBinding
import com.proyecto.chattiemporeal.ui.activities.MainActivity
import com.squareup.picasso.Picasso

class UserListAdapter(
    private var items: List<UserData>,
    private var name: String // Debería ser el username que proviene del LoginActivity
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    // Función para establecer el nombre
    fun setName(name: String) {
        this.name = name
    }

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: UserListItemBinding = UserListItemBinding.bind(view)
        private var name: String = ""

        fun render(item: UserData) {
            binding.usernameTextView.text = item.username
            Picasso.get().load(item.image).into(binding.userImageView)

            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, MainActivity::class.java)

                intent.putExtra("username", name)
                context.startActivity(intent)
            }
        }

        // Función para establecer el nombre dentro del ViewHolder
        fun setName(name: String) {
            this.name = name
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListAdapter.UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = UserViewHolder(
            inflater.inflate(
                R.layout.user_list_item,
                parent, false
            )
        )
        viewHolder.setName(name) // Pasar el nombre a cada instancia de UserViewHolder
        return viewHolder
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserViewHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount(): Int = items.size
}