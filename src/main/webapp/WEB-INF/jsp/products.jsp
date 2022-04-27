<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:forEach items="${products}" var="product">
    <div class="col-md-4 col-sm-6 mb-4">
        <div class="card mb-4 box-shadow">
            <img class="card-img-top" alt="${product.name}"
                 src="${product.picture}" data-holder-rendered="true">
            <div class="card-body">
                <h4>${product.name}</h4>
                <p class="card-text">${product.description}</p>
            </div>
            <div class="card-footer">
                <div class="row justify-content-center">
                    <spring:url value="/products/${product.id}" var="url" htmlEscape="true"/>
                    <a type="button" class="btn btn-sm btn-primary mr-4" href="${url}">Voir</a>
                    <button type="button" class="btn btn-sm btn-outline-secondary" href="">Mettre dans le panier</button>
                </div>
            </div>
        </div>
    </div>
</c:forEach>
