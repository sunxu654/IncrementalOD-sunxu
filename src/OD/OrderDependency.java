package OD;
import java.util.ArrayList;

public class OrderDependency {
	ArrayList<String> LHS,RHS;
	static final String lr_separator="->";
	static final String attr_separator=","; 
	
	public OrderDependency() {
		LHS= new ArrayList<String>();
		RHS = new ArrayList<String>();
	}
	public void copy(OrderDependency d) {
		for(String it:d.LHS) LHS.add(it);
		for(String it:d.RHS) RHS.add(it);
	}
	void addLHS(String s) {
		LHS.add(s);
	}
	void addArray2LHS(String[] as) {
		for(int i=0;i<as.length;i++) {
			LHS.add(as[i]);
		}
	}
	void addRHS(String s) {
		RHS.add(s);
	}
	void addArray2RHS(String[] as) {
		for(int i=0;i<as.length;i++) {
			RHS.add(as[i]);
		}
	}
	void deleteRHS() {
		RHS.remove(RHS.size()-1);
	}
	
	public void printOD() {
		System.out.print(LHS.get(0));
		for(int i=1;i<LHS.size();i++) {
			System.out.print(attr_separator+LHS.get(i));
		}
		
		System.out.print(lr_separator+RHS.get(0));
		for(int i=1;i<RHS.size();i++) {
			System.out.print(attr_separator+RHS.get(i));
		}
		System.out.print("\n");
	}
	
}
