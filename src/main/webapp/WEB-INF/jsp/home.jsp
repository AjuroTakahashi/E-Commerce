<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ include file="header.jsp"%>

<main role="main">

    <section class="jumbotron text-center mokoko">
        <div class="container">
            <h1 class="jumbotron-heading">Je sais pas ce que je vend</h1>
            <p class="lead text-muted">Du coup je vends rien à des prix différents.</p>
            <p>
                <a href="#" class="btn btn-secondary my-2">Acheter rien et demi</a>
                <a href="#" class="btn btn-primary my-2">Acheter rien</a>
            </p>
        </div>
    </section>

    <div class="album py-5 bg-light">
        <div class="container">

            <div class="row">

                <c:import var="products" url="products.jsp"/>
                <c:out value="${products}" escapeXml="false"/>

            </div>
        </div>
    </div>

</main>

<%@ include file="footer.jsp"%>
