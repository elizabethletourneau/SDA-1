package edu.carleton.comp4601.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import edu.carleton.comp4601.resources.MyMongoDB;

import java.net.UnknownHostException;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentCollection {
	@XmlElement(name="documents")
	private List<edu.carleton.comp4601.dao.Document> documents;
	
	//Singelton Stuff
	public static void setInstance(DocumentCollection instance) {
		DocumentCollection.instance = instance;
	}
	public static DocumentCollection getInstance() {
		if (instance == null)
			instance = new DocumentCollection();
		return instance;
	}
	private static DocumentCollection instance;
	
	//Database Stuff
	MyMongoDB db;
	
	public DocumentCollection(){
		//Database connection
		db = MyMongoDB.getInstance();
		
		
		
		
		//populateList();
	}
	
	public List<edu.carleton.comp4601.dao.Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<edu.carleton.comp4601.dao.Document> documents) {
		this.documents = documents;
	}
	
	
	public edu.carleton.comp4601.dao.Document find(Integer id_in){
		MongoCursor<Document> cursor = db.getCursor();
		edu.carleton.comp4601.dao.Document searchDocument = null;
		
		while (cursor.hasNext()) {
			Document doc = cursor.next(); //DB Document, not prof Document
			
			Integer id_I = (Integer) doc.get("id");
			System.out.println("HERE IS THE SEARCHED DOCS ID: " + id_I);
			if(id_in == id_I){
				searchDocument = new edu.carleton.comp4601.dao.Document(id_I);
				searchDocument.setLinks((ArrayList<String>) doc.get("links"));
				searchDocument.setName((String) doc.get("name"));
				searchDocument.setTags((ArrayList<String>) doc.get("tags"));
				searchDocument.setScore((double) doc.get("score"));
				searchDocument.setUrl((String) doc.get("url"));
			}
	    }
		return searchDocument;
	}
	
	public void deleteByTag(String tag){
		System.out.println("FLAG 1");
		MongoCursor<Document> cursor = db.getCursor();
		System.out.println("FLAG 2");
		while (cursor.hasNext()) {
			Document doc = cursor.next(); //DB Document, not prof Document
			ArrayList<String> tags = (ArrayList<String>) doc.get("tags");
			System.out.println(tags);
			if(tags.contains(tag)){
				System.out.println("TAG HIT");
				Integer id_I = (Integer) doc.get("id");
				delete(id_I);
			}
	    }
	}
	
	public void add(){
	
		//TEST CODE
		
		edu.carleton.comp4601.dao.Document testDoc = new edu.carleton.comp4601.dao.Document();
		testDoc.setId(1);
		ArrayList<String> links = new ArrayList<String>();
		links.add("Link1");
		links.add("Link2");
		links.add("Link3");
		testDoc.setLinks(links);
		testDoc.setName("Test Name");
		ArrayList<String> tags = new ArrayList<String>();
		tags.add("Tag1");
		tags.add("Tag2");
		tags.add("Tag3");
		testDoc.setTags(tags);
		testDoc.setScore(66.66);
		testDoc.setUrl("www.test.com");
		testDoc.setText("Test Text");

		if (find(testDoc.getId()) == null){
			db.add(testDoc);
		}
		else{
			System.out.println("Failed to Add, this dudes is already in the Database, based off the Doc Id");
		}
	}
	
	public void delete(Integer id){	
		db.delete(id);
	}
	
	public void update(Integer oldInt, edu.carleton.comp4601.dao.Document updateDoc){
		db.delete(oldInt);
		db.add(updateDoc);
	}
	
	public void populateList(){
		documents.clear(); //this function will repopulate the list so we need to delete whats inside to avoid duplication
		
		MongoCursor<Document> cursor = db.getCursor();
			while (cursor.hasNext()) {
				Document doc = cursor.next(); //not Prof made Document class
				
				edu.carleton.comp4601.dao.Document ourDocument = new  edu.carleton.comp4601.dao.Document();
				
				ourDocument.setId((Integer) doc.get("id"));
				ourDocument.setLinks((ArrayList<String>) doc.get("links"));
				ourDocument.setName((String) doc.get("name"));
				ourDocument.setTags((ArrayList<String>) doc.get("tags"));
				ourDocument.setScore((double) doc.get("score"));
				ourDocument.setUrl((String) doc.get("url"));
				
			    documents.add(ourDocument);
		   }
	}
	
}