package com.ruoyi.poem.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 诗词评论对象 poem_comment
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
public class PoemComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 诗词ID */
    @Excel(name = "诗词ID")
    private Long poemId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 评论内容 */
    @Excel(name = "评论内容")
    private String content;

    /** 相关用户 */
    @Excel(name = "相关用户")
    private Long withuser;

    /** 公开状态 */
    @Excel(name = "公开状态")
    private Integer pubilcStatus;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 删除标记 */
    private Integer delFlag;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long praseCount;

    /** 点赞数 */
    @Excel(name = "评论时间")
    private Date createtime;

    /** 点赞数 */
    @Excel(name = "更新时间")
    private Date updatetime;

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

    /** 诗词类别ID */
    @Excel(name = "诗词类别ID")
    private String poemTypeId;

    /** 上传用户 */
    @Excel(name = "上传用户")
    private String userName;


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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setWithuser(Long withuser) 
    {
        this.withuser = withuser;
    }

    public Long getWithuser() 
    {
        return withuser;
    }
    public void setPubilcStatus(Integer pubilcStatus) 
    {
        this.pubilcStatus = pubilcStatus;
    }

    public Integer getPubilcStatus() 
    {
        return pubilcStatus;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setDelFlag(Integer delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag() 
    {
        return delFlag;
    }
    public void setPraseCount(Long praseCount) 
    {
        this.praseCount = praseCount;
    }

    public Long getPraseCount() 
    {
        return praseCount;
    }
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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

    public String getPoemContent() {
        return poemContent;
    }
    public void setPoemContent(String poemContent) {
        this.poemContent = poemContent;
    }

    public String getPoemTypeId() {
        return poemTypeId;
    }
    public void setPoemTypeId(String poemTypeId) {
        this.poemTypeId = poemTypeId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("poemId", getPoemId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("withuser", getWithuser())
            .append("pubilcStatus", getPubilcStatus())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("praseCount", getPraseCount())
            .append("createtime", getCreatetime())
            .append("updatetime", getUpdatetime())
            .append("poemName", getPoemName())
            .append("poemDynasty", getPoemDynasty())
            .append("poemWriterId", getPoemWriterId())
            .append("poemContent", getPoemContent())
            .append("poemTypeId", getPoemTypeId())
            .append("userName", getUserName())
            .toString();
    }
}
