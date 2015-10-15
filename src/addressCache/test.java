package addressCache;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.ArrayList;

import javax.imageio.stream.IIOByteBuffer;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		operationCache opt = new operationCache(10);
		ArrayList<InetAddress> addr = new ArrayList<InetAddress>();
		try {
			addr.add( InetAddress.getByName("127.0.0.1"));
			addr.add( InetAddress.getByName("127.0.0.2"));
			addr.add( InetAddress.getByName("127.0.0.3"));
			addr.add( InetAddress.getByName("127.0.0.4"));
			addr.add( InetAddress.getByName("127.0.0.5"));
			addr.add( InetAddress.getByName("127.0.0.6"));
			addr.add( InetAddress.getByName("127.0.0.62"));
			addr.add( InetAddress.getByName("127.0.0.23"));
			addr.add( InetAddress.getByName("127.0.0.13"));
			addr.add( InetAddress.getByName("127.0.0.14"));
			addr.add( InetAddress.getByName("127.0.0.15"));
			addr.add( InetAddress.getByName("127.0.0.17"));
			addr.add( InetAddress.getByName("127.0.0.19"));
			addr.add( InetAddress.getByName("127.0.0.31"));
			addr.add( InetAddress.getByName("127.0.0.41"));
			addr.add( InetAddress.getByName("127.0.0.51"));
			addr.add( InetAddress.getByName("127.0.0.61"));
			addr.add( InetAddress.getByName("127.0.0.71"));
			addr.add( InetAddress.getByName("127.0.0.81"));
			addr.add( InetAddress.getByName("127.0.0.91"));
			addr.add( InetAddress.getByName("127.0.0.111"));
			addr.add( InetAddress.getByName("127.0.0.123"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		opt.setTTL(5000);
//		opt.offer(addr);
		System.out.println(opt.isEmpty());
		System.out.println(opt.size());
		System.out.println(opt.remove());
		for(int i = 0; i < 20; i ++){
			try {
				Thread.sleep(10);
				System.out.println(opt.offer(addr.get(i%addr.size())));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println(opt.size());
//		opt.close();
		System.out.println(opt.size());
		System.out.println(opt.remove(addr.get(2)));
		System.out.println(opt.peek());
		System.out.println(opt.remove(opt.peek()));
		System.out.println(opt.peek());
		System.out.println("if contians a value " + opt.contains(opt.peek()));
		while(true){
			
			try {
				
				System.out.println(opt.take());
				System.out.println(opt.take());
				System.out.println("here");
				System.out.println(opt.offer(addr.get(2)));
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
