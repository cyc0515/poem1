package com.ruoyi.poem.mapper;

import java.util.List;
import com.ruoyi.poem.domain.PoemLabelInfo;

/**
 * 诗词标签Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-08
 */
public interface PoemLabelInfoMapper 
{
    /**
     * 查询诗词标签
     * 
     * @param id 诗词标签ID
     * @return 诗词标签
     */
    public PoemLabelInfo selectPoemLabelInfoById(Long id);

    /**
     * 查询诗词标签列表
     * 
     * @param poemLabelInfo 诗词标签
     * @return 诗词标签集合
     */
    public List<PoemLabelInfo> selectPoemLabelInfoList(PoemLabelInfo poemLabelInfo);

    /**
     * 新增诗词标签
     * 
     * @param poemLabelInfo 诗词标签
     * @return 结果
     */
    public int insertPoemLabelInfo(PoemLabelInfo poemLabelInfo);

    /**
     * 修改诗词标签
     * 
     * @param poemLabelInfo 诗词标签
     * @return 结果
     */
    public int updatePoemLabelInfo(PoemLabelInfo poemLabelInfo);

    /**
     * 删除诗词标签
     * 
     * @param id 诗词标签ID
     * @return 结果
     */
    public int deletePoemLabelInfoById(Long id);

    /**
     * 批量删除诗词标签
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePoemLabelInfoByIds(Long[] ids);
}
