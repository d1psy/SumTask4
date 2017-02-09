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
<form action="Controller?action=editfaculty" method="post"  style="text-align: center;">
<h2><fmt:message key="editfaculty.main" /></h2><br />
<c:if test="${not empty param.error }">
<p class="bg-danger"><fmt:message key="editfaculty.invalid" /></p><br/>
</c:if>
<c:set var="faculty" value="${faculty}"/>
<div class="form-group">
<fmt:message key="editfaculty.name" /><br /> <input type="text" class="form-control" name="dname" value = "${faculty.getName()}"/>
</div>
<div class="form-group">
<fmt:message key="editfaculty.place" /><br /> <input type="number" class="form-control" name="dplace" value = "${faculty.getMaxplace()}" />
</div>
<div>
<input type = "hidden" name = "count" value = "${faculty.getCount() }">
</div>
<div class="form-group">
<fmt:message key="editfaculty.budget" /><br /> <input type="number" class="form-control" name="dbudget" value = "${faculty.getBudget()}" />
</div>
<div class="form-group">
<fmt:message key="editfaculty.s1" /><br />
<select id="subjects" name="dsub1">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
<div class="form-group">
<fmt:message key="editfaculty.s2" /><br /> <select id="subjects" name="dsub2">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
<div class="form-group">
<fmt:message key="editfaculty.s3" /><br /> 
<select id="subjects" name="dsub3">
	<c:forEach var="subjects" items="${subjects}" >
			<option value ="<c:out value="${subjects.getId()}"/>" selected="selected">${subjects.getName()}</option>
	</c:forEach>
	</select>
</div>
 <input type="hidden" name="did" value="${faculty.getId()}">
<br /><button type="submit" class="btn btn-default"><fmt:message key="editfaculty.edit" /></button>
</form>
</div>
<div class="col-md-4"></div>
</jsp:body>
</t:casualpage>