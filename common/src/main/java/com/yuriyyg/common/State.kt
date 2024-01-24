package com.yuriyyg.common

class State(val status: Status, var message: String? = null) {
     companion object{
         fun success(): State = State(status = Status.SUCCESS)

         fun error(message: String?): State = State(status = Status.ERROR, message = message)

         fun loading(): State= State(status = Status.LOADING)

     }

}


enum class Status {
    SUCCESS, ERROR, LOADING
}