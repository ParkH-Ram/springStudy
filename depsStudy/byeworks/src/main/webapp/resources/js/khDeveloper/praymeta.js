function addTask(obj) {
    var taskCount = 0;
    var tbody = obj.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("tbody")[0];
    var row = tbody.insertRow(-1);
    for (var i = 0; i <= 6; i++) {
        var cell = row.insertCell(i);
        taskCount++;
        switch (i) {
            case 0: cell.innerHTML = "<input type='hidden' value='"+taskCount+"'>"+taskCount; break;
            case 1: cell.innerHTML = "<input type='text'>"; break;
            case 2: cell.innerHTML = "<input type='date'>"; break;
            case 3: cell.innerHTML = "<input type='number'>"; break;
            case 4: cell.innerHTML = "<input type='text'>"; break;
            case 5: cell.innerHTML = "<input type='text'>"; break;
            case 6: cell.innerHTML = "<button onclick='removeTask(this);'>-</button>"; break;
        }
    }
    updateTaskNumbers();
}

function removeTask(obj) {
    var tbody = obj.parentNode.parentNode.parentNode;
    if (tbody.getElementsByTagName("tr").length == 1) {
        obj.parentNode.parentNode.parentNode.parentNode.remove();
        console.log(document.getElementById("tasks"));
    } else {
        obj.parentNode.parentNode.remove();
    }
    updateTaskNumbers();
}

function updateTaskNumbers() {
    var tmpTaskCount = 0;
    var tmpLength = document.getElementById("tasks").getElementsByTagName("table").length;
    for (i = 0; i < tmpLength; i++) {
        var table = document.getElementById("tasks").getElementsByTagName("table")[i];
        for (j = 2; j < table.getElementsByTagName("tr").length; j++) {
            tmpTaskCount++;
        	table.getElementsByTagName("tr")[j].childNodes[0].innerHTML = "<input type='hidden' value='"+tmpTaskCount+"' name='tasksCategory["+i+"].tasks["+(j-2)+"].taskNo'>"+tmpTaskCount;
            table.getElementsByTagName("tr")[j].getElementsByTagName("td")[1].getElementsByTagName("input")[0].setAttribute("name", "tasksCategory["+i+"].tasks["+(j-2)+"].title");
            table.getElementsByTagName("tr")[j].getElementsByTagName("td")[2].getElementsByTagName("input")[0].setAttribute("name", "tasksCategory["+i+"].tasks["+(j-2)+"].start");
            table.getElementsByTagName("tr")[0].getElementsByTagName("th")[1].getElementsByTagName("input")[0].setAttribute("name", "tasksCategory["+i+"].category");
            table.getElementsByTagName("tr")[j].getElementsByTagName("td")[3].getElementsByTagName("input")[0].setAttribute("name", "tasksCategory["+i+"].tasks["+(j-2)+"].duration");
            table.getElementsByTagName("tr")[j].getElementsByTagName("td")[4].getElementsByTagName("input")[0].setAttribute("name", "tasksCategory["+i+"].tasks["+(j-2)+"].memberName");
            table.getElementsByTagName("tr")[j].getElementsByTagName("td")[5].getElementsByTagName("input")[0].setAttribute("name", "tasksCategory["+i+"].tasks["+(j-2)+"].reqNo");
        }
    }
}

function addTable() {
    var tasks = document.getElementById("tasks");
    var table = document.createElement("table");
    table.innerHTML = "<thead><tr></tr><tr></tr></thead><tbody><tr></tr></tbody>";
    table.setAttribute("class", "table table-striped jambo_table bulk_action");
    tasks.appendChild(table);
    for (var i = 0; i <= 1; i++) {
        if (i == 0) {
            table.children[i].firstChild.setAttribute("class", "headings");
            var th = document.createElement("th");
            insertTh = table.children[i].children[0].appendChild(th);
            insertTh.innerHTML = "카테고리";
            var th = document.createElement("th");
            insertTh = table.children[i].children[0].appendChild(th);
            insertTh.setAttribute("class", "column-title");
            insertTh.innerHTML = "<input type='text'>";
            table.children[i].children[1].setAttribute("class", "headings");
            for (var j = 0; j <= 6; j++) {
                var th = document.createElement("th");
                insertTh = table.children[i].children[1].appendChild(th);
                insertTh.setAttribute("class", "column-title");
                switch (j) {
                    case 0: insertTh.innerHTML = "작업번호"; break;
                    case 1: insertTh.innerHTML = "작업명"; break;
                    case 2: insertTh.innerHTML = "시작일"; break;
                    case 3: insertTh.innerHTML = "기간 (일)"; break;
                    case 4: insertTh.innerHTML = "필요인원"; break;
                    case 5: insertTh.innerHTML = "요구작업번호"; break;
                    case 6: insertTh.innerHTML = "<button onclick='addTask(this); return false;'>+</button>"; break;
                }
            }
        } else {
            for (var j = 0; j <= 6; j++) {
                insertTd = table.children[i].children[0].insertCell(j);
                switch (j) {
                    case 0: insertTd.innerHTML = ""; break;
                    case 1: insertTd.innerHTML = "<input type='text'>"; break;
                    case 2: insertTd.innerHTML = "<input type='date'>"; break;
                    case 3: insertTd.innerHTML = "<input type='number'>"; break;
                    case 4: insertTd.innerHTML = "<input type='text'>"; break;
                    case 5: insertTd.innerHTML = "<input type='text'>"; break;
                    case 6: insertTd.innerHTML = "<button onclick='removeTask(this);'>-</button>"; break;
                }
            }
        }
    }
    updateTaskNumbers();
}

