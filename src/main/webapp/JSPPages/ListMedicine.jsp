<%@ include file="/HtmlPages/header.html"%>
<script>
function filterMedicine() {
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

function confirmDelete(medicineId){
	 var r = confirm("Are you sure to delete the medicine?");
	 if (r == true) {
	   window.location.assign("/admin/deleteMedicine?id="+medicineId);	   
	 }
}
</script>

<input type="text" id="myInput" onkeyup="filterMedicine()" placeholder="Search for medicines.." title="Type in a medicine name">

<table id="myTable">
  <tr class="header">
    <th >Medicine Name:</th>
    <th >Price:</th>
    <th >Category:</th>
    <th >Seller:</th>
    <th >Description:</th>
    <th >Manage</th>
  </tr>
  	${data}
</table>
<%@ include file="/HtmlPages/footer.html"%>

