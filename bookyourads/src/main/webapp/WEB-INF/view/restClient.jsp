<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="<%=request.getContextPath()%>/js/jquery-1.9.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.liveselectcombo.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/stylesheet-cog.css?v=123" type="text/css" media="print, screen" />
<title>Rest Client</title>
<script type="text/javascript">
	$(function() {
		// Reset Fields
		$('#reqPayload,#req-response,#session-n').hide();
		
		$("#RestAct").change(function(){
			$('#reqPayload').toggle($(this).val() == "user");		
			$('#session-n').toggle($(this).val() == "viewByName" || $(this).val() == "delete");
		}).change();
	});

	function sendMeto() {
		var act = $('#RestAct').val();
		if(act == "user"){
			addUser();
		}
		else if (act == "view-all"){
			viewAll();
		}
		else if(act == "viewByName"){
			viewByName();
		}
		else if(act == "delete"){
			deleteByName();
		}
	}

	function addUser() {
		var reqpld = $('#payload').val();
		$.ajax({
			contentType: "application/json",
			data: reqpld,
			dataType: 'JSON',
			type: "POST",
			url: "${pageContext.request.contextPath}/login-user/add",
			success: function(response) {
				$('#req-response').show();	
				$('#req-response-b').text(JSON.stringify(response));
			},
			error: function(e) {
				
			}
		});

	}

	function viewAll() {
		$.ajax({
			contentType: "application/json",
			dataType: 'JSON',
			type: "GET",
			url: "${pageContext.request.contextPath}/view-all",
			success: function(response) {
			$('#req-response').show();	
			$('#req-response-b').text(JSON.stringify(response));
			},
			error: function(e) {
				
			}
		});
	}
	
	function viewByName() {
		var sn = $('#session-name').val();
		$.ajax({
			contentType: "application/json",
			dataType: 'JSON',
			type: "GET",
			url: "${pageContext.request.contextPath}/view?name="+sn,
			success: function(response) {
				$('#req-response').show();	
				$('#req-response-b').text(JSON.stringify(response));
			},
			error: function(e) {
				
			}
		});
	}
	function deleteByName() {
		var sn = $('#session-name').val();
		$.ajax({
			contentType: "application/json",
			dataType: 'JSON',
			type: "GET",
			url: "${pageContext.request.contextPath}/remove?name="+sn,
			success: function(response) {
				$('#req-response').show();	
				$('#req-response-b').text(JSON.stringify(response));
			},
			error: function(e) {
				
			}
		});
	}
	
</script>
</head>
<body>
<div style="height:5px;width:100%;background:#000000"></div>
<div id="Rest-Client-h" style="height:100px;width:100%;background:#2BA3D4;color:#fff;line-height:90px" align="center">
	<h1>Rest Based Session Monitoring</h1>
</div>
<div id="Rest-Client-b">
	<div style="margin:30px">
		<div class="padBot10">
			<label class="floatleft w270 lineht34 txtAlgn-R padR10">Please Select Your Session Action :</label>
			<div class="selectBoxDiv item marginTop-1 floatleft">
				<div class="control select">
					<select id="RestAct" class="customselect w300">
						<option value="" selected="selected">Please Select</option>
						<option value="user">Create User Session</option>
						<option value="view-all">View All Session Attributes</option>
						<option value="viewByName">View Session Attribute By Name</option>
						<option value="delete">Delete Session Attribute By Name</option>
					</select>
				</div>
			</div>
			<div class="clearboth"></div>
		</div>
		<div id="reqPayload" class="valignTop padBot10 clearboth">
			<label class="floatleft w270 lineht34 txtAlgn-R padR10" for="payload">Request Payload :</label>
			<textarea id="payload" name="payload" class="w550 textarea-box" style="height:180px;"></textarea>
		</div>
		<div id="session-n" class=" padBot10 clearboth">
			<label class="floatleft w270 lineht34 txtAlgn-R padR10">Name of the Session :</label>
			<input type="text" id="session-name" name="sessionn" class="text-box" />
		</div>
		<a title="Send" href="#" class="primary-btn nav-link" onclick="sendMeto()"><span style="margin-left:280px;">Send</span></a>
		<div class="clearboth">&nbsp;</div>
		<div class="horizontal-sptr marginBot10"></div>
	</div>
	<div id="req-response">
		<label class="floatleft w270 txtAlgn-R padR10">Response Body:</label>
		<div id="req-response-b" style="color:#2BA3D4;float:left; width:65%; word-wrap:break-word;">
		</div>
	</div>
</div>
</body>
</html>