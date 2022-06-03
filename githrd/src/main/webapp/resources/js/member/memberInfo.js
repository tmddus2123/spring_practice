$(document).ready(function() {
	$('#lbtn').click(function() {
		$(location).attr('href', '/www/member/memberList.blp');
	});
	
	$('#dbtn').click(function() {
		$('#frm').attr('action', '/www/member/delMember.blp');
		$('#frm').submit();
	});
	
	$('#ebtn1').click(function() {
		$('#frm').attr('action', '/www/member/editInfo.blp');
		$('#frm').submit();
	});
});