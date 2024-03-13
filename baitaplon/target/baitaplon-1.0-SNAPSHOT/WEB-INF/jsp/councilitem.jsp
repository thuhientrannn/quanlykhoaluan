<%-- 
    Document   : councilitem
    Created on : Oct 14, 2022, 12:15:39 AM
    Author     : HIEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/css/councilItemStyle.css"/>" rel="stylesheet" />
    </head>
    <body>
        <script src="<c:url value="/js/council.js" />"></script>
        <script>
            window.onload = function load(){
                checkQuantityMember(document.getElementById('tableMember'), '${councilPage.maxMemberQuantity}', document.getElementById('addMemberArea'));
                console.log(${councilPage.thesises.size()})
            }
            var memberId, memberName
            function addMember(id,name){
                console.log(memberId,memberName);
                memberId = id;
                memberName = name;
                document.getElementById('myInputMember').value = name;
                showFilter(document.getElementById('myFilterMember'));
                console.log(memberId,memberName);
            }
            function addRowMember(){
                console.log(memberId, memberName)
                let isMember = checkMember(memberId,memberName);
                if(isMember){
                    alert("Thành viên đã có trong hội đồng!!!");
                }
                else{
                    var tbBody = document.getElementById('tableMemberBody');
                    tbBody.innerHTML = tbBody.innerHTML + `<tr id="rowRemove`+memberId+`" name="MEMBER">
                                                        <td id="memberIdCell`+memberId+`">`+memberId+`</td>
                                                        <td id="memberNameCell`+memberId+`">`+memberName+`</td>
                                                        <td id="memberRoleCell`+memberId+`" >Thành viên</td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
                                                                    Thay đổi
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listTeacher}" var="s">
                                                                        <a onclick="changeMember('${s.id}', '${s}', document.getElementById('memberNameCell`+memberId+`'), document.getElementById('memberIdCell`+memberId+`'))" class="dropdown-item" href="javascript:;">${s.id} - ${s}</a>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <button class="btn btn-danger" onclick="removeMember(document.getElementById('rowRemove`+memberId+`'),'${councilPage.maxMemberQuantity}')">Xóa</button>
                                                        </td>
                                                    </tr>`;
                    checkQuantityMember(document.getElementById('tableMember'), '${councilPage.maxMemberQuantity}', document.getElementById('addMemberArea'));
                }
            }
        </script>
        <div class="col-xl-12 row container-fluid content ">
            <div class="col-xl-2 border-right">
                <h3 class="text-center">QUẢN LÝ HỘI ĐỒNG</h3>
                <div style="padding: 10px">
                    <a href="<c:url value="/council"/>"<p class="text-left text-dark">Danh sách hội đồng</p></a>
