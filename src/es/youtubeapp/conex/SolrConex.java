package es.youtubeapp.conex;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

/**
 * @author Carlos Contreras Sanz
 * @tutorials http://www.programcreek.com/java-api-examples/index.php?api=org.apache.solr.client.solrj.SolrQuery
 */

public class SolrConex {
	
	String core = "http://localhost:8983/solr/SubSearch";
	String coreMT = "http://localhost:8983/solr/SubSearchMT";
	
	SolrClient solr = null;
	
	public SolrConex(boolean coreSelector){
		if(coreSelector){
			solr = new HttpSolrClient.Builder(this.coreMT).build();
		}else{
			solr = new HttpSolrClient.Builder(this.core).build();
		}
	}
	
	public SolrClient getSolr() {
		return solr;
	}
	public void setSolr(SolrClient solr) {
		this.solr = solr;
	}
	
	

}
