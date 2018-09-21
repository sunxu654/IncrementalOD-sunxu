package Test;
import java.util.Map.Entry;

import BplusTree.BplusTree;
import Data.*;
import OD.*;

import java.util.ArrayList;


public class Test {
	static public boolean debug=true;
	
	public static void main(String[] args) {
		
		final int order = 10;
		CSVtoDataObject2 cdo = new CSVtoDataObject2();
		//CSVtoDataObject increData=new CSVtoDataObject();
		ODs od=new ODs();
		try{
			od.storeOD("od.txt");
			cdo.readCSVData("test.csv");
			//increData.readCSVData();
		}catch(Exception e) {
			System.out.println("read fail!");
		}
		
		/*将读入的od输出*/
		System.out.println("The original od is:");
		od.print();
		
		ArrayList<DataStruct> objectList = cdo.datatoObject();
		
		DataStruct.printAttrName();
		for(DataStruct obj:objectList) {
			obj.printSingleData();
		}
		
		
		//先拿第7条数据来当做是增量数据
		DataStruct increData=objectList.get(6);
		
		
		
		//存储所有原有的od
		ArrayList<OrderDependency> originalODList=new ArrayList<OrderDependency>();
		for(OrderDependency o:od.ods) {
			originalODList.add(o);
		}
		
		
		//对每一条od进行验证
		for(OrderDependency nowOd:originalODList) {
			
			//TODO 假设我通过索引查到了前后数据，拿到了前后数据的tupleID，叫tName
			int preTupleId=0,nextTupleId=4,curTupleId=1;//cur是有三个,1,2,3
			
			//ArrayList<Integer> preList=
		
			DataStruct preData=objectList.get(preTupleId);
			DataStruct nextData=objectList.get(nextTupleId);
			DataStruct curData=objectList.get(curTupleId);
			
			Detect d=new Detect(preData,nextData,curData,increData);
			String detectRes=d.detectSingleOD(nowOd);
			
			System.out.println(detectRes);
			
			if(detectRes.equals("valid")==false) {
				od.ods.remove(nowOd);
				//扩展od
				ArrayList<Integer> preList=new ArrayList<Integer>(),nextList=new ArrayList<Integer>(),
						curList=new ArrayList<Integer>(),increList=new ArrayList<Integer>();
				preList.add(0);
				nextList.add(4);
				curList.add(1);
				curList.add(2);
				curList.add(3);
				increList.add(6);
				
				Extend et=new Extend(objectList,preList ,nextList,curList,increList);
				ArrayList<OrderDependency> newOdList=new ArrayList<OrderDependency>();
				newOdList=et.extend(nowOd,detectRes);
			
				if(!newOdList.isEmpty()) {
					for(OrderDependency no:newOdList) {
						no.printOD();
						od.ods.add(no);
					}
				}
			}
		}
		
		System.out.println("\n\n\nThe latest od is:");
		od.print();
	}
}
