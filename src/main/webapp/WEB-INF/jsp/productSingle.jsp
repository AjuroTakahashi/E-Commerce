<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="header.jsp"%>
<main>
    <div class="album py-5 bg-light">
        <div class="container">

            <h2>${product.name}</h2>
            <div class="col-md-12">
                <div class="card mb-4 box-shadow">
                    <div class="d-flex justify-content-center">
                        <img class="card-img-top" alt="${product.name}" style="height: 225px; width: 100%; display: block;"
                             src="${product.picture}" data-holder-rendered="true">
                    </div>
                    <div class="card-body">
                        <p class="card-text">${product.description}</p>
                    </div>
                    <div class="card-footer text-right">
                        <button type="button" class="btn btn-sm btn-primary">Mettre dans le panier</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>


<%@ include file="footer.jsp"%>
