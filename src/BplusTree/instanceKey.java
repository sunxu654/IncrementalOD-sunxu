package BplusTree;

import java.util.ArrayList;
import java.util.List;

import Data.Cmp;
import Data.DataStruct;

public class instanceKey implements Comparable<instanceKey>{
	public ArrayList<String> multiAtr = new ArrayList<String>();
	
	public instanceKey(List<String> LHS,DataStruct d) {
		for(String temp:LHS) {
			multiAtr.add(d.getByName(temp));
		}
	}



	@Override
	public int compareTo(instanceKey k2) {
		for(int i =0;i<multiAtr.size();i++) {
			int result = Cmp.compare(this.multiAtr.get(i), k2.multiAtr.get(i));
			if(result!=0)
				return result;
			
		}
		return 0;
	}
}
	
