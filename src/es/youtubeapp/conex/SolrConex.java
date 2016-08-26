package es.youtubeapp.conex;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * @author Carlos Contreras Sanz
 * @tutorials http://www.programcreek.com/java-api-examples/index.php?api=org.apache.solr.client.solrj.SolrQuery
 */

public class SolrConex {
	
	String urlString = "http://localhost:8983/solr/video";
	private SolrClient solr = new HttpSolrClient.Builder(urlString).build();
	
	public SolrClient getSolr() {
		return solr;
	}
	public void setSolr(SolrClient solr) {
		this.solr = solr;
	}
	
	

}
