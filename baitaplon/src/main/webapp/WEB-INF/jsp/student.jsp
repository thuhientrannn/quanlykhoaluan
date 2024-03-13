<%-- 
    Document   : student
    Created on : Sep 3, 2022, 3:01:42 AM
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
        <script src="<c:url value="/js/student.js" />"></script>
        <script>
            window.onload = function load(){
                loadClass(document.getElementById('majorSelected'),document.getElementById('classSelected'), document.getElementById('note'));
            }
        </script>
        <div class="col-xl-12 row container-fluid content ">
            <div class="col-xl-3 border-right">
                <h3 class="text-left">QUẢN LÝ SINH VIÊN</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/student"/>"><p class="text-left text-dark">Quản lý sinh viên</p></a>
                    <a href="<c:url value="/student/major"/>"><p class="text-left text-dark">Quản lý khoa</p></a>
                    <a href="<c:url value="/student/class"/>"><p class="text-left text-dark">Quản lý lớp</p></a>
                </div>
            </div>
            <div class="col-xl-9">
                <div class="d-flex flex-row-reverse">
                    <div class="p-2">
                        <form method="get" action="" class="input-group mb-3">
                            <select name="yearId" class="form-control">
                                <option value="">Niên khóa</option>
                                <c:forEach items="${listYear}" var="year">
                                    <option value="${year.id}">${year.year}</option>
                                </c:forEach>
                            </select>
                            <select name="majorId" class="form-control">
                                <option value="" >Khoa</option>
                                <c:forEach items="${listMajor}" var="major" >
                                    <option value="${major.id}">${major.name}</option>
                                </c:forEach>
                            </select>
                            <select id="classKw" name="classId" class="form-control">
                                <option value="">Lớp</option>
                                <c:forEach items="${listClass}" var="cs">
                                    <option value="${cs.id}">${cs.name}</option>
                                </c:forEach>
                            </select>
                            <input type="text" name="kw" class="form-control" placeholder="Từ khóa">
                            <div class="input-group-append">
                                <button class="btn btn-success" type="submit">Tìm</button>
                            </div>
                        </form>
                    </div>
                    <div class="p-2">
                        <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#addStudent">Thêm sinh viên</button>
                    </div>
                <!--                     Modal thêm sinh viên -->
                    <div class="modal" id="addStudent">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4 class="modal-title">Thêm sinh viên</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>

                                <div class="modal-body">
                                    <form:form modelAttribute="studentInfo" action="/baitaplon/student" method="post">
                                        <form:errors path="*" element="div"  cssClass=" alert alert-danger" />
                                            <div class="form-group">
                                                <label for="classId">Khoa</label>
                                                <select onchange="loadClass(this, document.getElementById('classSelected'), document.getElementById('note'))" id="majorSelected" class="form-control">
                                                    <c:forEach items="${listMajor}" var="major">
                                                        <option value="${major.id}">${major.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div id="note"></div>
                                            <div class="form-group" id="classArea">
                                                <label for="classId">Lớp</label>
                                                <form:select path="classId"  id="classSelected" class="form-control">
                                                </form:select>
                                            </div>
                                            <div class="form-group">
                                                <label for="id">Mã sinh viên:</label>
                                                <form:input path="id" type="text" class="form-control" placeholder="Mã sinh viên" id="id" value="${newStudentId}"></form:input>
                                            </div>
                                            <div class="form-group">
                                                <label for="name">Tên sinh viên:</label>
                                                <div class="row" id="name">
                                                    <div class="col">
                                                    <form:input path="firstName" type="text" class="form-control" id="first-name" placeholder="Trần Thị Thu" name="first-name"></form:input>
                                                    </div>
                                                    <div class="col">
                                                    <form:input path="lastName" type="text" class="form-control" id="last-name"  placeholder="Hiền" name="last-name"></form:input>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="email">Email:</label>
                                                <form:input path="email" type="email" class="form-control" placeholder="thuhien.tran060801@gmail.com" id="email"></form:input>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col">
                                                    <label for="phone-number">Số điện thoại: <label/>
                                                    <form:input path="phoneNumber" type="text" class="form-control" placeholder="0964345626" id="phone-number"></form:input>
                                                </div>
                                                <div class="col">
                                                    <label for="birthday">Ngày sinh: <label/>
                                                    <form:input path="birthday" type="date" class="form-control" id="birthday" name="birthday"></form:input>
                                                </div>
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
                    <div id="kwNote" class="p-2">
                        
                    </div>
                </div>
                <div>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Mã sinh viên</th>
                                <th>Tên sinh viên</th>
                                <th>Email</th>
                                <th>Số điện thoại</th>
                                <th>Ngày sinh</th>
                                <th>Lớp</th>
                                <th><th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listStudent}" var="c">
                                <tr>
                                    <td>${c.id}</td>
                                    <td>${c.firstName} ${c.lastName}</td>
                                    <td>${c.email}</td>
                                    <td>${c.phoneNumber}</td>
                                    <td>${c.birthday}</td>
                                    <td>${c.classId}</td>
                                    <td>
                                        <button type="button" onclick="loadMajor(document.getElementById('class-edit${c.id}'), document.getElementById('major-edit${c.id}'))" class="btn btn-secondary" data-toggle="modal" data-target="#editStudent${c.id}">Chỉnh sửa</button>
                                        <!-- The Modal -->
                                        <div class="modal" id="editStudent${c.id}">
                                          <div class="modal-dialog">
                                            <div class="modal-content">

                                              <!-- Modal Header -->
                                              <div class="modal-header">
                                                <h4 class="modal-title">Thông tin sinh viên</h4>
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                              </div>

                                              <!-- Modal body -->
                                              <div class="modal-body">
                                                  <form action="" method="post">
                                                        <form:errors path="*" element="div"  cssClass=" alert alert-danger" />
                                                            <div class="form-group">
                                                                <label for="classId">Khoa</label>
                                                                <select onchange="classChange(this, document.getElementById('class-edit${c.id}'), document.getElementById('note-edit${c.id}'))" id="major-edit${c.id}" class="form-control">
                                                                    <c:forEach items="${listMajor}" var="m">
                                                                        <option id="${m.id}" value="${m.id}">${m.name}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div id="note-edit${c.id}"></div>
                                                            <div class="form-group">
                                                                <label for="classId">Lớp</label>
                                                                <select  id="class-edit${c.id}" class="form-control">
                                                                    <c:forEach items="${c.classId.majorId.classes}" var="cs">
                                                                        <option id="${c.classId.majorId.id}" value="${cs.id}">${cs.name}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="form-group">
                                                                <label >Mã sinh viên:</label>
                                                                <input id="id-edit${c.id}" type="text" class="form-control" placeholder="Mã sinh viên" value="${c.id}"></input>
                                                            </div>
                                                            <div class="form-group">
                                                                <label >Tên sinh viên:</label>
                                                                <div class="row">
                                                                    <div class="col">
                                                                        <input id="first-name-edit${c.id}" type="text" class="form-control" placeholder="Trần Thị Thu" value="${c.firstName}"></input>
                                                                    </div>
                                                                    <div class="col">
                                                                        <input id="last-name-edit${c.id}" type="text" class="form-control" placeholder="Hiền" value="${c.lastName}"></input>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label >Email:</label>
                                                                <input id="email-edit${c.id}" type="email" class="form-control" placeholder="thuhien.tran060801@gmail.com" value="${c.email}"></input>
                                                            </div>
                                                            <div class="form-group row">
                                                                <div class="col">
                                                                    <label>Số điện thoại: </label>
                                                                    <input id="phone-number-edit${c.id}" type="text" class="form-control" placeholder="0964345626" value="${c.phoneNumber}"></input>
                                                                </div>
                                                                <div class="col">
                                                                    <label>Ngày sinh: <label/>
                                                                    <input id="birthday-edit${c.id}" type="date" class="form-control" value="${c.birthday}"></input>
                                                                </div>
                                                            </div>
                                                            <a href="<c:url value="/student/remove/${c.id}"/>"><input type="button" class="btn btn-danger"  value="Xóa"/></a>
                                                            <input onclick="editStudent(document.getElementById('class-edit${c.id}').value, document.getElementById('id-edit${c.id}').value, 
                                                                        document.getElementById('first-name-edit${c.id}').value, document.getElementById('last-name-edit${c.id}').value, 
                                                                        document.getElementById('email-edit${c.id}').value, document.getElementById('phone-number-edit${c.id}').value, 
                                                                        document.getElementById('birthday-edit${c.id}').value, '${c.id}')" type="button" class="btn btn-success"  value="Lưu thay đổi" />
                                                    </form>
                                              </div>

                                              <!-- Modal footer -->
                                              <div class="modal-footer">
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                              </div>

                                            </div>
                                          </div>
                                        </div>
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
