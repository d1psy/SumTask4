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
	<a href="Controller?action=facultylist&sort=namesort"
					class="btn btn-primary">
	<fmt:message key="facultylist.sortbyname" />
	</a>
	<a href="Controller?action=facultylist&sort=placesort"
					class="btn btn-primary">
	<fmt:message key="facultylist.sortbyplace" />
	</a>
	<a href="Controller?action=facultylist&sort=budgetsort"
					class="btn btn-primary">
	<fmt:message key="facultylist.sortbybudget" />
	</a><br/>
	<a href="Controller?action=facultylist&sort=countsort"
					class="btn btn-primary">
	Sort by count
	</a><br/>
	<div class="panel panel-default">
	<div style="margin-left: 2%">
	</div>
	<table class="table table-bordered" style="text-align: center;">
	<tr>
					<td><fmt:message key="facultylist.name" /></td>
					<td><fmt:message key="facultylist.place" /></td>
					<td><fmt:message key="facultylist.budget" /></td>
					<td>user count</td>
					<td><fmt:message key="facultylist.s1" /></td>
					<td><fmt:message key="facultylist.s2" /></td>
					<td><fmt:message key="facultylist.s3" /></td>
				</tr>
				
<c:forEach var="faculty" items="${facultylist}">
				<tr class="active">
					<td>
						<a href = "Controller?action=facultyinfoadmin&faculty=${faculty.getId()}">${faculty.getName()}</a>
					</td>
					<td>${faculty.getMaxplace()}</td>
					<td>${faculty.getBudget()}</td>
					<td>${faculty.getCount()}</td>
					<td>${faculty.getSub1()}</td>
					<td>${faculty.getSub2()}</td>
					<td>${faculty.getSub3()}</td>
					<td class = "active">
						<form action="Controller?action=deletefaculty" method="post">
							<input type="hidden"
					 		value="${faculty.getId()}"
					 		name="faculty"
					 		/>
					 		<fmt:message key="facultylist.delete" var = "delete"/>
						<input type="submit" value="${delete}"/>
						</form>
					</td>
					<td>
						<a href="Controller?action=editfacultypage&faculty=${faculty.getId()}">
						<input type="hidden"
					 		value="${faculty.getId()}"
					 		name="faculty"
					 		/>
						<fmt:message key="facultylist.edit" /></a>
					</td>
				</tr>
			</c:forEach>
			</table>
			</div>
</jsp:body>
</t:casualpage>