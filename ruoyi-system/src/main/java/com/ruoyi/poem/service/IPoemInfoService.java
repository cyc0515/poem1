package com.ruoyi.poem.service;

import java.util.List;
import com.ruoyi.poem.domain.PoemInfo;

/**
 * 诗词信息Service接口
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public interface IPoemInfoService 
{
    /**
     * 查询诗词信息
     * 
     * @param id 诗词信息ID
     * @return 诗词信息
     */
    public PoemInfo selectPoemInfoById(String id);

    /**
     * 查询诗词信息列表
     * 
     * @param poemInfo 诗词信息
     * @return 诗词信息集合
     */
    public List<PoemInfo> selectPoemInfoList(PoemInfo poemInfo);


    public List<PoemInfo> selectPoemInfoListIndex(PoemInfo poemInfo);
    /**
     * 新增诗词信息
     * 
     * @param poemInfo 诗词信息
     * @return 结果
     */
    public int insertPoemInfo(PoemInfo poemInfo);

    /**
     * 修改诗词信息
     * 
     * @param poemInfo 诗词信息
     * @return 结果
     */
    public int updatePoemInfo(PoemInfo poemInfo);

    /**
     * 批量删除诗词信息
     * 
     * @param ids 需要删除的诗词信息ID
     * @return 结果
     */
    public int deletePoemInfoByIds(String[] ids);

    /**
     * 删除诗词信息信息
     * 
     * @param id 诗词信息ID
     * @return 结果
     */
    public int deletePoemInfoById(String id);

    public int uploadPoemInfo(String id);

    public int undoPoemInfo(String id);

    /**
     * 校验诗词名称是否唯一
     * @param poemName
     * @return
     */
    public String checkPoemNameUnique(String poemName);

    public List<PoemInfo> selectPoemInfoByNameList(String[] label);

    /**
     * 根据诗词名称获取诗词ID
     * @param poemName
     * @return
     */
    public Long selectPoemInfoByName(String poemName);
}
