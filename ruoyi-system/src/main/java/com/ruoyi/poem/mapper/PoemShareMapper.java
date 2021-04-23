package com.ruoyi.poem.mapper;

import java.util.List;
import com.ruoyi.poem.domain.PoemShare;
import com.ruoyi.poem.domain.PoemInfo;

/**
 * 诗词分享Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
public interface PoemShareMapper 
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
     * 删除诗词分享
     * 
     * @param id 诗词分享ID
     * @return 结果
     */
    public int deletePoemShareById(Long id);

    /**
     * 批量删除诗词分享
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePoemShareByIds(Long[] ids);

    /**
     * 批量删除诗词审核
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePoemInfoByIDs(Long[] ids);
    
    /**
     * 批量新增诗词审核
     * 
     * @param poemInfoList 诗词审核列表
     * @return 结果
     */
    public int batchPoemInfo(List<PoemInfo> poemInfoList);
    

    /**
     * 通过诗词分享ID删除诗词审核信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deletePoemInfoByID(Long id);
}
