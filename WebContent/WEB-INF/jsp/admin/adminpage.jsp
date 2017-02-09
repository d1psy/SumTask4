<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:casualpage>
	<jsp:body>

<div class="col-md-2"></div>
	<div class="col-md-8">
		You are${sessionScope.user.getLogin() }
		
	</div>
<div class="col-md-2"></div>
</jsp:body>
</t:casualpage>