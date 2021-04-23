package com.ruoyi.poem.controller;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.poem.domain.PoemInfo;
import com.ruoyi.poem.service.IPoemInfoService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 诗词信息Controller
 * 
 * @author ruoyi
 * @date 2021-03-02
 */
@RestController
@RequestMapping("/poem/info")
public class PoemInfoController extends BaseController
{
    @Autowired
    private IPoemInfoService poemInfoService;

    /**
     * 查询诗词信息列表
     */
    @PreAuthorize("@ss.hasPermi('poem:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoemInfo poemInfo)
    {
        startPage();
        poemInfo.setUserName(SecurityUtils.getUsername());
        List<PoemInfo> list = poemInfoService.selectPoemInfoList(poemInfo);
        return getDataTable(list);
    }

    /**
     * 导出诗词信息列表
     */
    @PreAuthorize("@ss.hasPermi('poem:info:export')")
    @Log(title = "诗词信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PoemInfo poemInfo)
    {
        List<PoemInfo> list = poemInfoService.selectPoemInfoList(poemInfo);
        ExcelUtil<PoemInfo> util = new ExcelUtil<PoemInfo>(PoemInfo.class);
        return util.exportExcel(list, "诗词信息");
    }

    /**
     * 获取诗词信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('poem:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(poemInfoService.selectPoemInfoById(id));
    }

    /**
     * 新增诗词信息
     */
    @PreAuthorize("@ss.hasPermi('poem:info:add')")
    @Log(title = "诗词信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoemInfo poemInfo)
    {

        if (UserConstants.NOT_UNIQUE.equals(poemInfoService.checkPoemNameUnique(poemInfo.getPoemName())))
        {
            return AjaxResult.error("新增诗词'" + poemInfo.getPoemName() + "'失败，该名称诗词信息已存在");
        }
        poemInfo.setUserName(SecurityUtils.getUsername());
        poemInfo.setPoemUploadTime(new Date());
        return toAjax(poemInfoService.insertPoemInfo(poemInfo));
    }

    /**
     * 修改诗词信息
     */
    @PreAuthorize("@ss.hasPermi('poem:info:edit')")
    @Log(title = "诗词信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoemInfo poemInfo)
    {
        poemInfo.setPoemUploadTime(new Date());
        return toAjax(poemInfoService.updatePoemInfo(poemInfo));
    }

    /**
     * 上传审核诗词信息
     */
    @PreAuthorize("@ss.hasPermi('poem:info:audit')")
    @Log(title = "诗词信息", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/audit/{id}")
    public AjaxResult upload(@PathVariable String id)
    {
        return toAjax(poemInfoService.uploadPoemInfo(id));
    }

    /**
     * 撤回审核诗词信息
     */
    @PreAuthorize("@ss.hasPermi('poem:info:undo')")
    @Log(title = "诗词信息", businessType = BusinessType.DELETE)
    @DeleteMapping(value = "/undo/{id}")
    public AjaxResult undo(@PathVariable String id)
    {
        return toAjax(poemInfoService.undoPoemInfo(id));
    }


    /**
     * 删除诗词信息
     */
    @PreAuthorize("@ss.hasPermi('poem:info:remove')")
    @Log(title = "诗词信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(poemInfoService.deletePoemInfoByIds(ids));
    }

    /**
     * 根据标签查询
     * @param label
     * @return
     */
    @PostMapping(value = "/getInfoList/{label}")
    public AjaxResult getInfoByLabel(@PathVariable String[] label){

        String label1[]={"",""};
        label[0]="爱国";
        label[1]="花";
        return AjaxResult.success(poemInfoService.selectPoemInfoByNameList(label));
    }
}
