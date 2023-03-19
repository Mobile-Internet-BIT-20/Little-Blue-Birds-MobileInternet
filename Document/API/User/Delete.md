<div align = "center">

# Delete

</div>

### Request URL
</div>

`http://127.0.0.1:8080/api/user/delete`

### Request Method

- [ ] GET
- [X] POST

### Request Parameters

|参数名|参数类型|参数说明|备注|必填|
|:---:|:---:|:---:|:---:|:---:|
|UserEmail|String|用户注册时候所使用的邮箱||:heavy_check_mark:|
|UserPassword|String|用户注册时候所设置的密码|由前端进行加密|:heavy_check_mark:|
|UserName|String|用户自行设置的用户名||:heavy_check_mark:|

### Return Parameters

|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|data|String|数据|
|code|String|状态码|
|msg|String|返回信息|


### Example

`注册成功`

```json
{
    "data": null,
    "msg": "Success",
    "code": 2
}
```
---
<div align="right">

###### *Last Modified by [SeeChen](https://github.com/SeeChen/) @ 18-Mar-2023 22:11*
</div>