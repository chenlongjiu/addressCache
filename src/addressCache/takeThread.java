package addressCache;

import java.rmi.server.Operation;
import java.util.ArrayDeque;

public class Cache{
	public ArrayDeque<InetNode> cache;
	public operationCache oc; 
	public takeThread(operationCache oc, ArrayDeque<InetNode> cache){
		this.oc = oc;
		this.cache = cache;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (this.cache) {
			while(this.cache.isEmpty()){
				try {
					this.cache.wait();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			System.out.print(this.oc.remove());
			
		}
	}
	
}
