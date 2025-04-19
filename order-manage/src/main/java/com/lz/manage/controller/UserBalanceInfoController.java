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
import com.lz.manage.model.domain.UserBalanceInfo;
import com.lz.manage.model.vo.userBalanceInfo.UserBalanceInfoVo;
import com.lz.manage.model.dto.userBalanceInfo.UserBalanceInfoQuery;
import com.lz.manage.model.dto.userBalanceInfo.UserBalanceInfoInsert;
import com.lz.manage.model.dto.userBalanceInfo.UserBalanceInfoEdit;
import com.lz.manage.service.IUserBalanceInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 用户余额Controller
 *
 * @author YY
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/manage/userBalanceInfo")
public class UserBalanceInfoController extends BaseController
{
    @Resource
    private IUserBalanceInfoService userBalanceInfoService;

    /**
     * 查询用户余额列表
     */
    @PreAuthorize("@ss.hasPermi('manage:userBalanceInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserBalanceInfoQuery userBalanceInfoQuery)
    {
        UserBalanceInfo userBalanceInfo = UserBalanceInfoQuery.queryToObj(userBalanceInfoQuery);
        startPage();
        List<UserBalanceInfo> list = userBalanceInfoService.selectUserBalanceInfoList(userBalanceInfo);
        List<UserBalanceInfoVo> listVo= list.stream().map(UserBalanceInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出用户余额列表
     */
    @PreAuthorize("@ss.hasPermi('manage:userBalanceInfo:export')")
    @Log(title = "用户余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserBalanceInfoQuery userBalanceInfoQuery)
    {
        UserBalanceInfo userBalanceInfo = UserBalanceInfoQuery.queryToObj(userBalanceInfoQuery);
        List<UserBalanceInfo> list = userBalanceInfoService.selectUserBalanceInfoList(userBalanceInfo);
        ExcelUtil<UserBalanceInfo> util = new ExcelUtil<UserBalanceInfo>(UserBalanceInfo.class);
        util.exportExcel(response, list, "用户余额数据");
    }

    /**
     * 获取用户余额详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:userBalanceInfo:query')")
    @GetMapping(value = "/{balanceId}")
    public AjaxResult getInfo(@PathVariable("balanceId") Long balanceId)
    {
        UserBalanceInfo userBalanceInfo = userBalanceInfoService.selectUserBalanceInfoByBalanceId(balanceId);
        return success(UserBalanceInfoVo.objToVo(userBalanceInfo));
    }

    /**
     * 新增用户余额
     */
    @PreAuthorize("@ss.hasPermi('manage:userBalanceInfo:add')")
    @Log(title = "用户余额", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserBalanceInfoInsert userBalanceInfoInsert)
    {
        UserBalanceInfo userBalanceInfo = UserBalanceInfoInsert.insertToObj(userBalanceInfoInsert);
        return toAjax(userBalanceInfoService.insertUserBalanceInfo(userBalanceInfo));
    }

    /**
     * 修改用户余额
     */
    @PreAuthorize("@ss.hasPermi('manage:userBalanceInfo:edit')")
    @Log(title = "用户余额", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserBalanceInfoEdit userBalanceInfoEdit)
    {
        UserBalanceInfo userBalanceInfo = UserBalanceInfoEdit.editToObj(userBalanceInfoEdit);
        return toAjax(userBalanceInfoService.updateUserBalanceInfo(userBalanceInfo));
    }

    /**
     * 删除用户余额
     */
    @PreAuthorize("@ss.hasPermi('manage:userBalanceInfo:remove')")
    @Log(title = "用户余额", businessType = BusinessType.DELETE)
	@DeleteMapping("/{balanceIds}")
    public AjaxResult remove(@PathVariable Long[] balanceIds)
    {
        return toAjax(userBalanceInfoService.deleteUserBalanceInfoByBalanceIds(balanceIds));
    }
}
