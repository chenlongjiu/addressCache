package addressCache;

import java.net.InetAddress;
import java.util.ArrayDeque;
import java.util.HashMap;

import addressCache.InetNode;

public class operationCache implements AddressCache, Runnable {
	private ArrayDeque<InetNode> cache = new ArrayDeque<InetNode>(); 
	private HashMap<String, InetNode> dic = new HashMap<String, InetNode>();
	private int size = 0;
	private int TTE = 5; //time to evict
	private long TTL = 2592000; //default 30 days
	private boolean alive = true; //mark if terminate;
	
	//initial cache size and start garbage collection system
	public operationCache(int size){
		this.size = size;
		this.garbageCollection(); //start garbage collection thread
	}
	
	
	@Override
	public boolean offer(InetAddress address) {
		// TODO Auto-generated method stub
		if(this.size == this.size()){
			return false;
		}
		if(this.dic.containsKey(address.toString()))
			return true;
		else{
			InetNode node = new InetNode(address, this.TTL);
			cache.offer(node);
			dic.put(address.toString(), node);
			if(this.takeThreads.size() > 0){
				System.out.println("notify");
				synchronized (this.cache) {
					this.cache.notify();
				}
				
			}
			
		}
		return true;
	}
	

	@Override
	public boolean contains(InetAddress address) {
		// TODO Auto-generated method stub
		if(this.dic.containsKey(address.toString()))
			return true;
		return false;
	}

	@Override
	public boolean remove(InetAddress address) {
		// TODO Auto-generated method stub
		if(this.dic.containsKey(address.toString())){
			cache.remove(dic.get(address.toString()));
			dic.remove(address.toString());
			return true;
		}
		return false;
	}

	@Override
	public InetAddress peek() {
		// TODO Auto-generated method stub
		if(this.cache.size() == 0)
			return null;
		return this.cache.peek().getAddress();
	}

	@Override
	public InetAddress remove() {
		// TODO Auto-generated method stub
		if(this.cache.size()==0){
			return null;
		}
		InetAddress address = this.cache.pop().getAddress();
		dic.remove(address.toString());
		return address;
	}

	@Override
	public InetAddress take() throws InterruptedException {
		// TODO Auto-generated method stub
		if(!this.isEmpty()){
			return this.remove();
		}else{
			takeThread take = new takeThread(this, this.cache);
//			this.currentWait ++;
			Thread threadT = new Thread(take);
			synchronized(threadT){
				try {
					while(this.cache.isEmpty()){
						System.out.println("cache is empty and need to refill");
						System.out.println("the length of the thread waiting is " + this.takeThreads.size());
						threadT.start();
					}
					return this.remove();
					
				} catch (InterruptedException e) {
					System.out.println("take has been interruped");
					return null;
				}
			}
			
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		this.cache.clear();
		this.dic.clear();
		this.alive = false;
		
	}
	
	@Override
	public int size() {
		return this.cache.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.cache.size()==0?true:false;
	}
	
	//API for user define the TTL for each cache
	public boolean setTTL(long TTL){
		this.TTL = TTL;
		return true;
	}
	
	
	//garbage collection
	private void garbageCollection(){
		//create a thread running and simulate keep asking the evict if it's an useless 
		new Thread(this).start();
	}
	
	//garbage thread
	public void run(){
		try {
			while(this.alive){
				Thread.sleep(this.TTE*1000);
				if(!this.isEmpty())
					while(!this.isEmpty() && this.cache.getFirst().isExpire()){
						this.dic.remove(this.cache.pollFirst().getAddress().toString());
					}
			}
					
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
