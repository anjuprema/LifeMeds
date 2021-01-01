<%@ include file="/HtmlPages/header.html"%>
<div class="form-style-5">
  	<legend>${title}</legend>
  	<hr/>
  	<form action="/admin/createSeller" method="post">
	  	<fieldset>
		  	<input type="hidden" name="idSeller" value="${idSeller}">
		  	<label for="sellerName">Seller Name:</label>
		  	<input type="text" name="sellerName" value="${sellerName}" required>
		  	<div class="errorMsg">${errMsg}</div>
		  	<input type="submit" name="submit" value="Save">	
		</fieldset>
	</form>
</div>
<%@ include file="/HtmlPages/footer.html"%>


