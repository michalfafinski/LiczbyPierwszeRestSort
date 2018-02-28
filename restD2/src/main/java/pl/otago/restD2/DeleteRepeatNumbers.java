package pl.otago.restD2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//*** metoda usuwająca powtarzające się liczby w zbiorze ***//
public class DeleteRepeatNumbers {
	public static Set arrayMethod(Integer[] tableBeforeDeleteRepeat) {

		List<Integer> listTableBeforeDelete = Arrays.asList(tableBeforeDeleteRepeat);
		Collections.sort(listTableBeforeDelete);
		Set<Integer> set = new HashSet<Integer>(listTableBeforeDelete);

		return (set);
	}
}
