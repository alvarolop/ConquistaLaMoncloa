<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<title>Conquista la Moncloa</title>
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
							</c:choose> <c:out value="${urlLinktext}" /> <span class="caret"></span>
					</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



	<!-- /////////////////////////////////PROGRAMAS///////////////////////////////////// -->

	<h1 style="text-align: center">Programas</h1>
	<div class="container">
		<ul class="row" list-style="none">
			<c:forEach items="${programas}" var="programa">
				<li list-style="none" class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><a
					href="/perfilPrograma?programa_id=${programa.id}"> <img
						class="img-responsive" src='${programa.url_foto}'>
				</a></li>

			</c:forEach>
			<li class="col-lg-2 col-md-2 col-sm-3 col-xs-4"><a
				href="/listProgramas"><img class="img-responsive"
					src='http://cdns2.freepik.com/foto-gratis/_428465.jpg'></a>
			</li>
		</ul>
	</div>

		<!-- ////////////////////////////////////PROPUESTAS////////////////////////////////////////// -->

	<h1 style="text-align: center">Propuestas</h1>
	<table class="table table-striped" style="width: 60%;" align="center">
		<tr>
			<th>Categoría</th>
			<th>Propuesta</th>
		</tr>

		<c:forEach items="${propuestas}" var="propuesta">
			<tr>
				<td><c:out value="${propuesta.title}" /></td>
				<td><c:out value="${propuesta.description}" /></td>
				<!-- <td><c:out value="${reserve.resource}" /></td>-->
			</tr>
		</c:forEach>
		<tr>
			<th>
			<a href="/listPropuestas">Ver más +</a>
			</th>
		</tr>
	</table>
	<script src="js/bootstrap.min.js"></script>
	<script	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/photo-gallery.js"></script>
</body>
</html>
