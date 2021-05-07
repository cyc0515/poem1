package com.ruoyi.poem.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.poem.service.IPoemAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.poem.mapper.PoemAuditMapper;
import com.ruoyi.poem.domain.PoemInfo;

/**
 * 诗词审核Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-11
 */
@Service
public class PoemAuditServiceImpl implements IPoemAuditService
{
    @Autowired
    private PoemAuditMapper poemAuditMapper;

    /**
     * 查询诗词审核
     * 
     * @param id 诗词审核ID
     * @return 诗词审核
     */
    @Override
    public PoemInfo selectPoemInfoById(String id)
    {
        return poemAuditMapper.selectPoemInfoById(id);
    }

    /**
     * 查询诗词审核列表
     * 
     * @param poemInfo 诗词审核
     * @return 诗词审核
     */
    @Override
    public List<PoemInfo> selectPoemInfoList(PoemInfo poemInfo)
    {
        poemInfo.setUserName(SecurityUtils.getUsername());
        return poemAuditMapper.selectPoemInfoList(poemInfo);
    }




    /**
     * 通过诗词审核信息
     * 
     * @param id 诗词审核ID
     * @return 结果
     */
    @Override
    public int auditPoemInfoById(Long id)
    {
        return poemAuditMapper.auditPoemInfoById(id, SecurityUtils.getUsername());
    }

    /**
     * 驳回诗词审核信息
     *
     * @param id 诗词审核ID
     * @return 结果
     */
    @Override
    public int reauditPoemInfoById(Long id)
    {
        return poemAuditMapper.reauditPoemInfoById(id,SecurityUtils.getUsername());
    }
}