google.charts.load('current', { 'packages': ['table'] });
google.charts.load('current', { 'packages': ['gantt'] });
google.charts.setOnLoadCallback(tableChart);
google.charts.setOnLoadCallback(ganttChart);

function daysToMilliseconds(days) {
    return days * 24 * 60 * 60 * 1000;
}

function tableChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', '작업번호');
    data.addColumn('string', '작업명');
    data.addColumn('string', '카테고리');
    data.addColumn('date', '시작일');
    data.addColumn('date', '종료일');
    data.addColumn('number', '기간 (일)');
    data.addColumn('number', '진행률 (%)');
    data.addColumn('string', '필요인원');
    data.addColumn('string', '요구작업번호');
    dataRows = [];
    var tmpTables = document.getElementById("tasks").getElementsByTagName("TABLE");
    for (i = 0; i < tmpTables.length; i++) {
        var tmpTasks = tmpTables[i].getElementsByTagName("TBODY")[0].getElementsByTagName("TR");
        for (j = 0; j < tmpTasks.length; j++) {
            tmpTds = tmpTasks[j].getElementsByTagName("TD");
            var finishDate = new Date(tmpTds[2].getElementsByTagName("INPUT")[0].value);
            finishDate.setDate(finishDate.getDate(tmpTds[2].getElementsByTagName("INPUT")[0].value) + parseInt(tmpTds[3].getElementsByTagName("INPUT")[0].value));
            var currentDate = new Date();
            var percentComplete = parseInt((currentDate - new Date(tmpTds[2].getElementsByTagName("INPUT")[0].value)) / (24 * 60 * 60 * 1000)) / 3 * 100;
            if (percentComplete < 0) {
                percentComplete = 0;
            }
            tmpData =   [tmpTds[0].innerText, 
                        tmpTds[1].getElementsByTagName("input")[0].value, 
                        tmpTables[i].getElementsByTagName("THEAD")[0].getElementsByTagName("TR")[0].getElementsByTagName("TH")[1].getElementsByTagName("input")[0].value, 
                        new Date(tmpTds[2].getElementsByTagName("INPUT")[0].value), 
                        finishDate, 
                        parseInt(tmpTds[3].getElementsByTagName("INPUT")[0].value), 
                        percentComplete, 
                        tmpTds[4].getElementsByTagName("input")[0].value, 
                        tmpTds[5].getElementsByTagName("input")[0].value];
            data.addRow(tmpData);
        }
    }
    var table = new google.visualization.Table(document.getElementById('tableChart'));
    table.draw(data, {width:'100%'});
}

function ganttChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Task ID'); // 작업번호
    data.addColumn('string', 'Task Name'); // 작업명
    data.addColumn('string', 'Resource'); // 카테고리
    data.addColumn('date', 'Start Date'); // 시작일
    data.addColumn('date', 'End Date'); // 종료일
    data.addColumn('number', 'Duration'); // 기간 (일)
    data.addColumn('number', 'Percent Complete'); // 진행률 (%)
    data.addColumn('string', 'Dependencies'); // 요구작업번호
    data.addColumn('string', 'Member'); // 필요인원
    dataRows = [];
    var tmpTables = document.getElementById("tasks").getElementsByTagName("TABLE");
    for (i = 0; i < tmpTables.length; i++) {
        var tmpTasks = tmpTables[i].getElementsByTagName("TBODY")[0].getElementsByTagName("TR");
        for (j = 0; j < tmpTasks.length; j++) {
            tmpTds = tmpTasks[j].getElementsByTagName("TD");
            var currentDate = new Date();
            var percentComplete = parseInt((currentDate - new Date(tmpTds[2].getElementsByTagName("INPUT")[0].value)) / (24 * 60 * 60 * 1000)) / 3 * 100;
            if (percentComplete < 0) {
                percentComplete = 0;
            }else if (percentComplete > 100) {
                percentComplete = 100;
            }
            tmpData =   [tmpTds[0].innerText, 
                        tmpTds[1].getElementsByTagName("input")[0].value, 
                        tmpTables[i].getElementsByTagName("THEAD")[0].getElementsByTagName("TR")[0].getElementsByTagName("TH")[1].getElementsByTagName("input")[0].value, 
                        new Date(tmpTds[2].getElementsByTagName("INPUT")[0].value), 
                        null, 
                        daysToMilliseconds(parseInt(tmpTds[3].getElementsByTagName("INPUT")[0].value)), 
                        percentComplete, 
                        tmpTds[5].getElementsByTagName("input")[0].value, 
                        tmpTds[4].getElementsByTagName("input")[0].value];
            data.addRow(tmpData);
        }
    }
    var options = {
        width: 1200,
        height: 25 * 12,
        gantt: {
            trackHeight: 25,
            barHeight: 20,
            criticalPathEnabled: false
        }
    };
    var chart = new google.visualization.Gantt(document.getElementById('ganttChart'));
    chart.draw(data, options);
}