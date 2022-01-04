<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>소단지 주차 관리 시스템</title>
        <!-- Favicon-->
        <link rel="icon" type="/resourceimage/x-icon" href="/resourceassets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/resource/css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <div class="sidebar-heading border-bottom bg-light">소경관</div>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">주민 차량</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">정식 방문자 차량</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">무단 주차 차량</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">주민 등록</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">방문자 등록</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">차량체크</a>
                </div>
            </div>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-primary" id="sidebarToggle">메뉴</button>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                <li class="nav-item active"><a class="nav-link" href="#!">홈</a></li>
                                <li class="nav-item"><a class="nav-link" href="#!">Link</a></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">회원정보</a>
                                    <div class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="#!">오주현님</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#!">개인정보수정</a>
                                         <a class="dropdown-item" href="#!">로그아웃</a>
                                       
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- Page content-->
                <div class="container-fluid">
                    <h1 class="mt-4">소단지 주차 관리 시스템</h1>
                    <p>외부 무단 주차 차량을 체크하고 관리해 보세요 !</p>
                    -----------------------------------------------------------------------------------------------
                    <p>주민 차량 : 주민 차량 리스트를 확인할 수 있습니다.</p>
                    <p>정식 방문 차량 : 등록한 정식 방문 차량을 확인할 수 있습니다.</p>
                    <p>무단 주차 차량 : 지금까지 무단으로 주차한 차량의 정보를 확인할 수 있습니다.</p>
                    <p>주민 동록 : 주민 차량 등록과 주민 정보를 등록할 수 있습니다.</p>
                    <p>방문자 등록 : 정식 방문자를 등록할 수 있습니다.</p>
                    <p>차량체크 : 주차 차량 체크 업무를 시작할 수 있습니다.</p>
                    -----------------------------------------------------------------------------------------------
                </div>
            </div>
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="/resource/js/scripts.js"></script>
    </body>
</html>