<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<title>Modify Resource</title>
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
					<li><a href="/map"><span class="glyphicon glyphicon-th">
						</span> Map</a></li>
					<li><a href="/reserve"><span
							class="glyphicon glyphicon-tasks"> </span> Reserve</a></li>
					<c:choose>
						<c:when test="${userAdmin}">
							<li><a href="/create"><span
									class="glyphicon glyphicon-pencil"></span> Create</a></li>
							<li><a href="/listReserves"><span
									class="glyphicon glyphicon-tasks"></span> Reserves</a></li>
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
			<h1 style="text-align: center">Bienvenido!</h1>
			<div style="float: right;"></div>
		</div>
	</div>

	<c:if test="${dialogo != null}">
		<div class="alert alert-success" style="width: 100%;">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>${dialogo}</strong>
		</div>
	</c:if>

	<form action="/createUser" method="post" accept-charset="utf-8">
		<table class="table" style="width: 80%;" align="center">

			<tr>
				<td valign="available"><label for="available">¿Quiere
						ser candidato?</label></td>
				<td><input type="radio" name="available" id="available"
					value="1" checked>Candidato <input type="radio"
					name="available" id="available" value="0">Votante</td>

			</tr>

			<tr>
				<td colspan="2" align="right"><input type="submit"
					value="Aceptar" /></td>
			</tr>
		</table>
	</form>

	</div>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
