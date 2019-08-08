$(document).ready(function(){
    apiNations.getNation();

    $("#nation").change(function(){
        apiNations.getFlag($(this).val());
    });
});
var apiNations = {
    target_date : $("#api-nation").data("date"),
    getNation: function() {
        target_date = this.target_date;
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
            data: {authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: target_date, data: "AP01"},
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    if(data[i].cur_nm === "유로"){
                        data[i].cur_nm = "유럽 유로"
                    } else if(data[i].cur_nm === "위안화"){
                        data[i].cur_nm = "중국 위안화"
                    }
                    var cur_nation = data[i].cur_nm.split(" ")[0];
                    if (cur_nation === undefined) {
                        cur_nation = data[i].cur_nm;
                    }
                    if(data[i].cur_unit.substring(0,3) === $("#api-nation").data("nation")){
                        $("#nation").html($("#nation").html() + "<option value='" + data[i].cur_unit.substring(0,3) + "' selected>" + cur_nation + "</option>");
                    } else {
                        $("#nation").html($("#nation").html() + "<option value='" + data[i].cur_unit.substring(0,3) + "'>" + cur_nation + "</option>");
                    }
                }
            },
            error: function (request, status, error) {
            }
        });
    },

    getFlag: function(nationCode){
        target_date = this.target_date;
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
            data: { authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: target_date, data: "AP01"},
            success: function(data) {
                for(var i = 0; i < data.length; i++){
                    if(nationCode === data[i].cur_unit){

                        $("#nation-flag").attr("src", "/image/" + data[i].cur_unit + ".png");

                        break;
                    }
                }
            },
            error: function(request,status,error) {
            }
        });
    }
};