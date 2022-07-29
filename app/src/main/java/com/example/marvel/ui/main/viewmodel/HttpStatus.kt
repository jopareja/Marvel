package com.example.marvel.ui.main.viewmodel

sealed class HttpStatus {
    object IOException: HttpStatus()
    object HTTP400: HttpStatus()
    object HTTP500: HttpStatus()
    object GenericError: HttpStatus()
}
