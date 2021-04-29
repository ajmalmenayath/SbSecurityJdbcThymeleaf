package org.o7planning.sbsecurity.model;

public class AllCountry {
	
	 	private int id;
	    private String iso; 
	    private String name; 
	    private String nicename;
	    private int iso3;
	    private int phonecode;
	    
	    
	    public AllCountry() {
			
		}
	    
	    public AllCountry(int id, String iso, String name, String nicename, int iso3, int phonecode) {
			//super();
			this.id = id;
			this.iso = iso;
			this.name = name;
			this.nicename = nicename;
			this.iso3 = iso3;
			this.phonecode = phonecode;
		}
	    
	    
		public AllCountry(String iso, String name) {
			// TODO Auto-generated constructor stub
			this.iso = iso;
			this.name = name;
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getIso() {
			return iso;
		}
		public void setIso(String iso) {
			this.iso = iso;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNicename() {
			return nicename;
		}
		public void setNicename(String nicename) {
			this.nicename = nicename;
		}
		public int getIso3() {
			return iso3;
		}
		
		public void setIso3(int iso3) {
			this.iso3 = iso3;
		}
		public int getPhonecode() {
			return phonecode;
		}
		public void setPhonecode(int phonecode) {
			this.phonecode = phonecode;
		}
	    
	
	

}
