<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>

    <c:forEach items="${order.orderProducts}" var="orderProducts">
        <img class="card-img-top" alt="${orderProducts.product.name}"
             src="${orderProducts.product.picture}" data-holder-rendered="true">
        ${orderProducts.product.name}
        ${orderProducts.quantity}
    </c:forEach>

<%@ include file="footer.jsp"%>