package BplusTree;

import java.util.ArrayList;
import java.util.HashMap;
import Data.VoterObject;

public  class TupleIdReflectVoterNode {
	
	public HashMap<Integer,VoterObject> m = new HashMap<>();
	public TupleIdReflectVoterNode(ArrayList<VoterObject> list) {
		super();
		for(VoterObject temp:list) {
			m.put(temp.getVoter_id(), temp);
		}
	}
	public void add(ArrayList<VoterObject> list) {
		for(VoterObject temp:list) {
			m.put(temp.getVoter_id(), temp);
		}
	}
	public HashMap<Integer, VoterObject> getM() {
		return m;
	}
	
}
