<%-- 
    Document   : major
    Created on : Sep 4, 2022, 4:29:15 AM
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
                <h3 class="text-left">QUẢN LÝ KHOA</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/student"/>"><p class="text-left text-dark">Quản lý sinh viên</p></a>
                    <a href="<c:url value="/student/major"/>"><p class="text-left text-dark">Quản lý khoa</p></a>
                    <a href="<c:url value="/student/class"/>"><p class="text-left text-dark">Quản lý lớp</p></a>
                </div>
            </div>
            <div class="col-xl-9">
                <div class="d-flex flex-row-reverse">
                    <div class="p-2 ">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" placeholder="Search">
                            <div class="input-group-append">
                                <button class="btn btn-success" type="submit">Tìm</button>
                            </div>
                        </div>
                    </div>
                    <div class="p-2">
                        <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#addMajor">Thêm khoa</button>
                    </div>
                    <!--                     Modal thêm khoa -->
                    <div class="modal" id="addMajor">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h4 class="modal-title">Thêm khoa</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>

                                <div class="modal-body">
                                    <form:form modelAttribute="majorInfo" action="/baitaplon/student/major" method="post">
                                        <form:errors path="*" element="div"  cssClass=" alert alert-danger" />
                                        <div class="form-group">
                                            <label for="id">Mã khoa:</label>
                                            <form:input path="word" type="text" class="form-control" placeholder="Mã đại diện" id="id"></form:input>
                                        </div>
                                        <div class="form-group">
                                            <label for="name">Tên khoa:</label>
                                            <form:input path="name" type="text" class="form-control" placeholder="Tên khoa" id="name"></form:input>
                                        </div>
                                        
                                        <input type="submit" class="btn btn-success"  value="Thêm" />
                                    </form:form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Mã khoa</th>
                                <th>Tên khoa</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listMajor}" var="c">
                            <tr>
                                <td>${c.word}</td>
                                <td>${c.name}</td>
                                <td>
                                    <a href="<c:url value="/student/major/remove/${c.id}"/>">
                                        <button onclick="" class="btn btn-danger btn-sm">Xóa</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </body>
</html>
