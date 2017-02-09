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
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="styles/styles.css">
</head>
<body>
	<div class="col-md-1"></div>
	<div class="col-md-10" id="content">
		<div id="container">
			<div id="header" class="col-md-12">
				<div class="col-md-8">
					<h2>
						<fmt:message key="masterpage.title" />
					</h2>
				</div>
				<div class="col-md-4 text-right">
					<div style="padding-top: 10%; padding-bottom: 10px;">
						<a href="changelang?lang=en-en"> English</a>|<a
							href="changelang?lang=ru-ru">Русский</a>
						<t:loginpanel></t:loginpanel>
					</div>
				</div>
			</div>
			<div id="menu" class="col-md-12">
				<t:menu></t:menu>
			</div>
			<div id="body" class="col-md-12"
				style="background-color: DCDCDC; padding: 30px;">
				<jsp:doBody />
			</div>
		</div>
	</div>
	<div class="col-md-1"></div>
</body>
</html>