<!--                    <a href="/baitaplon/council/criteria"><p class="text-left text-dark">Điểm khóa luận</p></a>-->
                </div>
            </div>
            <div class="col-xl-10 ">
                <div id="message"></div>
                <div style="width: 100%; padding: 10px" class="border round-xxlarge">
                    <div class="">
                        <br>
                        <div class="header">
                            <span>
                                <h4 class="title">Hội đồng : ${councilPage.name} </h4>
                                <c:if test="${councilPage.active==false}">
                                    <small class="badge badge-pill badge-danger">Locked</small>
                                </c:if>
                                <c:if test="${councilPage.active==true}">
                                    <small class="badge badge-pill badge-success">Active</small>
                                </c:if>
                            </span>
                        </div>
                        <br>
                        <div class="body">
                            <div style="margin: 40px; margin-top: 20px"  class="form-group">
                                <strong>Danh sách thành viên: </strong>
                                <br>
                                <br>
                                <div>
                                    <table id="tableMember" class="table table-sm">
                                        <thead>
                                            <tr>
                                                <th>Mã thành viên</th>
                                                <th>Thành viên</th>
                                                <th>Chức vụ</th>
                                                <th>Chỉnh sửa</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody id="tableMemberBody">
                                            <c:forEach items="${councilPage.members}" var="member">
                                                <c:if test="${member.memberRole.id!=4}">
                                                    <tr id="${member.memberRole.role}" >
                                                        <td id="memberIdCell${member.userId.id}">${member.userId.id}</td>
                                                        <td id="memberNameCell${member.userId.id}">${member.userId.teacherId}</td>
                                                        <td id="memberRoleCell${member.userId.id}">${member.memberRole.rolev}</td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
                                                                    Thay đổi
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listTeacher}" var="s">
                                                                        <a onclick="changeMember('${s.id}', '${s}', document.getElementById('memberNameCell${member.userId.id}'), document.getElementById('memberIdCell${member.userId.id}'))" class="dropdown-item" href="javascript:;">${s.id} - ${s}</a>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                </c:if>
                                                <c:if test="${member.memberRole.id==4}">
                                                    <tr id="rowRemove${member.userId.id}" name="${member.memberRole.role}">
                                                        <td id="memberIdCell${member.userId.id}">${member.userId.id}</td>
                                                        <td id="memberNameCell${member.userId.id}">${member.userId.teacherId}</td>
                                                        <td id="memberRoleCell${member.userId.id}" >${member.memberRole.rolev}</td>
                                                        <td>
                                                            <div class="btn-group">
                                                                <button type="button" class="btn btn-light dropdown-toggle" data-toggle="dropdown">
                                                                    Thay đổi
                                                                </button>
                                                                <div class="dropdown-menu">
                                                                    <c:forEach items="${listTeacher}" var="s">
                                                                        <a onclick="changeMember('${s.id}', '${s}', document.getElementById('memberNameCell${member.userId.id}'), document.getElementById('memberIdCell${member.userId.id}'))" class="dropdown-item" href="javascript:;">${s.id} - ${s}</a>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <button class="btn btn-danger" onclick="removeMember(document.getElementById('rowRemove${member.userId.id}'),'${councilPage.maxMemberQuantity}')">Xóa</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <div id="addMemberArea" >
                                        <div class="filter">
                                            <input id="myInputMember" onclick="showFilter(document.getElementById('myFilterMember'))" onkeyup="findValue(document.getElementById('myInputMember'),document.getElementById('myFilterMember'))" type="text" placeholder="Tìm giảng viên" />
                                            <div id="myFilterMember" class="filter-content">
                                                <c:forEach items="${listTeacher}" var="teacher">
                                                    <a onclick="addMember('${teacher.id}', '${teacher}')" href="javascript:;">${teacher}</a>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <button onclick="addRowMember()" class="btn btn-success">Thêm</button>
                                    </div>
                                </div>
                                <br>
                            </div>
                        </div>
                        <br>
                        <c:if test="${councilPage.active==false}">
                            <a href="<c:url value="/council/unlock/${councilPage.id}" />"><button class="btn btn-info" >Mở khóa hội đồng</button></a>
                        </c:if>
                        <c:if test="${councilPage.active == true}">
                            <a href="<c:url value="/council/lock/${councilPage.id}"/>"><button class="btn btn-danger">Khóa hội đồng</button></a>
                        </c:if>
                        <button onclick="editCouncil('${councilPage.id}', document.getElementById('PRESIDENT'), document.getElementById('SECRETARY'), document.getElementById('REVIEWER'), document.getElementsByName('MEMBER'))" class="btn btn-success" >Cập nhật</button>
                        <div id="memberCouncilLoading" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                    </div>
                </div>
                <br>
                <c:if test="${msg!=null}">
                    <div class="alert alert-success">
                        ${msg}
                    </div>
                </c:if>
                <div style="width: 100%; padding: 10px" class="border round-xxlarge">
                    <div class="header">
                        <div class="d-flex">
                            <div class="p-2">
                                <h5 class="title">Danh sách khóa luận</h5>
                            </div>
                            <c:if test="${councilPage.thesises.size()<5}">
                                <div id="addThesisArea" class="p-2">
                                    <form action="<c:url value="/council/${councilPage.id}"/>" method="post" class="form-group" >
                                        <div class="input-group mb-3">
                                            <input style="display: none" class="input-group-append" id="myInputThesisId" type="text" name="addThesisId" />
                                            <div class="filter">
                                                <input style="width:500px;" onclick="showFilter(document.getElementById('myFilterThesis'))" onkeyup="findValue(document.getElementById('myInputThesis'), document.getElementById('myFilterThesis'))" id="myInputThesis" class="form-control input-group-append" placeholder="Thêm khóa luận" type="text" name="addThesisName" />
                                                <div id="myFilterThesis" class="filter-content">
                                                    <c:forEach items="${listThesises}" var="thesis">
                                                        <a onclick="addThesisInput('${thesis.id}', '${thesis.topic}')" href="javascript:;">${thesis.id} - ${thesis.topic}</a>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <input type="submit" value="Thêm" class="btn btn-info input-group-append" />
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                            <div id="thesisLoading" style="margin-left: 20px; display: none;" class="spinner-border text-success"></div>
                        </div>
                    </div>
                    <div name="thesisesArea" >
                        <c:if test="${councilPage.thesises.size()>0}">
                            <c:forEach items="${councilPage.thesises}" var="thes">
                                <div style="border: 1px solid gray; border-radius: 10px;padding: 10px;" name="thesisItem" >
                                    <div class="d-flex">
                                        <h7 style="margin:10px">Khóa luận: <strong>${thes.topic}</strong></h7>
                                        <br>
                                        <div class="form-group" >
                                            <div class="input-group mb-3">
                                                <div class="filter">
                                                    <input onclick="showFilter(document.getElementById('myFilterCriteria${thes.id}'))" onkeyup="findValue(document.getElementById('myInputCriteria${thes.id}'), document.getElementById('myFilterCriteria${thes.id}'))" id="myInputCriteria${thes.id}" class="form-control input-group-append" placeholder="Thêm tiêu chí" type="text" name="addCriteria" />
                                                    <div id="myFilterCriteria${thes.id}" class="filter-content">
                                                        <c:forEach items="${listCriteria}" var="criteria">
                                                            <a onclick="addCriteriaInput('${criteria.name}', document.getElementById('myInputCriteria${thes.id}'), document.getElementById('myFilterCriteria${thes.id}'),document.getElementById('tableCriteria${thes.id}'))" href="javascript:;">${criteria.name}</a>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <input id="myInputMaxScore${thes.id}" class="form-control" placeholder="Điểm tối đa" value="2.0" type="text" name="addMaxScore" />
                                                <button onclick="addCriteria('${thes.id}', document.getElementById('myInputCriteria${thes.id}'), document.getElementById('myInputMaxScore${thes.id}'), document.getElementById('criteriaLoading${thes.id}'))" class="btn btn-info">Thêm</button>
                                                <div id="criteriaLoading${thes.id}" style="margin: 5px;display: none" class="spinner-border text-dark"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <c:if test="${thes.thesisCriterias.size()<=0}">
                                        <span class="badge badge-danger">Chưa có tiêu chí để chấm diểm khóa luận !! Vui lòng thêm tiêu chí !!</span>
                                        <table id="tableCriteria${thes.id}" class="table"></table>
                                    </c:if>
                                    <c:if test="${thes.thesisCriterias.size()>0}">
                                        <table id="tableCriteria${thes.id}" class="table">
                                            <thead>
                                                <tr>
                                                    <th>Tiêu chí</th>
                                                    <th>Điểm</th>
                                                    <th>Thành viên chấm</th>
                                                    <th>Thời gian chấm</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${thes.thesisCriterias}" var="thesisCriteria">
                                                        <c:if test="${thesisCriteria.thesisScores.size()>0}">
                                                            <c:forEach items="${thesisCriteria.thesisScores}" var="thesisScore">
                                                            <tr>
                                                                <td>${thesisScore.thesisCriteriaId.criteriaId}</td>
                                                                <td>${thesisScore.score}</td>
                                                                <td>${thesisScore.userId.teacherId}</td>
                                                                <td>${thesisScore.createdDate}</td> 
                                                            </tr>
                                                            </c:forEach> 
                                                        </c:if>
                                                        <c:if test="${thesisCriteria.thesisScores.size()<=0}">
                                                            <tr>
                                                                <td>${thesisCriteria.criteriaId}</td>
                                                                <td></td>
                                                                <td></td>
                                                                <td></td>
                                                            </tr>
                                                        </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>
                                <br>
                            </c:forEach>
                        </c:if>
                        <c:if test="${councilPage.thesises.size()<=0}">
                            <span class="badge badge-danger">Không có khóa luận nào được bảo vệ bởi hội đồng này</span>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
