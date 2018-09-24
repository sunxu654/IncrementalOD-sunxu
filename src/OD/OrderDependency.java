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
	
	public OrderDependency(OrderDependency cp) {
		LHS= new ArrayList<String>();
		RHS = new ArrayList<String>();
		for(String lhs:cp.LHS) {
			LHS.add(lhs);
		}
		for(String rhs:cp.RHS) {
			RHS.add(rhs);
		}
	}
	public void copy(OrderDependency d) {
		this.LHS.clear();
		this.RHS.clear();
		for(String it:d.LHS) LHS.add(it);
		for(String it:d.RHS) RHS.add(it);
	}
	public void addLHS(String s) {
		LHS.add(s);
	}
	public void addArray2LHS(String[] as) {
		for(int i=0;i<as.length;i++) {
			LHS.add(as[i]);
		}
	}
	public void addRHS(String s) {
		RHS.add(s);
	}
	public void addArray2RHS(String[] as) {
		for(int i=0;i<as.length;i++) {
			RHS.add(as[i]);
		}
	}
	public void deleteRHS() {
		RHS.remove(RHS.size()-1);
	}
	
	public ArrayList<String> getLHS(){
		return this.LHS;
	}
	
	public ArrayList<String> getRHS(){
		return this.RHS;
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
