
var host = "http://127.0.0.1:8080"


window.onload = function (){
    loadPost();

    let isLoggedIn = getCookie("userId").substring(0,1);

    $("#userBtn").click(function(){
        if(isLoggedIn !== "U"){
            window.location.href = "/login";
        }else{
            window.location.href = "/"+getCookie("userId");
        }
    })

    $("#publishStoryBtn").click(function(){
        if(isLoggedIn !== "U"){
            window.location.href = "/login";
        }else{
            window.location.href = "/publish";
        }
    })
}

function loadPost(){
    let url = "api/allPost"
    $.getJSON(url,{
        requestNum : 10
    },function(data){
        showAllPost(data)
    })
}

function showAllPost(data){
    let isLoggedIn = getCookie("userId").substring(0,1);
    let i = 0
    console.log("hello")
    for(i; i < data.length;i++){
        console.log(data[i].postId)
        let oriHtml = $("#postArea").html();
        let newHtml = "<div class = 'post' >" +
            "            <h1 class = 'postTitle'>"+data[i].postTitle+"</h1>\n" +
            "            <p class = 'publisher'>Publisher:"+ data[i].holderId+"</p>\n" +
            "            <p class = 'postContent'>"+data[i].postContent+"</p>\n" +
            "            <div class = 'likeinfo'>"+data[i].likeNum+"</div>\n" +
            "            <button id = 'L"+data[i].postId+"' class='userInput'>Like</button>\n" +
            "            <button id = 'C"+data[i].postId+"' class='userInput'>Comment</button>\n" +
            "        </div>";

        $("#postArea").html(oriHtml + newHtml);
    }

    $(".userInput").click(function(){
        let id = this.id;
        let option = id.substring(0,1);
        let postId = id.substring(1,id.length);

        if(option === 'C'){

            window.location.href = "/post/"+postId;

        }else if(option === "L"){

            if(isLoggedIn !== "U"){
                window.location.href = "/login";
            }else{
                $.get(host + "/api/post/"+ postId +"/like",{
                    userId : getCookie("userId")
                },function(data){
                    if(data === true){
                        console.log("liked")
                    }else{
                        console.log("did not liked")
                    }
                })
            }
        }else{
            console.log("not a selection")
        }
    })
}



