package es.youtubeapp.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.common.SolrInputDocument;

/**
 * Esta Clase se encarga de recuperar los valores introducidos en el formulario para añadir nuevos documentos al indice, trata esos valores (limpiando los datos, normalizandolos,...) y añadirlos al indice.
 * Además de crear un documento .xml para poder utilizarlo en futuras versiones sin tener que volver a completar el formulario.
 * @author Carlos Contreras Sanz
 *
 */

public class AddDocumentRequestHandler implements RequestHandler {
	
	public static String [] campos = new String [] {"id", "titulo", "autor", "fecha_publicacion", "categoria", "descripcion", "subtitulos"};

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//	CAMPOS DEL DOCUMENTO A INDEXAR:
		//		- ID (ID del video de YouTube)
		//		- TITULO
		//		- AUTOR (Dueño del canal que ha subido el video)
		//		- FECHA PUBLICACION
		//		- CATEGORIA (Etiquetas de categoria del video encabvezadas por # y separadas por comas)
		//		- DESCRIPCION
		//		- CONTENIDO (Subtitulos sin marcas de tiempo del video)
		
		//	Mirar cosas del Prepare Statement
		
		String id = request.getParameter(campos[0]);
		String titulo = request.getParameter(campos[1]);
		String autor = request.getParameter(campos[2]);
		String fecha = request.getParameter(campos[3]);
		String categoria = request.getParameter(campos[4]);
		String descripcion = request.getParameter(campos[5]);
		String subtitulos = request.getParameter(campos[6]);

		subtitulos = clearSubtitlesText(subtitulos);
		
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", id);
		document.addField("titulo", titulo);
		document.addField("autor", autor);
		document.addField("fecha_publicacion", fecha);
		document.addField("categoria", categoria);
		document.addField("descripcion", descripcion);
		document.addField("contenido", subtitulos);

		createDocumentXML(id, titulo,  autor,  fecha,  categoria,  descripcion,  subtitulos);
		
//		SolrConex conection = new SolrConex();
//		
//		try {
//			UpdateResponse respuesta = conection.solr.add(document);
//			System.out.println("La respuesta es: "+respuesta);
//			conection.solr.commit();
//
//		} catch (SolrServerException e) {
//			e.printStackTrace();
//		}
		
		return "FormAddDocument.jsp";
	}
			
			
	/**
	 * Método que se encarga de crear un fichero .xml con los datos del documento a añadir a la coleccion para tenerlos guardados y poder recuperar la coleccion cuando se desee.
	 * @param id
	 * @param titulo
	 * @param autor
	 * @param fecha
	 * @param categoria
	 * @param descripcion
	 * @param subtitulos
	 * @throws IOException
	 */
	private void createDocumentXML(String id, String titulo, String autor, String fecha, String categoria, String descripcion, String subtitulos) throws IOException{
		
		File archivo = new File("/Users/Carlos/Desktop/Documentos Videos/" + id + ".xml");
		BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
		
		bw.write("<add>\n\t<doc>\n");
		bw.write("\t\t<field name=\"id\">"+ id + "</field>\n");
		bw.write("\t\t<field name=\"titulo\">"+ titulo + "</field>\n");
		bw.write("\t\t<field name=\"autor\">"+ autor + "</field>\n");
		bw.write("\t\t<field name=\"fecha_publicacion\">"+ fecha + "</field>\n");
		bw.write("\t\t<field name=\"categoria\">"+ categoria + "</field>\n");
		bw.write("\t\t<field name=\"descripcion\">"+ descripcion + "</field>\n");
		bw.write("\t\t<field name=\"contenido\">"+ subtitulos + "\n\t\t</field>\n");
		bw.write("\t</doc>\n</add>");


		bw.close();

		
	}

	/**
	 * Método que se encarga de limpiar el texto de los subtitulos recibido mediante el formulario de las marcas de tiempo que no aportan valor a la hora de indexar el documento
	 * @param subtitulos
	 * @return
	 */
	private String clearSubtitlesText(String subtitulos){
		
		String[] lines = subtitulos.split("\r\n|\r|\n");
		String subs = "";
		
		for(int i=0; i<lines.length; i++){
			if(!stringIsNumber(lines[i]) && !lines[i].contains("-->") && !lines[i].equals("") && !lines[i].contains("[")){
				subs = subs + ("\n\t\t\t" + lines[i]);
			}
//			
//			
//			
//			int ii=2;
//			for( ; (ii+i)<lines.length; ii++){
//				String text = "";
//				if(!(text = lines[ii+i]).equals("")){
//					subs = subs + ("\n\t\t\t" + text);
//				}else{
//					if((ii+i+1)<lines.length){
//						if((text = lines[ii+i+1]).equals("")){
//							ii++;
//							break;
//						}
//					}else{
//						break;
//					}
//				}
//			}
//			i = i + ii;
		}
		
		return subs;
		
	}
	
	
	private boolean stringIsNumber(String cadena){
		try{
			Integer.parseInt(cadena);
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
