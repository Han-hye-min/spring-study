<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<div id="msg">
	
	</div>
</body>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script>
	let count = 5;
	let timer = '';
	let msg = '';
	
	function error(){
		if(count == 0){
			clearInterval(timer);
			location.href= "/notice/list.do";			
		}else{
			msg = '서비스동작이 실패했습니다. ' + count + '초 후 페이지 이동합니다';
			count--;
			
		}
		$('#msg').empty();
		$('#msg').append(msg);
	}
	
	timer = setInterval(error, 1100);
</script>
</html>