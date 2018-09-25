package OD;

import java.util.ArrayList;

import Data.Cmp;
import Data.DataStruct;
import Test.*;

public class Detect {
//	DataStruct preData = new DataStruct(), nextData = new DataStruct(), curData = new DataStruct(),
//			increData = new DataStruct();
	
	ArrayList<Integer> preList=new ArrayList<Integer>(),nextList=new ArrayList<Integer>(),
			curList=new ArrayList<Integer>(),increList=new ArrayList<Integer>();
	ArrayList<DataStruct> objectList = new ArrayList<DataStruct>();
	public Detect() {

	}

//	public Detect(DataStruct pre, DataStruct next, DataStruct cur, DataStruct incre) {
//		preData = pre;
//		nextData = next;
//		curData = cur;
//		increData = incre;
//		
//	}
	
	public Detect(ArrayList<DataStruct> objList, ArrayList<Integer> pre, ArrayList<Integer> next,
			ArrayList<Integer> cur, ArrayList<Integer> incre) {
		preList = pre;
		nextList = next;
		curList = cur;
		increList = incre;
		objectList = objList;
	}

	// 这里需要一个循环，来应对AB->CDEF这种右边有多条od的情况
	public String detectSingleOD(OrderDependency od) {
		DataStruct preData=preList.isEmpty()?null:objectList.get(preList.get(0));
		DataStruct nextData=nextList.isEmpty()?null:objectList.get(nextList.get(0));
		DataStruct curData=curList.isEmpty()?null:objectList.get(curList.get(0));
		DataStruct increData=objectList.get(increList.get(0));
		
		System.out.print("\n\n\nchecking od: ");
		od.printOD();
		if (Test.debug) {
			System.out.print("ATTR NAME: ");
			DataStruct.printAttrName();
			System.out.print("increData: ");
			increData.printSingleData();
			
			if (curData != null) {
				System.out.print("curData:   ");
				curData.printSingleData();
			}
				
		}

		for (String it : od.RHS) {
			System.out.println("检查右边属性: "+it);
			String pv = preData == null ? "" : preData.getByName(it);
			String cv = curData == null ? "" : curData.getByName(it);
			String iv = increData.getByName(it);
			String nv = nextData == null ? "" : nextData.getByName(it);
			System.out.println("pre: " + pv + "  cur: " + cv + "  next: " + nv + "  incre: " + iv);

//			if ((nv.equals("") || Cmp.equals(iv, cv) == false) && (pv.equals("") || Cmp.compare(pv, iv) < 0)
//					&& (nv.equals("") || Cmp.compare(nv, iv) > 0))
//				return "split";
//			else if ((pv.equals("") || Cmp.compare(pv, iv) < 0)||(nv.equals("") || Cmp.compare(nv, iv) > 0))
//				return "swap";
			if (Cmp.equals(iv, cv) == false && Cmp.compare(pv, iv) <=0 &&  Cmp.compare(nv, iv) >= 0)
				return "split";
			else if (Cmp.compare(pv, iv) >0||Cmp.compare(nv, iv) < 0)
				return "swap";
		}
		return "valid";
	}
}
