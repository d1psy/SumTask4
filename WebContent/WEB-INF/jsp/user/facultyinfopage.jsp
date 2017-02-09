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
	<div class="panel panel-default">
	<div style="margin-left: 2%">
	</div>
	<div style="text-align:center"><h1><fmt:message key="facultyinfo.main" /></h1></div>
	<table class="table table-bordered" style="text-align: center;">
	<tr>
					<td><fmt:message key="facultyinfo.login" /></td>
					<td><fmt:message key="facultyinfo.surname" /></td>
					<td><fmt:message key="facultyinfo.name" /></td>
					<td><fmt:message key="facultyinfo.patron" /></td>
					<td><fmt:message key="facultyinfo.school" /></td>
					<td><fmt:message key="facultyinfo.city" /></td>
					<td><fmt:message key="facultyinfo.email" /></td>
				</tr>
			<c:forEach var="user" items="${budgetusers}">
				<tr bgcolor = "#00ff00"> 
					<td>${user.getLogin()}</td>
					<td>${user.getSurname()}</td>
					<td>${user.getName()}</td>
					<td>${user.getPatronymic()}</td>
					<td>${user.getSchool()}</td>
					<td>${user.getCity()}</td>
					<td>${user.getEmail()}</td>
					
				</tr>
			</c:forEach>
			<c:forEach var="user" items="${nonbudgetusers}">
				<tr bgcolor="#ffff00">
					<td>${user.getLogin()}</td>
					<td>${user.getSurname()}</td>
					<td>${user.getName()}</td>
					<td>${user.getPatronymic()}</td>
					<td>${user.getSchool()}</td>
					<td>${user.getCity()}</td>
					<td>${user.getEmail()}</td>
				</tr>
			</c:forEach>
			<c:forEach var="user" items="${otherusers}">
				<tr bgcolor="#ff0000">
					<td>${user.getLogin()}</td>
					<td>${user.getSurname()}</td>
					<td>${user.getName()}</td>
					<td>${user.getPatronymic()}</td>
					<td>${user.getSchool()}</td>
					<td>${user.getCity()}</td>
					<td>${user.getEmail()}</td>
				</tr>
			</c:forEach>
			
			</table>
			</div>
</jsp:body>
</t:casualpage>