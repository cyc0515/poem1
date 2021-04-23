package com.ruoyi.poem.controller;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.poem.service.IPoemInfoService;
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
import com.ruoyi.poem.domain.PoemLabelInfo;
import com.ruoyi.poem.service.IPoemLabelInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 诗词标签Controller
 * 
 * @author ruoyi
 * @date 2021-04-08
 */
@RestController
@RequestMapping("/poem/label")
public class PoemLabelInfoController extends BaseController
{
    @Autowired
    private IPoemLabelInfoService poemLabelInfoService;

    @Autowired
    private IPoemInfoService poemInfoService;

    /**
     * 查询诗词标签列表
     */
    @PreAuthorize("@ss.hasPermi('poem:label:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoemLabelInfo poemLabelInfo)
    {
        startPage();
        List<PoemLabelInfo> list = poemLabelInfoService.selectPoemLabelInfoList(poemLabelInfo);
        return getDataTable(list);
    }

    /**
     * 导出诗词标签列表
     */
    @PreAuthorize("@ss.hasPermi('poem:label:export')")
    @Log(title = "诗词标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PoemLabelInfo poemLabelInfo)
    {
        List<PoemLabelInfo> list = poemLabelInfoService.selectPoemLabelInfoList(poemLabelInfo);
        ExcelUtil<PoemLabelInfo> util = new ExcelUtil<PoemLabelInfo>(PoemLabelInfo.class);
        return util.exportExcel(list, "label");
    }

    /**
     * 获取诗词标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('poem:label:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(poemLabelInfoService.selectPoemLabelInfoById(id));
    }

    /**
     * 新增诗词标签
     */
    @PreAuthorize("@ss.hasPermi('poem:label:add')")
    @Log(title = "诗词标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoemLabelInfo poemLabelInfo)
    {
        if (!UserConstants.NOT_UNIQUE.equals(poemInfoService.checkPoemNameUnique(poemLabelInfo.getPoemName())))
        {
            return AjaxResult.error("新增失败，获取诗词'" + poemLabelInfo.getPoemName() + "'失败，该名称诗词信息不存在！");
        }
        poemLabelInfo.setPoemId(poemInfoService.selectPoemInfoByName(poemLabelInfo.getPoemName()));
        return toAjax(poemLabelInfoService.insertPoemLabelInfo(poemLabelInfo));
    }

    /**
     * 修改诗词标签
     */
    @PreAuthorize("@ss.hasPermi('poem:label:edit')")
    @Log(title = "诗词标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoemLabelInfo poemLabelInfo)
    {

        if (!UserConstants.NOT_UNIQUE.equals(poemInfoService.checkPoemNameUnique(poemLabelInfo.getPoemName())))
        {
            return AjaxResult.error("修改失败，获取诗词'" + poemLabelInfo.getPoemName() + "'失败，该名称诗词信息不存在！");
        }
        return toAjax(poemLabelInfoService.updatePoemLabelInfo(poemLabelInfo));
    }

    /**
     * 删除诗词标签
     */
    @PreAuthorize("@ss.hasPermi('poem:label:remove')")
    @Log(title = "诗词标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(poemLabelInfoService.deletePoemLabelInfoByIds(ids));
    }
}
