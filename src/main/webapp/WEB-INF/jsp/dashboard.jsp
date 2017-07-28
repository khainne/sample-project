<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<c:url value="/sign-up" var="signupURL" />

<vistana:template title="welcome.title">
	<jsp:attribute name="header">
		
	</jsp:attribute>
	<jsp:attribute name="script">
		
	</jsp:attribute>
	<jsp:body>
	<c:if test="${!empty session.user && session.isLoggedIn}">
		<h1>Welcome ${session.user.username}!</h1>
		<p>Thanks for logging in, your date of birth is <fmt:formatDate pattern="MMMM dd, yyyy" value="${session.user.dob}" /></p>
		<a href="<c:url value="/logout" />">Logout</a>
	</c:if>
	</jsp:body>
</vistana:template>