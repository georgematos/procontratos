<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="Procontrato">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />">
<title>Editar Contrato</title>

</head>
<body ng-controller="ProcontratoController" ng-init="iniciarFornecedor(${contrato.fornecedor.id})">

	<%@ include file="/template/nav.jsp" %>
	
	<form action="<c:url value="/contrato/contratoAlterar" />" method="post">
		
		<table class="table">
			<tr>
				<td>Numero</td>
				<td><input type="text" name="contrato.numero" value="${contrato.numero}" class="form-control" ng-required="true" <c:if test="${contrato.numero != null}">readonly="readonly"</c:if>></td>
			</tr>
			<tr>
				<td>Data Inicio</td>
				<td>
					<input placeholder="__/__/____" name="contrato.dataInicio" value="<f:formatDate pattern="dd/MM/yyyy" value="${contrato.dataInicio}" />" class="form-control" ng-required="true" />
				</td>
			</tr>
			<tr>
				<td>Data Final</td>
				<td>
					<input placeholder="__/__/____" name="contrato.dataFim" value="<f:formatDate pattern="dd/MM/yyyy" value="${contrato.dataFim}" />" class="form-control" ng-required="true" />
				</td>
				<td></td>
			</tr>
			<tr>
				<td>Fornecedor</td>
				<td>
					<%-- 
					name -> o valor será passado neste parametro
					ng-model -> fornecedor é um objeto javascript no scope do AngularJS ele é inicializado na diretiva ng-init que recupera o fornecedor recebendo seu id como argumento.
					ng-options -> lista os fornecedores 
					Veja o arquivo ProcontratoController.js
					--%> 
					<select name="selecionado.id" ng-model="fornecedor" ng-options="fornecedor.nome for fornecedor in fornecedores track by fornecedor.id" class="form-control"></select>
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Salvar" class="btn btn-default" /></td>
			</tr>
		</table>
		<hr />
		<c:forEach items="${errors}" var="error">
			<span class="label label-danger">${error.category} - ${error.message}</span>
			<br />
		</c:forEach>
	</form>
	
	<script type="text/javascript" src="<c:url value="/js/angular.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/angular-resource.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/main.js" />"></script>
	<script type="text/javascript" src="<c:url value="/js/ProcontratoController.js" />"></script>

</body>
</html>