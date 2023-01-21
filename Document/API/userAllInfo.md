<img src = "https://raw.githubusercontent.com/Mobile-Internet-BIT-20/TermProject/main/Element/Logo/loading.png" width = "100px" height = "100px"/>

# Get User All Info API

### Request URL
`http://127.0.0.1:8080/api/user/spacd/all`

### Request Method
- [x] GET
- [ ] POST

### Request Parameters
|参数名|参数类型|参数说明|备注|必填|
|:---:|:---:|:---:|:---:|:---:|
|userId|String|用户的 UID|由前端获取|:heavy_check_mark:|
|userEmail|String|用户登录所使用的邮箱||:heavy_check_mark:|
|userPass|String|用户注册时设置的密码|由前端进行 MD5 加密|:heavy_check_mark:|

### Return Parameter
|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|verifyCode|Integer|状态码|
|responseMessage|String|相关信息|

About Vertify Code: [Status Code](https://github.com/Mobile-Internet-BIT-20/TermProject/tree/main/Document/VerifyCode)

#### Register Success
> 登录成功 返回用户的 UID
```json
{
  "verifyCode"      : 2,
  "responseMessage" : "%UID%"
}
```

#### User Not Exists
> 登录失败 未找到所使用的 Email
```json
{
  "verifyCode"      : -2,
  "responseMessage" : "UserNotExists"
}
```

#### Wrong Password
> 登录失败 密码错误
```json
{
  "verifyCode"      : -3,
  "responseMessage" : "WrongPassword"
}
```
---
*Last Modified: SeeChen Lee @ 18-Jan-2023 20:04*
