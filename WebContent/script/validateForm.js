function validateForm() {
	
	/*var valido = true;
	
    var id = document.forms["CrearDocumento"]["id"].value;
    var clase = document.getElementById("id");
    var er = new RegExp("^[0-9a-zA-Z]{11}$");
    if (!id.match(er)){
        clase.className = "form-group has-error has-feedback";
    	$('#id-glyphicon-remove').show();
    	$('#id-helpBlock').show();
    	$('#id-glyphicon-ok').hide();
    	document.getElementById('id-helpBlock').innerHTML = "El identificador del video debe de contener 11 caracteres (Letras y n&uacute;meros)";
    	valido = false;
    }else{
    	clase.className = "form-group has-success has-feedback";
    	$('#id-glyphicon-ok').show();
    	$('#id-glyphicon-remove').hide();
    	$('#id-helpBlock').hide();
    }*/
    
    /*var titulo = document.forms["CrearDocumento"]["titulo"].value;
    var clase = document.getElementById("titulo");
    var er = new RegExp("^[\w\W]{5,100}$");
    if (!titulo.match(er)){
        clase.className = "form-group has-error has-feedback";
    	$('#titulo-glyphicon-remove').show();
    	$('#titulo-helpBlock').show();
    	$('#titulo-glyphicon-ok').hide();
    	document.getElementById('titulo-helpBlock').innerHTML = "El titulo del video debe de contener almenos 5 caracteres y m&aacute;ximo 100";
    	valido = false;
    }else{
    	clase.className = "form-group has-success has-feedback";
    	$('#titulo-glyphicon-ok').show();
    	$('#titulo-glyphicon-remove').hide();
    	$('#titulo-helpBlock').hide();
    }*/
    
    
    
    
    
    
    
    
    
    
    
    
    return true;
    
}

function ocultarGlyphicons() {
	
	$('#id-glyphicon-ok').hide();
	$('#id-glyphicon-remove').hide();
	$('#id-helpBlock').hide();
	
	$('#titulo-glyphicon-ok').hide();
	$('#titulo-glyphicon-remove').hide();
	$('#titulo-helpBlock').hide();
	
}
