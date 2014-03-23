package DataBase;

public class MemoInformation {

	     
	    //private variables
	    private int id;
	    private String title;
	    private String description;
	    private String address;

		
		public MemoInformation(int id, String title, String description,String address) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			//this.address = address;
		}
		
		public MemoInformation(String title, String description,String address) {
			super();
			this.title = title;
			this.description = description;
			//this.address = address;
		}
		public MemoInformation(String title, String description) {
			super();
			this.title = title;
			this.description = description;
			//this.address = address;
		}
		public MemoInformation() {
			// TODO Auto-generated constructor stub
		}
		// getter and setter for ID
	    public int getId() {
	    	return id;
	    }
	    public void setId(int id) {
	    	this.id = id;
	    }
	    
	    // getter and setter for Title
	    public String getTitle() {
	    	return title;
	    }
	    public void setTitle(String title) {
	    	this.title = title;
	    }
	    // getter and setter for Description
	    public String getDescription() {
	    	return description;
	    }
	    public void setDescription(String description) {
	    	this.description = description;
	    }
	    // getter and setter for Address
	    public String getAddress() {
	    	return address;
	    }
	    public void setAddress(String address) {
	    	this.address = address;
	    }
	    
		@Override
		public String toString() {
			//return "MemoInformation [id=" + id + ", title=" + title	+ ", description=" + description + ", address=" + address + "]";
			return id + ". " + title ;
		}  
	    
	
}


