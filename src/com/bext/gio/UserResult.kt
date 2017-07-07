package com.bext.gio

import java.math.BigDecimal

sealed class UserResult
data class Success(val users: List<User>): UserResult()
data class Failure(val message: String)  : UserResult()

fun retrieveUsers( fileName : String ): UserResult {
    val users = usersFromJSONFile( fileName)
    if (users.size > 0) {
        return Success(users)
    } else
        return Failure("La Lista viene vacia")

}

fun main(args: Array<String>) {

    val result = retrieveUsers("users.json")
    when (result){
        is Success -> result.users.forEach { println(it.userName) }
        is Failure -> println(result.message)
    }

    val result2 = retrieveUsers("users.jsonMALA")
    when (result2){
        is Success -> result2.users.forEach { println(it.userName) }
        is Failure -> println(result2.message)
    }

    val values = generateSequence(BigDecimal(1)) {
        it * BigDecimal(10)
    }
    values.take(30).forEach { println(it) }

    val users = usersFromJSONFile("users.json").asSequence()    //converted as lazy evaluation
    users.forEach { println(it) }
}