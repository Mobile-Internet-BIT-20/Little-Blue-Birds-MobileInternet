<div align = "center">

# Delete

<hr width = "20%"/>

### Request URL
<hr width = "20%"/>

`http://127.0.0.1:8080/api/user/delete`

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
|UserEmail|String|用户注册时候所使用的邮箱||:heavy_check_mark:|
|UserPassword|String|用户注册时候所设置的密码|由前端进行加密|:heavy_check_mark:|
|UserName|String|用户自行设置的用户名||:heavy_check_mark:|

<br/>
<hr width = "20%"/>

### Return Parameters
<hr width = "20%"/>

|参数名|参数类型|参数说明|
|:---:|:---:|:---:|
|data|String||
|code|String|状态码|
|msg|String|返回信息|

<br/>
<hr width = "20%"/>

### Example
<hr width = "20%"/>

`删除成功`
</div>


```json
{
    "data": null,
    "msg": "Success",
    "code": 2
}
```
---
<div align="right">

###### *Last Modified by [SeeChen](https://github.com/SeeChen/) @ 19-Mar-2023 20:59*
</div>