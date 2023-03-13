var defaultHost = "http://127.0.0.1:8080";


function editButtonClick(){
    let currentUrl = window.location.href
    let postId = currentUrl.split("/")[4]

    $("#editStoryBtn").click(function(){
        window.location.href = defaultHost+"/post/"+postId+"/edit"
    })
}


