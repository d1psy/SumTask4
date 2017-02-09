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
	<div class="col-md-4"></div>
	<div class="col-md-4">
<form action="Controller?action=adduser" method="post"  style="text-align: center;">
<h2><fmt:message
				key="register.main" /></h2><br />
<c:if test="${not empty param.error }">
<p class="bg-danger">Invalid field values!</p><br/>
</c:if>
<div class="form-group">
<fmt:message key="register.login" /><br /> <input type="text" class="form-control" name="plogin" />
</div>
<div class="form-group">
<fmt:message key="register.password" /><br /> <input type="password" class="form-control" name="ppassword" />
</div>
<div class="form-group">
<fmt:message key="register.name" /><br /> <input type="text" class="form-control" name="pname" />
</div>
<div class="form-group">
<fmt:message key="register.surname" /><br /> <input type="text" class="form-control" name="psurname" />
</div>
<div class="form-group">
<fmt:message key="register.patron" /><br /> <input type="text" class="form-control" name="ppatronymic" />
</div>
<div class="form-group">
<fmt:message key="register.email" /><br /> <input type="text" class="form-control" name="pemail" />
</div>
<div class="form-group">
<fmt:message key="register.region" /><br /> <input type="text" class="form-control" name="pregion" />
</div>
<div class="form-group">
<fmt:message key="register.city" /><br /> <input type="text" class="form-control" name="pcity" />
</div>
<div class="form-group">
<fmt:message key="register.school" /><br /> <input type="text" class="form-control" name="pschool" />
</div>
<br /><button type="submit" class="btn btn-default"><fmt:message key="register.button" /></button>
</form>
</div>
<div class="col-md-4"></div>
</jsp:body>
</t:casualpage>