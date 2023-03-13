var defaultHost = "http://127.0.0.1:8080"

window.onload = function(){
    $("#publishBtn").click(function(){
        publishPost();
    })
}

function publishPost(){
    $.post(defaultHost+"/api/post/publish",
        {
        holderId: getCookie("userId"),
        postTitle: $("#title").val(),
        content: $("#content").val()
    },
        function(data){
      console.log(data)
    })
}