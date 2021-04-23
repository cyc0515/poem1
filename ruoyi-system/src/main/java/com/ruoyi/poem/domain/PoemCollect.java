package com.ruoyi.poem.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 诗词收藏对象 poem_collect
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
public class PoemCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 诗词ID */
    @Excel(name = "诗词ID")
    private Long poemId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 删除标记 */
    private Integer delFlag;

    /** 备注 */
    @Excel(name = "备注")
    private String comment;

    /** 诗词名称 */
    @Excel(name = "诗词名称")
    private String poemName;

    /** 诗词创作朝代 */
    @Excel(name = "诗词创作朝代")
    private String poemDynasty;

    /** 诗词作者 */
    @Excel(name = "诗词作者")
    private String poemWriterId;

    /** 诗词内容 */
    @Excel(name = "诗词内容")
    private String poemContent;

    /** 诗词注释 */
    @Excel(name = "诗词注释")
    private String poemAnnotation;

    /** 诗词赏析 */
    @Excel(name = "诗词赏析")
    private String poemAppreciation;

    /** 诗词感悟 */
    @Excel(name = "诗词感悟")
    private String poemSentiment;

    /** 图片 */
    @Excel(name = "图片")
    private String picture;

    /** 诗词类别ID */
    @Excel(name = "诗词类别ID")
    private String poemTypeId;

    /** 诗词上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "诗词上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date poemUploadTime;

    /** 用户账号 */
    @Excel(name = "用户账号")
    private String userName;

    /** 诗词审核信息 */
    private List<PoemInfo> poemInfoList;
    private List<User> userList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPoemId(Long poemId) 
    {
        this.poemId = poemId;
    }

    public Long getPoemId() 
    {
        return poemId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }

    public List<PoemInfo> getPoemInfoList()
    {
        return poemInfoList;
    }

    public void setPoemInfoList(List<PoemInfo> poemInfoList)
    {
        this.poemInfoList = poemInfoList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getPoemName() {
        return poemName;
    }

    public void setPoemName(String poemName) {
        this.poemName = poemName;
    }

    public String getPoemDynasty() {
        return poemDynasty;
    }

    public void setPoemDynasty(String poemDynasty) {
        this.poemDynasty = poemDynasty;
    }

    public String getPoemWriterId() {
        return poemWriterId;
    }

    public void setPoemWriterId(String poemWriterId) {
        this.poemWriterId = poemWriterId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPoemContent() {
        return poemContent;
    }

    public void setPoemContent(String poemContent) {
        this.poemContent = poemContent;
    }

    public String getPoemAnnotation() {
        return poemAnnotation;
    }

    public void setPoemAnnotation(String poemAnnotation) {
        this.poemAnnotation = poemAnnotation;
    }

    public String getPoemAppreciation() {
        return poemAppreciation;
    }

    public void setPoemAppreciation(String poemAppreciation) {
        this.poemAppreciation = poemAppreciation;
    }

    public String getPoemSentiment() {
        return poemSentiment;
    }

    public void setPoemSentiment(String poemSentiment) {
        this.poemSentiment = poemSentiment;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPoemTypeId() {
        return poemTypeId;
    }

    public void setPoemTypeId(String poemTypeId) {
        this.poemTypeId = poemTypeId;
    }

    public Date getPoemUploadTime() {
        return poemUploadTime;
    }

    public void setPoemUploadTime(Date poemUploadTime) {
        this.poemUploadTime = poemUploadTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("poemId", getPoemId())
            .append("userId", getUserId())
            .append("delFlag", getDelFlag())
            .append("comment", getComment())
            .append("poemInfoList", getPoemInfoList())
            .append("userList",getUserList())
            .append("poemName", getPoemName())
            .append("poemDynasty", getPoemDynasty())
            .append("poemWriterId", getPoemWriterId())
            .append("userName", getUserName())
            .append("poemContent", getPoemContent())
            .append("poemAnnotation", getPoemAnnotation())
            .append("poemAppreciation", getPoemAppreciation())
            .append("poemSentiment", getPoemSentiment())
            .append("picture", getPicture())
            .append("poemTypeId", getPoemTypeId())
            .append("poemUploadTime", getPoemUploadTime())
            .toString();
    }
}
