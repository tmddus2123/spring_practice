$(document).ready(function(){
	$('.pbtn').click(function(){
		var sno = $(this).attr('id');
		
		$('#nowPage').val(sno);
		$('#frm').submit();
	});
	
	$('#hbtn').click(function(){
		$(location).attr('href', '/www/main.blp');
	});
	
	$('#lbtn').click(function(){
		$(location).attr('href', '/www/member/login.blp');
	});
	
	$('#jbtn').click(function(){
		$(location).attr('href', '/www/member/join.blp');
	});
	
	$('#wbtn').click(function() {
		$('#bno').prop('disabled', true);
		$('#view').prop('disabled', true);
		$('#frm').attr('action', '/www/reBoard/reBoardWrite.blp');
		$('#frm').submit();
	});
	$('#obtn').click(function() {
		$('#frm').attr('action', '/www/member/logout.blp');
		$('#view').val('/www/reBoard/reBoardList.blp');
		$('#bno').prop('disabled', true);
		$('#frm').submit();
	});
	
	$('#listbtn').click(function() {
		$('#frm').attr('action', '/www/reBoard/reBoardList.blp');
		$('#frm').submit();
	});
	
	$('#rbtn').click(function() {
		document.frm.reset();
	});
	
	$('#wpbtn').click(function() {
		var str = $('#body').val();
		if(!str) {
			$('#body').focus();
			return;
		}
		$('#frm').submit();
	});
});