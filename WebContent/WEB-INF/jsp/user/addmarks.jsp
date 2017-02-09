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
	<c:if test="${not empty param.error }">
	<p class="bg-danger"><fmt:message key="addfaculty.invalid" /></p><br/>
	</c:if>
<form action="Controller?action=addmarks&userid=${sessionScope.user.getId()}" method="post"  style="text-align: center;">
<h2><fmt:message key="addmarks.main" /></h2><br />

<div class="form-group">
<fmt:message key="addmarks.s1" /><br />
<select id="subjects" name="dsub1">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
<div class="form-group">
<fmt:message key="addmarks.mark" /><br /> <input type="number" class="form-control" name="dmark1" />
</div>
<div class="form-group">
<fmt:message key="addmarks.s2" /><br /> <select id="subjects" name="dsub2">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
<div class="form-group">
<fmt:message key="addmarks.mark" /><br /> <input type="number" class="form-control" name="dmark2" />
</div>
<div class="form-group">
<fmt:message key="addmarks.s3" /><br /> 
<select id="subjects" name="dsub3">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
<div class="form-group">
<fmt:message key="addmarks.mark" /><br /> <input type="number" class="form-control" name="dmark3" />
</div>
<div class="form-group">
<fmt:message key="addmarks.cert" /><br /> <input type="number" class="form-control" name="dcertmark" />
</div>
<br /><button type="submit" class="btn btn-default"><fmt:message key="addmarks.add" /></button>
</form>
</div>
<div class="col-md-4"></div>
</jsp:body>
</t:casualpage>