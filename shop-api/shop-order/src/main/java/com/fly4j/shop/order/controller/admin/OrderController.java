package com.fly4j.shop.order.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fly4j.common.core.bean.Result;
import com.fly4j.shop.order.pojo.dto.MoneyInfoDTO;
import com.fly4j.shop.order.pojo.dto.OrderDeliveryDTO;
import com.fly4j.shop.order.pojo.dto.OrderDetailDTO;
import com.fly4j.shop.order.pojo.dto.ReceiverInfoDTO;
import com.fly4j.shop.order.pojo.entity.Order;
import com.fly4j.shop.order.service.IOrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: Mr.
 * @create: 2020-03-24 17:04
 **/
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Resource
    private IOrderService iOrderService;

    @GetMapping("/pageNum/{pageNum}/pageSize/{pageSize}")
    public Result<Page<Order>> page(@PathVariable Integer pageNum, @PathVariable Integer pageSize, Order order) {
        Page<Order> page = new Page<>(pageNum, pageSize);
        Page<Order> data = (Page) iOrderService.page(page,new LambdaQueryWrapper<Order>()
                .eq(StringUtils.isNotBlank(order.getOrderNo()), Order::getOrderNo, order.getOrderNo())
                .like(StringUtils.isNotBlank(order.getReceiverName()), Order::getReceiverName, order.getReceiverName())
                .eq(order.getStatus()==null?false:true,Order::getStatus,order.getStatus())
                .eq(order.getOrderType()==null?false:true,Order::getOrderType,order.getOrderType())
                .eq(order.getSourceType()==null?false:true,Order::getSourceType,order.getSourceType())

        );
        return Result.success(data);
    }

    /**
     * 批量发货
     * @param deliverList
     * @return
     */
    @PutMapping("/deliverInfo")
    public Result putDeliverInfo(@RequestBody List<OrderDeliveryDTO> deliverList) {
        boolean status = iOrderService.deliver(deliverList);
        return Result.status(status);
    }

    /**
     * 批量关闭订单
     * @param ids
     * @param note
     * @return
     */
    @PutMapping("/close")
    public Result close(@RequestParam("ids") List<Long> ids, @RequestParam String note) {
        boolean status = iOrderService.close(ids, note);
        return Result.status(status);
    }

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam("ids") List<Long> ids) {
        boolean status = iOrderService.removeByIds(ids);
        return Result.status(status);
    }

    /**
     * 获取订单详情:订单信息、商品信息、操作记录
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<OrderDetailDTO> detail(@PathVariable Long id) {
        OrderDetailDTO orderDetailResult = iOrderService.getDetail(id);
        orderDetailResult.setId(id);
        return Result.success(orderDetailResult);
    }

    /**
     * 修改收货人信息
     * @param receiverInfoDto
     * @return
     */
    @PutMapping("/receiverInfo")
    public Result putReceiverInfo(@RequestBody ReceiverInfoDTO receiverInfoDto) {
        boolean status = iOrderService.updateReceiverInfo(receiverInfoDto);
        return Result.status(status);
    }

    /**
     * 修改订单费用信息
     * @param moneyInfoDto
     * @return
     */
    @PutMapping("/moneyInfo")
    public Result putMoneyInfo(@RequestBody MoneyInfoDTO moneyInfoDto) {
        boolean status = iOrderService.updateMoneyInfo(moneyInfoDto);
        return Result.status(status);
    }

    /**
     * 备注订单
     * @param id
     * @param note
     * @param status
     * @return
     */
    @PutMapping("/note")
    public Result putNote(@RequestParam("id") Long id,
                                   @RequestParam("note") String note,
                                   @RequestParam("status") Integer status) {
        boolean status1 = iOrderService.updateNote(id, note, status);
        return Result.status(status1);
    }
}