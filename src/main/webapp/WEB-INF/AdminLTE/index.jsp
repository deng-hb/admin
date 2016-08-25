<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <jsp:include page="/fragment/AdminLTE/head.jsp">
  		<jsp:param value="iCheck" name="css"/>
  		<jsp:param value="Sign In" name="title"/>
  </jsp:include>
  <body class="hold-transition login-page animsition">
    <div class="login-box">
      <div class="login-logo">
        <a href="/"><b>Admin</b>LTE</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Sign in to start your session</p>
		<span id="signin-form-msg" style="color:#00BCD4;font-size:14px;">${param.msg}</span>
	    <form id="signin-form" action="/signin" method="post">
          <div class="form-group has-feedback">
            <input id="signin-form-username" type="text" class="form-control" value="${param.username}" name="username" placeholder="Email">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input id="signin-form-password" type="password" class="form-control" name="password" placeholder="Password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <script src="http://api.geetest.com/get.php?gt=cbab9ad7ae92d4d555c87451764ee8a1"></script>
          </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox" name="remember" value="1"> Remember Me
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button id="signin-form-submit" type="submit" class="btn btn-primary btn-block btn-flat">Sign In</button>
            </div><!-- /.col -->
          </div>
        </form>

		<!--
        <div class="social-auth-links text-center">
          <p>- OR -</p>
          <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using Facebook</a>
          <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
        </div> /.social-auth-links -->

        <a href="#">I forgot my password</a><br>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <jsp:include page="/fragment/AdminLTE/js.jsp">
  		<jsp:param value="iCheck,md5,jq.utils" name="js"/>
  	</jsp:include>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
        jq.ajax('/validate-remember',{},function(data){});
        var showmsg = function(msg){
			$('#signin-form-msg').innerHTML = msg;
		}
		
		var error_count = 0;
		var el_username = $('#signin-form-username');
		var el_password = $('#signin-form-password');
		
		$('#signin-form-submit').on('click',function(){
			if('' == el_username.val()){
				showmsg('账户不能为空('+ ++error_count +')');
				el_username.focus();
				return;
			}
			if('' == el_password.val()){
				showmsg('密码不能为空('+ ++error_count +')');
				el_password.focus();
				return;
			}
			
			var geetest_challenge = $('.geetest_challenge').val();
			var geetest_validate = $('.geetest_validate').val();
			var geetest_seccode = $('.geetest_seccode').val();
			
			if('' == geetest_challenge || '' == geetest_validate || '' == geetest_seccode){
				showmsg('请滑动滑块验证('+ ++error_count +')');
				return;
			}

			el_password.val(md5(el_password.val()));
			$('#signin-form').submit();

		});
      });
    </script>
  </body>
</html>
