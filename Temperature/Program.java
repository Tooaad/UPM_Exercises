package Temperature;

import java.io.IOException;
import aop.runner.TestObject;


public class Program {	
	
	public static int findProblematicTemperature(TestObject temperature) {
		return (findProblematicBinarySearch(temperature, 0, temperature.size() - 1));	
	}
		
	public static int findProblematicBinarySearch(TestObject temperature, int left, int right) {
		int mid = (left + right) / 2;
		int tempMidValue = temperature.get(mid);
		int tempMidLeftValue = 0;
		if (mid > 0)
			tempMidLeftValue = temperature.get(mid - 1);
		
		if ((mid == temperature.size() - 1 || temperature.get(mid + 1) <= tempMidValue) && (mid == 0 || tempMidLeftValue <= tempMidValue))
			return mid;
		else if (mid > 0 && tempMidLeftValue > tempMidValue)
			return findProblematicBinarySearch(temperature, left, mid - 1);
		else
			return findProblematicBinarySearch(temperature, mid + 1, right);
		
	}

}
