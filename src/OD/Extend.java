package OD;
import java.util.ArrayList;
import java.util.HashMap;

import Data.VoterObject;

public class Extend {
	VoterObject preData=new VoterObject(),nextData=new VoterObject(),curData=new VoterObject(),increData=new VoterObject();
	public Extend() {
		
	}
	public Extend(VoterObject pre,VoterObject next,VoterObject cur,VoterObject incre) {
		preData=pre;
		nextData=next;
		curData=cur;
		increData=incre;
	}
	public ArrayList<OrderDependency> extend(OrderDependency od,String violationType) {
		//TODO::扩展当前od，左边加属性右边减属性；扩展成功的话可以加到原来的属ods里面
		
		ArrayList<OrderDependency> res=new ArrayList<OrderDependency>();
		Detect d=new Detect(preData,nextData,curData,increData);
		//如果是swap，可以在后面减属性看行不行
		if(violationType.equals("swap")) {
			
			while(violationType.equals("valid")==false&&od.RHS.size()>1) {
				od.RHS.remove(od.RHS.size()-1);
				violationType=d.detectSingleOD(od);
			}
			
			if(violationType.equals("valid")) res.add(od);
			
		}else if(violationType.equals("split")) {//split 左边加属性,右边减属性
			//尝试左边加属性，首先要get所有属性的名字
			ArrayList<String> attrName=VoterObject.getAttributeName();
			HashMap<String,Integer> m = new HashMap<String,Integer>();
			OrderDependency odIncre=new OrderDependency();
			odIncre.copy(od);
			//使用哈希map来记录已经被使用过的属性
			for(String it:odIncre.LHS) {
				m.put(it,new Integer(1));
			}
			for(String it:odIncre.RHS) {
				m.put(it,new Integer(1));
			}
			
			for(String adder:attrName) {
				
				//如果这个属性没有被使用
				if(m.get(adder)==null) {
					m.put(adder,new Integer(1));
					String pv=preData.getString(adder);
					String cv=curData.getString(adder);
					String iv=increData.getString(adder);
					String nv=nextData.getString(adder);
					System.out.println("尝试加新属性:: "+adder+":  "+pv+"/"+nv+"/"+cv+"/"+iv);
					
					//可以加进去的情况：
					if(cv.equals(iv)==false&&pv.compareTo(iv)<0&&pv.compareTo(nv)<0&&nv.compareTo(iv)>0&&nv.compareTo(cv)>0) {
						odIncre.LHS.add(adder);
						res.add(odIncre);
						odIncre.LHS.remove(odIncre.LHS.size()-1);
					}
					
				}
			}
			
			
			
			//尝试右边减属性，应该剪到cur和incre的相同的前半部分
			int prefixNum=0;//记录cur和增量数据在RHS中匹配的数据数量
			OrderDependency odReduce=new OrderDependency();
			odReduce.copy(od);
			for(String it:odReduce.RHS) {
				String cv=curData.getString(it);
				String iv=increData.getString(it);
				if(cv.equals(iv)) prefixNum++;
			}
			
			//如果匹配的数目不为零，那么说明减属性有戏,将prefixNum后面的都删掉
			if(prefixNum!=0) {
				odReduce.RHS.clear();
				for(int i=0;i<prefixNum;i++) odReduce.RHS.add(od.RHS.get(i));
				res.add(odReduce);
			}
		}
		return res;
	}
}
