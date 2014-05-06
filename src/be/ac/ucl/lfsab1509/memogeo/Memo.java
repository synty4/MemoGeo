package be.ac.ucl.lfsab1509.memogeo;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;


public class Memo implements Serializable
{
	private int id;
	private String title;
	private String memo;
	private String address;
	private double longitude;
	private double latitude;
	private String time;
	private String date;
	
	// Constructor
	public Memo(String title, String memo, String address, double longitude, double latitude, String time, String date)
	{

		this.title = title;
		this.memo = memo;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.time = time;
		this.date = date;
	}
	
	//Default constructor
	public Memo(){}
	
	// Getters and setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	// To String method.
	@Override
	public String toString() 
	{
		return "Memo [title" + title + "memo=" + memo + ", address=" + address + ", longitude="
				+ longitude + ", latitude=" + latitude + ", time=" + time + ", date=" + date + "]";
	}

	public int describeContents() 
	{
		// Return 0 because the class does not contain a FileDescriptor.
		return 0;
	}

	public void writeToParcel(Parcel destination, int flags)
	{
		destination.writeString(title);
		destination.writeString(memo);
		destination.writeString(address);
		destination.writeDouble(longitude);
		destination.writeDouble(latitude);
		destination.writeString(time);
		destination.writeString(date);
	}	
	
	public static final Parcelable.Creator<Memo> CREATOR = new Parcelable.Creator<Memo>()
	{
		@Override
		public Memo createFromParcel(Parcel source) {
		return new Memo(source);
		}
		
		@Override
		public Memo[] newArray(int size) {
		return new Memo[size];
		}
	};
	
	public Memo(Parcel in)
	{
		title = in.readString();
		memo = in.readString();
		address = in.readString();
		longitude = in.readDouble();
		latitude = in.readDouble();
		time = in.readString();
		date = in.readString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
