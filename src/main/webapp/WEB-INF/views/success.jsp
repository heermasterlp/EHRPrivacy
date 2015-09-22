<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="css/bootstrap.css" rel="stylesheet" />
	<link rel="stylesheet" href="css/bootstrap-table.css">
	<link href="css/query.css" rel="stylesheet" />
	<script type="text/javascript" src = "js/jquery-1.11.3.min.js" ></script>
	<script type="text/javascript">
		/* function btnQueryClicked() {
			
			patientid = $('#patientid').val();
			querydate = $('#querydate').val();
			hospitalid = $('#hospitalid').val();
			authorizationcode = $('#authorizationcode').val();
			
			$.ajax({
				type : 'POST',
				url : "queryrecord",
				data : {"patientid" : patientid, "querydate" : querydate, "hospitalid" : hospitalid, "authorizationcode": authorizationcode},
				success : function( data ) {
					
				}
			});
		} */
	
	</script>

</head>
<body>
	<div class="container">
		<!-- Doctor Informations -->
		<div class="doctorinfo">
			<h2>welcome, ${ username }</h2>
			 <a href="logout">Logout</a>  
		</div>
		<!-- Query Conditions -->
		<div class="conditions">
			<!-- Doctor conditions -->
			<form action="queryrecord" method="post">
				<div>
					Patient ID. 
					<input id="patientid" name="patientid" placeholder="Patient ID" required autofocus>
					Date.
					<input id="querydate" name="querystartdate" type="date" autofocus/>
					-
					<input id="querydate" name="queryenddate" type="date" autofocus/>
					Hospital ID.
					<!-- <input id="hospitalid" name="hospitalid" placeholder="Hospital ID" autofocus/> -->
					<select class="select" name="hospitalid" >
					  <option value="" selected="selected">All Hospitals</option>
					  <option value="kw">HW Hospital</option>
					  <option value="gov">GOW Hospital</option>
					  <option value="uh">University Hospital</option>
					</select>
					<!-- Patient Authorization Code.
					<input id="authorizationcode" type="password" name="authorizationcode" placeholder="Authorization Code" required autofocus/> -->
					<!-- <input type="button" value="Query" onClick="btnQueryClicked()" /> -->
					<input type="submit" value="Query" />
				</div>
			</form>
		</div>
		<!-- Query Results -->
			<table class="table table-striped">
				<tr>
	                <th>NO.</th>
	                <th>Content</th>
	                <th>Detail</th>
            	</tr>
            	<c:forEach var="erecord" items="${ehealthrecrods }" varStatus="status">
            		<tr>
	            		<td>${status.index+1 }</td>
	            		<td>Name： ${erecord.getPatientInfo().getPatientName() }  Hospital: ${erecord.getHospitalID()}　    ${erecord.getDate()}</td>
	            		<td><a href="detail?patientid=${erecord.getIDCardNO() }&recordid=${erecord.getPatientRecordID() }&hospitalid=${erecord.getHospitalID()}">详细信息</a></td>
            	 	</tr>
            	</c:forEach>
			</table>
		</div>
</body>
</html>