package com.ruoyi.poem.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.DictUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.poem.mapper.PoemTypeMapper;
import com.ruoyi.poem.domain.PoemType;
import com.ruoyi.poem.service.IPoemTypeService;

/**
 * 诗词类型Service业务层处理
 * 
 * @author cyc
 * @date 2021-04-14
 */
@Service
public class PoemTypeServiceImpl implements IPoemTypeService 
{
    @Autowired
    private PoemTypeMapper poemTypeMapper;

    /**
     * 查询诗词类型
     * 
     * @param dictCode 诗词类型ID
     * @return 诗词类型
     */
    @Override
    public PoemType selectPoemTypeById(Long dictCode)
    {
        return poemTypeMapper.selectPoemTypeById(dictCode);
    }

    /**
     * 查询诗词类型列表
     * 
     * @param poemType 诗词类型
     * @return 诗词类型
     */
    @Override
    public List<PoemType> selectPoemTypeList(PoemType poemType)
    {
        return poemTypeMapper.selectPoemTypeList(poemType);
    }

    /**
     * 新增诗词类型
     * 
     * @param poemType 诗词类型
     * @return 结果
     */
    @Override
    public int insertPoemType(PoemType poemType)
    {
        poemType.setCreateTime(DateUtils.getNowDate());
        poemType.setCreateBy(SecurityUtils.getUsername());
        poemType.setDictValue(poemTypeMapper.selectAutoAdd().toString());
        if(poemTypeMapper.updateAutoAdd()==0){
            return 0;
        }else{
            DictUtils.clearDictCache();
        }
        return poemTypeMapper.insertPoemType(poemType);
    }

    /**
     * 修改诗词类型
     * 
     * @param poemType 诗词类型
     * @return 结果
     */
    @Override
    public int updatePoemType(PoemType poemType)
    {
        poemType.setUpdateTime(DateUtils.getNowDate());
        poemType.setUpdateBy(SecurityUtils.getUsername());

        int row =poemTypeMapper.updatePoemType(poemType);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 批量删除诗词类型
     * 
     * @param dictCodes 需要删除的诗词类型ID
     * @return 结果
     */
    @Override
    public int deletePoemTypeByIds(Long[] dictCodes)
    {
        int row =poemTypeMapper.deletePoemTypeByIds(dictCodes);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }

    /**
     * 删除诗词类型信息
     * 
     * @param dictCode 诗词类型ID
     * @return 结果
     */
    @Override
    public int deletePoemTypeById(Long dictCode)
    {
        int row =poemTypeMapper.deletePoemTypeById(dictCode);
        if (row > 0)
        {
            DictUtils.clearDictCache();
        }
        return row;
    }
}
