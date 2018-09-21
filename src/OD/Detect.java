package OD;

import Data.Cmp;
import Data.VoterObject;

public class Detect {
	VoterObject preData=new VoterObject(),nextData=new VoterObject(),curData=new VoterObject(),increData=new VoterObject();
	public Detect(){
		//TODO::将与增量相同key的value和前后的value复制过来，增量的value也要拿到
		/*preData=new DataStruct(1,5);
		nextData=new DataStruct(5,8);
		curData=new DataStruct(4,7);
		increData=new DataStruct(4,6);*/
		
	}
	public Detect(VoterObject pre,VoterObject next,VoterObject cur,VoterObject incre) {
		preData=pre;
		nextData=next;
		curData=cur;
		increData=incre;
	}
	//这里需要一个循环，来应对AB->CDEF这种右边有多条od的情况
	public  String detectSingleOD(OrderDependency od) {
		System.out.print("checking od: ");
		od.printOD();
		increData.printData();
		curData.printData();
		for(String it:od.RHS) {
			String pv=preData==null?"":preData.getString(it);
			String cv=curData==null?"":curData.getString(it);
			String iv=increData.getString(it);
			String nv=nextData==null?"":nextData.getString(it);
			System.out.println(pv+"/"+nv+"/"+cv+"/"+iv);
			
			if((nv.equals("")||Cmp.equals(iv,cv)==false)&&(pv.equals("")||Cmp.compare(pv,iv)>0)&&(nv.equals("")||Cmp.compare(nv,iv)<0)) return "split";
			else if(iv.equals(cv)==false) return "swap";
		}
		return "valid";
	}
}
