<%@ tag trimDirectiveWhitespaces="true" description="Demo Template Tag" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<spring:message code="global.login" var="loginLabel"/>

<div id="modal-session" class="modal js-modal">
   <a href="#" class="modal--close js-modal-close">
      <vistana:svgIcon height="24" width="24" icon="close" title="global.modal.close" />
   </a>
   <div class="modal--content">
      <h1>
         <spring:message code="session.modal.title"/>
      </h1>
      
         
         <div class="row">
               <div class="column medium-12">
                  <p><spring:message code="session.modal.message"/></p>
                  <span class="js-clock clock">
                  	<span class="clock--seconds-timer js-seconds-timer"></span>
                  	<span class="clock--seconds-label"><spring:message code="session.modal.seconds"/></span>
                  </span>
                <a href="#" class="button centered"><spring:message code="session.modal.button.imStillHere"/></a>
               </div>
         </div>
         
      
   </div>
</div>