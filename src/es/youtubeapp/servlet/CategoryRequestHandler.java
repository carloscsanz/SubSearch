package es.youtubeapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.youtubeapp.conex.SolrQueries;

public class CategoryRequestHandler implements RequestHandler {
	
	/**
	 * @global
	 * 		@param	categorias	Array donde aparecen cada una de las categorías existentes
	 * 
	 * @input
	 * 		@param	Category	Número del indice del array categorias que permite conocer la categoria que quiere visualizar el usuario.
	 * 		@param	Page		Pagina sobre la que se obtendran los resultados deseados (Si es null le asignamos 1).
	 * 		@param	RandomSort	Número aleatorio generado la primera vez que se realiza una consulta sobre una misma categoria. Utilizado para la paginacion de los resultados.
	 * 
	 * @output
	 * 		@param 	ActivePage	Pagina que se mostrara al usuario, ya sea por peticion suya o al ser la primera o unica existente.
	 * 		@param 	MaxPages	Número maximo de paginas que tiene los resultados de la consulta.
	 * 		@param	Category	Número del indice del array categorias que permite conocer la categoria que quiere visualizar el usuario, utilizado para la paginación.
	 *		@param 	Categoria 	Nombre de la categoría que se desea visualizar. Utilizado en el titulo de la categoría.
	 *		@param	RandomSort	Salida del número aleatorio generado la primera vez que se consulta una categoria.
	 *		@param	resultado	ArrayList con los 10 resultados (maximo) que se mostraran por pantalla al usuario.
	 **/

	public static String [] categorias = new String [] {"#Education", "#Entertainment", "#Comedy"};
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoria = request.getParameter("Category");
		String pagina = request.getParameter("Page");
		String sortRandom = request.getParameter("RandomSort");	
		
		int randomInt;
		if(sortRandom == null){
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(100);

		}else{
			randomInt = Integer.parseInt(sortRandom);
		}
		
		SolrQueries query = new SolrQueries();
		ArrayList<Map<String, String>> resultado;

		if(pagina == null){
			resultado = query.DoRandomQuery("categoria:"+categorias[Integer.parseInt(categoria)], 10, 0, randomInt);
			pagina = "1";
		}else{
			resultado = query.DoRandomQuery("categoria:"+categorias[Integer.parseInt(categoria)], 10, (Integer.parseInt(pagina)-1)*10, randomInt);
		}
		int paginas = (int) Math.ceil(query.getNumeroDocumentos()/10.0);
		
		request.setAttribute("resultado", resultado);
		request.setAttribute("Categoria", categorias[Integer.parseInt(categoria)]);
		request.setAttribute("MaxPages", paginas);
		request.setAttribute("ActivePage", Integer.parseInt(pagina));
		request.setAttribute("RandomSort", ""+randomInt);
		request.setAttribute("Category", categoria);
		
		return "Categoria.jsp";
	}

}