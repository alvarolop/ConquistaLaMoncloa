<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>


<html>
<head>
<title>Propuestas</title>
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

	<h1 style="text-align: center">Propuestas</h1>
	<div class=container>
		<table class="table table-striped" style="width: 60%;" align="center">
			<tr>
				<th>Categoría</th>
				<th>Propuesta</th>
				<th>Nº votos</th>
				<th></th>
			</tr>

			<c:forEach var="propuesta" items="${Map}" varStatus="itemsRow">
				<tr>
					<c:forEach items="${propuestas}" var="propuesta2">
						<c:if test="${propuesta.key eq propuesta2.id}">
							<td><c:out value="${propuesta2.title}" /></td>
							<td><c:out value="${propuesta2.description}" /></td>
							<td><c:out value="${propuesta.value}" /></td>
							<c:choose>
								<c:when test="${user!=null}">
									<c:forEach var="propuesta3" items="${isVotada}"
										varStatus="itemsRow">

										<c:if test="${propuesta3.key eq propuesta2.id}">

											<c:if test="${propuesta3.value eq '0'}">

												<td><a class="btn btn-primary"
													href="<c:url value="/votaPropuesta?propuesta_id=${propuesta.key}"/>">Vota</a>
											</c:if>
											<c:if test="${propuesta3.value eq '1'}">
												<td><a class="btn btn-danger"
													href="<c:url value="/removeVoto?propuesta_id=${propuesta.key}"/>">Borrar</a>
											</c:if>
										</c:if>

									</c:forEach>

								</c:when>
							</c:choose>
						</c:if>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	<footer class=container>
		<h6 small>
			Hay un total de 
			<c:out value="${fn:length(propuestas)}" />
			propuestas.
		</h6>
	</footer>
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
