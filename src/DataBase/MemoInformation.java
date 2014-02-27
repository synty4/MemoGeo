package DataBase;

public class MemoInformation {
	private int id; 
	private String title;  
	private String description; 
	private String address; 
	
	
	public MemoInformation(){
		
	}

	public MemoInformation(int id, String title, String description, String address){
		this.address= address;
		this.id= id;
		this.description=description;
		this.title=title;
	}
	public MemoInformation(String title, String description, String address){
		this.title=title;
		this.description=description;
		this.address= address;
	}
	
	public int  getId(){
		return id;
	}
	public void setId(int id){
		this.id= id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "MemoInformation [id=" + id + ", title=" + title
				+ ", description=" + description + ", address=" + address + "]";
	}
	
	
}


