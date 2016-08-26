<!DOCTYPE html>
<html lang="es">
<head>
	<title>Añade un nuevo Video a la Colección</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="./script/validateForm.js"></script>
</head>

<body onload="ocultarGlyphicons()">

	<div class="container">
		<h2>Añade un nuevo video a la coleccion, Introduce los datos del video:</h2>
		<form class="form-horizontal" role="form" action="addDocument.input" method="POST" name="CrearDocumento" onsubmit="return validateForm()">
			
			<div id="id" class="form-group">
				<label class="control-label col-sm-2" for="id">ID</label>
				<div class="col-sm-10" name="id">
					<input type="text" class="form-control" name="id" placeholder="Identificador del video asignado por YouTube" autocomplete="off">
        			<span id="id-glyphicon-ok" class="glyphicon glyphicon-ok form-control-feedback"></span>
        			<span id="id-glyphicon-remove" class="glyphicon glyphicon-remove form-control-feedback"></span>
        			<span id="id-helpBlock" class="help-block"></span>
        			
				</div>
			</div>
			
			<div id="titulo" class="form-group">
				<label class="control-label col-sm-2" for="titulo">Titulo</label>
				<div class="col-sm-10" name="titulo">
					<input type="text" class="form-control" name="titulo" placeholder="Titulo del video que tiene en YouTube" autocomplete="off">
					<span id="titulo-glyphicon-ok" class="glyphicon glyphicon-ok form-control-feedback"></span>
        			<span id="titulo-glyphicon-remove" class="glyphicon glyphicon-remove form-control-feedback"></span>
        			<span id="titulo-helpBlock" class="help-block"></span>				
        		</div>
			</div>
			
			<!---->  <div class="form-group">
				<label class="control-label col-sm-2" for="autor">Autor</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="autor" id="autor" placeholder="Canal de YouTube que es el dueño del video" required>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="fecha_publicacion">Fecha de publicación</label>
				<div class="col-sm-10">
					<input type="date" class="form-control" name="fecha_publicacion" id="fecha_publicacion" placeholder="Fecha en la que fue publicado en YouTube" required>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="categoria">Categorias</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="categoria" id="categoria" placeholder="Categorías en las que esta etiquetado el video en YouTube" required>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="descripcion">Descripción</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="descripcion" id="descripcion" placeholder="Descripción del video en YouTube" required>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="subtitulos">Subtitulos</label>
				<div class="col-sm-10">
					<textarea class="form-control" name="subtitulos" id="subtitulos" placeholder="Subtitulos de YouTube del video" required></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Añadir Video al Indice</button>
				</div>
			</div>
			
		</form>
	</div>	

</body>
</html>