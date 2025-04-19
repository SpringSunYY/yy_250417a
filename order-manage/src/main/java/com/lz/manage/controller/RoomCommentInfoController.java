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
import com.lz.manage.model.domain.RoomCommentInfo;
import com.lz.manage.model.vo.roomCommentInfo.RoomCommentInfoVo;
import com.lz.manage.model.dto.roomCommentInfo.RoomCommentInfoQuery;
import com.lz.manage.model.dto.roomCommentInfo.RoomCommentInfoInsert;
import com.lz.manage.model.dto.roomCommentInfo.RoomCommentInfoEdit;
import com.lz.manage.service.IRoomCommentInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 商品评价Controller
 *
 * @author YY
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/manage/roomCommentInfo")
public class RoomCommentInfoController extends BaseController
{
    @Resource
    private IRoomCommentInfoService roomCommentInfoService;

    /**
     * 查询商品评价列表
     */
    @PreAuthorize("@ss.hasPermi('manage:roomCommentInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(RoomCommentInfoQuery roomCommentInfoQuery)
    {
        RoomCommentInfo roomCommentInfo = RoomCommentInfoQuery.queryToObj(roomCommentInfoQuery);
        startPage();
        List<RoomCommentInfo> list = roomCommentInfoService.selectRoomCommentInfoList(roomCommentInfo);
        List<RoomCommentInfoVo> listVo= list.stream().map(RoomCommentInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出商品评价列表
     */
    @PreAuthorize("@ss.hasPermi('manage:roomCommentInfo:export')")
    @Log(title = "商品评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RoomCommentInfoQuery roomCommentInfoQuery)
    {
        RoomCommentInfo roomCommentInfo = RoomCommentInfoQuery.queryToObj(roomCommentInfoQuery);
        List<RoomCommentInfo> list = roomCommentInfoService.selectRoomCommentInfoList(roomCommentInfo);
        ExcelUtil<RoomCommentInfo> util = new ExcelUtil<RoomCommentInfo>(RoomCommentInfo.class);
        util.exportExcel(response, list, "商品评价数据");
    }

    /**
     * 获取商品评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:roomCommentInfo:query')")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        RoomCommentInfo roomCommentInfo = roomCommentInfoService.selectRoomCommentInfoByCommentId(commentId);
        return success(RoomCommentInfoVo.objToVo(roomCommentInfo));
    }

    /**
     * 新增商品评价
     */
    @PreAuthorize("@ss.hasPermi('manage:roomCommentInfo:add')")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RoomCommentInfoInsert roomCommentInfoInsert)
    {
        RoomCommentInfo roomCommentInfo = RoomCommentInfoInsert.insertToObj(roomCommentInfoInsert);
        return toAjax(roomCommentInfoService.insertRoomCommentInfo(roomCommentInfo));
    }

    /**
     * 修改商品评价
     */
    @PreAuthorize("@ss.hasPermi('manage:roomCommentInfo:edit')")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RoomCommentInfoEdit roomCommentInfoEdit)
    {
        RoomCommentInfo roomCommentInfo = RoomCommentInfoEdit.editToObj(roomCommentInfoEdit);
        return toAjax(roomCommentInfoService.updateRoomCommentInfo(roomCommentInfo));
    }

    /**
     * 删除商品评价
     */
    @PreAuthorize("@ss.hasPermi('manage:roomCommentInfo:remove')")
    @Log(title = "商品评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
        return toAjax(roomCommentInfoService.deleteRoomCommentInfoByCommentIds(commentIds));
    }
}
