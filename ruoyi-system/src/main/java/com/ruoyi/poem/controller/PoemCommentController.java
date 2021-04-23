package com.ruoyi.poem.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.poem.domain.PoemComment;
import com.ruoyi.poem.service.IPoemCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 诗词评论Controller
 * 
 * @author ruoyi
 * @date 2021-03-24
 */
@RestController
@RequestMapping("/poem/comment")
public class PoemCommentController extends BaseController
{
    @Autowired
    private IPoemCommentService poemCommentService;

    /**
     * 查询诗词评论列表
     */
    @PreAuthorize("@ss.hasPermi('poem:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoemComment poemComment)
    {
        startPage();
        List<PoemComment> list = poemCommentService.selectPoemCommentList(poemComment);
        return getDataTable(list);
    }

    /**
     * 导出诗词评论列表
     */
    @PreAuthorize("@ss.hasPermi('poem:comment:export')")
    @Log(title = "诗词评论", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PoemComment poemComment)
    {
        List<PoemComment> list = poemCommentService.selectPoemCommentList(poemComment);
        ExcelUtil<PoemComment> util = new ExcelUtil<PoemComment>(PoemComment.class);
        return util.exportExcel(list, "comment");
    }

    /**
     * 获取诗词评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('poem:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(poemCommentService.selectPoemCommentById(id));
    }

    /**
     * 新增诗词评论
     */
    @Log(title = "诗词评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoemComment poemComment)
    {
        return toAjax(poemCommentService.insertPoemComment(poemComment));
    }

    /**
     * 修改诗词评论
     */
    @PreAuthorize("@ss.hasPermi('poem:comment:edit')")
    @Log(title = "诗词评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoemComment poemComment)
    {
        return toAjax(poemCommentService.updatePoemComment(poemComment));
    }

    /**
     * 删除诗词评论
     */
    @PreAuthorize("@ss.hasPermi('poem:comment:remove')")
    @Log(title = "诗词评论", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(poemCommentService.deletePoemCommentByIds(ids));
    }
}
