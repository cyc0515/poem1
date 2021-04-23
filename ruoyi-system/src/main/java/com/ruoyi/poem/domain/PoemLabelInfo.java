package com.ruoyi.poem.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 诗词标签对象 poem_label_info
 * 
 * @author ruoyi
 * @date 2021-04-08
 */
public class PoemLabelInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 诗词ID */
    @Excel(name = "诗词ID")
    private Long poemId;

    /** 标签信息 */
    @Excel(name = "标签信息")
    private String label;

    /** 诗词名称 */
    @Excel(name = "诗词名称")
    private String poemName;

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
    public void setLabel(String label) 
    {
        this.label = label;
    }

    public String getLabel() 
    {
        return label;
    }

    public String getPoemName() {
        return poemName;
    }

    public void setPoemName(String poemName) {
        this.poemName = poemName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("poemId", getPoemId())
            .append("label", getLabel())
            .append("poemName", getPoemName())
            .toString();
    }
}
