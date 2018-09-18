package BplusTree;
/**
 * B+树的定义：
 * 
 * 1.任意非叶子结点最多有M个子节点；且M>2；M为B+树的阶数
 * 2.除根结点以外的非叶子结点至少有 (M+1)/2个子节点；
 * 3.根结点至少有2个子节点；
 * 4.除根节点外每个结点存放至少（M-1）/2和至多M-1个关键字；（至少1个关键字）
 * 5.非叶子结点的子树指针比关键字多1个；
 * 6.非叶子节点的所有key按升序存放，假设节点的关键字分别为K[0], K[1] … K[M-2], 
 *  指向子女的指针分别为P[0], P[1]…P[M-1]。则有：
 *  P[0] < K[0] <= P[1] < K[1] …..< K[M-2] <= P[M-1]
 * 7.所有叶子结点位于同一层；
 * 8.为所有叶子结点增加一个链指针；
 * 9.所有关键字都在叶子结点出现
 */

/**
 * @author LeeJay 2014-04-03
 *
 */
 
/**
 * 1.搞清楚树结构
 * 2.链表就可以,get  list(index-1) 如果相等继续往前,如果到头就上面再找
 * 3.把数据填入b+树 (finish)
 * 4.修改node比较规则  不难  comparable(finish)
 * 5.创建接口  
 * 6. 搞清楚插入原理    查找前一个和后一个  
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import Data.CSVtoDataObject;
import Data.VoterObject;
 
public class BplusTree <K extends Comparable<K>, V extends List>{
 
	/** 根节点 */
	protected BplusNode<K, V> root;
 
	/** 阶数，M值 */
	protected int order;
 
	/** 叶子节点的链表头 */
	protected BplusNode<K, V> head;
 
	/** 树高*/
	protected int height = 0;
	
	public  Entry<K,V>  getPre(K key,int tid){
		return root.getPre(key,tid,this);
	}
	public  Entry<K,V> getNext(K key ,int tid){
		return root.getNext(key,tid,this);
	}
	public BplusNode<K, V> getHead() {
		return head;
	}
 
	public void setHead(BplusNode<K, V> head) {
		this.head = head;
	}
 
	public BplusNode<K, V> getRoot() {
		return root;
	}
 
	public void setRoot(BplusNode<K, V> root) {
		this.root = root;
	}
 
	public int getOrder() {
		return order;
	}
 
	public void setOrder(int order) {
		this.order = order;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public V get(K key) {
		return root.get(key).getValue();
	}
	
	public V remove(K key,int tid) {
		return root.remove(key,tid, this);
	}
 
	public void insertOrUpdate(K key, int tid) {
		root.insertOrUpdate(key, tid, this);
 
	}
 
	public BplusTree(int order) {
		if (order < 3) {
			System.out.print("order must be greater than 2");
			System.exit(0);
		}
		this.order = order;
		root = new BplusNode<K, V>(true, true);
		head = root;
	}
}
