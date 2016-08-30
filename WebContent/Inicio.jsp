<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="es">
	<head>
		<title>
			<% 
				if(request.getAttribute("Busqueda") != null){
			%>
			<%= request.getAttribute("Busqueda") %> - SubSearch
			<%	
				}else{
			%>
			Inicio - SubSearch
			<%	
				}
			%>
		</title>
		
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="./style/bootstrap-3.3.6-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="./style/font-awesome-4.6.3/css/font-awesome.min.css">
		<link rel="stylesheet" href="./style/template.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		<script src="./style/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
		<script src="./script/template.js"></script>
	</head>
	
	<!-- PAGINA DEL NAVBAR -->
	<!-- http://bootsnipp.com/snippets/featured/findcond-navbar -->
	<!-- http://getbootstrap.com/components/#navbar-default -->
	
	<body>
	
		<!-- NAVBAR -->
		<nav class="navbar navbar-findcond">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="./Inicio.input">SubSearch</a>
				</div>
	
				<div class="collapse navbar-collapse" id="navbar">
					<ul class="nav navbar-nav">
						<li><a href="BusquedaAvanzada.html">B&uacute;squeda Avanzada</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Categor&iacute;as <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="Categoria.input?Category=0">#Education</a></li>
								<li><a href="Categoria.input?Category=1">#Entertainment</a></li>
								<li><a href="Categoria.input?Category=2">#Comedy</a></li>
								<li><a href="Categoria.input?Category=3">#Music</a></li>
							</ul>
						</li>
					</ul>
					<form class="navbar-form navbar-left" action="Query.input" method="POST" role="search">
						<div class="form-group">
							<input type="text" class="form-control" name="Busqueda" id="Busqueda" placeholder="Buscar">
						</div>
						<button type="submit" class="btn btn-default">
							<span><i class="fa fa-search" aria-hidden="true"></i></span>
						</button>
					</form>
				</div>
			</div>
		</nav>
		<!-- NAVBAR -->
		
		<!-- CUERPO -->
		<div class="container-fluid body">
			<div class="row">
				<div class="col-sm-8 col-sm-offset-2 inicio">
					
					<%
						@SuppressWarnings("unchecked")
						ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) request.getAttribute("resultado");
						if(list.size() > 0){
							for(int i=0; i<list.size(); i++){
					%>
					<div class="row">
						<div class="col-sm-12">
							<div class="media">
								<a class="media-left" href="https://www.youtube.com/watch?v=<%= list.get(i).get("id")%>" target="_blank">
									<img class="media-object" src="http://img.youtube.com/vi/<%= list.get(i).get("id")%>/mqdefault.jpg" alt="<%= list.get(i).get("titulo")%>">
								</a>
								<div class="media-body">
									<h4 class="media-heading"><a href="https://www.youtube.com/watch?v=<%= list.get(i).get("id")%>" target="_blank"><%= list.get(i).get("titulo")%></a></h4>
									<p class="media-author"><a href="https://www.youtube.com/user/<%= list.get(i).get("user")%>" target="_blank"><%= list.get(i).get("autor")%></a></p>
									<div class="descripcion">
										<p><%= list.get(i).get("descripcion")%></p>
									</div>
								</div>
							</div>
						</div>
					</div>
									
					
					
					<%
							}
						}else{
					%>
					
					<div class="row">
						<div class="col-sm-8 col-sm-offset-2 text-center">
							<h2>No se han encontrado resultados</h2>
						</div>
					</div>
					
					<%		
						}
					%>
					
					<%	
						if(request.getAttribute("MaxPages") != null){
							int paginas = (int) request.getAttribute("MaxPages");
							if(paginas > 0){
					%>
					<div class="row">
						<div class="col-sm-12">
				
							<!-- Se puede mirar como hacer lo de que maximo tenga 5 paginas y la del centro sea la actual -->
							<div class="text-center paginas">
								<ul class="pagination">
									<%
										for(int i=1; i<=paginas;i++){			
									%>
									<li <% if(i == (int) request.getAttribute("ActivePage")){ %> class="active" <% } %>>
										<% 
											if(request.getAttribute("RandomSort") != null){ 
										%>
										<a href="Inicio.input?RandomSort=<%= request.getAttribute("RandomSort")%>&Page=<%= i %>"><%= i %></a>
										<%
											}else{
										%>
										<a href="Query.input?Busqueda=<%= request.getAttribute("Busqueda")%>&Page=<%= i %>"><%= i %></a>
										<%
											}
										%>
									</li>
									<%
										}				
									%>
								</ul>
							</div>
						</div>
					</div>
					<%
							}
						}
					%>
				</div>
			</div>
		</div>
		<!-- CUERPO -->
		
		<!-- FOOTER -->
		<footer class="footer">
        	<p>&copy; Copyright 2016. Carlos Contreras Sanz. All Rights Reserved.</p>
		</footer>
		<!-- FOOTER -->
		
	</body>
</html>

