<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="vistana" tagdir="/WEB-INF/tags"%>

<vistana:template title="security.title">
   <jsp:attribute name="script">
      <script src="<c:url value="/resources/js/jquery.inputmask.bundle.min.js"/>"></script>
      <script>
      $(document).ready(function() {
          var securitySelects = $('.js-security-question-select');
          filterQuestions();
          securitySelects.on("change", function(e) {
              var select = $(this);
              var value = select.val();
              var hiddenInput = select.siblings('input[type=hidden]');
              if (value != -1) {
                  hiddenInput.val(value);
              } else {
                  hiddenInput.val('');
              }
              filterQuestions();
          });

          function filterQuestions() {
              securitySelects.each(function() {
                  $(this).find("span option").unwrap();
              });
              securitySelects.each(function(i) {
                  var currentSelect = $(this);
                  var currentSelectValue = currentSelect.val();
                  securitySelects.each(function(j) {
                      if (i != j && currentSelectValue != -1) {
                          $(this).find("option[value='" + currentSelectValue + "']").wrap('<span/>');
                      }
                  });
              });
          }
      });
      </script>
   </jsp:attribute>
   <jsp:body>
      <h1>
         <spring:message code="security.heading"/>
      </h1>
      <form:form cssClass="form" id="securityQuestionForm" method="post" modelAttribute="securityQuestionsForm" novalidate="novalidate" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false">
         <div class="form--prompt">
            <p>
               <spring:message code="security.form.prompt"/>
            </p>
         </div>
         <c:forEach var="i" begin="0" end="${numberOfQuestions - 1}">
            <div class="row">
               <div class="column medium-3">
                  <form:label path="securityQuestionAnswers[${i}].securityQuestion" cssErrorClass="error">
                     <spring:message code="security.form.label.question"/> ${i + 1}
                  </form:label>
               </div>
               <div class="column medium-9">
                  <div class="select-wrapper">
                     <select class="js-security-question-select">
                        <option value="-1">
                           <spring:message code="security.form.select.question.prompt"/>
                        </option>
                        <c:forEach items="${securityQuestions}" var="question">
                           <c:choose>
                              <c:when test="${!empty securityQuestionsForm.securityQuestionAnswers[i].securityQuestion && securityQuestionsForm.securityQuestionAnswers[i].securityQuestion eq question }">
                                 <option value="${question}" selected>
                                    <spring:message code="securityQuestionEnum.${question}"/>
                                 </option>
                              </c:when>
                              <c:otherwise>
                                 <option value="${question}">
                                    <spring:message code="securityQuestionEnum.${question}"/>
                                 </option>
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
                  <form:label path="securityQuestionAnswers[${i}].answer" cssErrorClass="error">
                     <spring:message code="security.form.label.answer"/> ${i + 1}
                  </form:label>
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
               <button type="submit">
                  <spring:message code="security.form.button"/>
               </button>
            </div>
         </div>
      </form:form>
   </jsp:body>
</vistana:template>