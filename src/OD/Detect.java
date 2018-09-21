package OD;

import Data.Cmp;
import Data.DataStruct;

public class Detect {
	DataStruct preData=new DataStruct(),nextData=new DataStruct(),curData=new DataStruct(),increData=new DataStruct();
	public Detect(){
		//TODO::将与增量相同key的value和前后的value复制过来，增量的value也要拿到
		/*preData=new DataStruct(1,5);
		nextData=new DataStruct(5,8);
		curData=new DataStruct(4,7);
		increData=new DataStruct(4,6);*/
		
	}
	public Detect(DataStruct pre,DataStruct next,DataStruct cur,DataStruct incre) {
		preData=pre;
		nextData=next;
		curData=cur;
		increData=incre;
	}
	//这里需要一个循环，来应对AB->CDEF这种右边有多条od的情况
	public  String detectSingleOD(OrderDependency od) {
		System.out.print("checking od: ");
		od.printOD();
		increData.printSingleData();
		curData.printSingleData();
		for(String it:od.RHS) {
			String pv=preData==null?"":preData.getByName(it);
			String cv=curData==null?"":curData.getByName(it);
			String iv=increData.getByName(it);
			String nv=nextData==null?"":nextData.getByName(it);
			System.out.println("pre: "+pv+"  cur: "+cv+"  next: "+nv+"  incre: "+iv);
			
			if((nv.equals("")||Cmp.equals(iv,cv)==false)&&(pv.equals("")||Cmp.compare(pv,iv)<=0)&&(nv.equals("")||Cmp.compare(nv,iv)>=0)) return "split";
			else if(Cmp.equals(iv,cv)==false) return "swap";
		}
		return "valid";
	}
}
