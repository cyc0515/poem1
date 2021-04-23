package com.ruoyi.poem.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.poem.service.IPoemAuditService;
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
import com.ruoyi.poem.domain.PoemInfo;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 诗词审核Controller
 * 
 * @author ruoyi
 * @date 2021-03-11
 */
@RestController
@RequestMapping("/poem/audit")
public class PoemAuditController extends BaseController
{
    @Autowired
    private IPoemAuditService poemAuditService;

    /**
     * 查询诗词审核列表
     */
    @PreAuthorize("@ss.hasPermi('poem:audit:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoemInfo poemInfo)
    {
        startPage();
        List<PoemInfo> list = poemAuditService.selectPoemInfoList(poemInfo);
        return getDataTable(list);
    }

    /**
     * 导出诗词审核列表
     */
    @PreAuthorize("@ss.hasPermi('poem:audit:export')")
    @Log(title = "诗词审核", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PoemInfo poemInfo)
    {
        List<PoemInfo> list = poemAuditService.selectPoemInfoList(poemInfo);
        ExcelUtil<PoemInfo> util = new ExcelUtil<PoemInfo>(PoemInfo.class);
        return util.exportExcel(list, "aduit");
    }

    /**
     * 获取诗词审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('poem:audit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(poemAuditService.selectPoemInfoById(id));
    }

    /**
     * 通过诗词审核
     */
    @PreAuthorize("@ss.hasPermi('poem:audit:audit')")
    @Log(title = "诗词审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/audit/{id}")
    public AjaxResult audit(@PathVariable Long id)
    {
        return toAjax(poemAuditService.auditPoemInfoById(id));
    }

    /**
     * 驳回诗词审核
     */
    @PreAuthorize("@ss.hasPermi('poem:audit:reject')")
    @Log(title = "诗词审核", businessType = BusinessType.DELETE)
    @DeleteMapping("/reaudit/{id}")
    public AjaxResult reaudit(@PathVariable Long id)
    {
        return toAjax(poemAuditService.reauditPoemInfoById(id));
    }
}
