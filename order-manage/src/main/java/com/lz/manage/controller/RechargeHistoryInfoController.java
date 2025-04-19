package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lz.common.annotation.Log;
import com.lz.common.core.controller.BaseController;
import com.lz.common.core.domain.AjaxResult;
import com.lz.common.enums.BusinessType;
import com.lz.manage.model.domain.RechargeHistoryInfo;
import com.lz.manage.model.vo.rechargeHistoryInfo.RechargeHistoryInfoVo;
import com.lz.manage.model.dto.rechargeHistoryInfo.RechargeHistoryInfoQuery;
import com.lz.manage.model.dto.rechargeHistoryInfo.RechargeHistoryInfoInsert;
import com.lz.manage.model.dto.rechargeHistoryInfo.RechargeHistoryInfoEdit;
import com.lz.manage.service.IRechargeHistoryInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 充值记录Controller
 *
 * @author YY
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/manage/rechargeHistoryInfo")
public class RechargeHistoryInfoController extends BaseController
{
    @Resource
    private IRechargeHistoryInfoService rechargeHistoryInfoService;

    /**
     * 查询充值记录列表
     */
    @PreAuthorize("@ss.hasPermi('manage:rechargeHistoryInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(RechargeHistoryInfoQuery rechargeHistoryInfoQuery)
    {
        RechargeHistoryInfo rechargeHistoryInfo = RechargeHistoryInfoQuery.queryToObj(rechargeHistoryInfoQuery);
        startPage();
        List<RechargeHistoryInfo> list = rechargeHistoryInfoService.selectRechargeHistoryInfoList(rechargeHistoryInfo);
        List<RechargeHistoryInfoVo> listVo= list.stream().map(RechargeHistoryInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出充值记录列表
     */
    @PreAuthorize("@ss.hasPermi('manage:rechargeHistoryInfo:export')")
    @Log(title = "充值记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RechargeHistoryInfoQuery rechargeHistoryInfoQuery)
    {
        RechargeHistoryInfo rechargeHistoryInfo = RechargeHistoryInfoQuery.queryToObj(rechargeHistoryInfoQuery);
        List<RechargeHistoryInfo> list = rechargeHistoryInfoService.selectRechargeHistoryInfoList(rechargeHistoryInfo);
        ExcelUtil<RechargeHistoryInfo> util = new ExcelUtil<RechargeHistoryInfo>(RechargeHistoryInfo.class);
        util.exportExcel(response, list, "充值记录数据");
    }

    /**
     * 获取充值记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:rechargeHistoryInfo:query')")
    @GetMapping(value = "/{historyId}")
    public AjaxResult getInfo(@PathVariable("historyId") Long historyId)
    {
        RechargeHistoryInfo rechargeHistoryInfo = rechargeHistoryInfoService.selectRechargeHistoryInfoByHistoryId(historyId);
        return success(RechargeHistoryInfoVo.objToVo(rechargeHistoryInfo));
    }

    /**
     * 新增充值记录
     */
    @PreAuthorize("@ss.hasPermi('manage:rechargeHistoryInfo:add')")
    @Log(title = "充值记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RechargeHistoryInfoInsert rechargeHistoryInfoInsert)
    {
        RechargeHistoryInfo rechargeHistoryInfo = RechargeHistoryInfoInsert.insertToObj(rechargeHistoryInfoInsert);
        return toAjax(rechargeHistoryInfoService.insertRechargeHistoryInfo(rechargeHistoryInfo));
    }

    /**
     * 修改充值记录
     */
    @PreAuthorize("@ss.hasPermi('manage:rechargeHistoryInfo:edit')")
    @Log(title = "充值记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RechargeHistoryInfoEdit rechargeHistoryInfoEdit)
    {
        RechargeHistoryInfo rechargeHistoryInfo = RechargeHistoryInfoEdit.editToObj(rechargeHistoryInfoEdit);
        return toAjax(rechargeHistoryInfoService.updateRechargeHistoryInfo(rechargeHistoryInfo));
    }

    /**
     * 删除充值记录
     */
    @PreAuthorize("@ss.hasPermi('manage:rechargeHistoryInfo:remove')")
    @Log(title = "充值记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{historyIds}")
    public AjaxResult remove(@PathVariable Long[] historyIds)
    {
        return toAjax(rechargeHistoryInfoService.deleteRechargeHistoryInfoByHistoryIds(historyIds));
    }
}
