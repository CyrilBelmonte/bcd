<%--
  Created by IntelliJ IDEA.
  User: Valentin BELYN
  Date: 23/04/2019
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="nav-scroller">
    <nav class="container nav">
        <a id="logo" class="nav-link d-none d-sm-block" href="<c:url value="/search"/>">
            <img src="<c:url value="/img/nav-logo.png"/>" alt="Cook with ease">
        </a>

        <a class="nav-link <c:out value="${param.selected == 'search' ? 'active' : ''}" />" href="<c:url value="/search"/>">Recherche</a>
        <a class="nav-link <c:out value="${param.selected == 'discover' ? 'active' : ''}" />" href="<c:url value="/discover"/>">Découverte</a>
        <a class="nav-link <c:out value="${param.selected == 'assistant' ? 'active' : ''}" />" href="<c:url value="/assistant"/>">Assistant</a>
        <a class="nav-link <c:out value="${param.selected == 'friends' ? 'active' : ''}" />" href="<c:url value="/friends"/>">Amis</a>
        <a class="nav-link <c:out value="${param.selected == 'bookmarks' ? 'active' : ''}" />" href="<c:url value="/bookmarks"/>">Favoris</a>
        <a class="nav-link <c:out value="${param.selected == 'settings' ? 'active' : ''}" /> ml-auto" href="<c:url value="/settings"/>"><span class="icon" data-feather="edit-2"></span></a>
        <a class="nav-link" href="<c:url value="/logout"/>"><span class="icon" data-feather="log-out"></span></a>
    </nav>
</div>