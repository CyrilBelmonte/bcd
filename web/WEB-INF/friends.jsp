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

    <title>Amis - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="friends" />
    </c:import>
</header>

<section>
    <div class="container">
        <form action="<c:url value="/friends"/>" method="post">
            <h2>Amis</h2>

            <div class="card card-body card-hover friend">
                <p class="friend-name"><span class="icon" data-feather="user"></span>John Doe</p>
                <a class="btn btn-display" href="<c:url value="/profile"><c:param name="id" value="100" /></c:url>"><span class="icon" data-feather="home"></span>Profil</a>
                <button class="btn btn-delete" type="submit" name="delete" value="1"><span class="icon" data-feather="eye-off"></span>Ne plus suivre</button>
            </div>

        </form>
    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
</body>
</html>
