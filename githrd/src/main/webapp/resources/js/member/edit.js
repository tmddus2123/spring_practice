$(document).ready(function() {
	$('#hbtn').click(function() {
		$(location).attr('href', '/www/');
	});
	
	$('#rbtn').click(function() {
		document.frm.reset();
	});
	
	$('#ebtn').click(function() {
	
		var pwBool = false;
		var mailBool = false;
		var telBool = false;
		var anoBool = false;
	
	
		var spw = $('#pw').val();
		var spw2 = $('#repw').val();
		
		if(spw != spw2) {
			$('#repw').val('');
			$('#repw').focus();
			return;
		} else {
			pwBool = true;
		}
		
		if(!spw) {
			// 비밀번호를 수정하지 않는 경우이므로 비밀번호는 전송하지 않는다.
			$('#pw').prop('disabled', true);
		}
		
		var smail = $('#mail').val();
		if(smail == $('#tmail').val()) {
			$('#mail').prop('disabled', true);
		} else {
			mailBool = true;
		}
		
		var stel = $('#tel').val();
		if(stel == $('#ttel').val()) {
			$('#tel').prop('disabled', true);
		} else {
			telBool = true;
		}
		
		var sano = $('[name="ano"]:checked').val();
		if(sano == $('#tano').val()) {
			$('[name="ano"]').prop('disabled', true);
		} else {
			anoBool = true;
		}
		
		if(!(pwBool || mailBool || telBool || anoBool)) {
			// 수정 데이터가 없는 경우이므로 뷰로 돌려보낸다.
			return;
		}
		
		$('#frm').attr('action', '/www/member/infoEditProc.blp');
		$('#frm').submit();
	});

});