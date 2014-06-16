<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="Procontrato">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<title>Lista</title>
</head>
<body ng-controller="ProcontratoController">
	
	<%@ include file="/template/nav.jsp" %>
	
	<p>{{ mensagem }}</p>
	<table class="table">
	<tr>
		<th>Numero</th>
		<th>Data Inicio</th>
		<th>Data Fim</th>
		<th>Fornecedor</th>
	</tr>
		<tr ng-repeat="contrato in contratos">
			<td>{{ contrato.numero }}</td>
			<td>{{ contrato.dataInicio }}</td>
			<td>{{ contrato.dataFim }}</td>
			<td>{{ contrato.fornecedor.nome }}</td>
			<td>
				<form method="post" action="/procontratos/contrato/formEditarContrato">
					<input name="contrato" type="hidden" value="{{contrato}}">
					<input type="submit" value="Editar" />
				</form>
			</td>
			<td><button type="button" ng-click="remove(contrato)">Excluir</button></td>
		<tr>
	</table>
	<script type="text/javascript" src="<c:url value="/js/angular.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/angular-resource.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/main.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/ProcontratoController.js" />"></script>
</body>
</html>