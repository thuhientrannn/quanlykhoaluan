<%-- 
    Document   : stats
    Created on : Sep 4, 2022, 11:00:53 PM
    Author     : HIEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="<c:url value="/js/stats.js"/>"></script>
    </head>
    <body>
        <div class="col-xl-12 row container-fluid content ">
            <div class="col-xl-3 border-right">
                <h3 class="text-left">THỐNG KÊ BÁO CÁO</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/thesis"/>"><p class="text-left text-dark">Danh sách khóa luận</p></a>
                    <a href="<c:url value="/thesis/reviewer"/>"><p class="text-left text-dark">Giảng viên phản biện</p></a>
                    <a href="<c:url value="/stats"/>"><p class="text-left text-dark">Thống kê</p></a>
                </div>
            </div>
            <div class="col-xl-9">
                <br>
                <div class="row col-md-12 border-bottom">
                    <div class="col-md-8 ">
                        <h5>THỐNG KÊ ĐIỂM KHÓA LUẬN</h5>
                        <table class="table text-center">
                            <thead>
                                <tr>
                                    <th>Năm</th>
                                    <th>Điểm khóa luận trung bình</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach  items="${scoreStats}" var="i">
                                <tr>
                                    <td>${i[0]}</td>
                                    <td>${i[1]}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <div>
                            <canvas id="myScoreChart"></canvas>
                        </div>
                    </div>
                </div>
                <br>
                <div class="row col-md-12">
                    <div class="col-md-8">
                        <h5>THỐNG KÊ KHÓA LUẬN NGÀNH</h5>
                        <table class="table text-center">
                            <thead>
                                <tr>
                                    <th>Ngành</th>
                                    <th>Số lượng sinh viên tham gia làm khóa luận</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach  items="${thesisStats}" var="i">
                                <tr>
                                    <td>${i[1]}</td>
                                    <td>${i[2]}</td>
                                </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <div>
                            <canvas id="myThesisChart"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        let labels =[], datas =[], labels2 =[], datas2 =[];
        <c:forEach  items="${thesisStats}" var="i">
        labels.push('${i[1]}')
        datas.push(${i[2]})
        </c:forEach>
        <c:forEach  items="${scoreStats}" var="i">
        labels2.push('${i[0]}')
        datas2.push(${i[1]})
        </c:forEach>
        
        window.onload = function() {
            thesisChart("myThesisChart", labels, datas)
            scoreChart("myScoreChart", labels2, datas2)
        }
    </script>
</html>
