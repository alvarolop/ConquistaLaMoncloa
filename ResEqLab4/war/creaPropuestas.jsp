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
						</span> Inicio</a></li>
					<li><a href="/listProgramas"><span
							class="glyphicon glyphicon-th"> </span> Programas</a></li>
					<li><a href="/listPropuestas"><span
							class="glyphicon glyphicon-tasks"> </span> Propuestas</a></li>

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
		<form action="/createPropuesta?programa_id=${programa_id}"
			method="post" accept-charset="utf-8">
			<table class="table" style="width: 60%;" align="center">
				<tr>
					<td><label for="title">Categoría</label></td>
					<td>
						<select class="form-control" type="select" name="title"
						id="title" size="10">
						  <option value="Paro">Paro</option>
						  <option value="Corrupción y Fraude">Corrupción y Fraude</option>
						  <option value="Economía">Economía</option>
						  <option value="Política y Partidos">Política y Partidos</option>
						  <option value="Sanidad">Sanidad</option>
						  <option value="Problemas Sociales">Problemas Sociales</option>
						  <option value="Educación">Educación</option>
						  <option value="Recortes">Recortes</option>
						  <option value="Deshaucios">Deshaucios</option>
						  <option value="Recibo de la luz">Recibo de la luz</option>
						</select></td>
				</tr>
				<tr>
					<td valign="description"><label for="description">Description</label></td>
					<td><textarea class="form-control" rows="3" cols="50"
							name="description" id="description" pattern=".{1,10}." required title="Introduce una propuesta de con un número de caracteres entre 1 y 140"></textarea></td>
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
