$(document).ready(function () {
    lookupPage.setEvent();
});

var lookupPage = {
    setEvent: function () {
        $("#exchangeCode").keypress(function (e) {
            if(e.keyCode == 13){
                event.preventDefault();
                $("#lookupForm").attr("action", "/admin/lookup/" + $(this).val());
                $("#lookupForm").submit();
            }
        });

        $("#btnExchange").click(function(){
            window.location.href = "/admin/close/exchange/" + $("#reservationNum").val();
            // console.log("/close/exchange/" + $("#reservationNum").val());
        });
    }
}