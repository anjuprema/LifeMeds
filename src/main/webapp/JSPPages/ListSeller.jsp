<%@ include file="/HtmlPages/header.html"%>
<script>
function filterSeller() {
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

function confirmDelete(sellerId){
	 var r = confirm("Are you sure to delete the seller?");
	 if (r == true) {
	   window.location.assign("/admin/deleteSeller?id="+sellerId);	   
	 }
}
</script>
<script src="../../js/angular.min.js"></script>
<div ng-app="LifeMeds" ng-controller="sellerCtrl" >
<input type="text" id="myInput" onkeyup="filterSeller()" placeholder="Search for sellers.." title="Type in a seller name">

<table id="myTable">
  <tr class="header">
    <th style="width:100%;">Seller Name:</th>
    <th style="width:100%;">Manage</th>
  </tr>
  <tr ng-repeat="seller in data">
  	<td width='60%'>{{seller.sellerName}}</td>
  	<td>
  		<a href='/admin/editSeller?id={{seller.idSeller}}'><img src='../../icons/edit.png'></a>&nbsp;&nbsp;
  		<a href='/admin/deleteSeller?id={{seller.idSeller}}'><img src='../../icons/delete.png'></a>
	</td>
  </tr>  
</table>
</div>

<script>
var app = angular.module('LifeMeds', []);
app.controller('sellerCtrl', function($scope, $http) {
  $http.get("/client/listSeller")
  .then(function (response) {
	  console.log(response);
	  $scope.data = response.data;
	});
	  
});
</script>

<%@ include file="/HtmlPages/footer.html"%>

