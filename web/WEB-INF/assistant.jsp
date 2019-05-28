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
        <span class="icon mb-2" data-feather="github"></span>
        <p>Coming soon!</p>
    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
</body>
</html>
