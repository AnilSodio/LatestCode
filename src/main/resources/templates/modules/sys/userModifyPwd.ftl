<#include "/include/taglib.ftl" >
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#oldPassword").focus();
			$("#inputForm").validate({
				messages: {
					confirmNewPassword: {equalTo: "Old password input error"}
				}
			});
		});
	</script>
</head>
<body>
	<div class="page-container-custom">
        <div class="page-bar">
            <ul class="page-breadcrumb">
                <li>
                    <i class="icon-home"></i>
                    <a target="_parent" href="${ctx}"><@spring.message code="home"></@spring.message></a>
                    <i class="fa fa-angle-right"></i>
                </li>
                <li>
                    <span><@spring.message code="admin.platUser.changePws"/></span>
                </li>
            </ul>
        </div>
		<#if message??><@tags.message content=message /></#if>
		
		<div class="portlet light ">
            <div class="portlet-title">
                <div class="caption">
                    <span class="caption-subject font-green bold"><@spring.message code="admin.platUser.changePws"/></span>
                </div>
            </div>
            <div class="portlet-body form">
                <!-- BEGIN FORM-->
                <@form.form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/modifyPwd" method="post" class="form-horizontal">
                	<@form.hidden path="id"/>
                    <div class="form-body">
                        <div class="form-group">
							<label class="col-md-3 control-label"><span class="c-red">*</span><@spring.message code="admin.platUser.oldPwd"/>: </label>
							<div class="col-md-4">
								<input id="oldPassword" name="oldPassword" type="password" value="" maxlength="50" minlength="3" class="form-control required"/>
							</div>
						</div>
                        <div class="form-group">
							<label class="col-md-3 control-label" for="newPassword"><span class="c-red">*</span><@spring.message code="admin.platUser.newPwd"/>: </label>
							<div class="col-md-4">
								<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="form-control required"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label" for="confirmNewPassword"><span class="c-red">*</span><@spring.message code="sys.confirmPassword"></@spring.message>: </label>
							<div class="col-md-4">
								<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" class="form-control required" equalTo="#newPassword"/>
							</div>
						</div>
                    </div>
                    <div class="form-actions">
                        <div class="row">
                            <div class="col-md-offset-3 col-md-4">
                                <input id="btnSubmit" class="btn btn-primary" type="submit" value="<@spring.message code="form.save"></@spring.message>"/>
                            </div>
                        </div>
                    </div>
                    </@form.form>
            </div>
        </div>
</body>
</html>