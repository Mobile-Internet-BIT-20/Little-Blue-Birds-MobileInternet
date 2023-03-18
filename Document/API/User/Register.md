<div align = "center">

# Register

</div>
<div align = "center">

### Request URL
</div>

`http://127.0.0.1:8080/api/user/register`

<div align = "center">

### Request Method

- [ ] GET
- [X] Post

### Request Parameters

|参数名|参数类型|参数说明|备注|必填|
|:---:|:---:|:---:|:---:|:---:|
|UserEmail|String|用户注册时候所使用的邮箱||:heavy_check_mark:|
|UserPassword|String|用户注册时候所设置的密码|由前端进行加密|:heavy_check_mark:|

### Return Parameters

|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|data|T|数据|
|code|String|状态码|
|msg|String|返回信息|


### Example

</div>

`注册成功`

```json
{
    "data": {
        "userPassword": "2",
        "userEmail": "1",
        "userId": "userId"
    },
    "msg": "Success",
    "code": 2
}
```
---
<div align="right">

###### *Last Modified by [SeeChen](https://github.com/SeeChen/) @ 18-Mar-2023 21:33*
</div>