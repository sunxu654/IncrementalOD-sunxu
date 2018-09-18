package Data;

import java.util.ArrayList;
import java.util.Iterator;


public class CSVtoDataObject {
	public String tempString = new String();
	public ArrayList tempList = new ArrayList<>();
	public ArrayList<ArrayList> tempListTwoDem = new ArrayList<>() ;
	public  int listCount = 0;
	
	public  void readCSVData() throws Exception {

		CSVFileUtil cfu = new CSVFileUtil("e:/test.csv");
		while((tempString=cfu.readLine())!=null) {
			tempList = cfu.fromCSVLinetoArray(tempString);
			tempListTwoDem.add(tempList);
			
			listCount++;
		}
		
	}
	public  ArrayList<VoterObject> datatoObject() {
		ArrayList<VoterObject> objectList = new ArrayList<>();
		Iterator iter = tempListTwoDem.iterator();
//		ArrayList<String> typeContrroller = (ArrayList<String>) iter.next();
		ArrayList<String> nameContrroller = (ArrayList<String>) iter.next();
		ArrayList<String> dataList =new ArrayList<String>();
		String typePoint = null;
		String temp = null;
		if(tempListTwoDem.size()<2) {
			System.out.println("tuple is less than 1");
			return null;
		}
//		for(int tempCount = 2;tempCount<listCount;tempCount++) {
		for(;iter.hasNext();) {
			dataList = (ArrayList<String> )iter.next();
			
//			String[] array = (String[])dataList.toArray(new String[nameContrroller.size()]); 
			String[] array = (String[])dataList.toArray(new String[19]); 
			
			VoterObject tempObject = new VoterObject();
			
			tempObject.setVoter_id(Integer.parseInt(array[0]));
			tempObject.setVoter_reg_num(array[1]);
			tempObject.setName_prefix(array[2]);
			tempObject.setFirst_name(array[3]);
			tempObject.setMiddle_name(array[4]);
			tempObject.setLast_name(array[5]);
			tempObject.setName_suffix(array[6]);
			tempObject.setAge(Integer.parseInt(array[7]));
			tempObject.setGender(array[8]);
			tempObject.setRace(array[9]);
			tempObject.setEthnic(array[10]);
			tempObject.setStreet_address(array[11]);
			tempObject.setCity(array[12]);
			tempObject.setState(array[13]);
			tempObject.setZip_code(Integer.parseInt(array[14]));
			tempObject.setFull_phone_num(array[15]);
			tempObject.setBirth_place(array[16]);
			tempObject.setRegister_date(array[17]);
			tempObject.setDownload_month(array[18]);			
				
			objectList.add(tempObject);
		}
		return objectList;
	}
	public  void readData() {
		CSVtoDataObject cdo = new CSVtoDataObject();
		
		try {
			cdo.readCSVData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<VoterObject> objectList = cdo.datatoObject();
		for(int i=0;i<objectList.size();i++) {
			VoterObject temp=objectList.get(i);
			System.out.println(
						temp.getVoter_id()
+"/"+					temp.getVoter_reg_num()
+"/"+					temp.getName_prefix()
+"/"+					temp.getFirst_name()
+"/"+					temp.getMiddle_name()
+"/"+					temp.getLast_name()
+"/"+					temp.getName_suffix()
+"/"+					temp.getAge()
+"/"+					temp.getGender()
+"/"+					temp.getRace()
+"/"+					temp.getEthnic()
+"/"+					temp.getStreet_address()
+"/"+					temp.getCity()
+"/"+					temp.getState()
+"/"+					temp.getZip_code()
+"/"+					temp.getFull_phone_num()
+"/"+					temp.getBirth_place()
+"/"+					temp.getRegister_date()
+"/"+					temp.getDownload_month()	);						
		}
	}
	
	
	
	
	
	
}
