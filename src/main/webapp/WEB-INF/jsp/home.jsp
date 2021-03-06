<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<c:url value="/sign-up" var="signupURL" />
<c:url value="/user-dashboard" var="dashboardURL" />
<c:url value="/logout" var="logoutURL" />

<spring:message code="welcome.loggedIn.dashboard" var="dashboardLabel"/>
<spring:message code="global.logout" var="logoutLabel"/>
<spring:message code="global.login" var="loginLabel"/>
<spring:message code="global.signUp" var="signupLabel"/>

<vistana:template title="welcome.title">
   <jsp:attribute name="script">
      <script>
      $(document).ready(function() {
    	    $('#validateUsernameForm').submit(function(event) {
    	        event.preventDefault();
    	        var errorDisplay = $(".js-login-errors");
    	        var username = $(this).find("input[name='username']").val();
    	        if (usernameIsValid(username)) {
    	            var url = $(this).attr("action");
    	            var response = $.post(url, {
    	                username: $(this).find("input[name='username']").val()
    	            });
    	            response.done(function() {
    	                var isValidUser = response.responseJSON;
    	                if (isValidUser) {
    	                    window.location = "<c:url value='/validate' />";
    	                } else {
    	                    displayError(error.login.invalid);
    	                }
    	            }).fail(function() {
    	                displayError(error.login.failure);
    	            })
    	        } else {
    	            displayError(error.login.invalidUsernameFormat);
    	        }

    	        function displayError(message) {
    	            errorDisplay.empty();
    	            errorDisplay.prepend("<span class=\"error\">" + message + "</span>").show();
    	        }
    	    });
    	});

    	function usernameIsValid(username) {
    	    if (username.match(/^[a-zA-Z0-9]{5,12}$/)) {
    	        return true;
    	    }
    	    return false;
    	}
      </script>
   </jsp:attribute>
   <jsp:attribute name="modal">
      <vistana:loginModal />
   </jsp:attribute>
   <jsp:body>
      <c:choose>
               <c:when test="${!empty sessionScope['scopedTarget.applicationSession'] && !empty sessionScope['scopedTarget.applicationSession'].user && sessionScope['scopedTarget.applicationSession'].isLoggedIn}">
                  <div class="alert">
                  <h1>
                     <spring:message code="welcome.loggedIn.heading" htmlEscape="true" arguments="${sessionScope['scopedTarget.applicationSession'].user.username}"/>
                  </h1>
                  <p><spring:message code="welcome.loggedIn.message" arguments="<a href=\"${dashboardURL}\">${dashboardLabel}</a>,<a href=\"${logoutURL}\">${logoutLabel}</a>"/></p>
                  </div>
               </c:when>
               <c:when test="${!empty sessionScope['scopedTarget.applicationSession'] && !empty sessionScope['scopedTarget.applicationSession'].newUser && sessionScope['scopedTarget.applicationSession'].newUser.isValid()}">
                  <div class="alert">
                  <h1>
                     <spring:message code="welcome.newUser.heading" htmlEscape="true"/>
                  </h1>
                  <p><spring:message code="welcome.newUser.message" arguments="<a href=\"#\" data-modal-id=\"modal-login\">${loginLabel}</a>"/></p>
                  </div>
               </c:when>
      </c:choose>
      <h1>
         <spring:message code="welcome.heading"/>
      </h1>
      <p><spring:message code="welcome.message" arguments="<a href=\"#\" data-modal-id=\"modal-login\">${loginLabel}</a>,<a href=\"${signupURL}\">${signupLabel}</a>"/></p>
      <div class="button-group">
         <div class="row">
            <div class="column medium-6">
               <a href="#" class="button" data-modal-id="modal-login">${loginLabel}</a>
            </div>
            <div class="column medium-6">
               <a href="${signupURL}" class="button secondary">${signupLabel}</a>
            </div>
         </div>
      </div>
      <a href="<c:url value="?lang=en"/>">English</a> | <a href="<c:url value="?lang=es"/>">Spanish</a>
   </jsp:body>
</vistana:template>