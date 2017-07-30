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
		
			<h1>Select and Answer Security Questions</h1>
			<form:form cssClass="form" id="securityQuestionForm" method="post" modelAttribute="securityQuestionsForm" novalidate="novalidate" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">
			<div class="form--prompt">
				<p>Please choose 3 security questions and provide an answer to each.</p>
			</div>
       		<c:forEach var="i" begin="0" end="${numberOfQuestions - 1}">
       			<div class="row">
      		   <div class="column medium-3">
                  <form:label path="securityQuestionAnswers[${i}].securityQuestion" cssErrorClass="error">Question ${i + 1}</form:label>
               </div>
               <div class="column medium-9">
               	  <div class="select-wrapper">
               	  <select class="js-security-question-select">
					  <option value="-1">Select a Question</option>
					  <c:forEach items="${securityQuestions}" var="question">
					  	<c:choose>
					  		<c:when test="${!empty securityQuestionsForm.securityQuestionAnswers[i].securityQuestion && securityQuestionsForm.securityQuestionAnswers[i].securityQuestion eq question }">
					  			<option value="${question}" selected>${question.question}</option>
					  		</c:when>
					  		<c:otherwise>
					  			<option value="${question}">${question.question}</option>		
					  		</c:otherwise>
					  	</c:choose>
					  </c:forEach>
				  </select>
                  <form:errors path="securityQuestionAnswers[${i}].securityQuestion" cssClass="error" element="span" />
                  <form:hidden path="securityQuestionAnswers[${i}].securityQuestion" />
                  </div>
               </div>
            </div>
            <div class="row">
      		   <div class="column medium-3">
                  <form:label path="securityQuestionAnswers[${i}].answer" cssErrorClass="error">Answer ${i + 1}</form:label>
               </div>
               <div class="column medium-9">
                  <form:input path="securityQuestionAnswers[${i}].answer" cssErrorClass="error" maxlength="128" />
                  <form:errors path="securityQuestionAnswers[${i}].answer" cssClass="error" element="span" />
               </div>
            </div>
       		</c:forEach>
       		<div class="row">
      		   <div class="column medium-3 empty">
                  
               </div>
               <div class="column medium-9">
                  <button type="submit">Continue</button>
               </div>
            </div>
        	</form:form>

	</jsp:body>
</vistana:template>