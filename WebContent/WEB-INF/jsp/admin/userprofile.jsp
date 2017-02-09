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
<form action="Controller?action=userprofile" method="post"  style="text-align: center;">
<c:if test="${sessionScope.language == 'en-en' }">
<h2> ${user.getLogin()}<fmt:message key="userprofile.page" /></h2><br />
</c:if>
<c:if test="${sessionScope.language == 'ru-ru' }">
<h2><fmt:message key="userprofile.page" /> ${user.getLogin()}</h2><br />
</c:if>

<h1>${user.getSurname()} ${user.getName()} ${user.getPatronymic()}</h1>
<c:choose>
    			<c:when test="${path==null}">
    				<td><fmt:message key="userprofile.nophoto" /></td>
    			</c:when>    
    			<c:otherwise>
					<img src="${path}" width="100%" height="50%"/>
        			<br />
    			</c:otherwise>
</c:choose>
<div>
<c:if test="${empty marks}">
    <fmt:message key="userprofile.nomarks" />
</c:if>
<c:if test="${not empty marks}">
   <table class="table table-bordered" style="text-align: center;">
<c:set var="mark" value="${marks}"/>
	<tr>
					<td>${mark.getSub1ID()}</td>
					<td>${mark.getSub2ID()}</td>
					<td>${mark.getSub3ID()}</td>
					<td><fmt:message key="markspage.cert" /></td>
				</tr>
				
				<tr class="active">
					<td>${mark.getMark1()}</td>
					<td>${mark.getMark2()}</td>
					<td>${mark.getMark3()}</td>
					<td>${mark.getCert()}</td>
				</tr>
			</table>
			<c:choose>
    			<c:when test="${cert==null}">
    				<td><fmt:message key="userprofile.nocertphoto" /></td>
    			</c:when>    
    			<c:otherwise>
					<img src="${cert}" width="100%" height="50%"/>
        			<br />
    			</c:otherwise>
</c:choose>
</c:if>
</div>
<div>
</div>
<c:set var="faculty" value="${faculty}"/>
<div>
</div>
<div>
<fmt:message key="userprofile.lives" />: ${user.getCity()}, ${user.getRegion()}
</div>
<div>
<fmt:message key="userprofile.school" />: ${user.getSchool()}
</div>
<div>
Email: ${user.getEmail()}
</div>
</form>
</div>
<div class="col-md-4"></div>
</jsp:body>
</t:casualpage>