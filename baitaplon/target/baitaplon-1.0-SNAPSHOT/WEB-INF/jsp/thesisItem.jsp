<%-- 
    Document   : thesisItem
    Created on : Sep 5, 2022, 12:25:52 PM
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
        <script>
            window.onload = function load(){
                document.getElementById('reviewerId').value = "${thesisPage.reviewerId.id}";
            }
        </script>
        <script src="<c:url value="/js/thesis.js"/>"></script>
        <script src="<c:url value="/js/main.js"/>"></script>
        <div class="col-xl-12 row container-fluid content ">
            <div class="col-xl-3 border-right">
                <h3 class="text-left">QUẢN LÝ KHÓA LUẬN</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/thesis"/>"><p class="text-left text-dark">Danh sách khóa luận</p></a>
                    <a href="<c:url value="/stats"/>"><p class="text-left text-dark">Thống kê</p></a>
                    <a onclick="showCouncilChoose()" href="javascript:;"><p class="text-left text-dark">Chấm điểm</p></a>
                </div>
            </div>
            <div onclick="showCouncilChoose()" id="councilChoose" style="position: absolute;z-index: 2;display: flex;align-items: center;width: 100%;height: 100%;visibility: hidden">
                <div style="width: 35%;"></div>
                <div style="width: 30%">
                    <div style="width: 100%;">
                        <div style="border: 1px solid gray; border-radius: 10px;padding: 10px;background-color: #e9f3f7; margin: 20px">
                            <div style="">
                                <h4 class="text-center">Chọn hội đồng</h4>
                            </div>
                            <c:forEach items="${currentUser.members}" var="member">
                                <hr>
                                <a href="<c:url value="/thesis/score/${currentUser.id}/${member.councilId.id}"/>">
                                    <h6 class="text-dark text-center">${member.councilId.name}</h6>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div style="width: 35%;"></div>
            </div>
            <div class="col-xl-9">
                <div style="padding: 10px" class="border round-xlarge">
                    <div class="">
                        <br>
                        <div class="header">
                            <h4 class="title">Khóa luận : ${thesisPage.topic}</h4>
                        </div>
                        <br>
                        <div class="body">
                            <div>
                                <form:errors path="*" element="div"  cssClass=" alert alert-danger" />
                                <div class="form-group">
                                    <label for="topic">Chủ đề</label>
                                    <input type="text" class="form-control" placeholder="Chủ đề" id="topic" value="${thesisPage.topic}"></input>
                                </div>
                                <div class="form-group">
                                    <label for="description">Mô tả:</label>
                                    <textarea class="form-control" placeholder="Nội dung" id="description" >${thesisPage.description}</textarea>
                                </div>
                                <br>
                                <div class="input-group">
                                    <c:if test="${thesisPage.reviewerId.id!=null}">
                                        <div style="margin-right: 30px">
                                            <label for="reviewerId">Giảng viên phản biện: <strong><span id="reviewerIdLabel">${thesisPage.reviewerId.id}</span> - <span id="reviewerNameLabel">${thesisPage.reviewerId.teacherId.firstName} ${thesisPage.reviewerId.teacherId.lastName}</span></strong></label>
                                        </div>
                                        <select onchange="changeReviewer(this.value)" class="form-control" id="reviewerId">
                                            <c:forEach items="${listTeacher}" var="t">
                                                <option value="${t.id}">${t.id} - ${t.teacherId.firstName} ${t.teacherId.lastName}</option>
                                            </c:forEach>
                                        </select>
                                        <div id="addReviewerLoading" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                    </c:if>
                                    <c:if test="${thesisPage.reviewerId.id==null}">
                                        <div style="margin-right: 30px">
                                            <label for="reviewerId">Giảng viên phản biện: <strong id="reviewerLabel"><span class="badge badge-danger">Chưa có giảng viên phản biện</span></strong></label>
                                        </div>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                Thêm giảng viên phản biện
                                            </button>
                                            <div class="dropdown-menu">
                                                <c:forEach items="${listTeacher}" var="te">
                                                    <a onclick="addReviewer(this.innerText)" class="dropdown-item" href="javascript:;">${te.id}</a>
                                                </c:forEach>
                                            </div>
                                            <div id="addReviewerLoading" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                        </div>
                                    </c:if>
                                </div>
                                <br>
                                <div class="form-group">
                                    <strong>Danh sách sinh viên thực hiện: </strong>
                                    <br>
                                    <div>
                                        <table id="tableStudent" class="table">
                                            <thead>
                                                <tr>
                                                    <th>Mã sinh viên</th>
                                                    <th>Tên sinh viên</th>
                                                    <th></th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${thesisPage.students.size()==2}">
                                                    <c:forEach items="${thesisPage.students}" var="st">
                                                        <tr>
                                                            <td id="cellStudentId${st.id}">${st.id}</td>
                                                            <td id="cellStudentName${st.id}">${st.firstName} ${st.lastName}</td>
                                                            <td>
                                                                <div class="btn-group">
                                                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
                                                                       Chọn sinh viên
                                                                    </button>
                                                                    <div class="dropdown-menu">
                                                                        <c:forEach items="${listStudent}" var="s">
                                                                            <a onclick="changeStudent(this.innerText, document.getElementById('cellStudentId${st.id}'), 
                                                                                        document.getElementById('cellStudentName${st.id}'), document.getElementById('studentLoading${st.id}'))" class="dropdown-item" 
                                                                                        href="javascript:;">${s.id}</a>
                                                                        </c:forEach>
                                                                    </div>
                                                                    <div id="studentLoading${st.id}" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <button onclick="refr(document.getElementById('cellStudentId${st.id}'), 
                                                                    document.getElementById('cellStudentName${st.id}'))" class="btn btn-light">Xóa</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${thesisPage.students.size()==1}">
                                                    <c:forEach items="${thesisPage.students}" var="st">
                                                        <tr>
                                                            <td id="cellStudentId${st.id}">${st.id}</td>
                                                            <td id="cellStudentName${st.id}">${st.firstName} ${st.lastName}</td>
                                                            <td>
                                                                <div class="btn-group">
                                                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
                                                                       Chọn sinh viên
                                                                    </button>
                                                                    <div class="dropdown-menu">
                                                                        <c:forEach items="${listStudent}" var="s">
                                                                            <a onclick="changeStudent(this.innerText, document.getElementById('cellStudentId${st.id}'), 
                                                                                        document.getElementById('cellStudentName${st.id}'), document.getElementById('studentLoading${st.id}'))" class="dropdown-item" 
                                                                                        href="javascript:;">${s.id}</a>
                                                                        </c:forEach>
                                                                    </div>
                                                                    <div id="studentLoading${st.id}" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <button onclick="refr(document.getElementById('cellStudentId${st.id}'), 
                                                                    document.getElementById('cellStudentName${st.id}'))" class="btn btn-secondary">Xóa</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td id="cellStudentId"></td>
                                                        <td id="cellStudentName"></td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                                   Thêm sinh viên
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listStudent}" var="st">
                                                                        <a onclick="addStudent(this.innerText, document.getElementById('cellStudentId'), 
                                                                            document.getElementById('cellStudentName'), document.getElementById('studentLoading2'))" class="dropdown-item" href="javascript:;">${st.id}</a>
                                                                    </c:forEach>
                                                                </div>
                                                                <div id="studentLoading2" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <button onclick="refr(document.getElementById('cellStudentId'), 
                                                                    document.getElementById('cellStudentName'))" class="btn btn-secondary">Xóa</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${thesisPage.students.size()==0}">
                                                    <tr>
                                                        <td id="cellStudentId1"></td>
                                                        <td id="cellStudentName1"></td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                                   Thêm sinh viên
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listStudent}" var="st">
                                                                        <a onclick="addStudent(this.innerText, document.getElementById('cellStudentId1'), 
                                                                            document.getElementById('cellStudentName1'), document.getElementById('studentLoading1'))" class="dropdown-item" href="javascript:;">${st.id}</a>
                                                                    </c:forEach>
                                                                </div>
                                                                <div id="studentLoading1" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                                
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <button onclick="refr(document.getElementById('cellStudentId1'), 
                                                                    document.getElementById('cellStudentName1'))" class="btn btn-secondary">Xóa</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td id="cellStudentId2"></td>
                                                        <td id="cellStudentName2"></td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                                   Thêm sinh viên
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listStudent}" var="st">
                                                                        <a onclick="addStudent(this.innerText, document.getElementById('cellStudentId2'), 
                                                                            document.getElementById('cellStudentName2'),document.getElementById('studentLoading2'))" class="dropdown-item" href="javascript:;">${st.id}</a>
                                                                    </c:forEach>
                                                                </div>
                                                                <div id="studentLoading2" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <button onclick="refr(document.getElementById('cellStudentId2'), 
                                                                    document.getElementById('cellStudentName2'))" class="btn btn-secondary">Xóa</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                    <br>
                                </div>
                                <div class="form-group">
                                    <strong>Danh sách giảng viên hướng dẫn: </strong>
                                    <br>
                                    <div>
                                        <table id="tableInstructor" class="table">
                                            <thead>
                                                <tr>
                                                    <th>Mã giảng viên</th>
                                                    <th>Tên giảng viên</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:if test="${thesisPage.thesisInstructors.size()==2}">
                                                    <c:forEach items="${thesisPage.thesisInstructors}" var="ins">
                                                        <tr>
                                                            <td id="cellInstructorId${ins.instructorId.id}">${ins.instructorId.id}</td>
                                                            <td id="cellInstructorName${ins.instructorId.id}">${ins.instructorId.teacherId.firstName} ${ins.instructorId.teacherId.lastName}</td>
                                                            <td>
                                                                <div class="btn-group">
                                                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
                                                                       Chọn giảng viên
                                                                    </button>
                                                                    <div class="dropdown-menu">
                                                                        <c:forEach items="${listTeacher}" var="te">
                                                                            <a onclick="changeInstructor(this.innerText, document.getElementById('cellInstructorId${ins.instructorId.id}'), 
                                                                                        document.getElementById('cellInstructorName${ins.instructorId.id}'),document.getElementById('instructorLoading${ins.instructorId.id}'))" class="dropdown-item" 
                                                                                        href="javascript:;">${te.id}</a>
                                                                        </c:forEach>
                                                                    </div>
                                                                    <div id="instructorLoading${ins.instructorId.id}" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <button onclick="refr(document.getElementById('cellInstructorId${ins.instructorId.id}'), 
                                                                    document.getElementById('cellInstructorName${ins.instructorId.id}'))" class="btn btn-light">Xóa</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                                
                                                <c:if test="${thesisPage.thesisInstructors.size()==1}">
                                                    <c:forEach items="${thesisPage.thesisInstructors}" var="ins">
                                                        <tr>
                                                            <td id="cellInstructorId${ins.instructorId.id}">${ins.instructorId.id}</td>
                                                            <td id="cellInstructorName${ins.instructorId.id}">${ins.instructorId.teacherId.firstName} ${ins.instructorId.teacherId.lastName}</td>
                                                            <td>
                                                                <div class="btn-group">
                                                                    <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">
                                                                       Chọn giảng viên
                                                                    </button>
                                                                    <div class="dropdown-menu">
                                                                        <c:forEach items="${listTeacher}" var="te">
                                                                            <a onclick="changeInstructor(this.innerText, document.getElementById('cellInstructorId${ins.instructorId.id}'), 
                                                                                        document.getElementById('cellInstructorName${ins.instructorId.id}'), document.getElementById('instructorLoading${ins.instructorId.id}'))" class="dropdown-item" 
                                                                                        href="javascript:;">${te.id}</a>
                                                                        </c:forEach>
                                                                    </div>
                                                                    <div id="instructorLoading${ins.instructorId.id}" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <button onclick="refr(document.getElementById('cellInstructorId${ins.instructorId.id}'), 
                                                                    document.getElementById('cellInstructorName${ins.instructorId.id}'))" class="btn btn-light">Xóa</button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    <tr>
                                                        <td id="cellInstructorId"></td>
                                                        <td id="cellInstructorName"></td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                                   Thêm giảng viên
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listTeacher}" var="te">
                                                                        <a onclick="addInstructor(this.innerText, document.getElementById('cellInstructorId'), 
                                                                                    document.getElementById('cellInstructorName'), document.getElementById('instructorLoading2'))" class="dropdown-item" 
                                                                                    href="javascript:;">${te.id}</a>
                                                                    </c:forEach>
                                                                </div>
                                                                <div id="instructorLoading2" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <button onclick="refr(document.getElementById('cellInstructorId'), 
                                                                document.getElementById('cellInstructorName'))" class="btn btn-light">Xóa</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${thesisPage.thesisInstructors.size()==0}">
                                                    <tr>
                                                        <td id="cellInstructorId1"></td>
                                                        <td id="cellInstructorName1"></td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                                   Thêm giảng viên
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listTeacher}" var="te">
                                                                        <a onclick="addInstructor(this.innerText, document.getElementById('cellInstructorId1'), 
                                                                                    document.getElementById('cellInstructorName1'), document.getElementById('instructorLoading1'))" class="dropdown-item" 
                                                                                    href="javascript:;">${te.id}</a>
                                                                    </c:forEach>
                                                                </div>
                                                                <div id="instructorLoading1" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <button onclick="refr(document.getElementById('cellInstructorId1'), 
                                                                document.getElementById('cellInstructorName1'))" class="btn btn-light">Xóa</button>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td id="cellInstructorId2"></td>
                                                        <td id="cellInstructorName2"></td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown">
                                                                   Thêm giảng viên
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listTeacher}" var="te">
                                                                        <a onclick="addInstructor(this.innerText, document.getElementById('cellInstructorId2'), 
                                                                                    document.getElementById('cellInstructorName2'), document.getElementById('instructorLoading2'))" class="dropdown-item" 
                                                                                    href="javascript:;">${te.id}</a>
                                                                    </c:forEach>
                                                                </div>
                                                                <div id="instructorLoading2" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <button onclick="refr(document.getElementById('cellInstructorId2'), 
                                                                document.getElementById('cellInstructorName2'))" class="btn btn-light">Xóa</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                    <br>
                                </div>
                                
                                <a href="<c:url value="/thesis/remove/${thesisPage.id}" />" ><button class="btn btn-danger" >Xóa khóa luận</button></a>
                                <input onclick="editThesis(${thesisPage.id}, document.getElementById('topic').value, document.getElementById('description').value,
                                    document.getElementById('reviewerIdLabel').innerText, document.getElementById('tableStudent'), 
                                    document.getElementById('tableInstructor'), document.getElementById('editThesisLoading'))" type="submit" class="btn btn-success"  value="Lưu thay đổi" />
                                <div id="editThesisLoading" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                            </div>
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
