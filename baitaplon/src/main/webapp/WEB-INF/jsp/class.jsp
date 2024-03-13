<%-- 
    Document   : class
    Created on : Sep 4, 2022, 4:28:57 AM
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
        <script src="<c:url value="/js/student.js"/>"></script>
        <script>
            window.onload = function(){
                fillId();
            }
            function fillId(){
                let a = document.getElementById('listMajor');
                let b = document.getElementById('name');
                let c = document.getElementById('major');
                let d = document.getElementById('majorWord')
                console.log(a.value);
                var id = a.options[a.selectedIndex].id;
                b.value = id;
                c.innerText = a.options[a.selectedIndex].innerText;
                d.innerText = a.options[a.selectedIndex].id;
            }
        </script>
        <div class="col-xl-12 row container-fluid content ">
             <div class="col-xl-3 border-right">
                <h3 class="text-left">QUẢN LÝ LỚP</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/student"/>"><p class="text-left text-dark">Quản lý sinh viên</p></a>
                    <a href="<c:url value="/student/major"/>"><p class="text-left text-dark">Quản lý khoa</p></a>
                    <a href="<c:url value="/student/class"/>"><p class="text-left text-dark">Quản lý lớp</p></a>
                </div>
            </div>
            <div class="col-xl-9">
                <div class="d-flex flex-row-reverse">
                    <div class="p-2 ">
                        <div class="form-group">
                            <select onchange="filterByMajor(this.value)" id="major-filter" class="form-control">
                                <c:forEach var="u" items="${listMajor}" > 
                                    <option id="${u.word}" value="${u.id}">${u.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="p-2">
                        <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#addClass">Thêm lớp</button>
                    </div>
                    <!--                     Modal thêm lớp -->
                    <div class="modal" id="addClass">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h4 class="modal-title">Thêm lớp</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>

                                <div class="modal-body">
                                    <form:form modelAttribute="classInfo" action="/baitaplon/student/class" method="post">
                                        <form:errors path="*" element="div"  cssClass=" alert alert-danger" />
                                        <div class = "form-group">
                                            <label for="listMajor">Khoa: </label>
                                            <form:select onchange="fillId()" path="majorId" cssClass="form-control" id="listMajor" name="listMajor">
                                                <c:forEach var="u" items="${listMajor}" > 
                                                    <option id="${u.word}" value="${u.id}">${u.name}</option>
                                                </c:forEach>
                                            </form:select>
                                        </div>
                                        <div class="badge badge-pill badge-secondary">Tên lớp thuộc khoa <span id="major"></span> nên bắt đầu bằng <span id="majorWord"></span></div>
                                        <div class="form-group">
                                            <label for="name">Tên lớp:</label>
                                            <form:input path="name" type="text" class="form-control" placeholder="Tên lớp" id="name"></form:input>
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
                                <th>Tên lớp</th>
                                <th>Khoa</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody id="table-body">
                        <c:forEach items="${listClass}" var="c">
                            <tr>
                                <td>${c.name}</td>
                                <td>${c.majorId}</td>
                                <td>
                                    <a href="<c:url value="/student/class/remove/${c.id}"/>">
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
