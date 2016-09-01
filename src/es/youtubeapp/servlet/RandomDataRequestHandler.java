package es.youtubeapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.youtubeapp.conex.SolrQueries;

/**
 * Esta Clase se encarga de recuperar documentos de la coleccion de forma aleatoria para que estos se muestren en la
 * pagina principal. Se ha creado una clase para esto ya que necesita que se generen numeros alatorios para poder 
 * obtener la aleatoriedad que se desea conseguir. La busqueda que se realiza sobre la coleccion es la del valor *:*
 * que en Solr vendria a ser un SELECT * FROM ALL.
 * @author Carlos Contreras Sanz
 *
 */

public class RandomDataRequestHandler implements RequestHandler {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			resultado = query.DoRandomQuery("*:*", 10, 0, randomInt);
			pagina = "1";
		}else{
			resultado = query.DoRandomQuery("*:*", 10, (Integer.parseInt(pagina)-1)*10, randomInt);
		}
		int paginas = (int) Math.ceil(query.getNumeroDocumentos()/10.0);
		
		request.setAttribute("resultado", resultado);
		request.setAttribute("MaxPages", paginas);
		request.setAttribute("ActivePage", Integer.parseInt(pagina));
		request.setAttribute("RandomSort", ""+randomInt);
				
		return "Inicio.jsp";
	}

}
