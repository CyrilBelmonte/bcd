<%--
  Created by IntelliJ IDEA.
  User: Valentin BELYN
  Date: 22/04/2019
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="includes/head.jsp" %>

    <title>Rechercher - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="search" />
    </c:import>

    <div id="search-banner">
        <h2>Qu'allez-vous cuisiner aujourd'hui ?</h2>
    </div>
</header>

<section id="search">
    <div class="container">
        <!-- Search bar -->
        <form class="search-box" action="<c:url value="/search"/>" method="post">
            <div class="search-icon">
                <span class="icon" data-feather="search"></span>
            </div>
            <input class="form-control" type="search" name="search" value="<c:out value="${param.search}" />" placeholder="Rechercher une recette">
            <div class="search-help d-none d-md-flex">
                <p class="help"><span class="icon" data-feather="filter"></span>Les résultats sont filtrés automatiquement</p>
            </div>
        </form>

        <!-- Results -->
        <c:if test="${not empty error}">
            <p><c:out value="${error.description}" /></p>
        </c:if>

        <div class="row">
            <c:forEach items="${recipes}" var="recipe">
                <div class="col-sm-6 col-md-4">
                    <a class="card recipe-card" href="<c:url value="/recipe"><c:param name="id" value="${recipe.id}" /></c:url>">
                        <c:choose>
                            <c:when test="${recipe.difficulty == 'LOW'}">
                                <span class="badge difficulty-easy">• Facile</span>
                            </c:when>
                            <c:when test="${recipe.difficulty == 'AVERAGE'}">
                                <span class="badge difficulty-average">• Moyen</span>
                            </c:when>
                            <c:when test="${recipe.difficulty == 'HIGH'}">
                                <span class="badge difficulty-hard">• Difficile</span>
                            </c:when>
                        </c:choose>
                        <img class="card-img-top" src="<c:out value="${recipe.picture}" />">
                        <div class="card-body">
                            <h3 class="card-title"><c:out value="${recipe.name}" /></h3>
                            <p class="score"><span class="icon" data-feather="star"></span> <strong><fmt:formatNumber value="${recipe.rating}" maxFractionDigits="1" /></strong> sur 5</p>
                        </div>
                        <div class="card-footer">
                            <p><span class="icon" data-feather="clock"></span><c:out value="${recipe.duration}" />m</p>
                            <p><span class="icon" data-feather="user"></span><c:out value="${recipe.persons}" /> personnes</p>
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
<script src="<c:url value="/js/search.js"/>"></script>
</body>
</html>
