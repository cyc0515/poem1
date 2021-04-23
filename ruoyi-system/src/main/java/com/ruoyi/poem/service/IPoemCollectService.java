package com.ruoyi.poem.service;

import java.util.List;
import com.ruoyi.poem.domain.PoemCollect;

/**
 * 诗词收藏Service接口
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
public interface IPoemCollectService 
{
    /**
     * 查询诗词收藏
     * 
     * @param id 诗词收藏ID
     * @return 诗词收藏
     */
    public PoemCollect selectPoemCollectById(Long id);

    /**
     * 查询诗词收藏列表
     * 
     * @param poemCollect 诗词收藏
     * @return 诗词收藏集合
     */
    public List<PoemCollect> selectPoemCollectList(PoemCollect poemCollect);

    /**
     * 新增诗词收藏
     * 
     * @param poemCollect 诗词收藏
     * @return 结果
     */
    public int insertPoemCollect(PoemCollect poemCollect);

    /**
     * 修改诗词收藏
     * 
     * @param poemCollect 诗词收藏
     * @return 结果
     */
    public int updatePoemCollect(PoemCollect poemCollect);

    /**
     * 批量删除诗词收藏
     * 
     * @param ids 需要删除的诗词收藏ID
     * @return 结果
     */
    public int deletePoemCollectByIds(Long[] ids);

    /**
     * 删除诗词收藏信息
     * 
     * @param id 诗词收藏ID
     * @return 结果
     */
    public int deletePoemCollectById(Long id);
}
