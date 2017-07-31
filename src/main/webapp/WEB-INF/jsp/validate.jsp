<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<vistana:template title="validate.title">
	<jsp:body>
			<h1><spring:message code="validate.heading" /></h1>
			<form:form cssClass="form" id="validateUserForm" method="post" modelAttribute="validateUserForm" novalidate="novalidate" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">
       		<div class="form--prompt">
				<p><spring:message code="validate.form.prompt" /></p>
			</div>
       		<div class="row">
       			<div class="column medium-4">
                  <span class="label"><spring:message code="validate.label.question" htmlEscape="true"/></span>
               </div>
      		   <div class="column medium-8">
                  <p class="input-inline-text"><spring:message code="securityQuestionEnum.${validateUserForm.question}" /></p>
               </div>
            </div>
       		<div class="row">
      		   <div class="column medium-4">
                  <form:label path="answer" cssErrorClass="error"><spring:message code="validate.label.answer"/></form:label>
               </div>
               <div class="column medium-8">
                  <form:hidden path="question" />
                  <form:input path="answer" cssClass="js-auto-focus" cssErrorClass="error js-auto-focus" maxlength="128" />
                  <form:errors path="answer" cssClass="error" element="span" />
               </div>
            </div>
            <div class="row">
      		   <div class="column medium-4 empty">
                  
               </div>
               <div class="column medium-8">
                  <button type="submit"><spring:message code="validate.button.validate"/></button>
               </div>
            </div>
       		
        	</form:form>

	</jsp:body>
</vistana:template>