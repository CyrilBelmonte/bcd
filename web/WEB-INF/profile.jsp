<%--
  Created by IntelliJ IDEA.
  User: Valentin BELYN
  Date: 22/04/2019
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="includes/head.jsp" %>

    <title><c:out value="${profile.firstName} ${profile.lastName}" /> - Cook with ease</title>
</head>
<body>
<header>
    <c:import url="includes/navbar.jsp">
        <c:param name="selected" value="" />
    </c:import>
</header>

<section id="profile">
    <div class="container">
        <h2><c:out value="${profile.firstName} ${profile.lastName}" /></h2>
        <p class="profile-pseudo">@<c:out value="${profile.pseudo}" /></p>
        <div class="profile-information flex-column flex-md-row">
            <p><span class="icon" data-feather="home"></span>Membre depuis le <fmt:formatDate value="${profile.inscriptionDate}" /></p>
            <p><span class="icon" data-feather="message-circle"></span><c:out value="${profile.commentsCount}" /> commentaires</p>
            <p><span class="icon" data-feather="user-plus"></span><c:out value="${profile.friendsCount}" /> amis</p>
            <p><span class="icon" data-feather="radio"></span><c:out value="${profile.bookmarksCount}" /> abonnements</p>
        </div>
        <c:choose>
            <c:when test="${not isFriend}">
                <form action="<c:url value="/profile"/>" method="post">
                    <button class="btn btn-follow" type="submit" name="follow" value="<c:out value="${param.id}" />"><span class="icon" data-feather="plus"></span>Suivre</button>
                </form>
            </c:when>
            <c:otherwise>
                <button class="btn btn-follow" type="button" disabled><span class="icon" data-feather="check"></span>Vous êtes amis</button>
            </c:otherwise>
        </c:choose>
    </div>
</section>

<section id="timeline">
    <div class="container">
        <h2>Timeline</h2>

        <c:if test="${profile.commentsCount == 0}">
            <p>Cet utilisateur n'a pas encore évalué de recettes.</p>
        </c:if>

        <c:forEach items="${profile.comments}" var="comment" varStatus="status">
            <c:if test="${comment.publicationDate != lastDate}">
                <hr>
            </c:if>

            <div class="row">
                <div class="col-sm-2">
                    <c:if test="${comment.publicationDate != lastDate}">
                        <p class="timeline-date"><fmt:formatDate value="${comment.publicationDate}" /></p>
                    </c:if>
                </div>

                <div class="col-sm-10">
                    <div class="card card-body profile-publication">
                        <div class="publication-header">
                            <a class="publication-name" href="<c:url value="/recipe"><c:param name="id" value="${comment.recipeID}" /></c:url>">
                                <c:out value="${comment.recipeName}" />
                            </a>
                            <p class="publication-score"><span class="icon" data-feather="star"></span><c:out value="${comment.rating}" /></p>
                        </div>
                        <p><c:out value="${comment.description}" /></p>
                    </div>
                </div>
            </div>

            <c:set var="lastDate" value="${comment.publicationDate}" />
        </c:forEach>

    </div>
</section>

<%@ include file="includes/footer.jsp" %>

<!-- JavaScript -->
<%@ include file="includes/javascript.jsp" %>
</body>
</html>
