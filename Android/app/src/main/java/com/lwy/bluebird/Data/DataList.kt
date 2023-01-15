package com.lwy.bluebird.Data

data class DataList(val data: List<String>)

data class userLogin(val email: String?, val password: String?)

data class userRegister(val email: String?, val password: String?)

data class responseModel(val message: String?)