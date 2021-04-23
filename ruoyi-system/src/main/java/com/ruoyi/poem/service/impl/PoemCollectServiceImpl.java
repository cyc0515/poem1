package com.ruoyi.poem.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.poem.mapper.PoemInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.poem.domain.PoemInfo;
import com.ruoyi.poem.mapper.PoemCollectMapper;
import com.ruoyi.poem.domain.PoemCollect;
import com.ruoyi.poem.service.IPoemCollectService;

/**
 * 诗词收藏Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
@Service
public class PoemCollectServiceImpl implements IPoemCollectService 
{
    @Autowired
    private PoemCollectMapper poemCollectMapper;

    @Autowired
    private PoemInfoMapper poemInfoMapper;

    /**
     * 查询诗词收藏
     * 
     * @param id 诗词收藏ID
     * @return 诗词收藏
     */
    @Override
    public PoemCollect selectPoemCollectById(Long id)
    {
        return poemCollectMapper.selectPoemCollectById(id);
    }

    /**
     * 查询诗词收藏列表
     * 
     * @param poemCollect 诗词收藏
     * @return 诗词收藏
     */
    @Override
    public List<PoemCollect> selectPoemCollectList(PoemCollect poemCollect)
    {
        //List<String> poemList = poemCollectMapper.selectPoemCollectByUserName(SecurityUtils.getUsername());

        return poemCollectMapper.selectPoemCollectList(poemCollect);
    }

    /**
     * 新增诗词收藏
     * 
     * @param poemCollect 诗词收藏
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPoemCollect(PoemCollect poemCollect)
    {
        int rows = poemCollectMapper.insertPoemCollect(poemCollect);
        insertPoemInfo(poemCollect);
        return rows;
    }

    /**
     * 修改诗词收藏
     * 
     * @param poemCollect 诗词收藏
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePoemCollect(PoemCollect poemCollect)
    {
        poemCollectMapper.deletePoemInfoByID(poemCollect.getId());
        insertPoemInfo(poemCollect);
        return poemCollectMapper.updatePoemCollect(poemCollect);
    }

    /**
     * 批量删除诗词收藏
     * 
     * @param ids 需要删除的诗词收藏ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePoemCollectByIds(Long[] ids)
    {
        return poemCollectMapper.deletePoemCollectByIds(ids);
    }

    /**
     * 删除诗词收藏信息
     * 
     * @param id 诗词收藏ID
     * @return 结果
     */
    @Override
    public int deletePoemCollectById(Long id)
    {
        poemCollectMapper.deletePoemInfoByID(id);
        return poemCollectMapper.deletePoemCollectById(id);
    }

    /**
     * 新增诗词审核信息
     * 
     * @param poemCollect 诗词收藏对象
     */
    public void insertPoemInfo(PoemCollect poemCollect)
    {
        List<PoemInfo> poemInfoList = poemCollect.getPoemInfoList();
        Long id = poemCollect.getId();
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
                poemCollectMapper.batchPoemInfo(list);
            }
        }
    }
}
