package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderProductId implements Serializable {

    @Column(name = "product_id")
    private Long productId;
    @Column(name = "order_id")
    private Long orderId;

    public OrderProductId() {
    }

    public OrderProductId(Long productId, Long orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
