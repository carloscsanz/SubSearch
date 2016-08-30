package es.youtubeapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.youtubeapp.conex.SolrQueries;

public class AdvancedSearchRequestHandler implements RequestHandler {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pagina = request.getParameter("Page");
		String allWords = request.getParameter("AllWords");
		String anyWord = request.getParameter("AnyWord");
		String notAnyWord = request.getParameter("NotAnyWord");
		String categoria = request.getParameter("Category");
		String author = request.getParameter("Author");
		String dateSince = request.getParameter("DateSince");
		String dateUntil = request.getParameter("DateUntil");
		
		if(allWords.trim() == ""){
			allWords = null;
		}
		
		if(anyWord.trim() == ""){
			anyWord = null;
		}
		
		if(notAnyWord.trim() == ""){
			notAnyWord = null;
		}
		
		if(author.trim() == ""){
			author = null;
		}
		
		if(dateSince == ""){
			dateSince = null;
		}else{
			dateSince = dateSince + "T00:00:00Z";
		}
		
		if(dateUntil == ""){
			dateUntil = null;
		}else{
			dateUntil = dateUntil + "T00:00:00Z";
		}
		
		System.out.println("allWords: " + allWords);
		System.out.println("anyWord: " + anyWord);
		System.out.println("notAnyWord: " + notAnyWord);
		System.out.println("categoria: " + categoria);
		System.out.println("author: " + author);
		System.out.println("dateSince: " + dateSince);
		System.out.println("dateUntil: " + dateUntil);
		
		
		SolrQueries query = new SolrQueries();
		ArrayList<Map<String, String>> resultado;

		if(pagina == null){
			resultado = query.DoAdvancedQuery(allWords, anyWord, notAnyWord, categoria, author, dateSince, dateUntil, 10, 0);
			pagina = "1";
		}else{
			resultado = query.DoAdvancedQuery(allWords, anyWord, notAnyWord, categoria, author, dateSince, dateUntil, 10, (Integer.parseInt(pagina)-1)*10);
		}
		int paginas = (int) Math.ceil(query.getNumeroDocumentos()/10.0);

		request.setAttribute("resultado", resultado);
		request.setAttribute("MaxPages", paginas);
		request.setAttribute("ActivePage", Integer.parseInt(pagina));


		
		return "Inicio.jsp";
	}

}
