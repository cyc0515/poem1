package com.ruoyi.poem.mapper;

import java.util.List;
import com.ruoyi.poem.domain.PoemInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 诗词审核Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-11
 */
public interface PoemAuditMapper
{
    /**
     * 查询诗词审核
     * 
     * @param id 诗词审核ID
     * @return 诗词审核
     */
    public PoemInfo selectPoemInfoById(String id);

    /**
     * 查询诗词审核列表
     * 
     * @param poemInfo 诗词审核
     * @return 诗词审核集合
     */
    public List<PoemInfo> selectPoemInfoList(PoemInfo poemInfo);

    public int auditPoemInfoById(@Param("id") Long id, @Param("auditorId") String auditorId);

    public int reauditPoemInfoById(@Param("id") Long id, @Param("auditorId") String auditorId);
}
