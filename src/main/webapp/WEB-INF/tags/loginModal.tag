<%@ tag trimDirectiveWhitespaces="true" description="login modal tag" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<spring:message code="global.login" var="loginLabel"/>

<div id="modal-login" class="modal js-modal">
   <a href="#" class="modal--close js-modal-close">
      <vistana:svgIcon height="24" width="24" icon="close" title="global.modal.close" />
   </a>
   <div class="modal--content">
      <h1>
         <spring:message code="welcome.login.heading"/>
      </h1>
      <form action="<c:url value="/validate-username"/>" id="validateUsernameForm" novalidate="novalidate" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">
         <div class="modal--errors js-modal-error-display js-login-errors"></div>
         <div class="input-group">
            <div class="row">
               <div class="column medium-12">
                  <label for="username">
                     <spring:message code="welcome.login.label.username"/>
                  </label>
               </div>
            </div>
            <div class="row">
               <div class="column medium-12">
                  <input id="username" name="username" type="text" maxlength="12" />
               </div>
            </div>
         </div>
         <button type="submit">${loginLabel}</button>
      </form>
   </div>
</div>