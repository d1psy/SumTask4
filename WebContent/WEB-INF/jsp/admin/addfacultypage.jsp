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
<form action="Controller?action=addfaculty" method="post"  style="text-align: center;">
<h2><fmt:message key="addfaculty.main" /></h2><br />
<c:if test="${not empty param.error }">
<p class="bg-danger"><fmt:message key="addfaculty.invalid" /></p><br/>
</c:if>
<div class="form-group">
<fmt:message key="addfaculty.name" /><br /> <input type="text" class="form-control" name="dname" />
</div>
<div class="form-group">
<fmt:message key="addfaculty.place" /><br /> <input type="number" class="form-control" name="dplace" value = 0 />
</div>
<div class="form-group">
<fmt:message key="addfaculty.budget" /><br /> <input type="number" class="form-control" name="dbudget" value = 0 />
</div>
<div class="form-group">

<fmt:message key="addfaculty.s1" /><br />
<select id="subjects" name="dsub1">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
<div class="form-group">
<fmt:message key="addfaculty.s2" /><br /> <select id="subjects" name="dsub2">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
<div class="form-group">
<fmt:message key="addfaculty.s3" /><br /> 
<select id="subjects" name="dsub3">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
<br /><button type="submit" class="btn btn-default"><fmt:message key="addfaculty.add" /></button>
</form>
</div>
<div class="col-md-4"></div>
</jsp:body>
</t:casualpage>