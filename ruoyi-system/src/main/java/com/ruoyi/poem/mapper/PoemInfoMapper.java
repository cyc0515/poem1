package com.ruoyi.poem.mapper;

import java.util.List;
import com.ruoyi.poem.domain.PoemInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 诗词信息Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
public interface PoemInfoMapper 
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
     * 删除诗词信息
     * 
     * @param id 诗词信息ID
     * @return 结果
     */
    public int deletePoemInfoById(String id);

    /**
     * 批量删除诗词信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePoemInfoByIds(String[] ids);

    public int uploadPoemInfoById(String id);

    public int undoPoemInfoById(String id);

    /**
     * 查询是否存在重复诗词名称
     * @param poemName
     * @return
     */
    public int checkPoemNameUnique(String poemName);

    /**
     * 标签查询
     * @param label
     * @return
     */
    public List<String> selectPoemInfoByLabel1(String label);
    public List<String> selectPoemInfoByLabel2(String label);
    public List<PoemInfo> selectPoemInfoByNameList (@Param("id1") List<String> id1,@Param("id2") List<String> id2);


    public List<PoemInfo> selectPoemInfoByLabel(String[] label);


    /**
     * 根据诗词名称获取诗词ID
     * @param poemName
     * @return
     */
    public Long selectPoemInfoByName(String poemName);
}
