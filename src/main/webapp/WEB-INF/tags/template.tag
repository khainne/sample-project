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


<!doctype html>
<html lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title><spring:message code="${title}" htmlEscape="true"/></title>
        <meta name="description" content="${description}">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/app.css"/>">
        
        <jsp:invoke fragment="header" />
	</head>
    <body class="${bodyCssClass}">
    	<header>
	    	<div class="vistana-logo">
	    		<vistana:svgVistanaLogo />
	    		<span class="vistana-logo--subtitle"><spring:message code="global.logo.subHeading" /></span> <span class="vistana-logo--title"><spring:message code="global.logo.heading" /></span> 
			</div>
		</header>
        <jsp:doBody/>

        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
		<jsp:invoke fragment="script" />
        <script>
            (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
            e=o.createElement(i);r=o.getElementsByTagName(i)[0];
            e.src='https://www.google-analytics.com/analytics.js';
            r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
            ga('create','UA-XXXXX-X','auto');ga('send','pageview');
        </script>
    </body>
</html>
