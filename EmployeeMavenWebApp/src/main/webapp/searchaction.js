function search() {
	var checkedValue = document.querySelector('input[name="userIdType"]:checked').value;
	switch (checkedValue) {
	case "EmployeeId":
		document.getElementById("searchForm").action = "/EmployeeWeb/Employee";
		break;
	case "DepartmentId":
		document.getElementById("searchForm").action = "/EmployeeWeb/Department";
		break;
	default:
		break;
	}
	
	document.getElementById("searchForm").submit();
}