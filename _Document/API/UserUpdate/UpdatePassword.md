<div align = "center">

# Update Password

<hr width = "20%"/>

### Request URL
<hr width = "20%"/>

`http://127.0.0.1:8080/api/user/update/password`

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
|UserEmail|String|用户的邮箱||:heavy_check_mark:|
|OldPassword|String|用户的旧密码||:heavy_check_mark:|
|NewPassword|String|用户的新密码||:heavy_check_mark:|

<br/>
<hr width = "20%"/>

### Return Parameters
<hr width = "20%"/>

|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|data|[UserSecurity](https://github.com/Mobile-Internet-BIT-20/Little-Blue-Birds-MobileInternet/blob/main/Document/Structure/User/UserSecurity.md)|数据|
|code|String|状态码|
|msg|String|返回信息|

<br/>
<hr width = "20%"/>

### Example
<hr width = "20%"/>

`更换密码成功`
</div>


```json
{
    "data": {
        "userPassword": "Password",
        "userEmail": "Email",
        "userId": "Id"
    },
    "msg": "Success",
    "code": 2
}
```
---
<div align="right">

###### *Last Modified by [SeeChen](https://github.com/SeeChen/) @ 18-Mar-2023 22:44*
</div>