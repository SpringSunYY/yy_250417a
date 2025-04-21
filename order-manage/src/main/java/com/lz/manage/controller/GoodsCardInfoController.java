package com.lz.manage.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.lz.manage.model.dto.goodsCardInfo.GoodsCardPay;
import org.springframework.security.access.prepost.PreAuthorize;
import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
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
import com.lz.manage.model.domain.GoodsCardInfo;
import com.lz.manage.model.vo.goodsCardInfo.GoodsCardInfoVo;
import com.lz.manage.model.dto.goodsCardInfo.GoodsCardInfoQuery;
import com.lz.manage.model.dto.goodsCardInfo.GoodsCardInfoInsert;
import com.lz.manage.model.dto.goodsCardInfo.GoodsCardInfoEdit;
import com.lz.manage.service.IGoodsCardInfoService;
import com.lz.common.utils.poi.ExcelUtil;
import com.lz.common.core.page.TableDataInfo;

/**
 * 购物车Controller
 *
 * @author YY
 * @date 2025-04-21
 */
@RestController
@RequestMapping("/manage/goodsCardInfo")
public class GoodsCardInfoController extends BaseController
{
    @Resource
    private IGoodsCardInfoService goodsCardInfoService;

    /**
     * 查询购物车列表
     */
    @PreAuthorize("@ss.hasAnyPermi('manage:goodsCardInfo:list,manage:goodsCardInfo:query')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsCardInfoQuery goodsCardInfoQuery)
    {
        GoodsCardInfo goodsCardInfo = GoodsCardInfoQuery.queryToObj(goodsCardInfoQuery);
        startPage();
        List<GoodsCardInfo> list = goodsCardInfoService.selectGoodsCardInfoList(goodsCardInfo);
        List<GoodsCardInfoVo> listVo= list.stream().map(GoodsCardInfoVo::objToVo).collect(Collectors.toList());
        TableDataInfo table = getDataTable(list);
        table.setRows(listVo);
        return table;
    }

    /**
     * 导出购物车列表
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCardInfo:export')")
    @Log(title = "购物车", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsCardInfoQuery goodsCardInfoQuery)
    {
        GoodsCardInfo goodsCardInfo = GoodsCardInfoQuery.queryToObj(goodsCardInfoQuery);
        List<GoodsCardInfo> list = goodsCardInfoService.selectGoodsCardInfoList(goodsCardInfo);
        ExcelUtil<GoodsCardInfo> util = new ExcelUtil<GoodsCardInfo>(GoodsCardInfo.class);
        util.exportExcel(response, list, "购物车数据");
    }

    /**
     * 获取购物车详细信息
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCardInfo:query')")
    @GetMapping(value = "/{cardId}")
    public AjaxResult getInfo(@PathVariable("cardId") Long cardId)
    {
        GoodsCardInfo goodsCardInfo = goodsCardInfoService.selectGoodsCardInfoByCardId(cardId);
        return success(GoodsCardInfoVo.objToVo(goodsCardInfo));
    }

    /**
     * 新增购物车
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCardInfo:add')")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsCardInfoInsert goodsCardInfoInsert)
    {
        GoodsCardInfo goodsCardInfo = GoodsCardInfoInsert.insertToObj(goodsCardInfoInsert);
        return toAjax(goodsCardInfoService.insertGoodsCardInfo(goodsCardInfo));
    }

    /**
     * 修改购物车
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCardInfo:edit')")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsCardInfoEdit goodsCardInfoEdit)
    {
        GoodsCardInfo goodsCardInfo = GoodsCardInfoEdit.editToObj(goodsCardInfoEdit);
        return toAjax(goodsCardInfoService.updateGoodsCardInfo(goodsCardInfo));
    }

    /**
     * 删除购物车
     */
    @PreAuthorize("@ss.hasPermi('manage:goodsCardInfo:remove')")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cardIds}")
    public AjaxResult remove(@PathVariable Long[] cardIds)
    {
        return toAjax(goodsCardInfoService.deleteGoodsCardInfoByCardIds(cardIds));
    }

    @PreAuthorize("@ss.hasPermi('manage:goodsCardInfo:remove')")
    @Log(title = "购物车购买商品", businessType = BusinessType.INSERT)
    @PostMapping("/pay")
    public AjaxResult payOrderCard(@RequestBody @Validated GoodsCardPay goodsCardPay) {
        return toAjax(goodsCardInfoService.payOrderCard(goodsCardPay));
    }
}
