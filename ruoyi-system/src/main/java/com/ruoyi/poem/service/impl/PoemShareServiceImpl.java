package com.ruoyi.poem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.poem.domain.PoemInfo;
import com.ruoyi.poem.mapper.PoemShareMapper;
import com.ruoyi.poem.domain.PoemShare;
import com.ruoyi.poem.service.IPoemShareService;

/**
 * 诗词分享Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
@Service
public class PoemShareServiceImpl implements IPoemShareService 
{
    @Autowired
    private PoemShareMapper poemShareMapper;

    /**
     * 查询诗词分享
     * 
     * @param id 诗词分享ID
     * @return 诗词分享
     */
    @Override
    public PoemShare selectPoemShareById(Long id)
    {
        return poemShareMapper.selectPoemShareById(id);
    }

    /**
     * 查询诗词分享列表
     * 
     * @param poemShare 诗词分享
     * @return 诗词分享
     */
    @Override
    public List<PoemShare> selectPoemShareList(PoemShare poemShare)
    {
        return poemShareMapper.selectPoemShareList(poemShare);
    }

    /**
     * 新增诗词分享
     * 
     * @param poemShare 诗词分享
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPoemShare(PoemShare poemShare)
    {
        int rows = poemShareMapper.insertPoemShare(poemShare);
        insertPoemInfo(poemShare);
        return rows;
    }

    /**
     * 修改诗词分享
     * 
     * @param poemShare 诗词分享
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePoemShare(PoemShare poemShare)
    {
        poemShareMapper.deletePoemInfoByID(poemShare.getId());
        insertPoemInfo(poemShare);
        return poemShareMapper.updatePoemShare(poemShare);
    }

    /**
     * 批量删除诗词分享
     * 
     * @param ids 需要删除的诗词分享ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePoemShareByIds(Long[] ids)
    {
        poemShareMapper.deletePoemInfoByIDs(ids);
        return poemShareMapper.deletePoemShareByIds(ids);
    }

    /**
     * 删除诗词分享信息
     * 
     * @param id 诗词分享ID
     * @return 结果
     */
    @Override
    public int deletePoemShareById(Long id)
    {
        poemShareMapper.deletePoemInfoByID(id);
        return poemShareMapper.deletePoemShareById(id);
    }

    /**
     * 新增诗词审核信息
     * 
     * @param poemShare 诗词分享对象
     */
    public void insertPoemInfo(PoemShare poemShare)
    {
        List<PoemInfo> poemInfoList = poemShare.getPoemInfoList();
        Long id = poemShare.getId();
        if (StringUtils.isNotNull(poemInfoList))
        {
            List<PoemInfo> list = new ArrayList<PoemInfo>();
            for (PoemInfo poemInfo : poemInfoList)
            {
                poemInfo.setId(id);
                list.add(poemInfo);
            }
            if (list.size() > 0)
            {
                poemShareMapper.batchPoemInfo(list);
            }
        }
    }
}
