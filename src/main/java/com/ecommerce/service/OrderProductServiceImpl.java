package com.ecommerce.service;

import com.ecommerce.model.OrderProduct;
import com.ecommerce.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("orderProduct")
public class OrderProductServiceImpl implements OrderProductService{
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Override
    public void save(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }
}
