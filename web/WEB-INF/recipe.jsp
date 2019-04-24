<%--
  Created by IntelliJ IDEA.
  User: Valentin BELYN
  Date: 22/04/2019
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="includes/head.jsp" %>

    <title>Velouté de champignons - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="search" />
    </c:import>
</header>

<section id="recipe-header">
    <div class="container">
        <img class="recipe-picture" src="img/samples/soupe.jpg" alt="Illustration de recette">
        <h2>Velouté de champignons</h2>
        <div class="card card-body recipe-information">
            <p class="score"><span class="icon" data-feather="star"></span><strong>4.9</strong></p>
            <p><span class="icon" data-feather="clock"></span>50m</p>
            <p><span class="icon" data-feather="user"></span>3 personnes</p>
            <p class="d-none d-md-flex"><span class="icon" data-feather="bar-chart-2"></span>Facile</p>
            <p class="d-none d-md-flex"><span class="icon" data-feather="dollar-sign"></span>Cher</p>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4 recipe-ingredients mb-5 mb-md-0">
                <h3>Ingrédients</h3>
                <p><span class="icon" data-feather="hash"></span>3 cuillères à soupe de beurre</p>
            </div>

            <div class="col-12 col-md-7 offset-md-1 recipe-steps">
                <h3>Let's get started!</h3>
                <h4>Étape 1</h4>
                <p>Faire fondre dans une casserole 3 cuillères à soupe de beurre.</p>

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
    <div class="container">
        <h2>Vous aimerez peut-être aussi</h2>

        <div class="row">
            <div class="col-12 col-sm-4 col-md-3">
                <a class="card card-body recipe-suggestion" href="recipe.html">
                    <p>Soupe de champignons</p>
                </a>
            </div>
        </div>
    </div>
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
                    <textarea class="form-control" rows="3"></textarea>
                    <div class="rating">
                        <div id="recipe-rating"></div>
                        <input id="rating-input" type="hidden" name="rating" value="3">
                        <button class="btn btn-comment" type="submit" name="comment"><span class="icon" data-feather="edit-3"></span>Commenter</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="card card-body recipe-comment">
            <div class="comment-header">
                <a class="comment-pseudo" href="<c:url value="/profile"><c:param name="id" value="100" /></c:url>">Marie L.</a>
                <p class="comment-date">10/02/2019</p>
                <p class="comment-score"><span class="icon" data-feather="star"></span>5</p>
            </div>
            <p>Excellente recette. Je suis très contente du résultat.</p>
        </div>

    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
<script src="<c:url value="/js/emotion-ratings.min.js"/>"></script>
<script src="<c:url value="/js/recipe.js"/>"></script>
</body>
</html>
