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
import com.ruoyi.poem.domain.PoemShare;
import com.ruoyi.poem.service.IPoemShareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 诗词分享Controller
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/poem/share")
public class PoemShareController extends BaseController
{
    @Autowired
    private IPoemShareService poemShareService;

    /**
     * 查询诗词分享列表
     */
    @PreAuthorize("@ss.hasPermi('poem:share:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoemShare poemShare)
    {
        startPage();
        List<PoemShare> list = poemShareService.selectPoemShareList(poemShare);
        return getDataTable(list);
    }

    /**
     * 导出诗词分享列表
     */
    @PreAuthorize("@ss.hasPermi('poem:share:export')")
    @Log(title = "诗词分享", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PoemShare poemShare)
    {
        List<PoemShare> list = poemShareService.selectPoemShareList(poemShare);
        ExcelUtil<PoemShare> util = new ExcelUtil<PoemShare>(PoemShare.class);
        return util.exportExcel(list, "share");
    }

    /**
     * 获取诗词分享详细信息
     */
    @PreAuthorize("@ss.hasPermi('poem:share:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(poemShareService.selectPoemShareById(id));
    }

    /**
     * 新增诗词分享
     */
    @PreAuthorize("@ss.hasPermi('poem:share:add')")
    @Log(title = "诗词分享", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoemShare poemShare)
    {
        return toAjax(poemShareService.insertPoemShare(poemShare));
    }

    /**
     * 修改诗词分享
     */
    @PreAuthorize("@ss.hasPermi('poem:share:edit')")
    @Log(title = "诗词分享", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoemShare poemShare)
    {
        return toAjax(poemShareService.updatePoemShare(poemShare));
    }

    /**
     * 删除诗词分享
     */
    @PreAuthorize("@ss.hasPermi('poem:share:remove')")
    @Log(title = "诗词分享", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(poemShareService.deletePoemShareByIds(ids));
    }
}
