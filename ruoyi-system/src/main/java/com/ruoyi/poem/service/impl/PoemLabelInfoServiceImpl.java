package com.ruoyi.poem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.poem.mapper.PoemLabelInfoMapper;
import com.ruoyi.poem.domain.PoemLabelInfo;
import com.ruoyi.poem.service.IPoemLabelInfoService;

/**
 * 诗词标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-08
 */
@Service
public class PoemLabelInfoServiceImpl implements IPoemLabelInfoService 
{
    @Autowired
    private PoemLabelInfoMapper poemLabelInfoMapper;

    /**
     * 查询诗词标签
     * 
     * @param id 诗词标签ID
     * @return 诗词标签
     */
    @Override
    public PoemLabelInfo selectPoemLabelInfoById(Long id)
    {
        return poemLabelInfoMapper.selectPoemLabelInfoById(id);
    }

    /**
     * 查询诗词标签列表
     * 
     * @param poemLabelInfo 诗词标签
     * @return 诗词标签
     */
    @Override
    public List<PoemLabelInfo> selectPoemLabelInfoList(PoemLabelInfo poemLabelInfo)
    {
        return poemLabelInfoMapper.selectPoemLabelInfoList(poemLabelInfo);
    }

    /**
     * 新增诗词标签
     * 
     * @param poemLabelInfo 诗词标签
     * @return 结果
     */
    @Override
    public int insertPoemLabelInfo(PoemLabelInfo poemLabelInfo)
    {
        return poemLabelInfoMapper.insertPoemLabelInfo(poemLabelInfo);
    }

    /**
     * 修改诗词标签
     * 
     * @param poemLabelInfo 诗词标签
     * @return 结果
     */
    @Override
    public int updatePoemLabelInfo(PoemLabelInfo poemLabelInfo)
    {
        return poemLabelInfoMapper.updatePoemLabelInfo(poemLabelInfo);
    }

    /**
     * 批量删除诗词标签
     * 
     * @param ids 需要删除的诗词标签ID
     * @return 结果
     */
    @Override
    public int deletePoemLabelInfoByIds(Long[] ids)
    {
        return poemLabelInfoMapper.deletePoemLabelInfoByIds(ids);
    }

    /**
     * 删除诗词标签信息
     * 
     * @param id 诗词标签ID
     * @return 结果
     */
    @Override
    public int deletePoemLabelInfoById(Long id)
    {
        return poemLabelInfoMapper.deletePoemLabelInfoById(id);
    }
}
