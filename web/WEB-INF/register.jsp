<%--
  Created by IntelliJ IDEA.
  User: Valentin BELYN
  Date: 22/04/2019
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="includes/head.jsp" %>

    <title>Inscription - Cook with ease</title>
</head>
<body>
<div id="login" class="container-fluid">
    <div class="row">
        <div id="login-section" class="col-12 col-sm-6 col-xl-4">
            <div id="login-box">
                <form action="<c:url value="/register"/>" method="post">
                    <h2>Inscription</h2>
                    <input class="form-control" type="text" name="first-name" placeholder="Prénom">
                    <input class="form-control" type="text" name="last-name" placeholder="Nom">
                    <input class="form-control" type="text" name="pseudo" placeholder="Nom d'utilisateur / pseudo">
                    <input class="form-control" type="text" name="email" placeholder="Adresse email">
                    <input class="form-control" type="password" name="password" placeholder="Mot de passe">
                    <input class="form-control" type="password" name="password-confirmation" placeholder="Confirmation du mot de passe">
                    <button class="btn btn-login" type="submit" name="register">Commencer</button>
                </form>
            </div>

            <footer>
                <div class="container">
                    <p>Copyright © 2019 Valentin BELYN, Cyril BELMONTE,<br>Vincent ARCHAMBAULT &amp; Loïc TRAMIS.</p>
                </div>
            </footer>
        </div>

        <div id="login-banner" class="d-none d-sm-flex col-sm-6 col-xl-8">
            <!-- Banner -->
        </div>
    </div>
</div>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
</body>
</html>
