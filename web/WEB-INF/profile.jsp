<%--
  Created by IntelliJ IDEA.
  User: Valentin BELYN
  Date: 22/04/2019
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="includes/head.jsp" %>

    <title>John Doe - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="" />
    </c:import>
</header>

<section id="profile">
    <div class="container">
        <h2>John Doe</h2>
        <p class="profile-pseudo">@johndoe</p>
        <div class="profile-information flex-column flex-md-row">
            <p><span class="icon" data-feather="home"></span>Membre depuis le 20/02/2017</p>
            <p><span class="icon" data-feather="message-circle"></span>16 commentaires</p>
            <p><span class="icon" data-feather="user-plus"></span>14 amis</p>
            <p><span class="icon" data-feather="radio"></span>3 abonnements</p>
        </div>
        <form action="<c:url value="/profile"/>" method="post">
            <button class="btn btn-follow" type="submit" name="button"><span class="icon" data-feather="plus"></span>Suivre</button>
        </form>
    </div>
</section>

<section id="timeline">
    <div class="container">
        <h2>Timeline</h2>

        <hr>

        <div class="row">
            <div class="col-sm-2">
                <p class="timeline-date">12/01/2019</p>
            </div>
            <div class="col-sm-10">
                <div class="card card-body profile-publication">
                    <div class="publication-header">
                        <a class="publication-name" href="<c:url value="/recipe"><c:param name="id" value="100" /></c:url>">Verrine de mousse au chèvre</a>
                        <p class="publication-score"><span class="icon" data-feather="star"></span>5</p>
                    </div>
                    <p>Super recette, tout le monde a adoré ! J'ai fait à la pommes et d'autres aux tomates ! La pomme a fait fondre tout le monde !</p>
                </div>

                <div class="card card-body profile-publication">
                    <div class="publication-header">
                        <a class="publication-name" href="<c:url value="/recipe"><c:param name="id" value="100" /></c:url>">Mousse banane et kiwi</a>
                        <p class="publication-score"><span class="icon" data-feather="star"></span>4</p>
                    </div>
                    <p>Excellent mais ne se conserve pas longtemps.</p>
                </div>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col-sm-2">
                <p class="timeline-date">10/01/2019</p>
            </div>
            <div class="col-sm-10">
                <div class="card card-body profile-publication">
                    <div class="publication-header">
                        <a class="publication-name" href="<c:url value="/recipe"><c:param name="id" value="100" /></c:url>">Dinde farcie aux marrons</a>
                        <p class="publication-score"><span class="icon" data-feather="star"></span>5</p>
                    </div>
                    <p>Très bonne recette. La farce est un délice. Au lieu de beurre et huile d’olive, j’y mets une cuillerée de graisse de canard.</p>
                </div>
            </div>
        </div>

        <hr>

        <div class="row">
            <div class="col-sm-2">
                <p class="timeline-date">09/01/2019</p>
            </div>
            <div class="col-sm-10">
                <div class="card card-body profile-publication">
                    <div class="publication-header">
                        <a class="publication-name" href="<c:url value="/recipe"><c:param name="id" value="100" /></c:url>">Cheesecake coeur coulant aux fraises</a>
                        <p class="publication-score"><span class="icon" data-feather="star"></span>5</p>
                    </div>
                    <p>J'ai fait la recette en mixant des fraises en boîte, le coulis à donc été très facile à réaliser. J'ai mis tout ça dans de jolis moules en silicones le résultat était vraiment très très joli. Il faut rajouter un peu de coulis de fraises au dessus.</p>
                </div>
            </div>
        </div>

    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
</body>
</html>
