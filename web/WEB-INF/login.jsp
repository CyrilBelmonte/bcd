<%--
  Created by IntelliJ IDEA.
  User: Valentin BELYN
  Date: 22/04/2019
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="includes/head.jsp" %>

    <title>Connexion - Cook with ease</title>
</head>
<body>
<div id="login" class="container-fluid">
    <div class="row">
        <div id="login-section" class="col-12 col-sm-6 col-xl-4">
            <div id="login-box">
                <form action="<c:url value="/login"/>" method="post">
                    <h2>Connexion</h2>
                    <input class="form-control" type="text" name="pseudo" value="<c:out value="${requestedUser.pseudo}" />" placeholder="Nom d'utilisateur">
                    <input class="form-control" type="password" name="password" placeholder="Mot de passe">
                    <button class="btn btn-login" type="submit" name="connection">Connexion</button>

                    <c:if test="${not empty error}">
                        <div class="alert alert-warning">
                            <c:out value="${error.description}" />
                        </div>
                    </c:if>

                    <a class="link" href="<c:url value="/register"/>">S'inscrire&nbsp;&rarr;</a>
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
