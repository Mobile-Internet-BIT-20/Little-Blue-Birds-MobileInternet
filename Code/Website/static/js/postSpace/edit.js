var defaultHost = "http://127.0.0.1:8080";

window.onload = function(){

    let postId = window.location.href.split("/")[4];

    loadContent(postId)

    $("#submitBtn").click(function(){
        $.post(defaultHost+"/api/post/"+postId+"/edit",{
            newPostTitle: $("#editTitle").val(),
            newPostContent: $("#editContent").val()
        },function(data){
            console.log(data)
        })
    })
}

function loadContent(postId){
    $.getJSON(defaultHost+"/api/post/"+postId,function(data){
        $("#editTitle").val = $("#editTitle").val(data.postTitle);
        $("#editContent").val = $("#editContent").val(data.postContent);
    })
}
