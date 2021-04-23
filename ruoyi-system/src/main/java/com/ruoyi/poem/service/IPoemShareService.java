package com.ruoyi.poem.service;

import java.util.List;
import com.ruoyi.poem.domain.PoemShare;

/**
 * 诗词分享Service接口
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
public interface IPoemShareService 
{
    /**
     * 查询诗词分享
     * 
     * @param id 诗词分享ID
     * @return 诗词分享
     */
    public PoemShare selectPoemShareById(Long id);

    /**
     * 查询诗词分享列表
     * 
     * @param poemShare 诗词分享
     * @return 诗词分享集合
     */
    public List<PoemShare> selectPoemShareList(PoemShare poemShare);

    /**
     * 新增诗词分享
     * 
     * @param poemShare 诗词分享
     * @return 结果
     */
    public int insertPoemShare(PoemShare poemShare);

    /**
     * 修改诗词分享
     * 
     * @param poemShare 诗词分享
     * @return 结果
     */
    public int updatePoemShare(PoemShare poemShare);

    /**
     * 批量删除诗词分享
     * 
     * @param ids 需要删除的诗词分享ID
     * @return 结果
     */
    public int deletePoemShareByIds(Long[] ids);

    /**
     * 删除诗词分享信息
     * 
     * @param id 诗词分享ID
     * @return 结果
     */
    public int deletePoemShareById(Long id);
}
