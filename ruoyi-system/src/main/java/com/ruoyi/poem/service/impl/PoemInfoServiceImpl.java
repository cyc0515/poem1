package com.ruoyi.poem.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.poem.mapper.PoemInfoMapper;
import com.ruoyi.poem.domain.PoemInfo;
import com.ruoyi.poem.service.IPoemInfoService;

/**
 * 诗词信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
@Service
public class PoemInfoServiceImpl implements IPoemInfoService 
{
    @Autowired
    private PoemInfoMapper poemInfoMapper;

    /**
     * 查询诗词信息
     * 
     * @param id 诗词信息ID
     * @return 诗词信息
     */
    @Override
    public PoemInfo selectPoemInfoById(String id)
    {
        return poemInfoMapper.selectPoemInfoById(id);
    }

    /**
     * 查询诗词信息列表
     * 
     * @param poemInfo 诗词信息
     * @return 诗词信息
     */
    @Override
    public List<PoemInfo> selectPoemInfoList(PoemInfo poemInfo)
    {
        return poemInfoMapper.selectPoemInfoList(poemInfo);
    }

    /**
     * 新增诗词信息
     * 
     * @param poemInfo 诗词信息
     * @return 结果
     */
    @Override
    public int insertPoemInfo(PoemInfo poemInfo)
    {

        return poemInfoMapper.insertPoemInfo(poemInfo);
    }

    /**
     * 修改诗词信息
     * 
     * @param poemInfo 诗词信息
     * @return 结果
     */
    @Override
    public int updatePoemInfo(PoemInfo poemInfo)
    {
        return poemInfoMapper.updatePoemInfo(poemInfo);
    }

    /**
     * 上传审核诗词信息
     *
     * @param id 诗词信息
     * @return 结果
     */
    @Override
    public int uploadPoemInfo(String id) {
        return poemInfoMapper.uploadPoemInfoById(id);
    }


    /**
     * 撤回审核诗词信息
     *
     * @param id 诗词信息
     * @return 结果
     */
    @Override
    public int undoPoemInfo(String id) {
        return poemInfoMapper.undoPoemInfoById(id);
    }

    /**
     * 查询是否存在重复诗词名称
     * @param poemName
     * @return
     */
    @Override
    public String checkPoemNameUnique(String poemName) {
        int count = poemInfoMapper.checkPoemNameUnique(poemName);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    /**
     * 标签查询
     * @param label
     * @return
     */
    @Override
    public List<PoemInfo> selectPoemInfoByNameList(String[] label) {

        return poemInfoMapper.selectPoemInfoByLabel(label);
//        List<String> id1=poemInfoMapper.selectPoemInfoByLabel1(label);
//        List<String> id2=poemInfoMapper.selectPoemInfoByLabel2(label);
//        return poemInfoMapper.selectPoemInfoByNameList(id1,id2);

    }

    @Override
    public Long selectPoemInfoByName(String poemName) {
        return poemInfoMapper.selectPoemInfoByName(poemName);
    }


    /**
     * 批量删除诗词信息
     * 
     * @param ids 需要删除的诗词信息ID
     * @return 结果
     */
    @Override
    public int deletePoemInfoByIds(String[] ids)
    {
        return poemInfoMapper.deletePoemInfoByIds(ids);
    }

    /**
     * 删除诗词信息信息
     * 
     * @param id 诗词信息ID
     * @return 结果
     */
    @Override
    public int deletePoemInfoById(String id)
    {
        return poemInfoMapper.deletePoemInfoById(id);
    }


}
