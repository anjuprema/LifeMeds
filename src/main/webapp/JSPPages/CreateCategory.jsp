<%@ include file="/HtmlPages/header.html"%>
<div class="form-style-5">
  	<legend>${title}</legend>
  	<hr/>
  	<form action="/admin/createCategory" method="post">
	  	<fieldset>
		  	<input type="hidden" name="idCategory" value="${idCategory}">
		  	<label for="categoryName">Category Name:</label>
		  	<input type="text" name="categoryName" value="${categoryName}" required>
		  	<div class="errorMsg">${errMsg}</div>
		  	<input type="submit" name="submit" value="Save">	
		</fieldset>
	</form>
</div>
<%@ include file="/HtmlPages/footer.html"%>


