<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<title>Add/Update Student</title>
</head>

<body>

	<div class="container">

		<h3>Student Directory</h3>
		<hr>

		<p class="h4 mb-4">Update Student Details</p>

		<form action="/StudentManagementProject/student/save" method="POST">

			<!-- Add hidden form field to handle update -->
			<input type="hidden" name="id" value="${Students.id}" />

			<div class="form-inline">
				<input type="text" name="name" value="${Students.name}"
					class="form-control mb-4 col-4" placeholder="Name">

			</div>
			<div class="form-inline">
				<input type="text" name="department" value="${Students.department}"
					class="form-control mb-4 col-4" placeholder="department">
			</div>

			<div class="form-inline">

				<input type="text" name="country" value="${Students.country}"
					class="form-control mb-4 col-4" placeholder="country">
			</div>
			<button type="submit" class="btn btn-info col-2">Save</button>
		</form>

		<hr>
		<a href="/StudentManagementProject/student/list">Back to Student List</a>

	</div>
</body>

</html>
