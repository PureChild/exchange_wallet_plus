<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
      src="https://code.jquery.com/jquery-3.4.1.min.js"
      integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
      crossorigin="anonymous"></script>
</head>
<body>
	<select id="nation">
	</select>
	<p id="ratio"></p>
</body>
<script>
$(document).ready(function(){
	testPage.getNation();
	$("#nation").change(function(){
		testPage.getRatio($(this).val());
	});
});
var target_date = "20190731";
var testPage = {
	getNation: function(nationCode){
		$.ajax({
			type: 'get',
			dataType: 'json',
			url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
			data: { authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: target_date, data: "AP01"},
			success: function(data) {
				for(var i = 0; i < data.length; i++){
					var cur_nation = data[i].cur_nm.split(" ")[0];
					if(cur_nation === undefined) {
						cur_nation = data[i].cur_nm;
					}
					$("#nation").html($("#nation").html() + "<option value='" + data[i].cur_unit + "'>" + cur_nation + "</option>");
				}
			},
			error: function(request,status,error) {
			}
		});
	},
		
	getRatio: function(nationCode){
		$.ajax({
			type: 'get',
			dataType: 'json',
			url: 'https://www.koreaexim.go.kr/site/program/financial/exchangeJSON',
			data: { authkey: "TVREMCLuKhL0lEgNOEQq4cRB99gBojKq", searchdate: target_date, data: "AP01"},
			success: function(data) {
				for(var i = 0; i < data.length; i++){
					if(nationCode === data[i].cur_unit){
						var cur_unit = data[i].cur_nm.split(" ")[1];
						if(cur_unit === undefined) {
							cur_unit = data[i].cur_nm;
						}
						$("#ratio").html("1" + cur_unit + "<br/> 매매 기준율 " + data[i].deal_bas_r + "원 <br/> 송금 받으실 때 " + data[i].ttb + "원 <br/> 송금 보내실 때 " + data[i].tts + "원");
						
						break;
					}
				}
			},
			error: function(request,status,error) {
			}
		});
	}
};
</script>
</html>