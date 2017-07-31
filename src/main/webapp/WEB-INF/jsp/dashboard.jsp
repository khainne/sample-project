<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<c:url value="/sign-up" var="signupURL" />
<fmt:formatDate pattern="MMMM dd, yyyy" value="${session.user.dob}" var="formattedDob" />

<vistana:template title="dashboard.title">
	<jsp:body>
	<c:if test="${!empty session.user && session.isLoggedIn}">
		<h1><spring:message code="dashboard.heading" htmlEscape="true" arguments="${session.user.username}" /></h1>
		<p><spring:message code="dashboard.message" htmlEscape="true" arguments="${formattedDob}" argumentSeparator=";" /></p>
		<a class="button centered" href="<c:url value="/logout" />"><spring:message code="global.logout" /></a>
	</c:if>
	</jsp:body>
</vistana:template>