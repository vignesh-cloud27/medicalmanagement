

const ctx = document.getElementById('lineChart');
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['Sat', 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
        datasets: [{
            label: 'Hematocrist',
            data: [0, 3, 7, 11, 16, 22, 30, 40],
            borderColor: [
                'deepskyblue'
            ],
            borderWidth: 3
        }]
    },
    options: {
        y: {
            beginAtZero: true
        }
    }
});

