package com.luisa.smartnatal.Data

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
        var email: String? = "loisenjoki6@gmail.com"
)
