$(document).ready(function() {
	$('#idck').click(function() {
		var sid = $('#id').val();
		
		if(!sid){
			$('#id').focus();
			return;
		}
	
		$.ajax ({
			url: '/www/member/idCheck.blp',
			type: 'POST',
			dataType:'json',
			data: {
				id: sid
			},
			success: function(data){
				var result = data.result;
				
				if(result == 'OK'){
					alert('사용 가능한 아이디입니다.');
				} else {
					alert('사용 불가능한 아이디입니다.');
				}	
			},
			error: function(){
				alert('### 통신 에러 ###');
			}
		});
	});
	
	$('#gen').change(function() {
		var sgen = $('[name="gen"]:checked').val();
		
		//$('#avtfr').css(
	});
	
	$('#jbtn').click(function() {
		// 데이터 유효성 검사
		$('#frm').attr('action', '/www/member/joinProc.blp');
		$('#frm').submit();
	});
}); 