package com.proyecto.chattiemporeal.ui.utilities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(val email: String, val password: String, val username: String, val image: String):
    Parcelable