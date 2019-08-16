$(document).ready(function(){
    var exchangeCode = $("#exchangeCode").val();
    $("#barcodeContainer").barcode(exchangeCode, "code128");
});