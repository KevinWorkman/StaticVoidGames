<html>
<head>
<title>Edit Account</title>
</head>
<body style="background-image:url(<c:url value="${backgroundImage}"/>);" onload='document.f.username.focus();'>
	<h3>Edit Account</h3>
	<form name='f' action='' method='POST' enctype="multipart/form-data">
	
		<p>Change Profile picture: <input type='file' name='profilePicture' /></p>
		Tag: <input type='text' name='tag' value='${member.getTag()}' /> 
		
		
		<input name="submit" type="submit" value="Submit" />
	</form>
</body>
</html>