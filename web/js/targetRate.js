$(document).ready(function () {
    var targetDate = $("#targetDate").val();
    var targetNation = $("#targetNation").val();
    var targetRate = $("#targetRate").val();
    targetRatePage.displayCompareResult(targetDate, targetNation, targetRate);
    targetRatePage.setEvent();
})

var targetRatePage = {
    displayCompareResult: function(targetDate, targetNation, targetRate){
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
            data: {authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: targetDate, data: "AP01"},
            success: function (data) {
                for(var i = 0; i < data.length; i++){
                    if(data[i].cur_unit.substring(0,3) === targetNation){
                        var compareResult = "";
                        if(Number(data[i].deal_bas_r.replace(",","")) <= Number(targetRate)){
                            compareResult = "<p class='text-warning'>현재 기준 환율(" + data[i].deal_bas_r + ")이 목표보다 낮습니다.</p>"
                        } else {
                            compareResult = "<p class='text-warning'>현재 기준 환율(" + data[i].deal_bas_r + ")이 목표 이상입니다.</p>"
                        }
                        $("#compareResult").html(compareResult);
                    }
                }
            },
            error: function (request, status, error) {
            }
        });
    },

    setEvent: function(){
        var targetDate = $("#businessDate").val();
        var targetNation = $("#targetNation").val();
        var targetRate = $("#targetRate").val();

        $("#btnGetRates").unbind();
        $("#btnGetRates").click(function(){
            this.displayCompareResult(targetDate, targetNation, targetRate);
        }.bind(this));
    }
}