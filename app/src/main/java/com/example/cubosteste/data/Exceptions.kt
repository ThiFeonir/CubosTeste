package com.example.cubosteste.data

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Data not found in remote data source")

class LocalDataNotFoundException : DataSourceException("Data not found in local data source")