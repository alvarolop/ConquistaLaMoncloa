<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@page isELIgnored="false"%>

<!DOCTYPE html>


<html>
<head>
<title>Reserves</title>
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
					<li class="active">
					<a href="<c:url value="${url}"/>"> 
							<c:choose>
								<c:when test="${user != null}">
									<span class="glyphicon glyphicon-off"> </span>
								</c:when>
								<c:otherwise>
									<span class="glyphicon glyphicon-user"> </span>
								</c:otherwise>
							</c:choose> <c:out value="${urlLinktext}" />
							  
						<span class="caret"></span>					
					</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



	<!-- /////////////////////////////////PROGRAMAS///////////////////////////////////// -->
	<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">
			<h1 style="text-align: center">Programas</h1>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table table-striped" style="width: 60%;" align="center">		
			<c:forEach var="i" begin="0" end="1">
				<tr>
					<c:forEach var="i" begin="0" end="4">
						<td><a href="/listPropuestas"><img src='http://static.guiainfantil.com/pictures/1111-4-dibujo-para-imprimir-y-colorear-de-una-mano.jpg' width='200' height='133'></a></a></td>
					</c:forEach>
				</tr>
			</c:forEach>
	</table>
	</div>
	</div>
	<div style="float: right;"><a href="/listProgramas"><span class="glyphicon glyphicon-th">Ver más +</a></div>
	</div>
	<!-- 
	<div >
	<%int iVariable=0;%>
		<c:forEach var="i" begin="0" end="11">
				<% iVariable++; %>
				<img src='http://static.guiainfantil.com/pictures/1111-4-dibujo-para-imprimir-y-colorear-de-una-mano.jpg' width='200' height='133'> 
		</c:forEach>

	 -->
	<!-- ////////////////////////////////////PROPUESTAS////////////////////////////////////////// -->
	
	
	<div style="width: 100%;">
		<div class="line"></div>
			<h1 style="text-align: center">Propuestas</h1>
			<div style="float: right;"></div>
		</div>
	</div>
	
	<table class="table table-striped" style="width: 60%;" align="center">
			<tr>
				<th>Categoría</th>
				<th>Descripción</th>
			</tr>
			
			<c:forEach items="${propuestas}" var="propuesta">
				<tr>
					<td><c:out value="${propuesta.title}" /></td>
					<td><c:out value="${propuesta.description}" /></td>
					<!-- <td><c:out value="${reserve.resource}" /></td>-->


				</tr>

			</c:forEach>
	</table>
	<div style="float: right;"><a href="/listPropuestas"><span class="glyphicon glyphicon-th">Ver más +</a></div>
	
	<!-- ///////////////////////////////////////////////////////////////////////////////////////// -->
		
	<div class=container>
		<c:if test="${dialogo != null}">
			<div class="alert alert-success" style="width: 100%;">
				<a href="#" class="close" data-dismiss="alert">&times;</a> <strong>${dialogo}</strong>
			</div>
		</c:if>

	</div>

	<hr />

	<footer>
	<!--
		<h6 small>
			You have a total number of
			<c:out value="${fn:length(resources)}" />
			Resources.
		</h6>
	-->
	</footer>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
