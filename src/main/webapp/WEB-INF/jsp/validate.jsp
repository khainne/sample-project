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
		
			<h1>Answer Security Question</h1>
			<form:form id="validateUserForm" method="post" modelAttribute="validateUserForm" novalidate="novalidate">
       		<div class="row">
      		   <div class="column medium-12">
                  <p>${validateUserForm.question.question}</p>
               </div>
            </div>
       		<div class="row">
      		   <div class="column medium-4">
                  <form:label path="answer" cssErrorClass="error">Your Answer</form:label>
               </div>
               <div class="column medium-8">
                  <form:hidden path="question" />
                  <form:input path="answer" cssErrorClass="error" />
                  <form:errors path="answer" cssClass="error" element="span" />
               </div>
            </div>
       		<button type="submit">Validate</button>
        	</form:form>

	</jsp:body>
</vistana:template>