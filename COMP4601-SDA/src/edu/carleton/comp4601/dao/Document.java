package edu.carleton.comp4601.dao;

import java.util.ArrayList;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class Document {
	private Integer id;
	private double score; //Originally was Float, but we dont fuck with that
	private String name;
	private String url;
	JSONObject json; //We added this code aswell
	
	

	private String text;
	private ArrayList<String> tags;
	private ArrayList<String> links;

	public Document() {
		tags = new ArrayList<String>();
		links = new ArrayList<String>();
	}

	public Document(Integer id) {
		this();
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public Document(Map<?, ?> map) {
		this();
		this.id = (Integer) map.get("id");
		this.score = (Float) map.get("score");
		this.name = (String) map.get("name");
		this.text = (String) map.get("text");
		this.url = (String) map.get("url");
		this.tags = (ArrayList<String>) map.get("tags");
		this.links = (ArrayList<String>) map.get("links");
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getId() {
		return id;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getScore() {
		return score;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public ArrayList<String> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<String> links) {
		this.links = links;
	}

	public void addTag(String tag) {
		tags.add(tag);
	}

	public void removeTag(String tag) {
		tags.remove(tag);
	}

	public void addLink(String link) {
		links.add(link);
	}

	public void removeLink(String link) {
		links.remove(link);
	}
	
	public JSONObject jsonify() throws JSONException{
		json = new JSONObject();
		json.put("id", String.valueOf(id));
		json.put("score", String.valueOf(score));
		json.put("url", url);
		json.put("name", name);
		json.put("text", text);
		json.put("tags", String.join(", ", tags));
		json.put("links", String.join(", ", links));
		
		return json;
	}
}