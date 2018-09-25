package Test;
import java.util.Map.Entry;

import BplusTree.BplusTree;
import BplusTree.InstanceKey;
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
		//DataStruct increData=objectList.get(6);
		
		
		
		//存储所有原有的od
		ArrayList<OrderDependency> originalODList=new ArrayList<OrderDependency>();
		for(OrderDependency o:od.ods) {
			originalODList.add(o);
		}
		
		
		//对每一条od进行验证
		for(OrderDependency nowOd:originalODList) {
			
			//build B+ tree
			BplusTree<InstanceKey, ArrayList<Integer>> tree = new BplusTree<InstanceKey, ArrayList<Integer>>(order);		
			
			for (int i=0;i<objectList.size();i++) {
				DataStruct temp=objectList.get(i);
				tree.insertOrUpdate(new InstanceKey(nowOd.getLHS(),temp),i);
			}
			
			
			
			//TODO 假设我通过索引查到了前后数据，拿到了前后数据的tupleID，叫tName
			//int preTupleId=0,nextTupleId=4,curTupleId=1;//cur是有三个,1,2,3
			
			ArrayList<Integer> preList=new ArrayList<Integer>(),nextList=new ArrayList<Integer>(),
					curList=new ArrayList<Integer>(),increList=new ArrayList<Integer>();
			
			//先拿最后一条数据来当做是增量数据
			increList.add(objectList.size()-1);
			
			InstanceKey key=new InstanceKey(nowOd.getLHS(),objectList.get(increList.get(0)));
			curList=tree.get(key);
			
			Entry<InstanceKey,ArrayList<Integer>> pre=tree.getPre(key,curList.get(0));
			preList=pre==null?new ArrayList<Integer>():pre.getValue();
			
			Entry<InstanceKey,ArrayList<Integer>> next=tree.getNext(key,curList.get(0));
			nextList=next==null?new ArrayList<Integer>():next.getValue();
			
			curList.remove(curList.size()-1);
			if(debug&&!curList.isEmpty())
				for(Integer i:curList) {
					System.out.print(i+" ");
				}
		
			Detect d=new Detect(objectList,preList,nextList,curList,increList);
			String detectRes=d.detectSingleOD(nowOd);
			
			System.out.println(detectRes);
			
			if(detectRes.equals("valid")==false) {
				od.ods.remove(nowOd);
				//扩展od
				
				Extend et=new Extend(objectList,preList ,nextList,curList,increList);
				ArrayList<OrderDependency> newOdList=new ArrayList<OrderDependency>();
				newOdList=et.extend(nowOd,detectRes);
				
				if(!newOdList.isEmpty()) {
					if(debug) {
						System.out.print("modify od: ");
						nowOd.printOD();
					}
					int count=0;
					for(OrderDependency no:newOdList) {
						if(debug) {
							System.out.print(count+". ");
							count++;
							no.printOD();
						}
						od.ods.add(no);
					}
				}
			}
		}
		
		System.out.println("\n\n\nThe latest od is:");
		od.print();
	}
}
