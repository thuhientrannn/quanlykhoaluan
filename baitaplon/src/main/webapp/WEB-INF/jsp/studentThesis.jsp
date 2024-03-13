<%-- 
    Document   : studentThesis
    Created on : Oct 23, 2022, 12:49:00 AM
    Author     : HIEN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <c:if test="${currentUser.studentId.thesisId.id==thesisInfo.id}">
            <div style="margin: 20px;padding: 20px">
                <c:if test="${msgSuccess!=null}">
                    <div class="alert alert-success">
                        ${msgSuccess}
                    </div>
                </c:if>
                <c:if test="${msgErr!=null}">
                    <div class="alert alert-danger">
                        ${msgErr}
                    </div>
                </c:if>
                <h2>Khóa luận của bạn: <span class="badge badge-info">${thesisInfo.topic}</span></h2>
                <br>
                <div>
                    <form method="GET" action="<c:url value="/student/thesisInfo/${thesisInfo.id}"/>">
                        <div class="form-group">
                            <label for="fileUrl" >Link báo cáo khóa luận</label>
                            <c:if test="${thesisInfo.fileUrl ==null }">
                                <input name="fileUrl" class="form-control" value="${thesisInfo.fileUrl}" id="fileId" type="text" />
                                <span class="badge badge-warning">Upload link báo cáo để hội đồng có thể chấm điểm cho bạn nhé ~~</span>
                            </c:if>
                            <c:if test="${thesisInfo.fileUrl !=null}">
                                <a href="${thesisInfo.fileUrl}">${thesisInfo.fileUrl}</a>
                                <span class="badge badge-success">Link uploaded</span>
                                <input name="fileUrl" class="form-control" id="fileId" placeholder="Update link" type="text" />
                            </c:if>
                            
                        </div>
                        <div class="form-group">
                            <label for="description">Mô tả:</label>
                            <textarea name="description" class="form-control" placeholder="Nội dung" id="description" >${thesisInfo.description}</textarea>
                        </div>
                        <input style="margin-top: 5px" type="submit" class="btn btn-primary" value="Cập nhật"/>
                    </form>
                    <br>
                    <div class="form-group">
                        <strong>Sinh viên thực hiện: </strong>
                        <br>
                        <div>
                            <table id="tableStudent" class="table">
                                <thead>
                                    <tr>
                                        <th>Mã sinh viên</th>
                                        <th>Tên sinh viên</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${thesisInfo.students}" var="student">
                                        <tr>
                                            <td>${student.id}</td>
                                            <td>${student}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="form-group">
                        <strong>Giáo viên hướng dẫn: </strong>
                        <br>
                        <div>
                            <table id="tableInstructor" class="table">
                                <thead>
                                    <tr>
                                        <th>Mã giảng viên</th>
                                        <th>Tên giảng viên</th>
                                        <th>Email</th>
                                        <th>Số điện thoại</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${thesisInfo.thesisInstructors}" var="thesisIns">
                                        <tr>
                                            <td>${thesisIns.instructorId.teacherId.id}</td>
                                            <td>${thesisIns.instructorId.teacherId}</td>
                                            <td>${thesisIns.instructorId.teacherId.email}</td>
                                            <td>${thesisIns.instructorId.teacherId.phoneNumber}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <c:if test="${thesisInfo.councilId.active == false}">
                    <a href="<c:url value="/export/pdf/${currentUser.studentId.id}/${thesisInfo.id}"/>" ><button class="btn btn-success">In file điểm</button></a>
                </c:if>
                <c:if test="${thesisInfo.councilId.active == true}">
                    <span class="badge badge-warning">Điểm chưa được cập nhật</span>
                </c:if>
            </div>
        </c:if>
    </body>
</html>
