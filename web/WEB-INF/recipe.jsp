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

    <title><c:out value="${recipe.name}" /> - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="search" />
    </c:import>
</header>

<section id="recipe-header">
    <div class="container">
        <img class="recipe-picture" src="<c:out value="${recipe.picture}" />" alt="Illustration de recette">
        <h2><c:out value="${recipe.name}" /></h2>
        <div class="card card-body recipe-information">
            <p class="score"><span class="icon" data-feather="star"></span><strong><fmt:formatNumber value="${recipe.rating}" maxFractionDigits="1" /></strong></p>
            <p><span class="icon" data-feather="clock"></span><c:out value="${recipe.duration}" />m</p>
            <p><span class="icon" data-feather="user"></span><c:out value="${recipe.persons}" /> personnes</p>
            <p class="d-none d-md-flex">
                <span class="icon" data-feather="bar-chart-2"></span>
                <c:choose>
                    <c:when test="${recipe.difficulty == 'LOW'}">
                        Facile
                    </c:when>
                    <c:when test="${recipe.difficulty == 'AVERAGE'}">
                        Moyen
                    </c:when>
                    <c:when test="${recipe.difficulty == 'HIGH'}">
                        Difficile
                    </c:when>
                </c:choose>
            </p>
            <p class="d-none d-md-flex">
                <span class="icon" data-feather="dollar-sign"></span>
                <c:choose>
                    <c:when test="${recipe.cost == 'LOW'}">
                        Bon marché
                    </c:when>
                    <c:when test="${recipe.cost == 'AVERAGE'}">
                        Coût moyen
                    </c:when>
                    <c:when test="${recipe.cost == 'HIGH'}">
                        Cher
                    </c:when>
                </c:choose>
            </p>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4 recipe-ingredients mb-5 mb-md-0">
                <h3>Ingrédients</h3>
                <c:forEach items="${recipe.ingredients}" var="ingredient">
                    <p>
                        <span class="icon" data-feather="hash"></span>
                        <c:if test="${ingredient.quantity > 0}">
                            <fmt:formatNumber value="${ingredient.quantity}" />
                            <c:out value=" - " />
                        </c:if>
                        <c:if test="${not empty ingredient.unit}">
                            <c:out value="${ingredient.unit} - " />
                        </c:if>
                        <c:out value="${ingredient.name}" />
                    </p>
                </c:forEach>
            </div>

            <div class="col-12 col-md-7 offset-md-1 recipe-steps">
                <h3>Let's get started!</h3>
                <c:forEach items="${recipe.steps}" var="step">
                    <h4>Étape <c:out value="${step.position}" /></h4>
                    <p><c:out value="${step.description}" /></p>
                </c:forEach>

                <form action="<c:url value="/recipe"/>" method="post">
                    <button class="btn btn-bookmark" type="submit" name="add-bookmark">
                        <span class="icon" data-feather="bookmark"></span>
                        Épingler cette recette
                    </button>
                </form>
            </div>
        </div>
    </div>
</section>

<section id="recipe-suggestions">
    <c:if test="${not empty suggestedRecipes}">
        <div class="container">
            <h2>Vous aimerez peut-être aussi</h2>

            <div class="more-recipes">
                <div>
                    <c:forEach items="${suggestedRecipes}" var="suggestedRecipe">
                        <a class="card card-body horizontal-recipe-card" href="<c:url value="/recipe"><c:param name="id" value="${suggestedRecipe.id}" /></c:url>">
                            <h3 class="card-title"><c:out value="${suggestedRecipe.name}" /></h3>
                            <p class="score"><span class="icon" data-feather="star"></span> <strong><fmt:formatNumber value="${suggestedRecipe.rating}" maxFractionDigits="1" /></strong> sur 5</p>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
</section>

<section>
    <div class="container">
        <h2>Commentaires</h2>

        <div class="card card-body recipe-comment">
            <form action="<c:url value="/recipe"/>" method="post">
                <a class="comment-indicator" data-toggle="collapse" href="#comment-collapse" aria-expanded="false" aria-controls="#comment-collapse">
                    <p><span class="icon" data-feather="message-circle"></span>Commentez et notez cette recette</p>
                </a>
                <div id="comment-collapse" class="comment-box collapse">
                    <textarea class="form-control" rows="3" name="comment"><c:out value="${param.comment}" /></textarea>
                    <div class="rating">
                        <div id="recipe-rating"></div>
                        <input id="rating-input" type="hidden" name="rating" value="3">
                        <button class="btn btn-comment" type="submit" name="add-comment" value="<c:out value="${param.id}" />"><span class="icon" data-feather="edit-3"></span>Commenter</button>
                    </div>
                </div>
                <c:if test="${(not empty error) && (formName == 'comment')}">
                    <div class="alert alert-warning">
                        <c:out value="${error.description}" />
                    </div>
                </c:if>
            </form>
        </div>

        <c:forEach items="${recipe.comments}" var="comment">
            <div class="card card-body recipe-comment">
                <div class="comment-header">
                    <a class="comment-pseudo" href="<c:url value="/profile"><c:param name="id" value="${comment.userID}" /></c:url>">
                        <c:out value="${comment.pseudo}" />
                    </a>
                    <p class="comment-date"><fmt:formatDate value="${comment.publicationDate}" /></p>
                    <p class="comment-score"><span class="icon" data-feather="star"></span><c:out value="${comment.rating}" /></p>
                </div>
                <p><c:out value="${comment.description}" /></p>
            </div>
        </c:forEach>
    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
<script src="<c:url value="/js/emotion-ratings.min.js"/>"></script>
<script src="<c:url value="/js/recipe.js"/>"></script>
</body>
</html>
