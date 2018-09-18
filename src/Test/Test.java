package Test;
import java.util.Map.Entry;

import BplusTree.BplusTree;
import Data.CSVtoDataObject;
import Data.VoterObject;
import OD.*;

import java.util.ArrayList;


public class Test {
	public static void main(String[] args) {
		CSVtoDataObject cdo = new CSVtoDataObject();
		//CSVtoDataObject increData=new CSVtoDataObject();
		ODs od=new ODs();
		try{
			od.storeOD("C:\\Users\\zhulin\\Desktop\\od.txt");
			cdo.readCSVData();
			//increData.readCSVData();
		}catch(Exception e) {
			System.out.println("read fail!");
		}
		System.out.println("The original od is:");
		od.print();
		
		ArrayList<VoterObject> objectList = cdo.datatoObject();
		/*for(int i=0;i<objectList.size();i++) {
			VoterObject temp=objectList.get(i);
			temp.printData();					
		}*/
		
		//先拿第0条数据来当做是增量数据
		VoterObject increData=objectList.get(0);
		int order = 10;
		BplusTree<Integer,ArrayList<Integer>> tree = new BplusTree<Integer, ArrayList<Integer>>(order);
		System.out.println("将元组插入度为"+ order+"的B+树中");
		int key=1;
		
		for (VoterObject temp:objectList) {
			tree.insertOrUpdate(temp.getAge(), temp.getVoter_id());
		}
		
		if(tree.get(key) == null)
			System.err.println("得不到voter_id为"+key+"的数据:" );
		else {
			ArrayList<Integer> temp = tree.get(key);
			
		}	
		
		Entry<Integer,ArrayList<Integer>> ee = tree.getPre(increData.getAge(),increData.getVoter_id());
		System.out.println("前缀为"+ee.getKey()+"/"+ee.getValue());
		ee = tree.getNext(increData.getAge(),increData.getVoter_id()); 
		System.out.println("后缀为"+ee.getKey()+"/"+ee.getValue());

		
		//存储所有新生成的od
		ArrayList<OrderDependency> originalODList=new ArrayList<OrderDependency>();
		for(OrderDependency o:od.ods) {
			originalODList.add(o);
		}
		//对每一条od进行验证
		for(OrderDependency nowOd:originalODList) {
			
			//TODO 假设我通过索引查到了前后数据，拿到了前后数据的tupleID，叫tName
			int preTupleId=2,nextTupleId=3,curTupleId=1;
			
			VoterObject preData=objectList.get(preTupleId);
			VoterObject nextData=objectList.get(nextTupleId);
			VoterObject curData=objectList.get(curTupleId);
			
			Detect d=new Detect(preData,nextData,curData,increData);
			String detectRes=d.detectSingleOD(nowOd);
			
			if(detectRes.equals("valid")==false) {
				System.out.println(detectRes);
				od.ods.remove(nowOd);
				//扩展od
				Extend e=new Extend(preData ,nextData,curData,increData);
				ArrayList<OrderDependency> newOdList=new ArrayList<OrderDependency>();
				newOdList=e.extend(nowOd,detectRes);
			
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
