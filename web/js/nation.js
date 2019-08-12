$(document).ready(function(){
    apiNations.getNation($("#api-nation").data("date"));
    apiNations.setEvent();

    $('body').bind("click", function() {
        apiNations.setEvent();
    });
});
var apiNations = {
    getNation: function(targetDate) {
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
            data: {authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: targetDate, data: "AP01"},
            success: function (data) {
                if(data.length > 0){
                    $("#nationContainer").html("<select id='nationList' class='form-control ta-center input-sm' name='nationCode'></select>");
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
                            $("#nationList").html($("#nationList").html() + "<option value='" + data[i].cur_unit.substring(0,3) + "' selected>" + cur_nation + "</option>");
                        } else {
                            $("#nationList").html($("#nationList").html() + "<option value='" + data[i].cur_unit.substring(0,3) + "'>" + cur_nation + "</option>");
                        }
                    }
                }
            },
            error: function (request, status, error) {
            }
        });
    },

    setEvent: function () {
        $("#btnGetNations").unbind();
        $("#btnGetNations").click(function(){
            $("#api-nation").data("date", $("#businessDate").val().replace(/-/g, ""));
            apiNations.getNation($("#api-nation").data("date"));
        });

        $("#nationList").unbind();
        $("#nationList").change(function(){
            $("#nationFlag").attr("src", "/image/flags/" + $(this).val() + ".png");
        });
    }
};