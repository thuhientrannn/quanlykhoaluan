/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
function loadClass(e, classSelected, note){
    console.log(e.value);
    const id = e.value.toString();
    console.log(id);
    fetch('/baitaplon/api/student/class',{
        method:'post',
        body: JSON.stringify({
            'majorId':id
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res => {
        return res.json();
    }).then(data=>{
        console.log(data);
        
        classSelected.innerHTML = ``;
        if(data.length<=0){
            note.innerHTML = `<span class="badge badge-pill badge-danger">Không tìm thấy danh sách lớp thuộc khoa này, vui lòng thêm lớp trước !</span>`;
        }
        else{
            note.innerHTML = ``;
        }
        
        for(var item in data){
            var option = document.createElement("option");
            option.text = data[item].name;
            option.value = data[item].id;
            classSelected.add(option);
            console.log(option.text);
        }
    })
}

function filterByMajor(id){
    fetch(`/baitaplon/api/student`,{
        method:'post',
        body: JSON.stringify({
            'majorId':id
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res => {
        return res.json();
    }).then(data=>{
        console.log(data);
        var a = document.getElementById('table-body');
        var b = document.getElementById('major-filter');
        a.innerHTML= ``;
        for (var item in data){
            a.innerHTML = `<tr>
                                <td>${data[item].name}</td>
                                <td>${b.options[b.selectedIndex].innerText}</td>
                            </tr>` + a.innerHTML;
        }
        
    })
}

function classChange(a,b,c){
    loadClass(a,b,c);
}

function loadMajor(classSelect, majorSelect){
    var options = classSelect.options;
    majorSelect.value = options[options.selectedIndex].id;
}


function editStudent(classId, id, firstName, lastName, email, phoneNumber, birthday, studentId){
    console.log(classId, id, firstName, lastName, email, phoneNumber, birthday, studentId);
    fetch(`/baitaplon/api/student/edit`,{
        method:'post',
        body: JSON.stringify({
            'studentId': studentId,
            'classId': classId,
            'id':id,
            'firstName': firstName,
            'lastName': lastName,
            'email': email,
            'phoneNumber': phoneNumber,
            'birthday': birthday
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res => {
        return res.json();
    }).then(data =>{
        console.log(data);
        location.reload();
    })
}

