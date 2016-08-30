package es.youtubeapp.conex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

public class SolrQueries{
	
	private static String [] campos = new String [] {"id","user", "titulo", "autor", "fecha_publicacion", "categoria", "descripcion"};
	private double numeroDocumentos = 0.0;
	
	public SolrQueries(){}
	
	/**
	 * @description	Metodo que se encarga de realizar la consulta que recibe por parametros y devolver el resultado obtenido.
	 * @input
	 * 		@param	query	Objeto SolrQuery que contiene la consulta a realizar.
	 * 
	 * @output
	 * 		@param	resultado	ArrayList que contiene los datos de cada uno de los documentos que encajan con la consulta.
	 * 		@param	numeroDocumentos	double que indica el numero de documentos que encajan con la consulta realizada.
	 * */
	public ArrayList<Map<String, String>> DoSolrQuery(SolrQuery query) throws IOException{
		SolrConex conection = new SolrConex();
		
		try {
			
			QueryResponse respuesta = conection.getSolr().query(query);
			SolrDocumentList list = respuesta.getResults();
			
			this.setNumeroDocumentos((double) list.getNumFound());
			
			ArrayList<Map<String, String>> resultado = new ArrayList<Map<String, String>>();
			for(int i=0; i < list.size(); i++){
				Map<String, String> it = new HashMap<String, String>();
				for(int ii=0; ii <campos.length; ii++){
					it.put(campos[ii], list.get(i).getFieldValue(campos[ii]).toString());
				}
				resultado.add(it);
			}
			return resultado;
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * @description	Metodo que consiste en crear el objeto SolrQuery y pasarselo al metodo DoSolrQuery para que lo consulte y recibir los resultados que encajan con la consulta.
	 * @input
	 * 		@param	consulta	String que contenga las palabras que se desean buscar en todos los campos.
	 * 		@param	maxResultados	int que indica el numero de resultados maximo que se desean recuperar.
	 * 		@param	primerResultado	int que indica el numero del primer resultado a recuperar, esto es utilizado para la paginacion de resultados.
	 * 
	 * @output
	 * 		@param	resultado	ArrayList que contiene los datos de cada uno de los documentos que encajan con la consulta.
	 * 		@param	numeroDocumentos	double que indica el numero de documentos que encajan con la consulta realizada.
	 * @throws IOException 
	 * */
	public ArrayList<Map<String, String>> DoSimpleQuery(String consulta, int maxResultados, int primerResultado) throws IOException{
		
		SolrQuery query = new SolrQuery();
		query.setQuery(consulta);
		query.setRows(maxResultados);
		query.setStart(primerResultado);
		
		return DoSolrQuery(query);
	}

	/**
	 * @description	Metodo que consiste en crear el objeto SolrQuery y pasarselo al metodo DoSolrQuery para que lo consulte y recibir los resultados que encajan con la consulta.
	 * @input
	 * 		@param	consulta	String que contenga las palabras que se desean buscar en todos los campos.
	 * 		@param	maxResultados	int que indica el numero de resultados maximo que se desean recuperar.
	 * 		@param	primerResultado	int que indica el numero del primer resultado a recuperar, esto es utilizado para la paginacion de resultados.
	 * 		@param	seedAleatoria	int que permite realizar busquedas aleatorias de documentos, para no mostrar siempre los documentos que fueron añadidos primero 
	 * 
	 * @output
	 * 		@param	resultado	ArrayList que contiene los datos de cada uno de los documentos que encajan con la consulta.
	 * 		@param	numeroDocumentos	double que indica el numero de documentos que encajan con la consulta realizada.
	 * @throws IOException 
	 * */
	public ArrayList<Map<String, String>> DoRandomQuery(String consulta, int maxResultados, int primerResultado, int seedAleatoria) throws IOException{
		
		SortClause randomSort = new SortClause("random_"+seedAleatoria, "desc");
		
		SolrQuery query = new SolrQuery();
		query.setQuery(consulta);
		query.setSort(randomSort);
		query.setRows(maxResultados);
		query.setStart(primerResultado);
		
		return DoSolrQuery(query);
	}

	
	
	
	
	
	
	
	
	public ArrayList<Map<String, String>> DoAdvancedQuery(String allWords, String anyWord, String notAnyWord, String Category, String author, String dateSince, String dateUntil, int maxResultados, int primerResultado) throws IOException{
		
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		
		/*	CAMPO CATEGORIA	*/
		
		
		/*	CAMPO DATE	*/
		if(dateSince != null){
			if(dateUntil != null){
				String fq = "fecha_publicacion:[" + dateSince + " TO " + dateUntil + "]";
				query.addFilterQuery(fq);
				
			}else{
				String fq = "fecha_publicacion:[" + dateSince + " TO *]";
				query.addFilterQuery(fq);

			}
		}else if(dateUntil != null){
			String fq = "fecha_publicacion:[* TO " + dateUntil + "]";
			query.addFilterQuery(fq);
		}
		
		/*	MIL Y UN IF'S PARA CREAR LA QUERY	*/
		
		query.setRows(maxResultados);
		query.setStart(primerResultado);
		return DoSolrQuery(query);
	}
	
	
	
	
	
	
	
	
	
	public double getNumeroDocumentos() {
		return numeroDocumentos;
	}

	public void setNumeroDocumentos(double numeroDocumentos) {
		this.numeroDocumentos = numeroDocumentos;
	}
	
}