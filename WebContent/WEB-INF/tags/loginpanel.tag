<%@ tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language :'en-en'}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.text" />

<c:choose>
	<c:when test="${not empty sessionScope.user }">
		<form action="Controller?action=logout" method="post">
			<input type="submit" class="btn btn-primary"
				value="<fmt:message key="masterpage.logoutbutton" />" />
		</form>
	</c:when>
	<c:otherwise>
		<form>
			<a href="Controller?action=loginpage" class="btn btn-primary"> <fmt:message
					key="masterpage.loginbutton" /></a>
		</form>
	</c:otherwise>
</c:choose>