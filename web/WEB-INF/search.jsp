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

    <title>Recherche - Cook with ease</title>
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
            <input class="form-control" type="search" name="search" placeholder="Rechercher une recette">
            <div class="search-help d-none d-md-flex">
                <p class="help"><span class="icon" data-feather="filter"></span>Précédez vos mots-clés d'un # pour filtrer les résultats</p>
            </div>
        </form>

        <!-- Results -->
        <div class="row">
            <div class="col-sm-6 col-md-4">
                <a class="card recipe-card" href="<c:url value="/recipe"><c:param name="id" value="100" /></c:url>">
                    <span class="badge difficulty-easy">• Facile</span>
                    <img class="card-img-top" src="img/samples/soupe.jpg" alt="Illustration de recette">
                    <div class="card-body">
                        <h3 class="card-title">Velouté de champignons</h3>
                        <p class="score"><span class="icon" data-feather="star"></span> <strong>4.9</strong> sur 5</p>
                    </div>
                    <div class="card-footer">
                        <p><span class="icon" data-feather="clock"></span>50m</p>
                        <p><span class="icon" data-feather="user"></span>3 personnes</p>
                    </div>
                </a>
            </div>

        </div>
    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
<script src="<c:url value="/js/search.js"/>"></script>
</body>
</html>
