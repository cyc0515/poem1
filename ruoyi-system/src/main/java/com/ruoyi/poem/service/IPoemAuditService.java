package com.ruoyi.poem.service;

import java.util.List;
import com.ruoyi.poem.domain.PoemInfo;

/**
 * 诗词审核Service接口
 * 
 * @author ruoyi
 * @date 2021-03-11
 */
public interface IPoemAuditService
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

    public int auditPoemInfoById(Long id);

    public int reauditPoemInfoById(Long id);
}
