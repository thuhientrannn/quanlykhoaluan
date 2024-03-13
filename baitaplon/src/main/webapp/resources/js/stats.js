/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
function thesisChart(id, labels = [], datas = []) {
    const data = {
        labels: labels,
        datasets: [{
                label: 'Thống kê sinh viên tham gia khóa luận từng ngành',
                data: datas,
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)'
                ],
                hoverOffset: 4
            }]
    };
    const config = {
        type: 'doughnut',
        data: data,
    };
    let ctx = document.getElementById(id).getContext("2d")
    new Chart(ctx, config)
}

function scoreChart(id, labels = [], datas = []) {
    const data = {
        labels: labels,
        datasets: [{
                label: 'Thống kê điểm khóa luận trung bình từng năm',
                data: datas,
                backgroundColor: [
                    'rgb(255, 99, 132)',
                    'rgb(54, 162, 235)',
                    'rgb(255, 205, 86)'
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

