<%-- 
    Document   : criteria
    Created on : Sep 3, 2022, 5:24:08 AM
    Author     : HIEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="col-xl-12 row container-fluid content ">
            <div class="col-xl-3 border-right">
                <h3 class="text-left">CHẤM ĐIỂM KHÓA LUẬN</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/council"/>"><p class="text-left text-dark">Danh sách hội đồng</p></a>
                    <a href="<c:url value="/council/criteria"/>"><p class="text-left text-dark">Điểm khóa luận</p></a>
                </div>
            </div>
            <div class="col-xl-9">
                <div class="d-flex">
                    <div class="p-2">
                        <form action="" method="">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="councilId" placeholder="Nhập mã hội đồng">
                                <div class="input-group-append">
                                    <button class="btn btn-success" type="submit">Tìm</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
