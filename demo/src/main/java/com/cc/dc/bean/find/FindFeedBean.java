package com.cc.dc.bean.find;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by dc on 2017/10/23.
 */
public class FindFeedBean {
    @JSONField(name = "nick_name")
    private String nickName;

    private String avatar;

    @JSONField(name = "is_followed")
    private int followedStatus;

    private String content;

    // 评论数
    private int comments;
    // 点赞数
    private int likes;
    // 转发数
    private int reposts;
    // 阅读/浏览数
    private int views;

    @JSONField(name = "created_at")
    private String createAt;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getFollowedStatus() {
        return followedStatus;
    }

    public void setFollowedStatus(int followedStatus) {
        this.followedStatus = followedStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReposts() {
        return reposts;
    }

    public void setReposts(int reposts) {
        this.reposts = reposts;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
