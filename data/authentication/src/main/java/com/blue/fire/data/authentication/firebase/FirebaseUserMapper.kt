package com.blue.fire.data.authentication.firebase

import com.blue.fire.authentication.model.User
import com.google.firebase.auth.FirebaseUser

fun mapFirebaseUserToModel(user: FirebaseUser?): User? {
    user ?: return null
    return User(user.displayName ?: "NA", user.uid)
}