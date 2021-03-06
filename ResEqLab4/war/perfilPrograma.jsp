<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>


<html>
<head>
<title>${programa.titulo}</title>
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

	<h1 style="text-align: center">${programa.titulo}</h1>
	
	<div class="col-md-offset-2 col-md-3">
		<img width='300' align='middle' src='${programa.url_foto}'>
	</div>
	<div class="col-md-5">
		<a href="https://twitter.com/share" class="twitter-share-button"
			data-text="Este es mi programa en Conquista la Moncloa">Compartir</a>
		<br>
		<form action="/votaPropuesta" method="post" accept-charset="utf-8"
				id="formulario">
			<div class="table-responsive">
				<table class="table table-striped" style=""	width: 70%;" align="center">
					<tr>
						<th>#</th>
						<th>Categoría</th>
						<th>Descripción</th>
						<th>Voto</th>
					</tr>
					<% int Variable = 0;%>
					<c:forEach items="${programa.propuestas}" var="propuesta">
						<c:forEach items="${propuestas}" var="propuesta2">
							<c:if test="${propuesta2.id eq propuesta}">
								<% Variable++;%>

								<tr>
									<td><%=Variable%></td>
									<td><c:out value="${propuesta2.title}" /></td>
									<td><c:out value="${propuesta2.description}" /></td>

									<td><div class="checkbox">
											<label><input name="votos" type="checkbox"
												value="${propuesta2.id}"></label>
										</div></td>
								</tr>
							</c:if>

						</c:forEach>
					</c:forEach>
					<c:if test="${programa.user eq appUser.id}">
						<td><%=Variable + 1%></td>
						<td><c:out value="¿Quiere Añadir una propuesta?" /></td>
						<td><a class="btn btn-primary"
							href="<c:url value="/createPropuesta?programa_id=${programa.id}"/>">Añadir</a>
						</td>
					</c:if>
				</table>
				<button type="submit" class="btn btn-default">Votar</button>
			</div>
		</form>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/twitterscript.js"></script>
</body>
</html>
