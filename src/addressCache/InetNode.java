package addressCache;

import java.net.InetAddress;
import java.util.Date;

public class InetNode {
	private InetAddress address;
	private long TTL; // record TTL 
	private Date live; //record start date
	public InetNode(InetAddress address, long TTL){
		this.address = address;
		this.TTL = TTL;
		this.live = new Date(); 
	}
	public InetAddress getAddress(){
		return this.address;
	}
	public boolean isExpire(){
		Date now = new Date(); 
		return (now.getTime()-this.live.getTime()) >= this.TTL ? true:false;
	}
}
