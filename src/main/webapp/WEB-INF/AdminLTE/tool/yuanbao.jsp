<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <jsp:include page="/fragment/AdminLTE/head.jsp">
  		<jsp:param value="iCheck" name="css"/>
  		<jsp:param value="元宝" name="title"/>
  </jsp:include>
  <body class="hold-transition login-page animsition">
    <div class="login-box">
      <div class="login-logo">
		${null == message?'摇元宝了':message}
      </div><!-- /.login-logo -->
      <div class="login-box-body">
	    <form id="signin-form" action="/tool/yuanbao" method="post">
          <div class="form-group has-feedback">
            <input id="signin-form-username" type="text" class="form-control" name="username" placeholder="账号">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input id="signin-form-password" type="password" class="form-control" name="password" placeholder="密码">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <script src="http://api.geetest.com/get.php?gt=cbab9ad7ae92d4d555c87451764ee8a1"></script>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox" name="userType" value="1"> PPD？
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button id="signin-form-submit" type="submit" class="btn btn-primary btn-block btn-flat">领取</button>
            </div><!-- /.col -->
          </div>
        </form>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <jsp:include page="/fragment/AdminLTE/js.jsp">
  		<jsp:param value="iCheck" name="js"/>
  	</jsp:include>
  	<script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>
