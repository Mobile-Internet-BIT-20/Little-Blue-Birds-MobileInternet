window.onload = function() {

    // var p = document.getElementById("password_field");
    //
    // p.onfocus = function() {
    //     document.querySelector(".img_holder").className += " password";
    // }
    //
    // p.onblur = function() {
    //     document.querySelector(".password").className = "img_holder";
    // }
    //
    // document.querySelector("body").className = "";

    $("#submit_button").click(function(){
        login();
    });
}

function login(){
    let userEmail = $("#userEmail").val()
    let userPass = $("#userPass").val()

    let md5Pass = $.md5(userPass)

    let url = "http://127.0.0.1:8080/api/login"

    $.post(url,{
        userEmail : userEmail,
        userPass : md5Pass
    },function(data){
        if(data !== "User Not Exists" && data !== "Wrong Password"){
            document.cookie = "userId=" + data
            document.cookie = "userEmail=" + userEmail
            document.cookie = "userPass=" + md5Pass

            window.location.href = "http://127.0.0.1:8080/home"
        }
    })
}

