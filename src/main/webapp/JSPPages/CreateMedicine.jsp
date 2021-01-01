<%@ include file="/HtmlPages/header.html"%>
<div class="form-style-5">
  	<legend>${title}</legend>
  	<hr/>
  	<form action="/admin/createMedicine" method="post" enctype="multipart/form-data">
	  	<fieldset>
		  	<label for="medicineName">Medicine Name:</label>
		  	<input type="text" name="medicineName" value="${medicineName}" required>
		  	<label for="idCategory">Category:</label>
		  	<select id="idCategory" name="idCategory" value="${idCategory}" required>
		  	  <option value="">- Select -</option>
		  	  ${categoryOptions}
			</select>
		  	<label for="idSeller">Seller:</label>
		  	<select id="idSeller" name="idSeller" value="${idSeller}" required>
		  	  <option value="">- Select -</option>
			  ${sellerOptions}
			</select>
		  	<label for="medicinePrice">Price:</label>		  	
		  	<input type="number" name="medicinePrice" value="${medicinePrice}" required step="any">
		  	<label for="medicineDescription">Description:</label>	
		  	<textarea name="medicineDescription">${medicineDescription}</textarea>
		  	<label for="newpicturePath">Upload an Image:</label>	
		  	<input type="file" name="newpicturePath" value="${newpicturePath}">		  	
		  	<div class="errorMsg">${errMsg}</div>
		  	<input type="hidden" name="idMedicine" value="${idMedicine}">
		  	<input type="hidden" name="oldFileName" value="${newpicturePath}">
		  	<input type="submit" name="submit" value="Save">	
		</fieldset>
	</form>
</div>
<%@ include file="/HtmlPages/footer.html"%>


