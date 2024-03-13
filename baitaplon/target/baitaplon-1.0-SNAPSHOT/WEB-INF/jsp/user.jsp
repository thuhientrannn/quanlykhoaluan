<%-- 
    Document   : user
    Created on : Aug 30, 2022, 11:45:11 PM
    Author     : HIEN
--%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </head>
    <body>
        <script src="<c:url value="/js/user.js"/>"></script>
        <script>
//            window.onload = function load(){
//                fillId();
//            }

        </script>
        <div class="container-fluid col-xl-12 row content" >
            <!-- LEFT -->
            <div class="col-xl-3 border-right left" style="padding-left: 30px">
                <h3 class="text-left">QUẢN LÝ NGƯỜI DÙNG</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/user/"/>?userRoleName=TEACHER"><p class="text-left text-dark">Giảng viên</p></a>
                    <a href="<c:url value="/user/"/>?userRoleName=STUDENT"><p class="text-left text-dark">Sinh viên</p></a>
                    <a href="<c:url value="/user/"/>?userRoleName=MANAGER"><p class="text-left text-dark">Giáo vụ khoa</p></a>
                    <a href="<c:url value="/user/"/>?userRoleName=ADMIN"><p class="text-left text-dark">Quản trị</p></a>
                </div>
            </div>

            <!-- RIGHT -->
            <div class="col-xl-9 right">
                <div class="d-flex flex-row-reverse">
                    <div class="p-2 ">
                        <form action="" method="">
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" name="kw" placeholder="Search by username">
                                <select class="form-control" name="userRoleName">
                                    <c:forEach items="${listUserRole}" var="ur">
                                        <option value="${ur.name}">${ur.name}</option>
                                    </c:forEach>
                                </select>
                                <div class="input-group-append">
                                    <button class="btn btn-success" type="submit">Tìm</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="p-2">
                        <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#addUserModal">Thêm tài khoản</button>
                    </div>
                    <!--                     Modal thêm tài khoản -->
                    <div class="modal" id="addUserModal">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <h4 class="modal-title">Thêm tài khoản người dùng</h4>
                                    <button type="button" class="close" data-dismiss="modal"></button>
                                </div>

                                <div class="modal-body">
                                    <div>
                                        <form:errors path="*" element="div"  cssClass=" alert alert-danger" />
                                        <div class = "form-group">
                                            <label for="list-userrole">Phân quyền: </label>
                                            <select onchange="fillId(this.value)" class="form-control" id="list-userrole" name="list-userrole">
                                                <c:forEach items="${listUserRole}" var="u">
                                                    <option value="${u.id}" >${u.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div id="majorArea" class="form-group">
                                            <label for="list-major">Khoa: </label>
                                            <select class="form-control" id="list-major" name="list-major">
                                                <option value=""></option>
                                                <c:forEach items="${listMajor}" var="major">
                                                    <option value="${major.id}">${major.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div id="areaListStudent" class="form-group">
                                        </div>
                                        <div class="form-group">
                                            <label for="id">Mã người dùng:</label>
                                            <input disabled="true" onblur="loadStudent(this.value)" type="text" class="form-control" id="id"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="name">Tên người dùng:</label>
                                            <div class="row" id="name">
                                                <div class="col">
                                                    <input type="text" class="form-control" id="first-name" placeholder="Họ và tên lót" name="first-name"/>
                                                </div>
                                                <div class="col">
                                                    <input type="text" class="form-control" id="last-name" placeholder="Tên"  name="last-name"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="email">Email:</label>
                                            <input type="email" class="form-control" id="email"/>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col">
                                                <label for="phone-number">Số điện thoại: <label/>
                                                    <input type="text" class="form-control" id="phone-number"/>
                                            </div>
                                            <div class="col">
                                                <label for="birthday">Ngày sinh: <label/>
                                                    <input type="date" class="form-control" id="birthday" name="birthday"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="account">Tài khoản người dùng</label>
                                            <div class="row" id="account">
                                                <div class="col">
                                                    <input type="text" class="form-control" id="username" name="username" placeholder="Username"/>
                                                </div>
                                                <div class="col">
                                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password"/>
                                                </div>
                                            </div>
                                        </div>
                                        <button onclick="addUser(document.getElementById('list-userrole').value)" class="btn btn-success" >Thêm</button>
                                    </div>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Hủy</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="row col-md-12" style="border: 1px solid gray; border-radius: 10px;padding: 10px;margin: 10px;">
                        <div class="col-md-6 ">
                            <h5>THỐNG KÊ TÀI KHOẢN NGƯỜI DÙNG</h5>
                            <table class="table text-center">
                                <thead>
                                    <tr>
                                        <th>Phân quyền</th>
                                        <th>Số lượng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach  items="${userStats}" var="i">
                                    <tr>
                                        <td>${i[0]}</td>
                                        <td>${i[1]}</td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <div>
                                <canvas id="myUserChart" ></canvas>
                            </div>
                        </div>
                    </div>
                    <div id="message" ></div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Mã người dùng</th>
                                <th>Tên người dùng</th>
                                <th>Email</th>
                                <th>Chức vụ</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="u" items="${user}">
                                <c:if test="${u.userRole.id != 4}">
                                    <tr>
                                        <td>${u.id}</td>
                                        <td>${u.teacherId}</td>
                                        <td>${u.teacherId.email}</td>
                                        <td>${u.userRole}</td>
                                        <td>
                                            <a href="<c:url value="/user/account/edit/${u.id}"/>">
                                                <button class="btn btn-secondary btn-sm">Chỉnh sửa</button>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="<c:url value="/user/account/remove/${u.id}"/>">
                                                <button onclick="" class="btn btn-danger btn-sm">Xóa</button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${u.userRole.id == 4}">
                                    <tr>
                                        <td>${u.id}</td>
                                        <td>${u.studentId}</td>
                                        <td>${u.studentId.email}</td>
                                        <td>${u.userRole}</td>
                                        <td>
                                            <a href="<c:url value="/user/account/edit/${u.id}"/>">
                                                <button class="btn btn-secondary btn-sm">Chỉnh sửa</button>
                                            </a>
                                        </td>
                                        <td>
                                            <a href="<c:url value="/user/account/remove/${u.id}"/>">
                                                <button onclick="" class="btn btn-danger btn-sm">Xóa</button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
    <script>
        let labels =[], datas =[], labels2 =[], datas2 =[];
        <c:forEach  items="${userStats}" var="i">
            labels.push('${i[0]}')
            datas.push(${i[1]})
        </c:forEach>
        
        window.onload = function() {
            userChart("myUserChart", labels, datas)
        }
    </script>
</html>
