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
import com.ruoyi.poem.domain.PoemType;
import com.ruoyi.poem.service.IPoemTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 诗词类型Controller
 * 
 * @author cyc
 * @date 2021-04-14
 */
@RestController
@RequestMapping("/poem/type")
public class PoemTypeController extends BaseController
{
    @Autowired
    private IPoemTypeService poemTypeService;

    /**
     * 查询诗词类型列表
     */
    @PreAuthorize("@ss.hasPermi('poem:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoemType poemType)
    {
        startPage();
        List<PoemType> list = poemTypeService.selectPoemTypeList(poemType);
        return getDataTable(list);
    }

    /**
     * 导出诗词类型列表
     */
    @PreAuthorize("@ss.hasPermi('poem:type:export')")
    @Log(title = "诗词类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PoemType poemType)
    {
        List<PoemType> list = poemTypeService.selectPoemTypeList(poemType);
        ExcelUtil<PoemType> util = new ExcelUtil<PoemType>(PoemType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取诗词类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('poem:type:query')")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable("dictCode") Long dictCode)
    {
        return AjaxResult.success(poemTypeService.selectPoemTypeById(dictCode));
    }

    /**
     * 新增诗词类型
     */
    @PreAuthorize("@ss.hasPermi('poem:type:add')")
    @Log(title = "诗词类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoemType poemType)
    {

        return toAjax(poemTypeService.insertPoemType(poemType));
    }

    /**
     * 修改诗词类型
     */
    @PreAuthorize("@ss.hasPermi('poem:type:edit')")
    @Log(title = "诗词类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoemType poemType)
    {
        return toAjax(poemTypeService.updatePoemType(poemType));
    }

    /**
     * 删除诗词类型
     */
    @PreAuthorize("@ss.hasPermi('poem:type:remove')")
    @Log(title = "诗词类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes)
    {
        return toAjax(poemTypeService.deletePoemTypeByIds(dictCodes));
    }
}
