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
		CSVtoDataObject cdo = new CSVtoDataObject();
		CSVtoDataObject ind=new CSVtoDataObject();
		ODs od=new ODs();
		try{
			od.storeOD("od.txt");
			cdo.readCSVData("test.csv");
			ind.readCSVData("incrementalData.csv");
		}catch(Exception e) {
			System.out.println("read fail!");
		}
		
		/*将读入的od输出*/
		System.out.println("The original od is:");
		od.print();
		
		ArrayList<DataStruct> objectList = cdo.datatoObject();
		ArrayList<DataStruct> iObjectList=ind.datatoObject();
		
		
		if(debug) {
			DataStruct.printAttrName();
			for(DataStruct obj:objectList) {
				obj.printSingleData();
			}
			System.out.println("增量数据");
			for(DataStruct obj:iObjectList) {
				obj.printSingleData();
			}
		}
		
		//TODO::对多条增量数据进行测试
		
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
			
			ArrayList<Integer> preList=new ArrayList<Integer>(),nextList=new ArrayList<Integer>(),
					curList=new ArrayList<Integer>(),increList=new ArrayList<Integer>();
			
			
			//InstanceKey key=new InstanceKey(nowOd.getLHS(),objectList.get(increList.get(0)));
			InstanceKey key=new InstanceKey(nowOd.getLHS(),iObjectList.get(0));
			curList=tree.get(key);
			
			Entry<InstanceKey,ArrayList<Integer>> pre=tree.getPre(key,curList.get(0));
			preList=pre==null?new ArrayList<Integer>():pre.getValue();
			
			Entry<InstanceKey,ArrayList<Integer>> next=tree.getNext(key,curList.get(0));
			nextList=next==null?new ArrayList<Integer>():next.getValue();

			
			//将增量的数据放到原始数据集的最后一行
			objectList.add(iObjectList.get(0));
			increList.add(objectList.size()-1);
			
			
			//curList.remove(curList.size()-1);
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
