<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Info</title>
<link rel="stylesheet" type="text/css" href="/www/css/w3.css">
<link rel="stylesheet" type="text/css" href="/www/css/base.css">
<script type="text/javascript" src="/www/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/www/js/member/memberInfo.js"></script>
<script type="text/javascript" src="/www/js/member/memberList.js"></script>
<style type="text/css">
	.avtbox {
	
	}
	.w3-display-container {
		position: relative;
		top: 10px;
	}
	
	.pdl20 > h4 > span:nth-child(2) {
		position: relative;
		left: 20px;
	}
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<%--
		요청시 부가정보 전달용 태그
	 --%>
	 <form method="POST" action="" id="frm" name="frm">
	 	<input type="hidden" name="mno" id="smno">
	 	<input type="hidden" name="id" id="sid" value="${SID}">
	 </form>

<div class="w3-container">
  <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-black">Open Modal</button>

  <div id="id01" class="w3-modal">
    <div class="w3-modal-content">
      <header class="w3-container w3-teal"> 
        <span onclick="document.getElementById('id01').style.display='none'" 
        class="w3-button w3-display-topright">&times;</span>
        <h2>Modal Header</h2>
      </header>
      <div class="w3-container">
        <p>정말 삭제하시겠습니까?</p>
      </div>
      <footer class="w3-container w3-teal">
      	<div class="w3-right">
	        <div class="w3-button">예</div>
	        <div class="w3-button">아니오</div>
      	</div>
      </footer>
    </div>
  </div>
</div>

	<div class="w3-content mx650">
<c:if test="${DATA.gen ne 'F'}">
		<h1 class="w3-blue w3-center w3-padding w3-card-4">
	<c:if test="${DATA.id eq SID}">
			My Information
	</c:if>
	<c:if test="${DATA.id ne SID}">
			${DATA.name} 회원 정보
	</c:if>
		</h1>
</c:if>
<c:if test="${DATA.gen eq 'F'}">
		<h1 class="w3-pink w3-center w3-padding w3-card-4">
	<c:if test="${DATA.id eq SID}">
			My Information
	</c:if>
	<c:if test="${DATA.id ne SID}">
			${DATA.name} 회원 정보
	</c:if>
		</h1>
</c:if>
		<div class="w3-col w3-margin-top w3-padding w3-card-4">
			<div class="w3-display-container w3-col w3-border-right" style="width: 200px; height: 270px;">
				<div class="avtbox w3-display-middle">
					<img class="avtimg" src="/www/img/avatar/${DATA.savename}" id="infoavt">
				</div>
			</div>
			<div class="w3-rest w3-text-grey pdl20">
				<h4><span class="w3-col w100 w3-right-align">회원번호 : </span><span class="w3-rest w3-center" id="mno">${DATA.mno}</span></h4>
				<h4><span class="w3-col w100 w3-right-align">회원이름 : </span><span class="w3-rest w3-center" id="name">${DATA.name}</span></h4>
				<h4><span class="w3-col w100 w3-right-align">아 이 디 : </span><span class="w3-rest w3-center" id="id">${DATA.id}</span></h4>
				<h4><span class="w3-col w100 w3-right-align">전화번호 : </span><span class="w3-rest w3-center" id="tel">${DATA.tel}</span></h4>
				<h4><span class="w3-col w100 w3-right-align">이 메 일 : </span><span class="w3-rest w3-center" id="mail">${DATA.mail}</span></h4>
				<h4><span class="w3-col w100 w3-right-align">회원성별 : </span><span class="w3-rest w3-center" id="gen">${DATA.gen == 'M' ? "남자":"여자"}</span></h4>
				<h4><span class="w3-col w100 w3-right-align">가 입 일 : </span><span class="w3-rest w3-center" id="jdate">${DATA.sdate}</span></h4>
			</div>
		</div>
		<footer class="w3-col w3-margin-top w3-card-4">
<c:if test="${DATA.id eq SID}"><%-- 내 정보를 조회한 경우... --%>
			<div class="w3-third w3-button w3-green" id="lbtn">Member List</div>
			<div class="w3-third w3-button w3-blue" id="ebtn">edit</div>
			<div class="w3-third w3-button w3-red" id="dbtn" class="id01 w3-button w3-black" onclick="document.getElementById('id01').style.display='block'">회원탈퇴</div>
</c:if>
<c:if test="${DATA.id ne SID}"><%-- 다른 회원의 정보를 조회한 경우... --%>
			<div class="w3-col w3-button w3-green" id="lbtn">Member List</div>
</c:if>
		</footer>
	</div>
</body>
</html>