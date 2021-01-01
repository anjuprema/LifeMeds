<%@ include file="/HtmlPages/header.html"%>
<div class="form-style-5">
  	<legend>Change Password</legend>
  	<hr/>
    <form action="/admin/updatePassword" method="post">
	<fieldset>
		<label for="oldPassword">Old Password:</label>
		<input type="password" name="oldPassword" required>
		<label for="newPassword">New Password:</label>
		<input type="password" name="newPassword" required>
		<label for="confirmPassword">Confirm Password:</label>
		<input type="password" name="confirmPassword" required>
		<div class="errorMsg">${errMsg}</div>
		<input type="submit" name="submit" value="Submit">
		<input type="hidden" name="userId" value="${userId}">
	</fieldset>
	</form>
  </div>


<%@ include file="/HtmlPages/footer.html"%>


