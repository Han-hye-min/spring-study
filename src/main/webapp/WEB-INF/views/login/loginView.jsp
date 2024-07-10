<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 기본 섹션 스타일 */
        section {
            width: auto;
            height: auto;
            margin: auto;
        }

        /* 제목 스타일 */
        .notice-board {
            height: 150px;
            text-align: center;
            margin-bottom: 5vh;
            font-size: 6rem;
            background: linear-gradient(145deg, rgb(34,81,255), rgb(69,144,255), rgb(241,247,255));
            background-size: 400% 400%;
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            animation: bg 4s linear infinite;
        }

        /* 배경 애니메이션 정의 */
        @keyframes bg {
            0% {background-position: 0% 50%;}
            50% {background-position: 90% 60%;}
            100% {background-position: 0% 50%;}
        }

        /* 로그인 텍스트 스타일 */
        .login-text {
            font-family: 'gmarket-extrabold';
            font-size: 2rem;
            margin-top: 15px;
            margin-bottom: 16px;
        }
        
        .login-parent {
            width: 100vw;
            display: flex;
            flex-direction: row;
            justify-content: center;
            margin: 0 auto;
        }

        /* 로그인 폼 스타일 */
        .login-wrapper1 {
            text-align: center;
            width: 60vw;
            height: auto;
            box-sizing: border-box;
            background-color: white;
            margin-bottom: auto;
            border: 1px solid #eee;
            border-radius: 13px;
            border-top-left-radius: 0;
            border-bottom-left-radius: 0;
            padding: 32px;
        }
        
		div.form-group{
			text-align: left;
		}
		
        .form-control {
            font-size: 1.2rem;
            padding: 10px;
            margin-bottom: 16px;
        }

        .btn {
            font-size: 1.2rem;
            padding: 10px 20px;
        }

        .button {
            margin-top: 40px;
        }
    </style>
</head>
<body class="bg-light">
    <section>
        <!-- 제목 섹션 -->
        <div class="notice-board">
            Tech Board
        </div>
        <div class="login-parent">
            <!-- 로그인 폼 섹션 -->
            <div class="login-wrapper1">
                <div class="login-text">
                    LOGIN
                </div>
                <!-- 로그인 폼 -->
                <form>
                    <div class="form-group">
                        <label for="memId">아이디</label>
                        <input type="text" class="form-control" id="memId" name="memId" placeholder="아이디 입력">
                    </div>
                    <div class="form-group">
                        <label for="memPasswd">비밀번호</label>
                        <input type="password" class="form-control" id="memPasswd" placeholder="비밀번호 입력">
                    </div>
                    <div class="button">
                        <button type="button" class="btn btn-primary btn-block" onclick="goLogin()">로그인</button>
                        <hr class="login_separator">
                        <button type="button" id="btn" onclick="joinLink()" class="btn btn-light btn-block">회원가입</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        //유효성 체크
        function validated() {
            //아이디 객체 가져오기.
            const memId = $('#memId'); //jquery방식
            //패스워드 객체 가져오기.
            const memPasswd = $('#memPasswd') //기본 방식
            
            if ($.trim(memId.val()).length === 0) {
                alert('아이디를 입력해주십시오.');
                memId.focus();
                return false;
            }
            if ($.trim(memPasswd.val()).length === 0) {
                alert('비밀번호를 입력해주십시오.');
                memPasswd.focus();
                return false;
            }
            return true;
        }

        function goLogin() {
            if (validated()) {
                //로그인 처리
                const dataParam = {
                    memId: $('#memId').val(),
                    memPasswd: $('#memPasswd').val()
                };
                
                $.ajax({
                    url: '/login/proc.do',
                    type: 'get',
                    dataType: 'json',
                    data: dataParam
                }).done(function(resp) {
                    if (resp.resultCode === 200) {
                        location.href = '/notice/list.do';
                    } else {
                        alert('아이디 또는 패스워드를 확인해주십시오.');
                    }
                }).fail(function(xhr, status, error) {
                    // 서버에서 오류가 난 경우
                    console.log('로그인 실패');
                });
            }
        }

        //자동실행 함수
        $(document).ready(function() {
            //엔터키 누르면 로그인
            $('#memPasswd').on('keyup', function(e) {
                if (e.keyCode === 13) {
                    goLogin();
                }
            });
        });
    </script>
</body>
</html>
