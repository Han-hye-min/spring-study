<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${notice.title}</title>
<style >
	.container{
		margin-top: 5%;
		text-align : center;
	}
	.main{
		height: 600px;
		width: 700px;
		margin: 0 auto;
	}
	.tables{
		width : 100%;
		border: 1px solid black;
		border-collapse: collapse;
		margin-bottom: 20px;
	}
	table th, td{
		border: 1px solid black;
		height: 40px;
	}
	table td {
		text-align: left;
		padding: 10px;
	}
	.txt-title{
		margin-bottom: 10px;
		font-weight: 700;
	}
	.tx-contents{
		height: 300px;	
		padding: 30px
	}

</style>
<link rel="stylesheet" href="/webjars/bootstrap/4.6.2/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<section class="container">
	<div class="txt-title">
		<h3>글 내용 보기</h3>
	</div>
		<div class="main">
			<table class="tables">
			<colgroup>
			    <col width="20%">
			  	<col width="80%">
			</colgroup>
		  		<tbody>
				 <tr>
					 <th>글번호</th>
				 	 <td>
						${notice.noId }
				  	</td>
				 </tr>
				 <tr>
					 <th>글제목</th>
				  	 <td>
						${notice.title }
				    </td>
				 </tr>
				 <tr>
					 <th>조회수</th>
				  	 <td>
						${notice.readCnt }
				    </td>
				 </tr>
				 <tr>
					 <th>첨부파일</th>
				  	 <td>
						<c:if test="${notice.file != null}">
							<a href="javascript:void(0)" onclick="downFile(${notice.file.fileId})">
								${notice.file.fileName }
							</a>
						</c:if>
						<c:if test="${notice.file == null}">
						없음
						</c:if>
				    </td>
				 </tr>
				 <tr>
					 <th>글 내용</th>
				  	 <td class="tx-contents">
						${notice.contents }
				    </td>
				 </tr>
				 <tr>
		  </tbody>
			</table>
			<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
			<input type="hidden" id="noId" value="${notice.noId}">
		<div>
			<button type="button" class="btn btn-primary" onclick="writeNotice();">수정</button>
			<button type="button" class="btn btn-danger" onclick="listDelete();">삭제</button>
			<button type="button" class="btn btn-secondary" onclick="goList();">목록</button>
		</div>
		</div>
	</section>
</body>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script>
	function writeNotice(){
		const nowPage = $('#nowPage').val();
		const noId = $('#noId').val();
		
		location.href = '/notice/up/view.do?noId='+ noId +'&nowPage=' + nowPage;
	}
	function goList(){
		location.href = '/notice/list.do?nowPage=' + $("#nowPage").val();
	}
	function downFile(fileId){
		location.href = '/notice/file/down.do?fileId=' + fileId;
	}
	function listDelete(){
		const noId = $('#noId').val();
		const isConfirm = confirm('게시글을 삭제하시겠습니까?');
		if(isConfirm){
			location.href = '/notice/delete.do?noId=' + noId;
		}
	}
	
</script>
</html>