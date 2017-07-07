package com.bext.gio

import java.math.BigDecimal
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus

/**
 * Created by Bext on 04/07/2017.
 */


data class Money(val amount: BigDecimal, val currency: String)

fun sendPayment(money: Money, message: String = "mensaje default") {
    println ("Enviando ${money.amount} " + message)
}

fun sum(x : Int, y: Int) = x + y   //single expression function
/* Version 1.0
fun convertToDollars(money: Money) : Money {
    when (money.currency){
      "$" -> return money
      "EUR" -> return Money(money.amount * BigDecimal(1.10), "$")
      else -> throw IllegalArgumentException("${money.currency} tipo de moneda no considerada")
    }
}
*/
/* version 2.0
fun convertToDollars(money: Money) : Money {
    return when (money.currency){
        "$" -> money
        "EUR" -> Money(money.amount * BigDecimal(1.10), "$")
        else -> throw IllegalArgumentException("${money.currency} tipo de moneda no considerada")
    }
}
*/
fun convertToDollars(money: Money) =
    when (money.currency){
        "$" -> money
        "EUR" -> Money(money.amount * BigDecimal(1.10), "$")
        else -> throw IllegalArgumentException("${money.currency} tipo de moneda no considerada")
    }

fun BigDecimal.percent( percentage: BigDecimal ) = this.multiply(percentage).divide(BigDecimal(100))

infix fun Int.percentOf(money: Money) = money.amount.multiply(BigDecimal(this).divide(BigDecimal(100)))

fun javaMoney(money: Money?){  //no acepta nulos por default
    if (money != null)
       println("${money.amount} es valido")
    else
        println( "javaMoney recivio un parametro null")
}

// high order function
fun  findEmails(users: List<User>, predicate: (User) -> (Boolean) ) : List<User> {
    //TODO("Despues...")
    return users.filter(predicate)
}

fun main(args : Array<String> ){
    val tickets = Money(BigDecimal(100), "$")
    val popcorn = tickets.copy(200.bd, "EUR")
    val rara = Money(BigDecimal(123),"Yen")

    val long = 100L

    val bd1 = BigDecimal(100)
    val bd2 = 100.bd         //now with extention properties Alt + Enter -> create extencion property Int.bd
    val porcentage : BigDecimal = BigDecimal(7.23)
    println("popcorn ${popcorn.amount} el ${porcentage}% -> " + popcorn.amount.percent(porcentage))

    7.percentOf(popcorn)
    //it has only one parameter so can convert to infix Alt + Enter -> Add "infix" modifier
    7 percentOf popcorn

    sendPayment(tickets)
    sendPayment(message = "Mensaje al enviar", money= tickets)

    try {
        convertToDollars(tickets)
        convertToDollars(popcorn)
        //convertToDollars(rara)
    } catch (e : IllegalArgumentException){
        e.printStackTrace()
    }

    if (tickets != popcorn){
        println("tickets y popcorn son diferentes")
    }

    val javaMoney = JavaMoney(123, "$")

    val costs = tickets + popcorn

    var train = Money(1000.bd, "$")
    //train = null

    javaMoney(null)

    val users = usersFromJSONFile("users.json")
    users.forEach { println(it) }

    val dotComUsers = users.filter { it.email.endsWith(".com") }
            .sortedBy { it.userName }
            //.map { Pair(it.email,it.userName) }
            .map { it.email to it.userName }
    dotComUsers.forEach { println(it) }

    val dotEduUser = users.filter {it.email.endsWith(".edu")}
            .sortedByDescending { it.userName }
            .first()
    println(dotEduUser)

    // destructure
    val (id, userName, email) = users.filter { it.email.endsWith(".edu") }
            .sortedBy { it.userName }
            .first()
    println(id)

    // parameters not used or needed
    val (id2, _, _) = users.filter { it.email.endsWith(".edu") }
            .sortedBy { it.userName }
            .first()
    println(id2)

    findEmails(users, {value -> value.email.endsWith(".edu")})
            .forEach { println(it) }
    findEmails(users, {it.email.endsWith(".com")})
            .forEach { println(it) }
}

private operator fun  Money.plus(popcorn: Money): BigDecimal { return this.amount + popcorn.amount}

private val  Int.bd: BigDecimal
    get() = BigDecimal(this)
