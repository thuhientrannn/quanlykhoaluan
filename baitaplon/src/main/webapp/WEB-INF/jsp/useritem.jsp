<%-- 
    Document   : useritem
    Created on : Aug 31, 2022, 12:43:29 PM
    Author     : HIEN
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <script>
            $(document).ready(function(){
                $('[data-toggle="tooltip"]').tooltip();   
            });
            window.onload = function load(){
                document.getElementById("${userEdit.userRole.name}").selected = true;
                if(${userEdit.userRole.id!=4}){
                    document.getElementById('urMajorId').value = ${userEdit.teacherId.majorId.id};
                }
            }
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
                <div class="border-right">
                    <c:if test="${param.msgSuccess!=null}">
                        <div class="alert alert-success">
                            ${param.msgSuccess}
                        </div>
                    </c:if>
                    <c:if test="${param.msgErr!=null}">
                        <div class="alert alert-danger">
                            ${param.msgErr}
                        </div>
                    </c:if>
                    <c:if test="${userEdit != null}">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Tài khoản</h4>
                        </div>
                        <form  action="/baitaplon/user/account/edit/${userEdit.id}" method="post">
                        <div class="row mt-3">
                            <div class="col-md-3" style="display: none">
                                <label class="labels">Mã người dùng</label>
                                <input id="urId" name ="urId" type="text" class="form-control" value="${userEdit.id}"></input>
                            </div>
                            <div class="col-md-2">
                                <c:if test="${userEdit.active==true}">
                                    <span class="badge badge-success">Active</span>
                                </c:if>
                                <c:if test="${userEdit.active==false}">
                                    <span class="badge badge-danger">Locked</span>
                                </c:if>
                            </div>
                        </div>
                        
                        <c:if test="${userEdit.userRole.id!=4}">
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label for="urMajorId" class="labels">Khoa</label>
                                <select  id="urMajorId" name="urMajorId"  class="form-control">
                                    <option value=""></option>
                                    <c:forEach items="${listMajor}" var="major">
                                        <option value="${major.id}">${major.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <br>
                        <div class="input-group">
                            <label style="margin: 5px;" for="urUserRoleId" class="labels">Phân quyền</label>
                            <select id="urUserRoleId" name="urUserRoleId"  class="form-control col-md-3">
                                <option id="ADMIN" value="1">ADMIN</option>
                                <option id="MANAGER" value="2">MANAGER</option>
                                <option id="TEACHER" value="3">TEACHER</option>
                            </select>
                        </div>
                        <div class="row mt-2">
                            <div class="col-md-6">
                                <label class="labels">Họ và tên lót</label>
                                <input id="urFirstName" name="urFirstName" type="text" class="form-control" value="${userEdit.teacherId.firstName}"/>
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Tên</label>
                                <input id="urLastName" name="urLastName" type="text" class="form-control" value="${userEdit.teacherId.lastName}"/>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <label class="labels">Số điện thoại</label>
                                <input id="urPhoneNumber" name="urPhoneNumber" type="text" class="form-control" value="${userEdit.teacherId.phoneNumber}"/>
                            </div>
                            <div class="col-md-12">
                                <label class="labels">Email</label>
                                <input id="urEmail" name="urEmail" type="text" class="form-control" value="${userEdit.teacherId.email}"></input>
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${userEdit.userRole.id==4}">
                        <div data-toggle="tooltip" title="Để chỉnh sửa vui lòng sang trang sinh viên nhé" class="row mt-2">
                            <div class="col-md-6">
                                <label for="urClass" class="labels">Lớp</label>
                                <input disabled="true" id="urClass" name="urClass" type="text" class="form-control" value="${userEdit.studentId.classId.name}"></input>
                            </div>
                            <div class="col-md-6">
                                <label for="urMajor" class="labels">Khoa</label>
                                <input disabled="true" id="urMajor" name="urMajor" type="text" class="form-control" value="${userEdit.studentId.classId.majorId.name}"></input>
                            </div>
                        </div>
                        <br>
                            <div class="input-group">
                                <label style="margin: 5px;" for="urUserRoleId" class="labels">Phân quyền</label>
                                <select id="urUserRoleId" name="urUserRoleId"  class="form-control col-md-3">
                                    <option id="STUDENT" value="4">STUDENT</option>
                                </select>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6">
                                    <label class="labels">Họ và tên lót</label>
                                    <input id="urFirstName" name="urFirstName" type="text" class="form-control" value="${userEdit.studentId.firstName}"/>
                                </div>
                                <div class="col-md-6">
                                    <label class="labels">Tên</label>
                                    <input id="urLastName" name="urLastName" type="text" class="form-control" value="${userEdit.studentId.lastName}"/>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels">Số điện thoại</label>
                                    <input id="urPhoneNumber" name="urPhoneNumber" type="text" class="form-control" value="${userEdit.studentId.phoneNumber}"/>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Email</label>
                                    <input id="urEmail" name="urEmail" type="text" class="form-control" value="${userEdit.studentId.email}"></input>
                                </div>
                            </div>
                        </c:if>
                        <div class="row mt-3">
                            <div style="margin-top: 10px" class="col-md-6">
                                <label class="labels">Tên đăng nhập</label>
                                <input style="margin-top:5px;" id="urUsername" name="urUsername" type="text" class="form-control" value="${userEdit.username}"></input>
                            </div>
                            <div class="col-md-6">
                                <label class="labels">Mật khẩu</label>
                                <a href="<c:url value="/user/resetPassword/${userEdit.id}" />"><input style="margin:5px;" type="button" onclick="javascript:;" class="btn btn-info" value="Reset password"/></a>
                                <input disabled="true" id="urUsername" type="password" class="form-control" value="${userEdit.password}"></input>
                            </div>
                        </div>
                        <div class="mt-5 text-center">
                        <c:if test="${userEdit.active == true}">
                            <a href="/baitaplon/user/lock/${userEdit.id}">
                                <button class="btn btn-secondary profile-button" type="button">Khóa tài khoản</button>
                            </a>
                        </c:if>
                        <c:if test="${userEdit.active == false}">
                            <a href="/baitaplon/user/unlock/${userEdit.id}">
                                <button class="btn btn-succes profile-button" type="button">Mở khóa tài khoản</button>
                            </a>
                        </c:if>    
                            <button class="btn btn-primary profile-button" type="submit">Lưu thay đổi</button>
                            <a href="<c:url value="/user" />">
                                <button class="btn btn-danger profile-button" type="button">Thoát</button>
                            </a>
                        </div>
                        </form>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
