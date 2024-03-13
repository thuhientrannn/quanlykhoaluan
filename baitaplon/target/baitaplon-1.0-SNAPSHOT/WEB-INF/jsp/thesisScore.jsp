<%-- 
    Document   : thesisScore
    Created on : Sep 4, 2022, 10:11:00 PM
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
        <c:if test="${thesisScore!=null}">
             <div class="container rounded bg-white mt-5 mb-5">
                <div class="row">
                    <div class="col-md-12 border-right border-left">
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Chi tiết điểm</h4>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-3"><label  class="labels">Mã khóa luận</label><input disabled="true" type="text" class="form-control" value="${thesisScore.id}"></div>
                                <div class="col-md-9"><label class="labels">Chủ đề</label><input disabled="true" type="text" class="form-control" value="${thesisScore.topic}"></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels">Chi tiết điểm</label>
                                    <div>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Tiêu chí</th>
                                                    <th>Điểm</th>
                                                    <th>Giáo viên chấm</th>
                                                    <th>Thời gian</th>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${thesisScore.thesisScores}" var="a">
                                                    <tr>
                                                        <td>${a.criteriaId.name}</td>
                                                        <td>${a.score}</td>
                                                        <td>${a.userId.firstName} ${a.userId.lastName}</td>
                                                        <td>${a.createdDate}</td>
                                                    </tr>
                                                </c:forEach>
                                                
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-5 text-center"><a href="<c:url value="/council/criteria"/>"><button class="btn btn-danger profile-button" type="button">Thoát</button></a></div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${thesisScore==null}">
            <div class="alert alert-danger">
                <h1>ĐÃ CÓ LỖI XẢY RA !!! VUI LÒNG THỬ LẠI SAU!!</h1>
            </div>
        </c:if>
    </body>
</html>
