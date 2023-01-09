package com.example.demo.entity;

public class Post {

    private Integer postId;
    private String postContent;

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Integer getPostId() {
        return postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public Post(Integer postId, String postContent) {
        this.postId = postId;
        this.postContent = postContent;
    }

    public Post() {}

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}
