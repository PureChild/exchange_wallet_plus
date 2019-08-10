$(document).ready(function () {
    google.charts.load('current', {'packages':['line','controls']});
    chartDrowFun.chartDrow($("#nationCode").val());
});

var chartDrowFun = {
    chartDrow : function(nationCode){
        var chartData = '';
        var chartDateformat = 'yyyy년MM월dd일';
        //라인차트의 라인 수
        var chartLineCount = 5;
        //컨트롤러 바 차트의 라인 수
        var controlLineCount = 5;

        //date 포맷 함수
        function dateFormat(date){
            function pad(num) {
                num = num + '';
                return num.length < 2 ? "0" + num : num;
            }
            return date.getFullYear() + "" + pad(date.getMonth()+1) + "" + pad(date.getDate());
        }

        function drawDashboard() {
            var chartData = new google.visualization.DataTable();
            //그래프에 표시할 컬럼 추가
            chartData.addColumn('datetime', '날짜');
            chartData.addColumn('number', '매매기준율');

            //그래프에 표시할 데이터
            var dataRow = [];

            for(var i = 30; i > 0; i--){
                targetTime = new Date().getTime() - (i * 24 * 60 * 60 * 1000);
                targetDate = new Date(targetTime);
                $.ajax({
                    type: 'get',
                    dataType: 'json',
                    url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
                    data: {authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: dateFormat(targetDate), data: "AP01"},
                    async: false,
                    success: function (data) {
                        for(var i = 0; i < data.length; i++){
                            if(nationCode === data[i].cur_unit){
                                var exchangeRate = Number(data[i].deal_bas_r.replace(",",""));
                                dataRow = [targetDate, exchangeRate];
                                chartData.addRow(dataRow);

                                break;
                            }
                        }
                    },
                    error: function (request, status, error) {
                    }
                });

            }

            var chart = new google.visualization.ChartWrapper({
                chartType   : 'LineChart',
                containerId : 'lineChartArea', //라인 차트 생성할 영역
                options     : {
                    isStacked   : 'percent',
                    focusTarget : 'category',
                    height          : 300,
                    width              : '100%',
                    legend          : { position: "top", textStyle: {fontSize: 13}},
                    pointSize        : 5,
                    tooltip          : {textStyle : {fontSize:12}, showColorCode : true, trigger: 'both'},
                    hAxis              : {format: chartDateformat, gridlines:{count:chartLineCount, units: {
                                years : {format: ['yyyy년']},
                                months: {format: ['MM월']},
                                days  : {format: ['dd일']},
                                hours : {format: ['HH시']}}
                        },textStyle: {fontSize:12}},
                    vAxis : {gridlines:{count:-1}, textStyle:{fontSize:12}},
                    series: {0: {color: '#008485'}},
                    backgroundColor: "transparent",
                    animation : {startup: true, duration: 1000, easing: 'in'}
                }
            });

            var control = new google.visualization.ControlWrapper({
                controlType: 'ChartRangeFilter',
                containerId: 'controlsArea',  //control bar를 생성할 영역
                options: {
                    ui:{
                        chartType: 'LineChart',
                        chartOptions: {
                            chartArea: {'width': '60%','height' : 150},
                            series: {0: {color: '#000'}},
                            backgroundColor: "transparent",
                            hAxis: {'baselineColor': 'none', format: chartDateformat, textStyle: {fontSize:12},
                                gridlines:{count:controlLineCount,units: {
                                        years : {format: ['yyyy년']},
                                        months: {format: ['MM월']},
                                        days  : {format: ['dd일']},
                                        hours : {format: ['HH시']}}
                                }}
                        }
                    },
                    filterColumnIndex: 0
                }
            });

            var date_formatter = new google.visualization.DateFormat({ pattern: chartDateformat});
            date_formatter.format(chartData, 0);

            var dashboard = new google.visualization.Dashboard(document.getElementById('Line_Controls_Chart'));
            window.addEventListener('resize', function() { dashboard.draw(chartData); }, false); //화면 크기에 따라 그래프 크기 변경
            dashboard.bind([control], [chart]);
            dashboard.draw(chartData);

            $("#msgBeforeDrawChart").hide();
        }
        google.charts.setOnLoadCallback(drawDashboard);
    }
}