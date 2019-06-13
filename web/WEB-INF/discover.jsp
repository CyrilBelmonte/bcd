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

    <title>Découvrir - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="discover" />
    </c:import>
</header>

<section>
    <div class="container">
        <h2>Découvrez de nouvelles recettes</h2>

        <nav id="nav-discover">
            <form action="<c:url value="/discover"/>" method="post">
                <ul class="nav justify-content-center">
                    <li class="nav-item">
                        <button class="btn btn-discover <c:out value="${type == 'starters' ? 'active' : ''}" />" type="submit" name="starters">Entrées</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-discover <c:out value="${type == 'mainCourses' ? 'active' : ''}" />" type="submit" name="main-courses">Plats</button>
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-discover <c:out value="${type == 'desserts' ? 'active' : ''}" />" type="submit" name="desserts">Desserts</button>
                    </li>
                </ul>
            </form>
        </nav>

        <!-- Results -->
        <div class="row">
            <c:forEach items="${suggestions.recipesBasedOnUser}" var="recipe">
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

        <c:if test="${not empty suggestions.recipesBasedOnFriends}">
            </div>
            <p class="help">Recommandations basées sur vos amis</p>
            <div class="row">
        </c:if>

            <c:forEach items="${suggestions.recipesBasedOnFriends}" var="recipe">
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

        <c:if test="${not empty suggestions.recipesBasedOnProfiles}">
            </div>
            <p class="help">Recommandations basées sur la similarité des profils</p>
            <div class="row">
        </c:if>

            <c:forEach items="${suggestions.recipesBasedOnProfiles}" var="recipe">
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
</body>
</html>
