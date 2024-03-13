<%-- 
    Document   : addThesis
    Created on : Sep 3, 2022, 7:49:41 PM
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
        <div class="container border round-xlarge">
            <div class="">
                <br>
                <div class="header">
                    <h4 class="title">Thêm khóa luận</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <br>
                <div class="body">
                    <div class="form-group">
                            <form method="put" action="/baitaplon/thesis/addThesis">
                                <label for="studentId">Sinh viên thực hiện:</label>
                                <select id="studentId" name="studentId">
                                    <option value=""></option>
                                    <c:forEach items="${listStudent}" var="student">
                                        <option value="${student.id}">${student.firstName} ${student.lastName}</option>
                                    </c:forEach>
                                </select>
                                <input type="submit" class="btn btn-info" value="Thêm sinh viên"></input>
                            </form>
                            <br>
                            <div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Mã sinh viên</th>
                                            <th>Tên sinh viên</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${students != null}">
                                        <c:forEach items="${students}" var="c">
                                            <tr>
                                                <td>${c.id}</td>
                                                <td>${c.firstName} ${c.lastName}  </td>
                                                <td><a href="<c:url value="/thesis/addThesis/"/>?removeId=${c.id}"><button class="btn btn-danger">Xóa</button></a></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                    </div>
                    <div class="form-group">
                            <form method="put" action="/baitaplon/thesis/addThesis">
                                <label for="teacherId">Giáo viên hướng dẫn:</label>
                                <select id="teacherId" name="teacherId">
                                    <option value=""></option>
                                    <c:forEach items="${listTeacher}" var="teacher">
                                        <option value="${teacher.teacherId.id}">${teacher.teacherId.firstName} ${teacher.teacherId.lastName}</option>
                                    </c:forEach>
                                </select>
                                <input type="submit" class="btn btn-info" value="Thêm giảng viên"></input>
                            </form>
                            <br>
                            <div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Mã giảng viên</th>
                                            <th>Tên giảng viên</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${teachers != null}">
                                        <c:forEach items="${teachers}" var="t">
                                            <tr>
                                                <td>${t.teacherId.id}</td>
                                                <td>${t.teacherId.firstName} ${t.teacherId.lastName}</td>
                                                <td><a href="<c:url value="/thesis/addThesis/"/>?removeTId=${t.id}"><button class="btn btn-danger">Xóa</button></a></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                    </div>
                    <form:form modelAttribute="thesisInfo" action="/baitaplon/thesis/addThesis" method="post">
                        <form:errors path="*" element="div"  cssClass=" alert alert-danger" />
                        <div class="form-group">
                            <label for="topic">Chủ đề</label>
                            <form:input path="topic" type="text" class="form-control" placeholder="Chủ đề" id="topic"></form:input>
                        </div>
                        <div class="form-group">
                            <label for="description">Mô tả:</label>
                            <form:textarea path="description" class="form-control" placeholder="Nội dung" id="description"></form:textarea>
                        </div>
                        <div class="form-group">
                            <label for="reviewerId">Giảng viên phản biện</label>
                            <form:select path="reviewerId" class="form-control" id="reviewerId">
                                <option value=""></option>
                                <c:forEach items="${listTeacher}" var="t">
                                    <option value="${t.teacherId.id}">${t.teacherId.firstName} ${t.teacherId.lastName}</option>
                                </c:forEach>
                            </form:select>
                        </div>

                        <input type="submit" class="btn btn-success"  value="Thêm" />

                    </form:form>
                </div>
                <br>
            </div>
        </div>
    </body>
</html>
