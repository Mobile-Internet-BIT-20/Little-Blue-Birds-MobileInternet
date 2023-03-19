<div align = "center">

# Update Basic Info

<hr width = "20%"/>

### Request URL
<hr width = "20%"/>

`http://127.0.0.1:8080/api/user/update/basicInfo`

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
|Name|String|用户更改后的名字|||
|Intor|String|用户更改后的个人简介|||
|Sex|String|用户更改后的性别|||
|BirthDay|String|用户更改后的生日日期|||

<br/>
<hr width = "20%"/>

### Return Parameters
<hr width = "20%"/>

|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|data|[UserBasic](https://github.com/Mobile-Internet-BIT-20/Little-Blue-Birds-MobileInternet/blob/main/Document/Structure/User/UserBasic.md)|数据|
|code|String|状态码|
|msg|String|返回信息|

<br/>
<hr width = "20%"/>

### Example
<hr width = "20%"/>

`修改信息成功`
</div>


```json
{
    "data": {
        "name": "小蓝鸟 Little Blue Birds",
        "userId": "U6415c0c7df8304404f4c91ef",
        "likeNum": 0,
        "collectNum": 0,
        "birthDay": "2100-01-01",
        "joinDay": "2023.03.18-21:46:47",
        "intro": "随心所欲地分享故事",
        "postNum": 0,
        "picture": "U6415c0c7df8304404f4c91ef/profile.jpg",
        "followerNum": 0,
        "followingNum": 0,
        "sex": 0
    },
    "code": 2,
    "msg": "Success"
}
```
---
<div align="right">

###### *Last Modified by [SeeChen](https://github.com/SeeChen/) @ 18-Mar-2023 22:05*
</div>