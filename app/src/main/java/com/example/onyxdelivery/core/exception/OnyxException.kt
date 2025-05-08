package com.example.onyxdelivery.core.exception


open class OnyxException(message: String) : Exception(message)

class DatabaseException(message: String = "Database Error") : OnyxException(message)
class NetworkException(message: String = "Network Error") : OnyxException(message)
class CacheException(message: String = "Cache Error") : OnyxException(message)