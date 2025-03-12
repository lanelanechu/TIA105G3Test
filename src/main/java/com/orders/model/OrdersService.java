package com.orders.model;

import java.sql.Timestamp;
import java.util.List;

public class OrdersService {

	    private OrdersDAO_interface dao;

	    public OrdersService() {
	        dao = new OrdersDAO();
	    }

	    public OrdersVO addOrder(String orderId,String spaceId,String memberId,String branchId,Integer totalPrice,Timestamp paymentDatetime,Timestamp orderStart,
	    		Timestamp orderEnd,Timestamp commentTime,String commentContect,Integer satisfaction,Integer accountsPayable) {
	    	
	    	OrdersVO ordersVO = new OrdersVO();
	    	
	    	ordersVO.setOrderId(orderId);
			ordersVO.setSpaceId(spaceId);
            ordersVO.setMemberId(memberId);
            ordersVO.setBranchId(branchId);
            ordersVO.setTotalPrice(totalPrice);
            ordersVO.setPaymentDatetime(paymentDatetime);
            ordersVO.setOrderStart(orderStart);
            ordersVO.setOrderEnd(orderEnd);
            ordersVO.setCommentTime(commentTime);
            ordersVO.setCommentContect(commentContect);
            ordersVO.setSatisfaction(satisfaction);
            ordersVO.setAccountsPayable(accountsPayable);
         
	    	dao.insert(ordersVO);
	    	return ordersVO;
	    }

	    public OrdersVO updateOrder(String orderId,String spaceId,String memberId,String branchId,Integer totalPrice,Timestamp paymentDatetime,Timestamp orderStart,
	    		Timestamp orderEnd,Timestamp commentTime,String commentContect,Integer satisfaction,Integer accountsPayable) {
	    	
            OrdersVO ordersVO = new OrdersVO();
	    	
            ordersVO.setOrderId(orderId);
			ordersVO.setSpaceId(spaceId);
            ordersVO.setMemberId(memberId);
            ordersVO.setBranchId(branchId);
            ordersVO.setTotalPrice(totalPrice);
            ordersVO.setPaymentDatetime(paymentDatetime);
            ordersVO.setOrderStart(orderStart);
            ordersVO.setOrderEnd(orderEnd);
            ordersVO.setCommentTime(commentTime);
            ordersVO.setCommentContect(commentContect);
            ordersVO.setSatisfaction(satisfaction);
            ordersVO.setAccountsPayable(accountsPayable);;
	        dao.update(ordersVO);
	        
	        return ordersVO;
	    }

	    public void deleteOrsder(String orderId) {
	        dao.delete(orderId);
	    }

	    public OrdersVO getOneOrders(String orderId) {
	        return dao.findByPrimaryKey(orderId);
	    }

	    public List<OrdersVO> getAll() {
	        return dao.getAll();
	    }

}