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
<script src="../../js/angular.min.js"></script>
<div ng-app="LifeMeds" ng-controller="categoryCtrl" >
<input type="text" id="myInput" onkeyup="filterCategory()" placeholder="Search for categories.." title="Type in a category name">

<table id="myTable">
  <tr class="header">
    <th style="width:100%;">Category Name:</th>
    <th style="width:100%;">Manage</th>
  </tr>
  <tr ng-repeat="category in data">
	<td width='60%'>{{category.categoryName}}</td>
	<td>
		<a href="/admin/editCategory?id={{category.idCategory}}"><img src='../../icons/edit.png'></a>&nbsp;&nbsp;
		<a href="/admin/deleteCategory?id={{category.idCategory}}"><img src='../../icons/delete.png'></a>
	</td>
  </tr>	
</table>
</div>
<!-- onClick='confirmDelete({{category.categoryId}});' -->
<script>
var app = angular.module('LifeMeds', []);
app.controller('categoryCtrl', function($scope, $http) {
  $http.get("/client/listCategory")
  .then(function (response) {
	  $scope.data = response.data;
	});
	  
});
</script>

<%@ include file="/HtmlPages/footer.html"%>

