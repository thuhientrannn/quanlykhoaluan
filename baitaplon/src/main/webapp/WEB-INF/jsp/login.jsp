<%-- 
    Document   : login
    Created on : Sep 2, 2022, 5:44:57 PM
    Author     : HIEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <br>
        <c:url value="/login" var="action" />
        <h3 class="text-center">ĐĂNG NHẬP</h3>
        <c:if test="${param.error != null}">
            <div class="alert alert-danger">
                DA CO LOI XAY RA T_T 
            </div>
        </c:if>
        <c:if test="${param.accessDenied != null}">
            <div class="alert alert-danger">BẠN KHÔNG CÓ QUYỀN TRUY CẬP!!</div>
        </c:if>
        <form action="${action}" method="post">
            <div class="container">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required/>
                </div>
                <button type="submit" class="btn btn-success">Submit</button>
            </div>
        </form>
        <br>
    </body>
</html>
