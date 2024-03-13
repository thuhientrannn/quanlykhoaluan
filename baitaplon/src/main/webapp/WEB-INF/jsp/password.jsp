<%-- 
    Document   : password
    Created on : Sep 3, 2022, 3:29:43 AM
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
        <div class="col-md-6 offset-md-3">
            <span class="anchor" id="formChangePassword"></span>
            <hr class="mb-5">

            <!-- form card change password -->
            <div class="card card-outline-secondary">
                <div class="card-header">
                    <h3 class="mb-0">Đổi mật khẩu</h3>
                </div>
                <div class="card-body">
                    <form action="" method="post" class="form" role="form" autocomplete="off">
                        <div class="form-group">
                            <label for="inputPasswordOld">Mật khẩu hiện tại</label>
                            <input name="oldPassword" type="password" class="form-control" id="inputPasswordOld" required="true">
                        </div>
                        <div class="form-group">
                            <label for="inputPasswordNew">Mật khẩu mới</label>
                            <input name="newPassword" type="password" class="form-control" id="inputPasswordNew" required="true">
                        </div>
                        <div class="form-group">
                            <label for="inputPasswordNewVerify">Nhập lại mật khẩu mới</label>
                            <input onchange="checkPassword()" type="password" class="form-control" id="inputPasswordNewVerify" required="true">
                            <span class="form-text small text-muted">
                                Để xác nhận hãy nhập mật khẩu mới một lần nữa
                            </span>
                        </div>
                        <div class="form-group">
                            <button id="btnSubmit" type="submit" class="btn btn-success btn-lg float-right">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
            <c:if test="${msg!=null}">
                <div class="alert alert-success">
                    <strong>${msg}</strong>
                </div>
            </c:if>
            <!-- /form card change password -->
            <script>
                function checkPassword(){
                    let a = document.getElementById("inputPasswordNew").value
                    let b = document.getElementById("inputPasswordNewVerify").value
                    console.log(a,b)
                    if(a!=b){
                        document.getElementById("btnSubmit").setAttribute("disable",true);
                    }
                    else{
                        document.getElementById("btnSubmit").setAttribute("disable",false);
                    }
                }
            </script>
        </div>
        <br><!-- comment -->
    </body>
</html>
