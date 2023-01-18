<img src = "https://raw.githubusercontent.com/Mobile-Internet-BIT-20/TermProject/main/Element/Logo/loading.png" width = "100px" height = "100px"/>

# Register API

### Request URL
`http://127.0.0.1:8080/api/userRegister`

### Request Method
- [ ] GET
- [x] POST

### Request Parameters
|参数名|参数类型|参数说明|备注|必填|
|:---:|:---:|:---:|:---:|:---:|
|userEmail|String|用户注册所使用的邮箱||:heavy_check_mark:|
|userPass|String|用户所设置的密码|由前端进行 MD5 加密|:heavy_check_mark:|

### Return Parameter
|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|verifyCode|Integer|状态码|
|responseMessage|String|相关信息|

About Vertify Code: [Status Code](https://github.com/Mobile-Internet-BIT-20/TermProject/tree/main/Document/VerifyCode)

#### Register Success
> 注册成功 返回用户的 UID
```json
{
  "verifyCode"      : 1,
  "responseMessage" : "%UID%"
}
```

#### Email Duplicate
> 注册失败 Email 已被使用
```json
{
  "verifyCode"      : -1,
  "responseMessage" : "EmailExists"
}
```
---
*Last Modified: SeeChen Lee @ 18-Jan-2023 19:58*
