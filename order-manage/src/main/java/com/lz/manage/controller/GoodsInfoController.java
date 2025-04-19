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
import com.lz.manage.model.domain.GoodsInfo;
import com.lz.manage.model.vo.goodsInfo.GoodsInfoVo;
import com.lz.manage.model.dto.goodsInfo.GoodsInfoQuery;
import com.lz.manage.model.dto.goodsInfo.GoodsInfoInsert;
import com.lz.manage.model.dto.goodsInfo.GoodsInfoEdit;
import com.lz.manage.service.IGoodsInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 商品信息Controller
 *
 * @author YY
 * @date 2025-04-19
 */
@RestController
@RequestMapping("/manage/goodsInfo")
public class GoodsInfoController extends BaseController
{
    @Resource
    private IGoodsInfoService goodsInfoService;

    /**
     * 查询商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsInfoQuery goodsInfoQuery)
    {
        GoodsInfo goodsInfo = GoodsInfoQuery.queryToObj(goodsInfoQuery);
        startPage();
        List<GoodsInfo> list = goodsInfoService.selectGoodsInfoList(goodsInfo);
        List<GoodsInfoVo> listVo= list.stream().map(GoodsInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出商品信息列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsInfo:export')")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsInfoQuery goodsInfoQuery)
    {
        GoodsInfo goodsInfo = GoodsInfoQuery.queryToObj(goodsInfoQuery);
        List<GoodsInfo> list = goodsInfoService.selectGoodsInfoList(goodsInfo);
        ExcelUtil<GoodsInfo> util = new ExcelUtil<GoodsInfo>(GoodsInfo.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsInfo:query')")
    @GetMapping(value = "/{goodsId}")
    public AjaxResult getInfo(@PathVariable("goodsId") Long goodsId)
    {
        GoodsInfo goodsInfo = goodsInfoService.selectGoodsInfoByGoodsId(goodsId);
        return success(GoodsInfoVo.objToVo(goodsInfo));
    }

    /**
     * 新增商品信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsInfo:add')")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsInfoInsert goodsInfoInsert)
    {
        GoodsInfo goodsInfo = GoodsInfoInsert.insertToObj(goodsInfoInsert);
        return toAjax(goodsInfoService.insertGoodsInfo(goodsInfo));
    }

    /**
     * 修改商品信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsInfo:edit')")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsInfoEdit goodsInfoEdit)
    {
        GoodsInfo goodsInfo = GoodsInfoEdit.editToObj(goodsInfoEdit);
        return toAjax(goodsInfoService.updateGoodsInfo(goodsInfo));
    }

    /**
     * 删除商品信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsInfo:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{goodsIds}")
    public AjaxResult remove(@PathVariable Long[] goodsIds)
    {
        return toAjax(goodsInfoService.deleteGoodsInfoByGoodsIds(goodsIds));
    }
}
