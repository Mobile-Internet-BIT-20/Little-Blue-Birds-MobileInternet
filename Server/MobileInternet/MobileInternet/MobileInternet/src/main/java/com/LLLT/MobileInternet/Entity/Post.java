// Post
/*
 *  @Author : SeeChen Lee, ViHang Tan
 *  @Contact: leeseechen@petalmail.com, tvhang7@gmail.com
 */

/*  让我看看你的帖子是什么 ^_^
 *            .,,       .,:;;iiiiiiiii;;:,,.     .,,
 *          rGB##HS,.;iirrrrriiiiiiiiiirrrrri;,s&##MAS,
 *         r5s;:r3AH5iiiii;;;;;;;;;;;;;;;;iiirXHGSsiih1,
 *            .;i;;s91;;;;;;::::::::::::;;;;iS5;;;ii:
 *          :rsriii;;r::::::::::::::::::::::;;,;;iiirsi,
 *       .,iri;;::::;;;;;;::,,,,,,,,,,,,,..,,;;;;;;;;iiri,,.
 *    ,9BM&,            .,:;;:,,,,,,,,,,,hXA8:            ..,,,.
 *   ,;&@@#r:;;;;;::::,,.   ,r,,,,,,,,,,iA@@@s,,:::;;;::,,.   .;.
 *    :ih1iii;;;;;::::;;;;;;;:,,,,,,,,,,;i55r;;;;;;;;;iiirrrr,..
 *   .ir;;iiiiiiiiii;;;;::::::,,,,,,,:::::,,:;;;iiiiiiiiiiiiri
 *   iriiiiiiiiiiiiiiii;;;::::::::::::::::;;;iiiiiiiiiiiiiiiir;
 *  ,riii;;;;;;;;;;;;;:::::::::::::::::::::::;;;;;;;;;;;;;;iiir.
 *  iri;;;::::,,,,,,,,,,:::::::::::::::::::::::::,::,,::::;;iir:
 * .rii;;::::,,,,,,,,,,,,:::::::::::::::::,,,,,,,,,,,,,::::;;iri
 * ,rii;;;::,,,,,,,,,,,,,:::::::::::,:::::,,,,,,,,,,,,,:::;;;iir.
 * ,rii;;i::,,,,,,,,,,,,,:::::::::::::::::,,,,,,,,,,,,,,::i;;iir.
 * ,rii;;r::,,,,,,,,,,,,,:,:::::,:,:::::::,,,,,,,,,,,,,::;r;;iir.
 * .rii;;rr,:,,,,,,,,,,,,,,:::::::::::::::,,,,,,,,,,,,,:,si;;iri
 *  ;rii;:1i,,,,,,,,,,,,,,,,,,:::::::::,,,,,,,,,,,,,,,:,ss:;iir:
 *  .rii;;;5r,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,sh:;;iri
 *   ;rii;:;51,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.:hh:;;iir,
 *    irii;::hSr,.,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,.,sSs:;;iir:
 *     irii;;:iSSs:.,,,,,,,,,,,,,,,,,,,,,,,,,,,..:135;:;;iir:
 *      ;rii;;:,r535r:...,,,,,,,,,,,,,,,,,,..,;sS35i,;;iirr:
 *       :rrii;;:,;1S3Shs;:,............,:is533Ss:,;;;iiri,
 *        .;rrii;;;:,;rhS393S55hh11hh5S3393Shr:,:;;;iirr:
 *          .;rriii;;;::,:;is1h555555h1si;:,::;;;iirri:.
 *            .:irrrii;;;;;:::,,,,,,,,:::;;;;iiirrr;,
 *               .:irrrriiiiii;;;;;;;;iiiiiirrrr;,.
 *                  .,:;iirrrrrrrrrrrrrrrrri;:.
 *                        ..,:::;;;;:::,,.
 */
package com.LLLT.MobileInternet.Entity;

import java.io.Serializable;
import java.util.List;

public class Post implements Serializable {

    private String        postId;       // 帖子 ID ( 唯一值 )
    private String        holderId;     // 楼主 ID
    private String        postTitle;    // 帖子标题
    private String        postContent;  // 帖子内容

    private List<String>  likeUser;     // 点赞的人 内容为 userId
    private List<String>  postImg;      // 楼主发布的图片 前端将图片转换为二进制流图片

    private Integer       likeNum;      // 点赞的数量

    private List<Comment> commentInfo;  // 评论信息

    // getter 函数
    public String        getPostId()      { return postId;      }
    public String        getHolderId()    { return holderId;    }
    public String        getPostTitle()   { return postTitle;   }
    public String        getPostContent() { return postContent; }
    public List<String>  getLikeUser()    { return likeUser;    }
    public List<String>  getPostImg()     { return postImg;     }
    public Integer       getLikeNum()     { return likeNum;     }
    public List<Comment> getCommentInfo() { return commentInfo; }

    // setter 函数
    public void setPostId(String postId)                  { this.postId      = postId;      }
    public void setHolderId(String holderId)              { this.holderId    = holderId;    }
    public void setPostTitle(String postTitle)            { this.postTitle   = postTitle;   }
    public void setPostContent(String postContent)        { this.postContent = postContent; }
    public void setLikeUser(List<String> likeUser)        { this.likeUser    = likeUser;    }
    public void setPostImg(List<String> postImg)          { this.postImg     = postImg;     }
    public void setLikeNum(Integer likeNum)               { this.likeNum     = likeNum;     }
    public void setCommentInfo(List<Comment> commentInfo) { this.commentInfo = commentInfo; }

    // 构造函数
    public Post() {}
    public Post(String holderId, String postTitle, String postContent) {

        // 一个新帖字必须有的数据
        this.holderId    = holderId;
        this.postTitle   = postTitle;
        this.postContent = postContent;
    }
}
