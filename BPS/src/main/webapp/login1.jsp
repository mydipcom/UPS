<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->

<head>
	<fmt:requestEncoding value="utf-8"/>
	<meta charset="utf-8" />

	<title><s:message code="login.title"/></title>

	<meta content="width=device-width, initial-scale=1.0" name="viewport" />

	<meta content="" name="description" />

	<meta content="" name="author" />

	<!-- BEGIN GLOBAL MANDATORY STYLES -->

	<link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	
	<link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>

	<link href="media/css/style.css" rel="stylesheet" type="text/css"/>	

	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->

	<link href="media/css/login.css" rel="stylesheet" type="text/css"/>

	<!-- END PAGE LEVEL STYLES -->

	<link rel="shortcut icon" href="media/image/favicon.ico" />

</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->

<body class="login">

	<!-- BEGIN LOGO -->

	<div class="logo">

		<img src="media/image/logo-big.png" alt="" /> 

	</div>

	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->

	<div class="content">

		<!-- BEGIN LOGIN FORM -->
		<form:form action="login" method="post" commandName="user" cssClass="form-vertical login-form">		
			<h3 class="form-title"><s:message code="login.form.title"/></h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span><s:message code="login.form.alert"/></span>
			</div>
			<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9"><s:message code="login.form.username"/></label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i>						
						<form:input path="adminId" cssClass="m-wrap placeholder-no-fix" placeholder=""/>						
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9"><s:message code="login.form.password"/></label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<form:password path="password" cssClass="m-wrap placeholder-no-fix" placeholder=""/>						
					</div>
				</div>
			</div>

			<div class="form-actions">
				<label class="checkbox">
				<input type="checkbox" name="remember" value="1"/> <s:message code="login.form.rememberme"/>
				</label>
				<button type="submit" class="btn green pull-right">
				<s:message code="login.form.login"/> <i class="m-icon-swapright m-icon-white"></i>
				</button>            
			</div>

			<div class="forget-password">
				<h4>Forgot your password ?</h4>
				<p>
					no worries, click <a href="javascript:;" class="" id="forget-password">here</a>
					to reset your password.
				</p>
			</div>

			<div class="create-account">

				<p>

					Don't have an account yet ?&nbsp; 

					<a href="javascript:;" id="register-btn" class="">Create an account</a>

				</p>

			</div>

		</form:form>

		<!-- END LOGIN FORM --> 

	</div>

	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->

	<div class="copyright">

		2014 &copy; CampRay. Points Management System.

	</div>

	<!-- END COPYRIGHT -->

	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="media/js/bootstrap.min.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="media/js/excanvas.min.js"></script>

	<script src="media/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="media/js/jquery.uniform.min.js" type="text/javascript" ></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script src="media/js/jquery.validate.min.js" type="text/javascript"></script>

	<!-- END PAGE LEVEL PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->

	<script src="media/js/app.js" type="text/javascript"></script>

	<script src="media/js/login.js?rang=1" type="text/javascript"></script>      

	<!-- END PAGE LEVEL SCRIPTS --> 

	<script>

		jQuery(document).ready(function() {     

		  $("#adminId").attr("placeholder","<s:message code="login.form.username"/>");
		  $("#password").attr("placeholder","<s:message code="login.form.password"/>");
		  $("#adminId").focus();	
		  App.init();

		  Login.init();

		});

	</script>

	<!-- END JAVASCRIPTS -->



<!-- END BODY -->

</html>