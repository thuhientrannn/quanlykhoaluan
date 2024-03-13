<%-- 
    Document   : account
    Created on : Sep 3, 2022, 3:27:02 AM
    Author     : HIEN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:if test="${userPage!=null}">
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${userPage.firstName} ${userPage.lastName}</span><span class="text-black-50">${userPage.email}</span><span> </span></div>
                </div>
                <div class="col-md-9 border-right">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Tài khoản</h4>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6"><label  class="labels">Họ và tên lót</label><input disabled="true" type="text" class="form-control" value="${userPage.firstName}"></div>
                            <div class="col-md-6"><label class="labels">Tên</label><input disabled="true" type="text" class="form-control" value="${userPage.lastName}"></div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12"><label class="labels">Số điện thoại</label><input disabled="true" type="text" class="form-control"value="${userPage.phoneNumber}"></div>
                            <div class="col-md-12"><label class="labels">Email</label><input disabled="true" type="text" class="form-control"value="${userPage.email}"></div>
                            <div class="col-md-12"><label class="labels">Ngày sinh</label><input disabled="true" type="text" class="form-control" value="${userPage.birthday}"></div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-6"><label class="labels">Tên đăng nhập</label><input disabled="true" type="text" class="form-control"value="${userPage.username}"></div>
                            <div class="col-md-6"><label class="labels">Mật khẩu</label><input disabled="true" type="text" class="form-control" value="${userPage.password}"></div>
                        </div>
                        <div class="mt-5 text-center"><a href="<c:url value="/user/${userPage.id}/password"/>"><button class="btn btn-primary profile-button" type="button">Đổi mật khẩu</button></a></div>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
    </body>
</html>
