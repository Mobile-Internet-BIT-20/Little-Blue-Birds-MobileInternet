<div align = "center">

# Publish Post

<hr width = "20%"/>

### Request URL
<hr width = "20%"/>

`http://127.0.0.1:8080/api/post/publish`

<br/>
<hr width = "20%"/>

### Request Method
<hr width = "20%"/>

- [ ] GET
- [X] POST

<br/>
<hr width = "20%"/>

### Request Parameters
<hr width = "20%"/>

|参数名|参数类型|参数说明|备注|必填|
|:---:|:---:|:---:|:---:|:---:|
|PostTitle|String||帖子的标题|:heavy_check_mark:|
|PostContent|String||帖子的内容|:heavy_check_mark:|
|PostImg|Multipart||用户帖子的配图||

<br/>
<hr width = "20%"/>

### Return Parameters
<hr width = "20%"/>

|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|data|[Post](https://github.com/Mobile-Internet-BIT-20/Little-Blue-Birds-MobileInternet/blob/main/Document/Structure/Post/Post.md)||
|code|String|状态码|
|msg|String|返回信息|

<br/>
<hr width = "20%"/>

### Example
<hr width = "20%"/>

`成功发布帖子`
</div>


```json
{
    "data": {
        "content": "随心所欲地分享故事",
        "postId": "P6417f9ed7bd0d67afaa93aea",
        "holder": "U6415c0c7df8304404f4c91ef",
        "title": "Little Blue Birds",
        "publishTime": "2023.03.20-14:15:09",
        "likeUser": [],
        "likeNum": 0,
        "commentList": [],
        "collectUser": [],
        "collectNum": 0,
        "commentNum": 0,
        "img": [
            "U6415c0c7df8304404f4c91ef/P6417f9ed7bd0d67afaa93aea/48c0efb1-e593-454b-9370-8453783970b0.jpg",
            "U6415c0c7df8304404f4c91ef/P6417f9ed7bd0d67afaa93aea/17d0f229-5004-4215-a6fe-5f73a3c35e42.jpg"
        ]
    },
    "code": 2,
    "msg": "Success"
}
```
---
<div align="right">

###### *Last Modified by [SeeChen](https://github.com/SeeChen/) @ 20-Mar-2023 18:03*
</div>