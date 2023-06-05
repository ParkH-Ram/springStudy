/**
 * 바 형 차트
 * 
 */
// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Bar Chart Example
var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ["10대", "20대", "30대", "40대", "50대", "60대 이상"],
    datasets: 
     [{
      label: "남자",
      backgroundColor: "#ffc107",
      borderColor: "rgba(2,117,216,1)",
      data: [4215, 5312, 6251, 7841, 9821, 14984],
    },
    {
      label: "여자",
      backgroundColor: "#28a745",
      borderColor: "rgba(2,117,216,1)",
      data: [6000, 6000, 7561, 7830, 8001, 14984],
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'age'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 6
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 15000,
          maxTicksLimit: 5
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
