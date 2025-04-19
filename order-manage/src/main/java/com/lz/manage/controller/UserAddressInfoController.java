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
import com.lz.manage.model.domain.UserAddressInfo;
import com.lz.manage.model.vo.userAddressInfo.UserAddressInfoVo;
import com.lz.manage.model.dto.userAddressInfo.UserAddressInfoQuery;
import com.lz.manage.model.dto.userAddressInfo.UserAddressInfoInsert;
import com.lz.manage.model.dto.userAddressInfo.UserAddressInfoEdit;
import com.lz.manage.service.IUserAddressInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 用户地址Controller
 *
 * @author YY
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/manage/userAddressInfo")
public class UserAddressInfoController extends BaseController
{
    @Resource
    private IUserAddressInfoService userAddressInfoService;

    /**
     * 查询用户地址列表
     */
    @PreAuthorize("@ss.hasPermi('manage:userAddressInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserAddressInfoQuery userAddressInfoQuery)
    {
        UserAddressInfo userAddressInfo = UserAddressInfoQuery.queryToObj(userAddressInfoQuery);
        startPage();
        List<UserAddressInfo> list = userAddressInfoService.selectUserAddressInfoList(userAddressInfo);
        List<UserAddressInfoVo> listVo= list.stream().map(UserAddressInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出用户地址列表
     */
    @PreAuthorize("@ss.hasPermi('manage:userAddressInfo:export')")
    @Log(title = "用户地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserAddressInfoQuery userAddressInfoQuery)
    {
        UserAddressInfo userAddressInfo = UserAddressInfoQuery.queryToObj(userAddressInfoQuery);
        List<UserAddressInfo> list = userAddressInfoService.selectUserAddressInfoList(userAddressInfo);
        ExcelUtil<UserAddressInfo> util = new ExcelUtil<UserAddressInfo>(UserAddressInfo.class);
        util.exportExcel(response, list, "用户地址数据");
    }

    /**
     * 获取用户地址详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:userAddressInfo:query')")
    @GetMapping(value = "/{addressId}")
    public AjaxResult getInfo(@PathVariable("addressId") Long addressId)
    {
        UserAddressInfo userAddressInfo = userAddressInfoService.selectUserAddressInfoByAddressId(addressId);
        return success(UserAddressInfoVo.objToVo(userAddressInfo));
    }

    /**
     * 新增用户地址
     */
    @PreAuthorize("@ss.hasPermi('manage:userAddressInfo:add')")
    @Log(title = "用户地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserAddressInfoInsert userAddressInfoInsert)
    {
        UserAddressInfo userAddressInfo = UserAddressInfoInsert.insertToObj(userAddressInfoInsert);
        return toAjax(userAddressInfoService.insertUserAddressInfo(userAddressInfo));
    }

    /**
     * 修改用户地址
     */
    @PreAuthorize("@ss.hasPermi('manage:userAddressInfo:edit')")
    @Log(title = "用户地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserAddressInfoEdit userAddressInfoEdit)
    {
        UserAddressInfo userAddressInfo = UserAddressInfoEdit.editToObj(userAddressInfoEdit);
        return toAjax(userAddressInfoService.updateUserAddressInfo(userAddressInfo));
    }

    /**
     * 删除用户地址
     */
    @PreAuthorize("@ss.hasPermi('manage:userAddressInfo:remove')")
    @Log(title = "用户地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{addressIds}")
    public AjaxResult remove(@PathVariable Long[] addressIds)
    {
        return toAjax(userAddressInfoService.deleteUserAddressInfoByAddressIds(addressIds));
    }
}
