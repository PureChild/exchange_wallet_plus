$(document).ready(function(){
    var exchangeCode = $("#exchangeCode").val();
    $("#barcodeContainer").barcode(exchangeCode, "code128");
    $("#barcodeModal").barcode(exchangeCode, "code128", {barWidth:2});
});