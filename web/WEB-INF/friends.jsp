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

            <c:if test="${not empty error}">
                <p><c:out value="${error.description}" /></p>
            </c:if>

            <c:forEach items="${friends}" var="friend">
                <div class="card card-body card-hover friend">
                    <p class="friend-name"><span class="icon" data-feather="user"></span><c:out value="${friend.firstName} ${friend.lastName}" /></p>
                    <a class="btn btn-display" href="<c:url value="/profile"><c:param name="id" value="${friend.id}" /></c:url>"><span class="icon" data-feather="home"></span>Profil</a>
                    <button class="btn btn-delete" type="submit" name="delete" value="<c:out value="${friend.id}" />"><span class="icon" data-feather="eye-off"></span>Ne plus suivre</button>
                </div>
            </c:forEach>

            <c:if test="${not empty suggestedFriends}">
                <p class="help">Nous avons trouvé des membres qui partagent les mêmes goûts que vous !</p>

                <c:forEach items="${suggestedFriends}" var="suggestedFriend">
                    <div class="card card-body card-hover friend">
                        <p class="friend-name"><span class="icon" data-feather="user"></span><c:out value="${suggestedFriend.firstName} ${suggestedFriend.lastName}" /></p>
                        <a class="btn btn-display" href="<c:url value="/profile"><c:param name="id" value="${suggestedFriend.id}" /></c:url>"><span class="icon" data-feather="home"></span>Profil</a>
                    </div>
                </c:forEach>
            </c:if>

        </form>
    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
</body>
</html>
