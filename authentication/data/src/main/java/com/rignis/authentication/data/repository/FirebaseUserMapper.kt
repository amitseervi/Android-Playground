/***
 * Copyright 2024 Amit Seervi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rignis.authentication.data.repository

import com.google.firebase.auth.FirebaseUser
import com.rignis.authentication.domain.model.User

fun mapFirebaseUserToModel(user: FirebaseUser?): User? {
    user ?: return null
    return User(user.displayName ?: "NA", user.uid)
}