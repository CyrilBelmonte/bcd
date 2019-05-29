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

    <title>Mon chef - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="assistant" />
    </c:import>
</header>

<section>
    <div class="container">
        <c:if test="${not empty error}">
            <p><c:out value="${error.description}" /></p>
        </c:if>

        <c:forEach items="${days}" var="day">
            <div class="day">
                <h2><c:out value="${day.name}" /></h2>

                <div class="menu">
                    <div class="time">
                        <span class="icon" data-feather="sun"></span>
                    </div>
                    <div class="menu-details">
                        <div>
                            <c:forEach items="${day.lunchRecipes}" var="recipe">
                                <a class="card horizontal-recipe-card" href="<c:url value="/recipe"><c:param name="id" value="${recipe.id}" /></c:url>">
                                    <div class="row no-gutters">
                                        <div class="col-5">
                                            <img class="card-img" src="<c:out value="${recipe.picture}" />" alt="Illustration de recette">
                                        </div>
                                        <div class="col-7">
                                            <div class="card-body">
                                                <h3 class="card-title"><c:out value="${recipe.name}" /></h3>
                                                <p class="score"><span class="icon" data-feather="star"></span> <strong><fmt:formatNumber value="${recipe.rating}" maxFractionDigits="1" /></strong> sur 5</p>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="menu">
                    <div class="time">
                        <span class="icon" data-feather="moon"></span>
                    </div>
                    <div class="menu-details">
                        <div>
                            <c:forEach items="${day.dinnerRecipes}" var="recipe">
                                <a class="card horizontal-recipe-card" href="<c:url value="/recipe"><c:param name="id" value="${recipe.id}" /></c:url>">
                                    <div class="row no-gutters">
                                        <div class="col-5">
                                            <img class="card-img" src="<c:out value="${recipe.picture}" />" alt="Illustration de recette">
                                        </div>
                                        <div class="col-7">
                                            <div class="card-body">
                                                <h3 class="card-title"><c:out value="${recipe.name}" /></h3>
                                                <p class="score"><span class="icon" data-feather="star"></span> <strong><fmt:formatNumber value="${recipe.rating}" maxFractionDigits="1" /></strong> sur 5</p>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>

            </div>
        </c:forEach>

    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
</body>
</html>
