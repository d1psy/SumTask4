<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language :'en-en'}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.text" />
<c:if test="${not empty sessionScope.user }">
	<c:if test="${sessionScope.user.getRole().name()!='BLOCKED'}">
		<a href="Controller?action=profile" class="btn btn-info"><fmt:message
				key="menu.profile" /></a>
		<c:if test="${sessionScope.user.getRole().name()=='ADMIN' }">
			<a href="Controller?action=addfacultypage" class="btn btn-info"> <fmt:message
					key="menu.addfaculty" />
			</a>
			<a href="Controller?action=userlist" class="btn btn-info"><fmt:message
					key="menu.uslist" /></a>
			<a href="Controller?action=facultylist" class="btn btn-info"><fmt:message
					key="menu.faclist" /></a>
		</c:if>
		<c:if test="${sessionScope.user.getRole().name()=='USER' }">
			<a href="Controller?action=markspage&usrid=${sessionScope.user.getId()}" class="btn btn-info">
				<fmt:message key="menu.mymarks" /></a></a>
			<a href="Controller?action=facultyregisterpage&usrid=${sessionScope.user.getId()}" class="btn btn-info">
				<fmt:message key="menu.register" /></a>
		</c:if>
	</c:if>
</c:if>
