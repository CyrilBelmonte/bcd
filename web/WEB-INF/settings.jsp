<%--
  Created by IntelliJ IDEA.
  User: Valentin BELYN
  Date: 22/04/2019
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="includes/head.jsp" %>

    <title>Préférences - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="settings" />
    </c:import>
</header>

<section>
    <div class="container">
        <form action="<c:url value="/settings"/>" method="post">
            <div class="card card-hover settings">
                <div class="card-body">
                    <h2 class="icon" data-feather="user"></h2>

                    <label for="first-name">Prénom</label>
                    <input id="first-name" class="form-control" type="text" name="first-name">

                    <label for="last-name">Nom</label>
                    <input id="last-name" class="form-control" type="text" name="last-name">

                    <label for="email">Adresse email</label>
                    <input id="email" class="form-control" type="text" name="email">
                </div>
                <div class="card-footer">
                    <button class="btn btn-apply" type="submit" name="apply-user-settings">Appliquer</button>
                </div>
            </div>

            <div class="card card-hover settings">
                <div class="card-body">
                    <h2 class="icon" data-feather="shield"></h2>

                    <label for="current-password">Mot de passe actuel</label>
                    <input id="current-password" class="form-control" type="password" name="current-password">

                    <label for="new-password">Nouveau mot de passe</label>
                    <input id="new-password" class="form-control" type="password" name="password">

                    <label for="password-confirmation">Confirmation</label>
                    <input id="password-confirmation" class="form-control" type="password" name="password-confirmation">
                </div>
                <div class="card-footer">
                    <button class="btn btn-apply" type="submit" name="apply-password">Appliquer</button>
                </div>
            </div>

            <div class="card card-hover settings">
                <div class="card-body">
                    <h2 class="icon" data-feather="codesandbox"></h2>
                    <p>Bientôt de nouvelles options de personnalisation !</p>
                </div>
            </div>
        </form>

    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
</body>
</html>
