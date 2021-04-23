package com.ruoyi.poem.mapper;

import java.util.List;
import com.ruoyi.poem.domain.PoemCollect;
import com.ruoyi.poem.domain.PoemInfo;

/**
 * 诗词收藏Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
public interface PoemCollectMapper 
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
     * 删除诗词收藏
     * 
     * @param id 诗词收藏ID
     * @return 结果
     */
    public int deletePoemCollectById(Long id);

    /**
     * 批量删除诗词收藏
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePoemCollectByIds(Long[] ids);

    
    /**
     * 批量新增诗词审核
     * 
     * @param poemInfoList 诗词审核列表
     * @return 结果
     */
    public int batchPoemInfo(List<PoemInfo> poemInfoList);
    

    /**
     * 通过诗词收藏ID删除诗词审核信息
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deletePoemInfoByID(Long id);

    List<String> selectPoemCollectByUserName(String username);
}
