<%-- 
    Document   : header
    Created on : Aug 29, 2022, 2:07:25 AM
    Author     : HIEN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <script>
            function showNoti(){
                console.log("hsifhj");
                var notiArea = document.getElementById('showNotiArea');
                if(notiArea.style.display=="none"){
                    notiArea.style.display = "block";
                }
                else{
                    notiArea.style.display = "none";
                }
            }
        </script>
        <sec:authorize access="hasAuthority('STUDENT')">
            <c:url value="/student/thesisInfo/${currentUser.studentId.thesisId.id}" var="thesisUrl"></c:url>
        </sec:authorize>
        <sec:authorize access="hasAnyAuthority('ADMIN', 'MANAGER', 'TEACHER')">
            <c:url value="/thesis" var="thesisUrl"></c:url>
        </sec:authorize>
        <div class="d-flex bg-secondary text-dark" style="position: sticky; top: 0;z-index: 2">
            <div class="p-2 "><a class="nav-link text-light" href="${thesisUrl}">Quản lý khóa luận</a></div>
            <div class="p-2 "><a class="nav-link text-light" href="/baitaplon/council">Hội đồng</a></div>
            <div class="p-2 "><a class="nav-link text-light" href="/baitaplon/student">Sinh viên</a></div>
            <div class="p-2 "><a class="nav-link text-light" href="/baitaplon/user">Người dùng</a></div>
            
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <div class="p-2 ml-auto"><a class="nav-link text-light" href="/baitaplon/login">Đăng nhập</a></div>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <div onclick="showNoti()" style="position: relative" class="p-2">
                    <div style="position: relative;display: inline-block;">
                        <i class="fa fa-bell p-2 ml-auto" style="font-size: 24px;"></i>
                        <span id="countNotification" style="position: absolute;right: 0;top: 0;" class="badge badge-danger">2</span>
                    </div>
                    <div id="showNotiArea" style="display: none;position: absolute;background-color: #f6f6f6;min-width: 200px;border: 1px solid #ddd;z-index: 1;">
                        <c:forEach items="${listNoti}" var="noti">
                            <a style="color: black;padding: 12px 16px;text-decoration: none;display: block;">${noti.notificationId.content}</a>
                        </c:forEach>
                    </div>
                </div>
                <div class="dropdown p-2 ml-auto" style="">
                    <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown">${currentUser.username}</button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="<c:url value="/user/account"/>">Tài khoản</a>
                        <a class="dropdown-item" href="<c:url value="/user/${currentUser.id}/password"/>">Đổi mật khẩu</a>
                      <a class="dropdown-item" href="<c:url value="/logout" />">Đăng xuất</a>
                    </div>
                </div>
            </c:if>
        </div>
        
    </body>
</html>
