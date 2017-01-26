package org.cats.location.domain;

public class Fdp {

	private	Integer FDPID;
	private	String Name;
	private	String NameAm;
	private	String AdminUnitID;
	private	String Latitude;
	private	String  Longitude;
	private	Integer  HubID;
	public Integer getFDPID() {
		return FDPID;
	}
	public void setFDPID(Integer fDPID) {
		FDPID = fDPID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNameAm() {
		return NameAm;
	}
	public void setNameAm(String nameAm) {
		NameAm = nameAm;
	}
	public String getAdminUnitID() {
		return AdminUnitID;
	}
	public void setAdminUnitID(String adminUnitID) {
		AdminUnitID = adminUnitID;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public Integer getHubID() {
		return HubID;
	}
	public void setHubID(Integer hubID) {
		HubID = hubID;
	}
}
