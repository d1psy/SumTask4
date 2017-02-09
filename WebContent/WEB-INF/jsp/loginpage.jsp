<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language :'en-en'}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.text" />
<t:casualpage>
	<jsp:body>
<div style="text-align: center;">
<c:if test="${not empty param.error }">
<p class="bg-danger">
				<fmt:message key="login.error" /></p><br/>
</c:if>
<h3>
				<fmt:message key="login.entersite" />
			</h3>
</div>
<br />
<div class="col-md-5"></div>
	<div class="col-md-2">
		<form action="Controller?action=login" style="text-align: center;"
				method="post">
				<div class="form-group">
				<fmt:message key="login.loginlabel" />
					<br /> <input type="text" class="form-control" name="username"><br />
				</div> 
				<div class="form-group">
				<fmt:message key="login.passwordlabel"></fmt:message>
				<br /><input type="password" class="form-control" name="password" /><br />
				</div> 
		<input value='<fmt:message key="login.loginbutton"></fmt:message>'
					type="submit" />
					
		</form>
		<form action="Controller?action=register" style="text-align: center;"
				method="post">
		<input value='<fmt:message key="login.registerbutton"></fmt:message>'
					type="submit" />
		</form>
	</div>
<div class="col-md-5"></div>
</jsp:body>
</t:casualpage>