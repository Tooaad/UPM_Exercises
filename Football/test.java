package Football;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class test {

	public static void main(String[] args) {
		HashMap <String,Integer> fraude = new HashMap<String, Integer>();
		fraude.put("2", 3);
		fraude.put("1", 2);
		fraude.put("6", 1);
		fraude.put("4", 7);
		fraude.put("5", 0);
		fraude.put("9", 1);
		
		List<Entry<String, Integer>> fraudeList = new ArrayList<Entry<String, Integer>>(fraude.entrySet());
		Collections.sort(fraudeList, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		System.out.println("nombre_continente");
		System.out.println("ID\t #Ataques");
		int top = 10;
		for (Entry<String, Integer> entry : fraudeList) {
			if (top-- < 1)
				return ;
			System.out.println(entry.getKey() + "\t\t" + entry.getValue());
		}
	}
}
