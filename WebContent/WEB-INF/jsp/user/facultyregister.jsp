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
<c:if test="${not empty param.error }">
<p class="bg-danger"><fmt:message key="addfaculty.invalid" /></p><br/>
</c:if>
<t:casualpage>
	<jsp:body>
	<div class="panel panel-default">
	<div style="margin-left: 2%">
	</div>
	<table class="table table-bordered" style="text-align: center;">
	<tr>
					<td><fmt:message key="facultyregister.name" /></td>
					<td><fmt:message key="facultyregister.place" /></td>
					<td><fmt:message key="facultyregister.budget" /></td>
				</tr>
<c:set var="userid" value="${userid}"/>
<c:set var="marks" value="${marks}"/>
<c:set var="reg" value="${facreg}"/>
<c:forEach var="faculty" items="${facultylist}">
<c:set var="checkReg" value="${false}"/>
				<tr class="active">
					<td>
						<a href = "Controller?action=facultyinfopage&faculty=${faculty.getId()}">${faculty.getName()}</a>
					</td>
					<td>${faculty.getMaxplace()}</td>
					<td>${faculty.getBudget()}</td>
					<c:forEach var="regs" items="${facreg}">
					<c:if test ="${faculty.getId() == regs.getFacultyId()}">
					   <c:set var="checkReg" value="${true}"/>
					</c:if>
					</c:forEach>
					<c:if test="${checkReg}">
                       <td>
                            <fmt:message key="facultyregister.registered" />
                       </td>
                    </c:if>
					<c:if test="${checkReg eq false}">
                       <td class = "active">
                                <form action="Controller?action=joinfaculty" method="post">
                                    <input type="hidden"
                                    value="${faculty.getId()}"
                                    name="faculty"
                                    />
                                    <input type="hidden"
                                    value = "${marks}"
                                    name="marks"
                                    />
                                    <input type="hidden"
                                    value="${userid}"
                                    name="userid"
                                    />
                                    <fmt:message key="facultyregister.register" var = "register" />
                                    <input type="submit" value="${register}"/>
                                </form>
                        </td>
                    </c:if>
					
				</tr>
			</c:forEach>
			</table>
			</div>
</jsp:body>
</t:casualpage>