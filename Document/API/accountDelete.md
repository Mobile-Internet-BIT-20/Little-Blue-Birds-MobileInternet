<img src = "https://raw.githubusercontent.com/Mobile-Internet-BIT-20/TermProject/main/Element/Logo/loading.png" width = "100px" height = "100px"/>

# Delete Account API

### Request URL
`http://127.0.0.1:8080/api/userDelete`

### Request Method
- [ ] GET
- [x] POST

### Request Parameters
|参数名|参数类型|参数说明|备注|必填|
|:---:|:---:|:---:|:---:|:---:|
|userId|String|当前用户的 UID|非用户填写|:heavy_check_mark:|
|userEmail|String|账号的邮箱||:heavy_check_mark:|
|userPass|String|账号的密码|由前端进行 MD5 加密|:heavy_check_mark:|

### Return Parameter
|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|verifyCode|Integer|状态码|
|responseMessage|String|相关信息|

About Vertify Code: [Status Code](https://github.com/Mobile-Internet-BIT-20/TermProject/tree/main/Document/VerifyCode)

#### Delete Success
> 成功删除账号
```json
{
  "verifyCode"      : 3,
  "responseMessage" : "DeleteSuccess"
}
```

#### Wrong Password
> 删除失败 密码错误
```json
{
  "verifyCode"      : -3,
  "responseMessage" : "WrongPassword"
}
```

#### Wrong Email
> 删除失败 邮箱输入错误
```json
{
  "verifyCode"      : -4,
  "responseMessage" : "WrongEmail"
}
```
---
*Last Modified: SeeChen Lee @ 19-Jan-2023 09:33*
