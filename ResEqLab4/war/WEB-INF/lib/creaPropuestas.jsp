<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<title>Nueva Propuesta</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<meta charset="utf-8">
</head>
<body>
	<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="/main"><span class="glyphicon glyphicon-home">
						</span> Home</a></li>
					<li><a href="/reserve"><span
							class="glyphicon glyphicon-tasks"> </span> Programas</a></li>
					<c:choose>
						<c:when test="${userAdmin}">
							<li><a href="/create"><span
									class="glyphicon glyphicon-pencil"></span> Crear Programa</a></li>
							<li><a href="/listReserves"><span
									class="glyphicon glyphicon-tasks"></span> Propuestas</a></li>
							<li><a href="/stats"> <span
									class="glyphicon glyphicon-tasks"></span> Statistics
							</a></li>
						</c:when>
					</c:choose>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="<c:url value="${url}"/>"> <c:choose>
								<c:when test="${user != null}">
									<span class="glyphicon glyphicon-off"> </span>
								</c:when>
								<c:otherwise>
									<span class="glyphicon glyphicon-user"> </span>
								</c:otherwise>
							</c:choose> <c:out value="${urlLinktext}" /></a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">
			<h1 style="text-align: center">Nueva Propuesta</h1>
			<div style="float: right;"></div>
		</div>
	</div>
	<div class="container">
		<c:if test="${dialogo != null}">
			<div class="alert alert-success" style="width: 100%;">
				<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>${dialogo}</strong>
			</div>
		</c:if>

		<form action="/createPropuesta?programa_id=${programa_id}"
			method="post" accept-charset="utf-8">
			<table class="table" style="width: 60%;" align="center">
				<tr>
					<td><label for="title">Title</label></td>
					<td><input class="form-control" type="text" name="title"
						id="title" size="65" /></td>
				</tr>
				<tr>
					<td valign="description"><label for="description">Description</label></td>
					<td><textarea class="form-control" rows="4" cols="50"
							name="description" id="description"></textarea></td>
				</tr>


				<tr>
					<td colspan="2" align="right"><input type="submit"
						class="btn btn-default" value="Create" /></td>
				</tr>
			</table>
		</form>

	</div>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
