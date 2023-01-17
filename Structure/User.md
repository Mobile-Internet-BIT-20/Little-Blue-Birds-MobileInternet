<img src = "https://raw.githubusercontent.com/Mobile-Internet-BIT-20/TermProject/main/Element/Logo/loading.png" width = "100px" height = "100px"/>

# 用户
|变量类型|变量名|变量含义|备注|
|:---:|:---:|:---:|:---:|
|String|userId|用户 ID|唯一值 由系统自动生成|
|String|userEmail|用户邮箱|-|
|String|userPass|用户密码|MD5 加密后的字符串|
|String|userPhoto|用户的头像|暂未开发|
|String|userName|用户名|-|
|String|userIntro|用户简介||
|String|dayOfBirth|用户生日|YYYY-MM-DD|
|Integer|userSexIndex|用户性别|为性别下标 暂时为 0: 不显示; 1: 男性; 2: 女性; 3: 保密|
|List\<String\>|userFollower|用户关注的列表|存储用户的 UID|
|List\<String\>|userFollowing|用户的粉丝列表|存储用户的 UID|
|List\<String\>|userLike|用户所点赞的帖子|存储帖子的 PID|
|List\<String\>|userFav|用户收藏的帖子|存储帖子的 PID|
|String|joinDay|用户注册的时间|YYYY-MM-DD|
---
`Last Modified`&nbsp;&nbsp;[SeeChen Lee](https://github.com/SeeChen) @ 18-Jan-2023 01:34
