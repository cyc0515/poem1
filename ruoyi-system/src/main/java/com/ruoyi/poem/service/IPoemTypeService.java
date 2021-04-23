package com.ruoyi.poem.service;

import java.util.List;
import com.ruoyi.poem.domain.PoemType;

/**
 * 诗词类型Service接口
 * 
 * @author cyc
 * @date 2021-04-14
 */
public interface IPoemTypeService 
{
    /**
     * 查询诗词类型
     * 
     * @param dictCode 诗词类型ID
     * @return 诗词类型
     */
    public PoemType selectPoemTypeById(Long dictCode);

    /**
     * 查询诗词类型列表
     * 
     * @param poemType 诗词类型
     * @return 诗词类型集合
     */
    public List<PoemType> selectPoemTypeList(PoemType poemType);

    /**
     * 新增诗词类型
     * 
     * @param poemType 诗词类型
     * @return 结果
     */
    public int insertPoemType(PoemType poemType);

    /**
     * 修改诗词类型
     * 
     * @param poemType 诗词类型
     * @return 结果
     */
    public int updatePoemType(PoemType poemType);

    /**
     * 批量删除诗词类型
     * 
     * @param dictCodes 需要删除的诗词类型ID
     * @return 结果
     */
    public int deletePoemTypeByIds(Long[] dictCodes);

    /**
     * 删除诗词类型信息
     * 
     * @param dictCode 诗词类型ID
     * @return 结果
     */
    public int deletePoemTypeById(Long dictCode);
}
