$(document).ready(function(){
    exchangeInfoPage.getExchangeInfo($("#api-nation").data("date"));
    exchangeInfoPage.setFirstNationInfo($("#api-nation").data("date"));

    exchangeInfoPage.setEvent();
    $('body').bind("click", function() {
        exchangeInfoPage.setEvent();
    });

    if($("#nationFlag").attr("src") === "/image/flags/.png"){
        $("#nationFlag").hide();
        $("#exchangeInfo").hide();
    }
    $("#issueContainer").hide();
});
var exchangeInfoPage = {
    getExchangeInfo: function(targetDate) {
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
            data: {authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: targetDate, data: "AP01"},
            success: function (data) {
                if(data.length > 0){
                    for (var i = 0; i < data.length; i++) {
                        if(data[i].cur_unit.substring(0,3) === $("#nationList").val()){
                            $("#dealBaseRate").html(data[i].deal_bas_r);
                            $("#tts").html(data[i].tts);
                            $("#ttb").html(data[i].ttb);
                        }
                    }
                }
            },
            error: function (request, status, error) {
            }
        });
    },

    setFirstNationInfo: function(targetDate){
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
            data: {authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: targetDate, data: "AP01"},
            success: function (data) {
                if(data.length > 0){
                    $("#dealBaseRate").html(data[0].deal_bas_r);
                    $("#tts").html(data[0].tts);
                    $("#ttb").html(data[0].ttb);
                    $("#exchangeInfo").show();
                    $(".tab-item:first").addClass("selected");

                    $("#nationFlag").attr("src", "/image/flags/" + data[0].cur_unit.substring(0,3) + ".png");
                    $("#nationFlag").show();
                }
            },
            error: function (request, status, error) {
            }
        });
    },

    setEvent: function(){
        $("#btnGetNations").click(function(){
            this.setFirstNationInfo($("#businessDate").val());
        }.bind(this));

        $("#nationList").change(function(){
            this.getExchangeInfo($("#api-nation").data("date"));
        }.bind(this));

        $("#tabMenu").unbind();
        $("#tabMenu").click(function(e){

            var content = $(e.target).data("content");
            if(content === "info"){
                $("#issueContainer").hide();
                $("#exchangeInfoContainer").show();
            } else if(content === "issue"){
                $("#exchangeInfoContainer").hide();
                $("#issueContainer").show();
            }

            $(".selected").removeClass("selected");
            $($(e.target).addClass("selected"));
        });
    }
}