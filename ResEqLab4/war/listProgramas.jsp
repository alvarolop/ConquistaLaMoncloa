<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>


<html>
<head>
<title>Programas</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
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


	<h1 style="text-align: center">Programas</h1>
	<div class=container>
	<table class="table table-striped" style="width: 100%;" align="center">
			<tr>
				<th>Nombre del Partido</th>
				<th>Icono Partido</th>
			</tr>
				<tr>
					<c:forEach items="${programas}" var="programa">
						<td><c:out value="${programa.titulo}" /></td>
						<td><a
					href="/perfilPrograma?programa_id=${programa.id}"> <img
						class="img-responsive" src='${programa.url_foto}' WIDTH=200 HEIGHT=200>
						</a></td>
						<tr></tr>
					</c:forEach>
				</tr>
		</table>
	</div>
	<footer class=container>
		<h6 small>
			Hay un total de 
			<c:out value="${fn:length(programas)}" />
			programas.
		</h6>
	</footer>
	
	<script src="js/bootstrap.min.js"></script>
	<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/photo-gallery.js"></script>
</body>
</html>
