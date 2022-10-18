package com.example.redballtoy.findyourpet.core.domain.model

import java.io.IOException

class NoMoreAnimalsException(message: String): Exception(message)

class NetworkUnavailableException(message: String="No network available :("):IOException(message)