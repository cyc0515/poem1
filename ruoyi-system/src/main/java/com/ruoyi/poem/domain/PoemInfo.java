package com.ruoyi.poem.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 诗词信息对象 poem_info
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public class PoemInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

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
    @Excel(name = "诗词类别")
    private String poemTypeId;

    /** 上传用户 */
    @Excel(name = "上传用户")
    private String userName;

    /** 诗词上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "诗词上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date poemUploadTime;

    /** 审核状态 0未审核 1已审核 */
    @Excel(name = "审核状态")
    private Integer auditStatus;

    /** 审核人 */
    @Excel(name = "审核人")
    private String auditorId;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Integer praseCount;

    /** 评论数 */
    @Excel(name = "评论数")
    private Integer commentCount;

    /** 收藏数 */
    @Excel(name = "收藏数")
    private Integer collectCount;

    /** 删除标记 */
    private Integer delFlag;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setPoemName(String poemName) 
    {
        this.poemName = poemName;
    }

    public String getPoemName() 
    {
        return poemName;
    }
    public void setPoemDynasty(String poemDynasty) 
    {
        this.poemDynasty = poemDynasty;
    }

    public String getPoemDynasty() 
    {
        return poemDynasty;
    }
    public void setPoemWriterId(String poemWriterId) 
    {
        this.poemWriterId = poemWriterId;
    }

    public String getPoemWriterId() 
    {
        return poemWriterId;
    }
    public void setPoemContent(String poemContent) 
    {
        this.poemContent = poemContent;
    }

    public String getPoemContent() 
    {
        return poemContent;
    }
    public void setPoemAnnotation(String poemAnnotation) 
    {
        this.poemAnnotation = poemAnnotation;
    }

    public String getPoemAnnotation() 
    {
        return poemAnnotation;
    }
    public void setPoemAppreciation(String poemAppreciation) 
    {
        this.poemAppreciation = poemAppreciation;
    }

    public String getPoemAppreciation() 
    {
        return poemAppreciation;
    }
    public void setPoemSentiment(String poemSentiment) 
    {
        this.poemSentiment = poemSentiment;
    }

    public String getPoemSentiment() 
    {
        return poemSentiment;
    }
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setPoemTypeId(String poemTypeId) 
    {
        this.poemTypeId = poemTypeId;
    }

    public String getPoemTypeId() 
    {
        return poemTypeId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setPoemUploadTime(Date poemUploadTime) 
    {
        this.poemUploadTime = poemUploadTime;
    }

    public Date getPoemUploadTime() 
    {
        return poemUploadTime;
    }
    public void setAuditStatus(Integer auditStatus)
    {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditStatus()
    {
        return auditStatus;
    }
    public void setAuditorId(String auditorId)
    {
        this.auditorId = auditorId;
    }

    public String getAuditorId()
    {
        return auditorId;
    }

    public Integer getPraseCount() {
        return praseCount;
    }

    public void setPraseCount(Integer praseCount) {
        this.praseCount = praseCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public void setDelFlag(Integer delFlag)
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("poemName", getPoemName())
            .append("poemDynasty", getPoemDynasty())
            .append("poemWriterId", getPoemWriterId())
            .append("poemContent", getPoemContent())
            .append("poemAnnotation", getPoemAnnotation())
            .append("poemAppreciation", getPoemAppreciation())
            .append("poemSentiment", getPoemSentiment())
            .append("picture", getPicture())
            .append("poemTypeId", getPoemTypeId())
            .append("userName", getUserName())
            .append("poemUploadTime", getPoemUploadTime())
            .append("auditStatus", getAuditStatus())
            .append("auditorId", getAuditorId())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
