$(document).ready(function() {
	$('#hbtn').click(function() {
		$(location).attr('href', '/www/');
	});
	
	$('.lbtn').click(function() {
		// 누구 버튼이 클릭 되었는지 알아내고
		var sno = $(this).attr('id');
		$('#mno').val(sno);
		$('#frm').submit();
	});
});