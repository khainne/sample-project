<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<vistana:template title="personal.title">
   <jsp:attribute name="script">
      <script src="<c:url value="/resources/js/jquery.inputmask.bundle.min.js"/>"></script>
      <script>
      $(window).load(function() {
    	    var date = [{
    	        "mask": "##/##/####"
    	    }];
    	    $('.js-date').inputmask({
    	        mask: date,
    	        greedy: false,
    	        placeholder: "dd/mm/yyyy",
    	        definitions: {
    	            '#': {
    	                validator: "[0-9]",
    	                cardinality: 1
    	            }
    	        }
    	    });
    	    $('.js-alpha-numeric').keypress(function(event) {
    	        return isAlphaNumeric(event, this)
    	    });
    	});

    	function isAlphaNumeric(evt, element) {
    	    var code = (evt.which) ? evt.which : event.keyCode
    	    if (!(code > 47 && code < 58) && // numeric (0-9)
    	        !(code > 64 && code < 91) && // upper alpha (A-Z)
    	        !(code > 96 && code < 123)) { // lower alpha (a-z)
    	        return false;
    	    } else {
    	        return true;
    	    }
    	}
      </script>
   </jsp:attribute>
   <jsp:body>
      <h1>
         <spring:message code="personal.heading" />
      </h1>
      <form:form method="post" cssClass="form" modelAttribute="userForm" novalidate="novalidate" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">
         <div class="form--prompt">
            <p>
               <spring:message code="personal.form.prompt" />
            </p>
         </div>
         <div class="row">
            <div class="column medium-3">
               <form:label path="username" cssErrorClass="error">
                  <spring:message code="personal.form.label.userName" />
               </form:label>
            </div>
            <div class="column medium-9">
               <div class="input-tooltip-wrapper">
                  <form:input path="username" cssClass="js-auto-focus js-alpha-numeric" cssErrorClass="error js-auto-focus js-alpha-numeric" maxlength="12" />
                  <span class="input-tooltip">
                     <spring:message code="personal.form.tooltip.userName" />
                  </span>
                  <form:errors path="username" cssClass="error" element="span" />
               </div>
            </div>
         </div>
         <div class="row">
            <div class="column medium-3">
               <form:label path="dob" cssErrorClass="error">
                  <spring:message code="personal.form.label.dob" />
               </form:label>
            </div>
            <div class="column medium-9">
               <form:input path="dob" cssClass="js-date" cssErrorClass="error js-date" maxlength="10" />
               <form:errors path="dob" cssClass="error" element="span" />
            </div>
         </div>
         <div class="row">
            <div class="column medium-3 empty">
            </div>
            <div class="column medium-9">
               <button type="submit">
                  <spring:message code="personal.form.button" />
               </button>
            </div>
         </div>
      </form:form>
   </jsp:body>
</vistana:template>