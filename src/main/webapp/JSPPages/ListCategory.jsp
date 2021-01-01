<%@ include file="/HtmlPages/header.html"%>
<script>
function filterCategory() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}


function confirmDelete(categoryId){
	 var r = confirm("Are you sure to delete the category?");
	 if (r == true) {
	   window.location.assign("/admin/deleteCategory?id="+categoryId);	   
	 }
}
</script>

<input type="text" id="myInput" onkeyup="filterCategory()" placeholder="Search for categories.." title="Type in a category name">

<table id="myTable">
  <tr class="header">
    <th style="width:100%;">Category Name:</th>
    <th style="width:100%;">Manage</th>
  </tr>
  	${data}
</table>
<%@ include file="/HtmlPages/footer.html"%>

