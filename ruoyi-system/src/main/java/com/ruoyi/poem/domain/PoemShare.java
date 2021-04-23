package com.ruoyi.poem.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 诗词分享对象 poem_share
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
public class PoemShare extends BaseEntity
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

    /** 删除标记 */
    private Integer delFlag;

    /** 备注 */
    @Excel(name = "备注")
    private String comment;

    /** 诗词审核信息 */
    private List<PoemInfo> poemInfoList;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("poemId", getPoemId())
            .append("userId", getUserId())
            .append("delFlag", getDelFlag())
            .append("comment", getComment())
            .append("poemInfoList", getPoemInfoList())
            .toString();
    }
}
