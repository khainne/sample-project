<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<vistana:template title="welcome.title">
	<jsp:attribute name="header">
		
	</jsp:attribute>
	<jsp:attribute name="script">
		
	</jsp:attribute>
	<jsp:body>
		<div class="content">
			<h1>Welcome to the Sample Application</h1>
			<p>Thank you for trying out the sample application, if you already have an account please <a href="">login</a>, if not you may <a href="">sign-up</a>.</p>
			<div class="button-group">
				<a href="#" class="button">Login</a>
				<a href="#" class="button secondary">Sign-Up</a>
			</div>
		</div>
	</jsp:body>
</vistana:template>