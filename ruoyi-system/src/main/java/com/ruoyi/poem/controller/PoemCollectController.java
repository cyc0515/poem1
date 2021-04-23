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
import com.ruoyi.poem.domain.PoemCollect;
import com.ruoyi.poem.service.IPoemCollectService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 诗词收藏Controller
 * 
 * @author ruoyi
 * @date 2021-03-17
 */
@RestController
@RequestMapping("/poem/collect")
public class PoemCollectController extends BaseController
{
    @Autowired
    private IPoemCollectService poemCollectService;

    /**
     * 查询诗词收藏列表
     */
    @PreAuthorize("@ss.hasPermi('poem:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoemCollect poemCollect)
    {
        startPage();
        List<PoemCollect> list = poemCollectService.selectPoemCollectList(poemCollect);
        return getDataTable(list);
    }

    /**
     * 导出诗词收藏列表
     */
    @PreAuthorize("@ss.hasPermi('poem:collect:export')")
    @Log(title = "诗词收藏", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PoemCollect poemCollect)
    {
        List<PoemCollect> list = poemCollectService.selectPoemCollectList(poemCollect);
        ExcelUtil<PoemCollect> util = new ExcelUtil<PoemCollect>(PoemCollect.class);
        return util.exportExcel(list, "collect");
    }

    /**
     * 获取诗词收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('poem:collect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(poemCollectService.selectPoemCollectById(id));
    }

    /**
     * 新增诗词收藏
     */
    @PreAuthorize("@ss.hasPermi('poem:collect:add')")
    @Log(title = "诗词收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoemCollect poemCollect)
    {

        return toAjax(poemCollectService.insertPoemCollect(poemCollect));
    }

    /**
     * 修改诗词收藏
     */
    @PreAuthorize("@ss.hasPermi('poem:collect:edit')")
    @Log(title = "诗词收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoemCollect poemCollect)
    {
        return toAjax(poemCollectService.updatePoemCollect(poemCollect));
    }

    /**
     * 删除诗词收藏
     */
    @PreAuthorize("@ss.hasPermi('poem:collect:remove')")
    @Log(title = "诗词收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(poemCollectService.deletePoemCollectByIds(ids));
    }
}
