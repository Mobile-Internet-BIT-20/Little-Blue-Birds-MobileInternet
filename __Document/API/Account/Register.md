<div align = "center">

# Register

## Description

This API allows a user to register on the system.

## Request

### URL

`http://127.0.0.1:8080/api/user/register`

### Method

`POST`

### Headers

| Name           | Value          |
| :---: | :---: |
| Content-Type  | application/json|

### Parameters

| Name          | Type     | Description       | Required |
| :---: | :---: | :---: | :---: |
| UserEmail     | String   | User's email       | Yes      |
| UserPassword  | String   | User's password    | Yes      |

### Example

<div align = "left">

```json
{
    "UserEmail": "example@example.com",
    "UserPassword": "password"
}
```
</div>

## Response

### Parameters
| Name          | Type     | Description       |
| :---: | :---: | :---: |
| UserEmail     | String   | User's email       |
| UserPassword  | String   | User's password    |

### Example

<div align = "left">

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

</div>

</div>

---

<div align="right">

###### *Last Modified by [SeeChen](https://github.com/SeeChen/) @ 20-Apr-2023 02:32*
</div>