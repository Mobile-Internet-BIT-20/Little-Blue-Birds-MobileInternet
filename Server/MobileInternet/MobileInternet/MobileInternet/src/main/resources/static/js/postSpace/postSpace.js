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