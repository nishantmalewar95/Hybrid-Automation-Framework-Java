package api.models;

public class Post {
	//1.private Variables
	private String title;
	private String body;
	private int userId;
	
	//2.Constructor 
	public Post(String title,String body,int userId) {
		this.title=title;
		this.body=body;
		this.userId=userId;
	}
	
	//3.Getter and Setter
	public String getTitle() {return title; }
	public void setTitle(String title) {this.title=title;}
	
	public String getBody() {return body;}
	public void setBody(String body) {this.body = body;}
	
	public int getUserId() {return userId;}
	public void setUserId(int userId) {this.userId = userId;}
	
		
	}
	


