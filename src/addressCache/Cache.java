package addressCache;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Cache {
	private ArrayDeque<InetNode> cache = new ArrayDeque<InetNode>();
	private HashMap<String, InetNode> dic = new HashMap<String, InetNode>();
	private int size = 0; 
}
