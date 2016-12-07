package blkGUIone;


public enum TEMPLE_TYPE {
	PLEASE_SELECT_ONE_TEMPLE("",""),
	ASIA_MINOR("The Temple at Teos in Asia Minor","Teos was one of the twelve cities."),
	HERCULER("temple of herculer","Description");
	
	String name="";
	private String info="";
	
	TEMPLE_TYPE(String n,String i){
		name=n;
		info=i;
	}
	
	public String getName(){
		return name;
	}
	
	public String getInfo(){
		return info;
	}
	
}
