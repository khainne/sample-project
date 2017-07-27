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
		
			<h1>Sign Up for an Account</h1>
			<p>To get started, let's collect some of your personal information.</p>
			<form:form method="post" modelAttribute="userForm" novalidate="novalidate">
       		<div class="row">
      		   <div class="column medium-4">
                  <form:label path="username" cssErrorClass="error">Username</form:label>
               </div>
               <div class="column medium-8">
                  <form:input path="username" cssErrorClass="error" maxlength="12" />
                  <form:errors path="username" cssClass="error" element="span" />
               </div>
            </div>
            <div class="row">
      		   <div class="column medium-4">
                  <form:label path="dob" cssErrorClass="error">Date of Birth</form:label>
               </div>
               <div class="column medium-8">
                  <form:input path="dob" cssErrorClass="error" maxlength="10" />
                  <form:errors path="dob" cssClass="error" element="span" />
               </div>
            </div>
            <button type="submit">Continue</button>
        	</form:form>

	</jsp:body>
</vistana:template>