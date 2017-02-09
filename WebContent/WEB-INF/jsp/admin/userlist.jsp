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
	<table class="table table-bordered" style="text-align: center;">
	<tr>
					<td><fmt:message key="userlist.login" /></td>
					<td><fmt:message key="userlist.surname" /></td>
					<td><fmt:message key="userlist.name" /></td>
					<td><fmt:message key="userlist.patron" /></td>
					<td><fmt:message key="userlist.school" /></td>
					<td><fmt:message key="userlist.city" /></td>
					<td><fmt:message key="userlist.email" /></td>
					<td><fmt:message key="userlist.blockstatus" /></td>
				</tr>
<c:forEach var="user" items="${userlist}">
				<tr class="active">
					<td>
					<a href="Controller?action=userprofile&user=${user.getId()}">
					${user.getLogin()}</a>
					</td>
					<td>${user.getSurname()}</td>
					<td>${user.getName()}</td>
					<td>${user.getPatronymic()}</td>
					<td>${user.getSchool()}</td>
					<td>${user.getCity()}</td>
					<td>${user.getEmail()}</td>
					<c:if test="${user.getRole().toString().toLowerCase()=='blocked'}">
						<td><fmt:message key="userlist.blocked" /></td>
						<td class = "active">
						<form action="Controller?action=unblockuser" method="post">
							<input type="hidden"
					 		value="${user.getId()}"
					 		name="user"
					 		/>
					 		<fmt:message key="userlist.unblock" var = "unblock"/>
						<input type="submit" value="${unblock}"/>
						</form>
					</c:if>
					<c:if test="${user.getRole().toString().toLowerCase()=='user'}">
						<td><fmt:message key="userlist.notblocked" /></td>
						<td class = "active">
							<form action="Controller?action=blockuser" method="post">
							<input type="hidden"
					 		value="${user.getId()}"
					 		name="user"
					 		/>
					 		<fmt:message key="userlist.block" var = "block" />
						<input type="submit" value="${block}"/>
						</form>
					</c:if>
				</tr>
			</c:forEach>
			</table>
			</div>
</jsp:body>
</t:casualpage>