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
    			<c:when test="${sessionScope.certPhoto==null}">
    				<fmt:message key="markspage.nophoto" />
    				<center>
        				<form method="post" action="Controller?action=UploadCommand&user=${sessionScope.user.getId()}&photo=cert" enctype="multipart/form-data">
            				<fmt:message key="profile.choosephoto" /> <input type="file" name="uploadFile" />
            			<br/><br/>
            			<fmt:message key="profile.upload" var = "upload"/>
            			<input type="submit" value="${upload}"/>
        				</form>
    					</center>
        			<br />
    			</c:when>    
    			<c:otherwise>
					<img src="${sessionScope.certPhoto}" width="50%" height="50%"/>
        			<br />
    			</c:otherwise>
			</c:choose>
			</div>
</jsp:body>
</t:casualpage>