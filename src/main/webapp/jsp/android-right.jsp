<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/android-right.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/android-right.css">

<body>
	<div id="rightLine">
		<div class="p-first-word" > 
			<img src="${pageContext.request.contextPath}/image/Wechat.png" style="width:1.875rem;height:1.8rem">
			<p>扫码下载app</p>
		</div>
		
		<div id="rightBar">
			<!-- 触发模态框的div -->
			<div class="android-div" data-toggle="modal" data-target="#help_modal">
				<img src="${pageContext.request.contextPath}/image/AppUrl.jpg" style="width:5rem;height:5rem">
				<center><p>下载app</p></center>
			</div>
			
		</div>
	</div>

</body>
</html>