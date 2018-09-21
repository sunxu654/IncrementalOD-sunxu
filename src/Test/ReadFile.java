package Test;

import java.util.ArrayList;

import Data.CSVtoDataObject2;
import Data.Cmp;
import Data.DataStruct;
import OD.ODs;

public class ReadFile {
	public static void main(String[] args) {
		CSVtoDataObject2 cdo = new CSVtoDataObject2();
		
		try{
			cdo.readCSVData("C:\\Users\\zhulin\\Desktop\\test.csv");
		}catch(Exception e) {
			System.out.println("read fail!");
		}
		
		ArrayList<DataStruct> dataList=cdo.datatoObject();
		DataStruct.printAttrName();
		for(DataStruct ds:dataList) {
			ds.printSingleData();
		}
	
	}
}
