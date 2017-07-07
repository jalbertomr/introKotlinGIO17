/**
 * Created by Bext on 04/07/2017.
 */
package com.bext.gio

import java.io.FileReader

data class User( val id: Int, val userName: String, val email: String, val role: Role)

enum class Role{
    Admin,
    Regular
}

fun usersFromJSONFile(fileName: String): List<User> {
    //val gson = Gson()
    //return gson.fromJson<List<User>>(FileReader(fileName),object: TypeToken<List<User>>() { }.type)

    if (fileName =="users.json") {
        val user1: User = User(0, "Beto", "beto@kotlin.com", Role.Admin)
        val user2: User = User(1, "Marisol", "marisol@kotlin.com", Role.Regular)
        val user3: User = User(2, "Juan", "juan@kotlin.com", Role.Regular)
        val user4: User = User(3, "otro", "otro@kotlin.edu", Role.Regular)
        val user5: User = User(4, "fulano", "fulano@kotlin.edu", Role.Regular)
        return listOf(user1, user2, user3, user4, user5)
    } else return listOf()
}
