/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
function loadStudent(id){
    var a = document.getElementById("list-userrole");
    if(a.value==4){
        fetch('/baitaplon/api/user/addStudent',{
            method:'post',
            body: JSON.stringify({
                'studentId' : id
            }),
            headers:{
                'Content-Type':'application/json'
            }
        }).then(res=>{
            return res.json();
        }).then(data=>{
            if(data[0]==1){
                console.log(data[1]);
                fillStudent(data[1]);
            }
            else{
                alert(data[2]);    
                refresh();
            }
        })
    }
}

function loadListStudent(){
    area = document.getElementById('areaListStudent');
    area.innerHTML = `<label for="list-userrole">Danh sách sinh viên chưa có tài khoản: </label>
                        <select onchange="selectStudent(this.value)" class="form-control" id="listStudent" name="listStudent">
                        </select>`;
    fetch('/baitaplon/api/user/loadStudentAccount',{
        method:'get',
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res =>{
        return res.json();
    }).then(data =>{
        var a = document.getElementById('listStudent');
        a.innerHTML = ``;
        for(var item in data){
            var option = document.createElement("option");
            option.text = data[item].id + " - " + data[item].firstName + " " + data[item].lastName;
            option.value = data[item].id;
            a.add(option);
            console.log(option.text);
        }
    })
}

function selectStudent(id){
    fetch('/baitaplon/api/user/selectStudent',{
        method:'post',
        body: JSON.stringify({
            'studentId' : id
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res=>{
        return res.json();
    }).then(data=>{
        fillStudent(data);
        console.log(data);
    })
}

function fillStudent(student){
    document.getElementById('id').value = student.id;
    document.getElementById('first-name').value = student.firstName;
    document.getElementById('last-name').value = student.lastName;
    document.getElementById('email').value = student.email;
    document.getElementById('phone-number').value = student.phoneNumber;
    document.getElementById('birthday').value = new Date(Date.parse(student.birthday)).toLocaleDateString('fr-CA');
}

function refresh(){
    document.getElementById('first-name').value = "";
    document.getElementById('last-name').value = "";
    document.getElementById('email').value = "";
    document.getElementById('phone-number').value = "";
    document.getElementById('birthday').value = "";
}


function fillId(userRoleId){
    console.log(userRoleId);
    showMajor(userRoleId);
    fetch('/baitaplon/api/user/loadId',{
        method:'put',
        body: JSON.stringify({
            'userRoleId' : userRoleId
        }),
        headers:{
            'Content-Type':'application/json'
        }
    }).then(res => {
        return res.json();
    }).then(data =>{
        console.log(data[0]);
        document.getElementById('id').value = data[0];
        if(userRoleId==4){
            loadListStudent();
            selectStudent(data[0]);
        }
        else{
            refresh();
            document.getElementById('areaListStudent').innerHTML = "";
        }
    })
}
function showMajor(userRole){
    var majorArea = document.getElementById('majorArea');
    if(userRole==1||userRole==4){
        majorArea.style.display = "none";
    }
    else{
        majorArea.style.display = "block";
    }
}

function addUser(userRole){
    if(userRole==4){
        id = document.getElementById('id').value;
        username = document.getElementById('username').value;
        password = document.getElementById('password').value;
        fetch('/baitaplon/api/user/addStudentUser',{
            method:'post',
            body: JSON.stringify({
                'userRoleId' : userRole,
                'userId' : id,
                'username': username,
                'password': password
            }),
            headers:{
                'Content-Type':'application/json'
            }
        }).then(res=>{
            return res.json();
        }).then(data =>{
            console.log(data);
            location.reload();
            document.getElementById('message').innerHTML = `<div class="alert alert-success" role="alert">
                                                            Thêm tài khoản thành công ^^ !
                                                          </div>`;
        })
    }
    else{
        id = document.getElementById('id').value;
        firstName = document.getElementById('first-name').value;
        lastName = document.getElementById('last-name').value;
        email = document.getElementById('email').value;
        phoneNumber = document.getElementById('phone-number').value;
        birthday = document.getElementById('birthday').value;
        username = document.getElementById('username').value;
        password = document.getElementById('password').value;
        majorId = document.getElementById('list-major').value;
        console.log(id, firstName, lastName, email, phoneNumber, birthday, username, password, majorId);
        fetch('/baitaplon/api/user/addTeacherUser',{
            method:'post',
            body: JSON.stringify({
                'userRoleId' : userRole,
                'userId' : id,
                'firstName': firstName,
                'lastName': lastName,
                'email':email,
                'phoneNumber':phoneNumber,
                'birthday': birthday,
                'majorId':majorId,
                'username': username,
                'password': password
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
                                                            Thêm tài khoản thành công ^^ !
                                                          </div>`;
        })
    }
}

function userChart(id, labels = [], datas = []){
    const data = {
        labels: labels,
        datasets: [{
                label: 'Thống kê tài khoản người dùng',
                data: datas,
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)',
                    'rgb(255, 100, 65)'
                ],
                hoverOffset: 4
            }]
    };
    const config = {
        type: 'bar',
        data: data,
    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}

