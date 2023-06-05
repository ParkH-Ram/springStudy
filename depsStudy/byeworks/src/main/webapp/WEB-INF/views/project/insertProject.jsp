<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="${pageContext.request.contextPath}/resources/images/LogoExample.png" type="image/png" />
<title>ByeWorks</title>
</head>
<!-- menubar 위에 있던 파일의 경우에 css가 적용되지 않아 두 개의 css 따로 적용  -->
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/resources/css/basic/bootstrap.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/resources/css/custom.min.css" rel="stylesheet">
<body class="nav-md">
    <div class="container body">
        <div class="main_container">
        <jsp:include page="../common/menubar.jsp"/>
            <div class="right_col" role="main">
                <div>
                    <div class="page-title">
                        <div class="title_left">
                            <h3>프로젝트 관리</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12">
                            <div class="x_panel" style="padding-left: 0; padding-right: 0;">
                                <div class="x_content">
                                    <div id="wizard" class="form_wizard wizard_horizontal">
                                        <ul class="wizard_steps" style="padding-left: 0; padding-right: 0;">
                                            <li>
                                                <a href="#step-1">
                                                    <span class="step_no">1</span>
                                                    <span class="step_descr">기본설정<br></span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#step-2">
                                                    <span class="step_no">2</span>
                                                    <span class="step_descr">작업설정<br></span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#step-3">
                                                    <span class="step_no">3</span>
                                                    <span class="step_descr">Preview<br></span>
                                                </a>
                                            </li>
                                        </ul>
                                        <form action="postTasks" class="form-horizontal form-label-left" method="post">
                                            <div id="step-1" style="height: 550px;">
                                                <div class="form-group row">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align">프로젝트명</label>
                                                    <div class="col-md-6 col-sm-6">
                                                        <input type="text" required="required" class="form-control" name="title">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align">시작일</label>
                                                    <div class="col-md-6 col-sm-6">
                                                        <input type="date" required="required" class="form-control" name="startDate">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align">종료일</label>
                                                    <div class="col-md-6 col-sm-6">
                                                        <input type="date" required="required" class="form-control" name="finishDate">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align">참여인원</label>
                                                    <div class="col-md-6 col-sm-6">
                                                        <input type="text" required="required" class="form-control" name="memberName">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align">메모</label>
                                                    <div class="col-md-6 col-sm-6">
                                                        <textarea class="form-control" name="note" style="height:200px;"></textarea>
                                                    </div>
                                                </div>
                                                <!-- <div class="form-group row">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align">파일첨부</label>
                                                    <div class="col-md-6 col-sm-6">
                                                        <input type="file" name="oldTitle">
                                                    </div>
                                                </div>
                                                <button type="submit">Submit Test</button> -->
                                            </div>
                                            <div id="step-2" style="height: 550px;">
                                                <div class="row" style="display: block;">
                                                    <div class="col-md-12 col-sm-12">
                                                        <div class="x_content">
                                                            <div class="table-responsive" id="tasks"></div>
                                                            <div class="button">
                                                                <button type="submit">Submit Test</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <div id="step-3" style="height: 550px;">
                                            <div id="tableChart"></div>
                                            <div id="ganttChart"></div>
                                        </div>
                                    </div>	
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
    $(function(){
    window.onload = addTable();
    });
    </script>
    <jsp:include page="../common/footer.jsp"/>
    <!-- jQuery Smart Wizard -->
    <script src="${pageContext.request.contextPath}/resources/js/khDeveloper/jQuery-Smart-Wizard/js/jquery.smartWizard.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
    <!-- google loader -->
    <script src="${pageContext.request.contextPath}/resources/js/khDeveloper/loader.js"></script>
    <!-- pray meta -->
    <script src="${pageContext.request.contextPath}/resources/js/khDeveloper/praymeta.js"></script>
</body>
</html>