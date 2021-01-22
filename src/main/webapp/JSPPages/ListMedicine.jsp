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
<script src="../../js/angular.min.js"></script>
<div ng-app="LifeMeds" ng-controller="medicineCtrl" >
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
  <tr ng-repeat="medicine in data">
	<td width='20%'>{{medicine.medicineName}}</td>
	<td width='10%'>{{medicine.medicinePrice}}</td>
	<td width='15%'>{{medicine.category.categoryName}}</td>
	<td width='15%'>{{medicine.seller.sellerName}}</td>
	<td width='25%'>{{medicine.medicineDescription}}</td>
	<td> 
		<a href='/admin/updateStatus?id={{medicine.idMedicine}}'><img src='../../icons/{{medicine.enabled == true? "active.png" : "inactive.png"}}'></a>&nbsp;&nbsp;
		<a href='/admin/editMedicine?id={{medicine.idMedicine}}'><img src='../../icons/edit.png'></a>&nbsp;&nbsp;
		<a href='/admin/deleteMedicine?id={{medicine.idMedicine}}'><img src='../../icons/delete.png'></a>
	</td>
  </tr>	
</table>
</div>
<script>
var app = angular.module('LifeMeds', []);
app.controller('medicineCtrl', function($scope, $http) {
  $http.get("/client/listMedicine?getAll=true")
  .then(function (response) {
	  $scope.data = response.data;
	});
	  
});
</script>
<%@ include file="/HtmlPages/footer.html"%>

