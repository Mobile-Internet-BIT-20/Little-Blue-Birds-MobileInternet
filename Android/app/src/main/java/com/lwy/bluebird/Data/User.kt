package com.lwy.bluebird.Data

data class User (
    var userId: String,
    var userName: String,
    var userPass: String,
    var dayOfBirth: String,
    var userSexIndex: Int,
    var email: String,
    var userPost: List<String>,
    var follower: List<UserPI>,
    var following: List<UserPI>
)
