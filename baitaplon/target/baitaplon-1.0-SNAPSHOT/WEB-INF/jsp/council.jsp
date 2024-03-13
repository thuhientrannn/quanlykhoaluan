<%-- 
    Document   : council
    Created on : Aug 31, 2022, 2:19:15 AM
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
        <script src="<c:url value="/js/council.js" />"></script>
        <div class="col-xl-12 row container-fluid content ">
            <div class="col-xl-2 border-right">
                <h3 class="text-center">QUẢN LÝ HỘI ĐỒNG</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/council"/>"<p class="text-left text-dark">Danh sách hội đồng</p></a>
<!--                    <a href="/baitaplon/council/criteria"/>"><p class="text-left text-dark">Điểm khóa luận</p></a>-->
                </div>
            </div>
            <div class="col-xl-10">
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
                        <button type="button" class="btn btn-outline-info" data-toggle="modal" data-target="#addCouncil">Thêm hội đồng</button>
                    </div>
<!--                     Modal thêm hội đồng -->
                    <div class="modal" id="addCouncil">
                        <div class="modal-dialog modal-lg">
                          <div class="modal-content">

                            <div class="modal-header">
                              <h4 class="modal-title">Thêm hội đồng</h4>
                              <div id="addCouncilLoading" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                              <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>

                            <div class="modal-body">
                                <div>
                                    <form:errors path="*" element="div"  cssClass=" alert alert-danger" />
                                    <div class="form-group">
                                        <label for="name">Tên hội đồng:</label>
                                        <input type="text" class="form-control" placeholder="Tên hội đồng" id="name"></input>
                                    </div>
                                    <div class="form-group">
                                        <label for="president">Chủ tịch:</label>
                                        <select class="form-control" id="president" >
                                            <option value=""></option>
                                            <c:forEach items="${listTeacher}" var="t">
                                                <option value="${t.id}">${t.firstName} ${t.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="secrectary">Thư ký:</label>
                                        <select class="form-control" id="secretary" >
                                            <option value=""></option>
                                            <c:forEach items="${listTeacher}" var="t">
                                                <option value="${t.id}">${t.firstName} ${t.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="reviewer">Phản biện:</label>
                                        <select class="form-control" id="reviewer" >
                                            <option value=""></option>
                                            <c:forEach items="${listTeacher}" var="t">
                                                <option value="${t.id}">${t.firstName} ${t.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="member1">Thành viên 1:</label>
                                        <select class="form-control" id="member1" >
                                            <option value=""></option>
                                            <c:forEach items="${listTeacher}" var="t">
                                                <option value="${t.id}">${t.firstName} ${t.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="member2">Thành viên 2:</label>
                                        <select class="form-control" id="member2" >
                                            <option value=""></option>
                                            <c:forEach items="${listTeacher}" var="t">
                                                <option value="${t.id}">${t.firstName} ${t.lastName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <input onclick="addCouncil(document.getElementById('name'), 
                                                document.getElementById('president'), document.getElementById('secretary'), 
                                                document.getElementById('reviewer'), document.getElementById('member1'), 
                                                document.getElementById('member2'))" type="submit" class="btn btn-success"  value="Thêm" />
                                </div>
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
                            <th>Hội đồng</th>
                            <th>Chủ tịch</th>
                            <th>Thư ký</th>
                            <th>Phản biện</th>
                            <th>Thành viên</th>
                            <th>Thành viên</th>
                            <th></th>
                            <th></th>
                          </tr>
                        </thead>
                        <tbody>
                            <c:if test="${listCouncil !=null}" >
                                <c:forEach items="${listCouncil}" var="cM">
                                    <tr>
                                        <td>${cM[0].name}</td>
                                        <c:if test="${cM[1][0] !=null}">
                                            <td>${cM[1][0].userId.teacherId}</td>
                                        </c:if>
                                        <c:if test="${cM[1][0] ==null}">
                                            <td></td>
                                        </c:if>
                                        <c:if test="${cM[1][1] !=null}">
                                            <td>${cM[1][1].userId.teacherId}</td>
                                        </c:if>
                                        <c:if test="${cM[1][1] ==null}">
                                            <td></td>
                                        </c:if>
                                        <c:if test="${cM[1][2] !=null}">
                                            <td>${cM[1][2].userId.teacherId}</td>
                                        </c:if>
                                        <c:if test="${cM[1][2] ==null}">
                                            <td></td>
                                        </c:if>
                                        <c:if test="${cM[1][3] !=null}">
                                            <td>${cM[1][3].userId.teacherId}</td>
                                        </c:if>
                                        <c:if test="${cM[1][3] ==null}">
                                            <td></td>
                                        </c:if>
                                        <c:if test="${cM[1][4] !=null}">
                                            <td>${cM[1][4].userId.teacherId}</td>
                                        </c:if>
                                        <c:if test="${cM[1][4] ==null}">
                                            <td></td>
                                        </c:if>
                                        <c:if test="${cM[0].active == true}">
                                            <td>
                                                <small class="badge badge-pill badge-success">Active</small>
                                            </td>
                                        </c:if>
                                        <c:if test="${cM[0].active == false}">
                                            <td>
                                                <small class="badge badge-pill badge-danger">Locked</small>
                                            </td>
                                        </c:if>
                                            <td>
                                                <a href="<c:url value="/council/${cM[0].id}"/>"><button class="btn btn-info">Chi tiết</button></a>
                                            </td>
                                    </tr>
                                </c:forEach>
                            
                            </c:if>
                          
                        </tbody>
                    </table>
                </div>
                
            </div>
        </div>
    </body>
</html>
