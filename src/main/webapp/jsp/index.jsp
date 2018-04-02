<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jQuery-2.1.4.min.js"></script>
    <title>DCG CLOUD</title>
</head>

<style>
    .container{
        width:50rem;
        margin:7% auto;
    }
</style>
<body>

<%--右侧的二维码扫描下载功能--%>
<jsp:include page="android-right.jsp"/>


<div class="container">
    <div style="width:60%;display:inline-block;">
        <img src="${pageContext.request.contextPath}/image/111.jpg" style="height:31.25rem">
    </div>
    <div style="text-align:left;display:inline-block;height:31.5rem;vertical-align:top;
    padding:3.75rem 0 0 1.25rem;font-size: 1.5rem;">
        <p>你</p>
        <p>一会儿看我</p>
        <p>一会儿看云
        <p>我觉得</p>
        <p>你看我时很远</p>
        <p>你看云时很近。</p>

    </div>
    Copyright DCG

</div>

</body>
</html>