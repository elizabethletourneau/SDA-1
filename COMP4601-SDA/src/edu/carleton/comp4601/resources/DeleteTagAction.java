package edu.carleton.comp4601.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import edu.carleton.comp4601.dao.DocumentCollection;

public class DeleteTagAction {
	//Default stuff we assume we need...
		@Context
		UriInfo uriInfo;
		@Context
		Request request;
		
		String rawTags;
		List<String> organizedTags;
		String action;
		DocumentCollection documents;
		
	//Constructor
	public DeleteTagAction(UriInfo uriInfo, Request request) {
		this.uriInfo = uriInfo;
		this.request = request;
		documents = DocumentCollection.getInstance();
	}
	
	//Cleans tags into list of tags
	@Path("{tags}")
	@GET
	public void testSearch(@PathParam("tags") String rawtags){
		System.out.println("Hello Flag");
		documents.deleteByTag(rawtags);
		System.out.println("deleteSearch Success = " + rawtags);
	}
	
	
	
	

}