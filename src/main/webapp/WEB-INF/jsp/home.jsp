<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<c:url value="/sign-up" var="signupURL" />

<vistana:template title="welcome.title">
	<jsp:attribute name="header">
		
	</jsp:attribute>
	<jsp:attribute name="script">
		<script>
			$(document).ready(function() {
				$('#validateUsernameForm').submit(function( event ) {
					event.preventDefault();
					var errorDisplay = $(".js-login-errors");
					var username = $(this).find( "input[name='username']" ).val();
					if (usernameIsValid(username)) {
						var url = $(this).attr("action");
						var response = $.post( url, { username: $(this).find( "input[name='username']" ).val() } );
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
						errorDisplay.prepend( "<span class=\"error\">" + message + "</span>" ).fadeIn('slow');
					}
				});
			});
			
			function usernameIsValid(username) {
				if(username.match(/^[a-zA-Z0-9]{5,12}$/)) {
					return true;
				}
				return false;
			}
		</script>
	</jsp:attribute>
	<jsp:body>
	<c:if test="${!empty session && !empty session.user && session.user.isValid()}">
		<div class="alert">
			<c:choose>
				<c:when test="${session.isLoggedIn}">
					<h1>Welcome ${session.user.username}!</h1>
					<p>You can visit <a href="<c:url value="/user-dashboard" />">your dashboard</a>, or <a href="<c:url value="/logout" />">logout</a></p>
				</c:when>
				<c:otherwise>
					<h1>New User Created!</h1>
					<p>Thanks for signing up, you can now <a href="#" data-modal-id="modal-login">login</a></p>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
			<h1>Welcome to the Sample Application</h1>
			<p>Thank you for trying out the sample application, if you already have an account please <a href="#" data-modal-id="modal-login">login</a>, if not you may <a href="${signupURL}">sign-up</a>.</p>
			<div class="button-group">
				<div class="row">
					<div class="column medium-6">
						<a href="#" class="button" data-modal-id="modal-login">Login</a>
					</div>
					<div class="column medium-6">
						<a href="${signupURL}" class="button secondary">Sign-Up</a>
					</div>
				</div>
			</div>
			<div class="modal-stage">
			<div id="modal-overlay" class="modal-overlay">
			</div>
				<div id="modal-login" class="modal js-modal">
				<a href="#" class="modal--close js-modal-close"><vistana:svgIcon height="24" width="24" icon="close" title="global.modal.close" /></a>
					<div class="modal--content">
						<h1>Login to Your Account</h1>
						<form action="<c:url value="/validate-username"/>" id="validateUsernameForm" novalidate="novalidate" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">
							<div class="modal--errors js-modal-error-display js-login-errors"></div>
							<div class="input-group">
								<div class="row">
									<div class="column medium-12">
										<label for="username">Username</label>
									</div>
								</div>
								<div class="row">
									<div class="column medium-12">
										<input id="username" name="username" type="text" maxlength="12" />
									</div>
								</div>
							</div>
							<button type="submit">Login</button>
						</form>
					</div>
				</div>
			</div>
	</jsp:body>
</vistana:template>