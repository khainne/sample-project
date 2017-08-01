<%@ tag trimDirectiveWhitespaces="true" description="Demo Template Tag" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<%@ attribute name="title" required="true"%>
<%@ attribute name="description" required="false"%>
<%@ attribute name="bodyCssClass" required="false"%>
<%@ attribute name="header" required="false" fragment="true"%>
<%@ attribute name="script" required="false" fragment="true"%>
<%@ attribute name="modal" required="false" fragment="true" %>

<!doctype html>
<html lang="<c:out value="${pageContext.response.locale}"/>">
   <head>
      <meta charset="utf-8">
      <meta http-equiv="x-ua-compatible" content="ie=edge">
      <title>
         <spring:message code="${title}" htmlEscape="true"/>
      </title>
      <meta name="description" content="${description}">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/app.css"/>">
      <jsp:invoke fragment="header" />
   </head>
   <body class="${bodyCssClass}">
      <header>
         <div class="vistana-logo">
            <vistana:svgVistanaLogo />
            <span class="vistana-logo--subtitle">
               <spring:message code="global.logo.subHeading" />
            </span>
            <span class="vistana-logo--title">
               <spring:message code="global.logo.heading" />
            </span>
         </div>
      </header>
      <div class="content">
         <jsp:doBody/>
         <div class="modal-stage">
            <div id="modal-overlay" class="modal-overlay"></div>
            <jsp:invoke fragment="modal" />
            <vistana:sessionExpireModal />
         </div>
      </div>
      <script>
         var context = "<c:url value="/" />";
         var local = "<c:out value="${pageContext.response.locale}"/>"; 
         var loggedIn = ${sessionScope['scopedTarget.applicationSession'].isLoggedIn}
      </script>
      <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
      <script src="<c:url value="/resources/js/jquery.i18n.properties.min.js"/>"></script>
      <script src="<c:url value="/resources/js/app.js"/>"></script>
      <jsp:invoke fragment="script" />
   </body>
</html>