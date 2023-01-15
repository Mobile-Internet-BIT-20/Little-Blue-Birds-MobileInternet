var host = "http://127.0.0.1:8080"

window.onload = function(){
    let CurrentUrl = window.location.href;
    let postId = CurrentUrl.split("/")[4];

    let isLoggedIn = getCookie("userId").substring(0,1);

    if(isLoggedIn !== "U"){
        window.location.href = "/login";
    }else{
        loadPost(postId);
    }

    $("#commentBtn").click(function(){
        let commentContent = $("#commentBox").val()
        $.post("/api/post/"+postId+"/comment",{
            userId : getCookie("userId"),
            commentContent : commentContent
        },function(data){
            console.log(data)
        })
    })

    $("#likeCount").click(function(){
        $.get(host + "/api/post/"+ postId +"/like",{
            userId : getCookie("userId")
        },function(data){
            if(data === true){
                //get post info
                $.getJSON(host+"/api/post/"+postId,function(data){
                    let likeNum = data.likeNum
                    $("#likeCount").text(likeNum)
                })

            }else{
                console.log("did not liked")
            }
        })
    })

    //调用/postSpace/self.js的功能
    editButtonClick()
}

function loadPost(postId){
    $.getJSON("/api/post/"+postId,function(data){

        $("#postTitle").text(data.postTitle)
        $("#postContent").text(data.postContent)
        $("#publisher").text(data.holderId)
        $("#likeCount").text(data.likeNum)

        let comments = data.commentInfo
        let userName = ""

        let i = 0
        for(i;i<comments.length;i++){

            let oriHtml = $("#commentHistory").html();
            let newHtml = "<div class = 'commentBox'>\n" +
                "                <p class = 'commentUserId' id = '"+ i +""+ comments[i].commentUserId +"' >"+comments[i].commentUserId+"</p>\n" +
                "                <p id= '"+ i +"content"+comments[i].commentUserId+"'>"+comments[i].commentContent+"</p>\n" +
                "            </div>";
            $("#commentHistory").html(oriHtml + newHtml);
        }

    })
}