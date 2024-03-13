/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
function addCouncil(name, president, secretary, reviewer, member1, member2){
    document.getElementById('addCouncilLoading').style.display = "block";
    fetch('/baitaplon/api/council/add',{
        method:'post',
        body: JSON.stringify({
            "name": name.value,
            "presidentId": president.value,
            "secretaryId": secretary.value,
            "reviewerId": reviewer.value,
            "member1Id": member1.value,
            "member2Id": member2.values
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res =>{
        return res.json();
    }).then(data =>{
        console.log(data);
        location.reload();
    })
}

function removeMember(row,quantity){
    row.remove();
    checkQuantityMember(document.getElementById('tableMember'), quantity, document.getElementById('addMemberArea'));
}

function changeMember(id, name, nameCell, idCell){
    let isMember = checkMember(id,name);
    if(isMember){
        alert("Thành viên đã có trong hội đồng!!!");
    }
    else{
        idCell.innerText = id;
        nameCell.innerText = name;
    }
}

function checkMember(id,name){
    let isMember = false;
    const tbMember = document.getElementById('tableMember');
    for(let i = 0; i<tbMember.rows.length-1;i++)
    {
        if(id.toString()==tbMember.rows[i+1].cells.item(0).innerText.toString())
        {
            isMember = true;
        }
        console.log(id.toString(), tbMember.rows[i+1].cells.item(0).innerText.toString(), isMember);
    }
    return isMember;
}

function editCouncil(id, president, secretary, reviewer, members){
    document.getElementById('memberCouncilLoading').style.display = "block";
    console.log(id);
    var member1Id = "";
    var member2Id = "";
    var presidentId = president.cells.item(0).innerText;
    var secretaryId = secretary.cells.item(0).innerText;
    var reviewerId = reviewer.cells.item(0).innerText;
    if(members.length==1){
        member1Id = members[0].cells.item(0).innerText;
    }
    if(members.length==2){
        member1Id = members[0].cells.item(0).innerText;
        member2Id = members[1].cells.item(0).innerText;
    }
    fetch('/baitaplon/api/council/edit',{
        method:'post',
        body: JSON.stringify({
            "councilId": id,
            "presidentId": presidentId,
            "secretaryId": secretaryId,
            "reviewerId": reviewerId,
            "member1Id": member1Id,
            "member2Id": member2Id
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res =>{
        return res.json();
    }).then(data =>{
        console.log(data);
        location.reload();
        document.getElementById('message').innerHTML = `<div class="alert alert-success" role="alert">
                                                            Đã cập nhật thành công ^^ !
                                                          </div>`;
    })
}


function showFilter(myFilter) {
    myFilter.classList.toggle("show");
}

function findValue(input, div) {
  var filter, ul, li, a, i;
  filter = input.value.toUpperCase();
  a = div.getElementsByTagName("a");
  for (i = 0; i < a.length; i++) {
    txtValue = a[i].textContent || a[i].innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      a[i].style.display = "";
    } else {
      a[i].style.display = "none";
    }
  }
}

function checkQuantityMember(table, quantity, divAdd){
    if(table.rows.length>quantity){
        divAdd.style.display = "none";
    }
    else{
        divAdd.style.display = "block";
    }
}

function addThesisInput(id,name){
    document.getElementById('thesisLoading').style.display = "block";
    fetch('/baitaplon/api/council/thesis',{
        method:'put',
        body: JSON.stringify({
            "thesisId": id
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res=>{
        return res.json();
    }).then(data =>{
        console.log(data.councilId);
        if(data.councilId ==null){
            document.getElementById('myInputThesisId').value = id;
            document.getElementById('myInputThesis'). value =name;
            showFilter(document.getElementById('myFilterThesis'));
        }
        else{
            alert("Khóa luận này đã có hội đồng bảo vệ")
        }
        document.getElementById('thesisLoading').style.display = "none";
    })
}

function addCriteriaInput(name, input, filter, table){
    var isValid = checkCriteria(name, table);
    if(isValid==true){
        alert("Tiêu chí đã tồn tại");
    }
    else{
        input.value = name;
        showFilter(filter);
    }
}

function checkCriteria(name, table){
    var isValid = false;
    if(table.rows.length>0){
        for(let i=1; i<table.rows.length;i++){
            console.log(name,table.rows[i].cells.item(0).innerText)
            if(name.toString()==table.rows[i].cells.item(0).innerText.toString()){
                isValid = true;
            }
        }
    }
    return isValid;
}

function addCriteria(thesisId, inputCriteria, inputMaxscore, loading){
    console.log(thesisId, inputCriteria.value);
    loading.style.display = "block";
    fetch('/baitaplon/api/council/addThesisCriteria',{
        method:'post',
        body: JSON.stringify({
            "thesisId": thesisId,
            "criteriaName": inputCriteria.value,
            "maxScore": inputMaxscore.value
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res =>{
        return res.json();
    }).then(data=>{
        console.log(data);
        location.reload();
    })
}





