package com.ruoyi.poem.service;

import java.util.List;
import com.ruoyi.poem.domain.PoemComment;

/**
 * 诗词评论Service接口
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
public interface IPoemCommentService 
{
    /**
     * 查询诗词评论
     * 
     * @param id 诗词评论ID
     * @return 诗词评论
     */
    public PoemComment selectPoemCommentById(Long id);

    /**
     * 查询诗词评论列表
     * 
     * @param poemComment 诗词评论
     * @return 诗词评论集合
     */
    public List<PoemComment> selectPoemCommentList(PoemComment poemComment);

    /**
     * 新增诗词评论
     * 
     * @param poemComment 诗词评论
     * @return 结果
     */
    public int insertPoemComment(PoemComment poemComment);

    /**
     * 修改诗词评论
     * 
     * @param poemComment 诗词评论
     * @return 结果
     */
    public int updatePoemComment(PoemComment poemComment);

    /**
     * 批量删除诗词评论
     * 
     * @param ids 需要删除的诗词评论ID
     * @return 结果
     */
    public int deletePoemCommentByIds(Long[] ids);

    /**
     * 删除诗词评论信息
     * 
     * @param id 诗词评论ID
     * @return 结果
     */
    public int deletePoemCommentById(Long id);
}
