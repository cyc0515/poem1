package com.ruoyi.poem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.poem.mapper.PoemCommentMapper;
import com.ruoyi.poem.domain.PoemComment;
import com.ruoyi.poem.service.IPoemCommentService;

/**
 * 诗词评论Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@Service
public class PoemCommentServiceImpl implements IPoemCommentService 
{
    @Autowired
    private PoemCommentMapper poemCommentMapper;

    /**
     * 查询诗词评论
     * 
     * @param id 诗词评论ID
     * @return 诗词评论
     */
    @Override
    public PoemComment selectPoemCommentById(Long id)
    {
        return poemCommentMapper.selectPoemCommentById(id);
    }

    /**
     * 查询诗词评论列表
     * 
     * @param poemComment 诗词评论
     * @return 诗词评论
     */
    @Override
    public List<PoemComment> selectPoemCommentList(PoemComment poemComment)
    {
        return poemCommentMapper.selectPoemCommentList(poemComment);
    }

    /**
     * 新增诗词评论
     * 
     * @param poemComment 诗词评论
     * @return 结果
     */
    @Override
    public int insertPoemComment(PoemComment poemComment)
    {
        return poemCommentMapper.insertPoemComment(poemComment);
    }

    /**
     * 修改诗词评论
     * 
     * @param poemComment 诗词评论
     * @return 结果
     */
    @Override
    public int updatePoemComment(PoemComment poemComment)
    {
        return poemCommentMapper.updatePoemComment(poemComment);
    }

    /**
     * 批量删除诗词评论
     * 
     * @param ids 需要删除的诗词评论ID
     * @return 结果
     */
    @Override
    public int deletePoemCommentByIds(Long[] ids)
    {
        return poemCommentMapper.deletePoemCommentByIds(ids);
    }

    /**
     * 删除诗词评论信息
     * 
     * @param id 诗词评论ID
     * @return 结果
     */
    @Override
    public int deletePoemCommentById(Long id)
    {
        return poemCommentMapper.deletePoemCommentById(id);
    }
}
