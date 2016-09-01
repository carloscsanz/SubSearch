<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="es">
	<head>
		<title><%= request.getAttribute("Categoria")%> - SubSearch</title>
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
								<li><a href="Categoria.input?Category=4">#Technology</a></li>
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
				<div class="jumbotron banner">
					<h3><%= request.getAttribute("Categoria")%></h3>
				</div>
			</div>
			
			<div class="row">
				<div class="col-sm-8 col-sm-offset-2">
					<%
						@SuppressWarnings("unchecked")
						ArrayList<Map<String, String>> list = (ArrayList<Map<String, String>>) request.getAttribute("resultado");
						if(list.size() > 0){
					%>
				
					<div class="media">
						<a class="media-left" href="https://www.youtube.com/watch?v=<%= list.get(0).get("id")%>" target="_blank">
							<img class="media-object" src="http://img.youtube.com/vi/<%= list.get(0).get("id")%>/mqdefault.jpg" alt="<%= list.get(0).get("titulo")%>">
						</a>
						<div class="media-body">
							<h4 class="media-heading"><a href="https://www.youtube.com/watch?v=<%= list.get(0).get("id")%>" target="_blank"><%= list.get(0).get("titulo")%></a></h4>
							<p class="media-author"><a href="https://www.youtube.com/user/<%= list.get(0).get("user")%>" target="_blank"><%= list.get(0).get("autor")%></a></p>
							<div class="descripcion">
								<p><%= list.get(0).get("descripcion")%></p>
							</div>
						</div>
					</div>
					
					<% if(list.size() > 1){ %>
					<div class="row categoria">
						<%
								for(int i=1; i<list.size() && i<4; i++){
						%>
						
						<div class="col-sm-4">
							<a class="media" href="https://www.youtube.com/watch?v=<%= list.get(i).get("id")%>" target="_blank">
								<img class="media-object" src="http://img.youtube.com/vi/<%= list.get(i).get("id")%>/mqdefault.jpg" alt="<%= list.get(i).get("titulo")%>">
							</a>
							<h4 class="media-heading"><a href="https://www.youtube.com/watch?v=<%= list.get(i).get("id")%>" target="_blank"><%= list.get(i).get("titulo")%></a></h4>
							<p class="media-author"><a href="https://www.youtube.com/user/<%= list.get(i).get("user")%>" target="_blank"><%= list.get(i).get("autor")%></a></p>
						</div>
						
						<%
								}
							}
						%>
					</div>
					<% } %>
					
					<% if(list.size() > 4){ %>
					<div class="row categoria">
						<%
							if(list.size() > 0){
								for(int i=4; i<list.size() && i<7; i++){
						%>
						
						<div class="col-sm-4">
							<a class="media" href="https://www.youtube.com/watch?v=<%= list.get(i).get("id")%>" target="_blank">
								<img class="media-object" src="http://img.youtube.com/vi/<%= list.get(i).get("id")%>/mqdefault.jpg" alt="<%= list.get(i).get("titulo")%>">
							</a>
							<h4 class="media-heading"><a href="https://www.youtube.com/watch?v=<%= list.get(i).get("id")%>" target="_blank"><%= list.get(i).get("titulo")%></a></h4>
							<p class="media-author"><a href="https://www.youtube.com/user/<%= list.get(i).get("user")%>" target="_blank"><%= list.get(i).get("autor")%></a></p>
						</div>
						
						<%
								}
							}
						%>
					</div>
					<% } %>
					
					<% if(list.size() > 7){ %>
					<div class="row categoria">
						<%
							if(list.size() > 0){
								for(int i=7; i<list.size() && i<10; i++){
						%>
						
						<div class="col-sm-4">
							<a class="media" href="https://www.youtube.com/watch?v=<%= list.get(i).get("id")%>" target="_blank">
								<img class="media-object" src="http://img.youtube.com/vi/<%= list.get(i).get("id")%>/mqdefault.jpg" alt="<%= list.get(i).get("titulo")%>">
							</a>
							<h4 class="media-heading"><a href="https://www.youtube.com/watch?v=<%= list.get(i).get("id")%>" target="_blank"><%= list.get(i).get("titulo")%></a></h4>
							<p class="media-author"><a href="https://www.youtube.com/user/<%= list.get(i).get("user")%>" target="_blank"><%= list.get(i).get("autor")%></a></p>
						</div>
						
						<%
								}
							}
						%>
					</div>
					<% } %>
					
				</div>
			
			
			
				<%	
					if(request.getAttribute("MaxPages") != null && request.getAttribute("Category") != null && request.getAttribute("RandomSort") != null && request.getAttribute("ActivePage") != null){
						int paginas = (int) request.getAttribute("MaxPages");
						if(paginas > 0){
				%>
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2">
					
						<!-- Mirar lo del li active del pagination para utilizarlo en el pager -->
						<!-- Se puede mirar como hacer lo de que maximo tenga 5 paginas y la del centro sea la actual -->
						<div class="text-center paginas">
							<ul class="pagination">
								<%
									for(int i=1; i<=paginas;i++){			
								%>
								<li <% if(i == (int) request.getAttribute("ActivePage")){ %> class="active" <% } %>>
									<a href="Categoria.input?Category=<%= request.getAttribute("Category")%>&RandomSort=<%= request.getAttribute("RandomSort")%>&Page=<%= i %>"><%= i %></a>
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
		<!-- CUERPO -->
		
		<!-- FOOTER -->
		<footer class="footer">
        	<p>&copy; Copyright 2016. Carlos Contreras Sanz. All Rights Reserved.</p>
		</footer>
		<!-- FOOTER -->
		
	</body>
</html>

