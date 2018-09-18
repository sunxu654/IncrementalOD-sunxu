package Data;

public class AttrRecord {
	public static int countInt=0;
	public static int countString=0;
	String dataType;
	int cInt;
	int cString;
	public AttrRecord(String dataType,int count) {
		if(dataType.equals("string")) {
			cString=countString;
			countString++;
		}else {
			cInt=countInt;
			countInt++;
		}
	}
	
	public String getDataType() {
		return dataType;
	}
	
	public void setDataType(String type) {
		this.dataType=type;
	}
	
	public int getAttrNum() {
		if(dataType.equals("string")) return cString;
		else return cInt;
	}
	
}
