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

<div class="col-md-2"></div>
	<div class="col-md-8">
	<div class="panel panel-default">
	<c:if test="${sessionScope.user.getRole().name()=='BLOCKED' }">
			<h1>
				<fmt:message key="profile.blocked" />
			</h1>
	</c:if>
	<c:if test="${sessionScope.user.getRole().name()!='BLOCKED' }">
		<h3>
		
			<fmt:message key="profile.name" />${sessionScope.user.getName() }<br />
			<fmt:message key="profile.email" />${sessionScope.user.getEmail() }<br />
			<c:choose>
    			<c:when test="${sessionScope.photo==null}">
    				<fmt:message key="profile.photo" />
    				<center>
        				<form method="post" action="Controller?action=UploadCommand&user=${sessionScope.user.getId()}&photo=avatar" enctype="multipart/form-data">
            				<fmt:message key="profile.choosephoto" /> <input type="file" name="uploadFile" />
            			<br/><br/>
            			<fmt:message key="profile.upload" var = "upload"/>
            			<input type="submit" value="${upload}"/>
        				</form>
    					</center>
        			<br />
    			</c:when>    
    			<c:otherwise>
					<img src="${sessionScope.photo}" width="50%" height="50%"/>
        			<br />
    			</c:otherwise>
			</c:choose>
		</h3>
	</c:if>
	</div>
	</div>
<div class="col-md-2"></div>
</jsp:body>
</t:casualpage>