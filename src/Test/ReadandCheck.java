package Test;
import java.util.Map.Entry;

import BplusTree.BplusTree;
import BplusTree.InstanceKey;
import Data.*;
import OD.*;

import java.util.ArrayList;


public class ReadandCheck {
	public static boolean debug=false;
	final private static int order = 10;
	public static CSVtoDataObject cdo = new CSVtoDataObject();
	private static CSVtoDataObject ind=new CSVtoDataObject();
	private static ODs od=new ODs();
	public static ArrayList<DataStruct> objectList=new ArrayList<DataStruct>(),
			iObjectList=new ArrayList<DataStruct>();
	private static ArrayList<OrderDependency> originalODList=new ArrayList<OrderDependency>(),
			incorrectODList=new ArrayList<OrderDependency>(),
			enrichODList=new ArrayList<OrderDependency>();
	
	
public static void main(String[] args) {
		
   	    dataInitial();
		
		if(debug) {
			/*将读入的od输出*/
			System.out.println("The original od is:");
			od.print();
			
			DataStruct.printAttrName();
			for(DataStruct obj:objectList) {
				obj.printSingleData();
			}
			System.out.println("增量数据");
			for(DataStruct obj:iObjectList) {
				obj.printSingleData();
			}
		}
	
		checkAllOD(originalODList);
		
		enrichment();
		checkAllOD(enrichODList);
		
		
		
		System.out.println("\n\n\nThe latest od is:");
		od.print();
		
	}
	
	
	private static void dataInitial() {
		try{
			od.storeOD("od.txt");
			cdo.readCSVData("test.csv");
			ind.readCSVData("incrementalData.csv");
		}catch(Exception e) {
			System.out.println("read fail!");
		}
		
		//存储所有原有的od
		for(OrderDependency o:od.ods) {
			originalODList.add(o);
		}
		
		objectList = cdo.datatoObject();
		iObjectList=ind.datatoObject();
		
	}
	
	private static void checkAllOD(ArrayList<OrderDependency> checkedOD) {
		
		//对每一条od进行验证
		for(OrderDependency nod:checkedOD) {
			
			//build B+ tree
			BplusTree<InstanceKey, ArrayList<Integer>> tree = new BplusTree<InstanceKey, ArrayList<Integer>>(order);		
			
			for (int i=0;i<objectList.size();i++) {
				DataStruct temp=objectList.get(i);
				tree.insertOrUpdate(new InstanceKey(nod.getLHS(),temp),i);
			}
			
			ArrayList<Integer> preList,nextList,curList,increList=new ArrayList<Integer>();
			
			
			InstanceKey key=new InstanceKey(nod.getLHS(),iObjectList.get(0));
			curList=tree.get(key);
			curList=curList==null?new ArrayList<Integer>():curList;
			
			
			//将增量的数据放到原始数据集的最后一行
			objectList.add(iObjectList.get(0));
			
			
			Entry<InstanceKey,ArrayList<Integer>> pre=tree.getPre(key,objectList.size()-1);
			preList=pre==null?new ArrayList<Integer>():pre.getValue();
			
			Entry<InstanceKey,ArrayList<Integer>> next=tree.getNext(key,objectList.size()-1);
			nextList=next==null?new ArrayList<Integer>():next.getValue();
			
			//最后一行是增量数据
			increList.add(objectList.size()-1);
			
			
			
			if(debug&&(!curList.isEmpty())) {
				System.out.print("\n\nThe current data is tuple: ");
				for(Integer i:curList) {
					System.out.print(i+" ");
				}
			}
				
		
			Detect d=new Detect(preList,nextList,curList,increList);
			String detectRes=d.detectSingleOD(nod);
			
			System.out.println(detectRes);
			
			if(detectRes.equals("valid")==false) {
				od.ods.remove(nod);
				incorrectODList.add(nod);
				//扩展od
				Extend et=new Extend(preList ,nextList,curList,increList);
				ArrayList<OrderDependency> newOdList=new ArrayList<OrderDependency>();
				newOdList=et.extend(nod,detectRes);
				
				if(!newOdList.isEmpty()) {
					
					System.out.print("modify od: ");
					nod.printOD();
				
					int count=0;
					for(OrderDependency no:newOdList) {
					
						System.out.print((count++)+". ");
						no.printOD();
						
						od.ods.add(no);
					}
				}
			}
			objectList.remove(objectList.size()-1);
		}
	}
	
	//od:需要被扩展的od，iod：错误的od，it：需要插入iod右边的起始index.最后都放到enrichODList中
	public static void enrichSingleOD(OrderDependency od,OrderDependency iod,int it) {
		OrderDependency tmp;
		if(it==od.getLHS().size()) return;//如果iod正好在od的尾巴上，没必要扩展
		while(it<od.getLHS().size()) {
			tmp=new OrderDependency(od);
			int iter=it;
			for(String r:iod.getRHS()) {
				tmp.addLHS(iter++,r);
			}
			enrichODList.add(tmp);
			
			it++;
		}
		
	}
	
	public static void enrichment() {
		if(incorrectODList.isEmpty()) return;
		for(OrderDependency iod:incorrectODList) {
			for(OrderDependency ood:originalODList) {
				if(iod.getLHS().size()<ood.getLHS().size()&&ood.isEqual(iod)==false&&ood.isContain(iod)!=-1) {
					enrichSingleOD(ood,iod,ood.isContain(iod));
				}
			}
		}
	}
	
}
