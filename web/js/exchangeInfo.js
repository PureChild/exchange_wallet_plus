$(document).ready(function(){
    exchangeInfoPage.getExchangeInfo($("#api-nation").data("date"));
    exchangeInfoPage.setFirstNationInfo($("#api-nation").data("date"));
    $("#nationFlag").data("speech", "false");

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

                    if($("#nationFlag").attr("src") === "/image/flags/.png") {
                        $("#nationFlag").attr("src", "/image/flags/" + data[0].cur_unit.substring(0, 3) + ".png");
                        $("#nationFlag").show();
                    }
                }
            },
            error: function (request, status, error) {
            }
        });
    },

    getIssue: function(keyword){
        $.ajax({
            type: 'get',
            dataType: 'json',
            url: '/crawling/news/' + keyword,
            success: function (data) {
                $("#issueContainer").empty();
                for(var i = 0; i < data.length; i++){
                    var issue = $("#issue").text().replace("{{thumb}}", data[i].thumb).replace("{{title}}",data[i].title);
                    $("#issueContainer").html($("#issueContainer").html() + issue);
                }
            },
            error: function (request, status, error) {
            }
        });
    },

    readInfo: function(){
        var dealBaseRate = $("#dealBaseRate").text();
        var tts = $("#tts").text();
        var ttb = $("#ttb").text();

        var exchangeInfo = new SpeechSynthesisUtterance();
        exchangeInfo.rate = 1.5; // 0.1 to 10
        exchangeInfo.pitch = 1; //0 to 2
        exchangeInfo.text = "정보()해당 국가의 현재 매매기준율은 " + dealBaseRate + "원이며"
                        + ", 전신환(송금) 보내실 때 " + tts + "원"
                        + ", 전신환(송금) 받으실 때 " + ttb + "원 입니다.";
        exchangeInfo.onend = function() {
            $("#nationFlag").data("speech", "false");
        };

        window.speechSynthesis.speak(exchangeInfo);
    },

    setEvent: function(){
        $("#btnGetNations").click(function(){
            this.setFirstNationInfo($("#businessDate").val());
        }.bind(this));

        $("#nationList").change(function(){
            this.getExchangeInfo($("#api-nation").data("date"));

            var keyword = $("option[value=" + $("#nationList").val() + "]").text();
            this.getIssue(keyword);
        }.bind(this));

        $("#tabMenu").unbind();
        $("#tabMenu").click(function(e){

            var content = $(e.target).data("content");
            if(content === "info"){
                $("#issueContainer").hide();
                $("#exchangeInfoContainer").show();
            } else if(content === "issue"){
                $("#exchangeInfoContainer").hide();


                var keyword = $("option[value=" + $("#nationList").val() + "]").text();
                this.getIssue(keyword);
                $("#issueContainer").show();
            }

            $(".selected").removeClass("selected");
            $($(e.target).addClass("selected"));
        }.bind(this));

        $("#nationFlag").unbind();
        $("#nationFlag").click(function () {
            if($("#nationFlag").data("speech") == "true"){
                window.speechSynthesis.cancel();
                $("#nationFlag").data("speech", "false");
            } else if($("#nationFlag").data("speech") == "false"){
                this.readInfo();
                $("#nationFlag").data("speech", "true");
            }
        }.bind(this));
    }
}