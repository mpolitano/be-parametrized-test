package java2.util2.treemap;

import java2.util2.Iterator;
import java2.util2.Set;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Rule;
import utils.TestHarness;

public class TreeMapTest extends TestHarness {

	 @Rule
	  public final ExpectedException exception = ExpectedException.none();

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList(Object o) {
		TreeMap l = (TreeMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  { addInvalidTest(); return; } //Maybe need filter one time before. Is better to Randoop
		Object e = getObj(l.firstKey());
		int k = getInt(-1000, 1000);
		if (!l.containsKey(e)) {
			int oldSize = l.size();
			Object res = l.put(e,k);
			assertNull(res);
			assertEquals(oldSize + 1, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsBelongToList(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		if (l.isEmpty()) return;
		Object e = getElementFrom(l);
		Object k = getInt(-1000, 1000);
		int oldSize = l.size();
		Object res = l.put(e,k);
		assertTrue(l.containsKey(e));
		assertEquals(oldSize , l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void clear(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		l.clear();
		assertEquals(l.size() , 0);
	}

//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void containsValue(Object o) {
//		TreeMap l = (TreeMap) o;
//		if (l.isEmpty()) return;
//		Object e = getElementFromValues(l);
//		int oldSize = l.size();
//		assertTrue(l.containsValue(e));
//		assertEquals(oldSize, l.size());
//	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void noEleme_containsValue(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		if (l.isEmpty()) return;
		Object e = getInt(-1000,1000);
		if(!l.containsValue(e)) {
			int oldSize = l.size();
			assertFalse(l.containsValue(e));
			assertEquals(oldSize, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void Belong_remove(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		if (l.isEmpty()) return;
		Object e = getElementFrom(l);
		if(l.containsKey(e)) {
			int oldSize = l.size();
			Object c = l.remove(e);
			assertFalse(l.containsKey(e));
			assertEquals(oldSize -1, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void noEleme_remove(Object o) {
		TreeMap l = (TreeMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		Object e = getObj(l.firstKey());
		if(!l.containsKey(e)) {
			int oldSize = l.size();
			Object c = l.remove(e);
			assertEquals(c,null);
			assertFalse(l.containsKey(e));
			assertEquals(oldSize, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void last_key_test(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		if (l.isEmpty()) return;
		int oldSize = l.size();
		Object c = l.lastKey();

		//Check maximium.
		Comparable max = (Comparable) l.firstKey();
 		Iterator iterator
				= l.keySet().iterator();
		while (iterator.hasNext()) {
			Comparable key = (Comparable) iterator.next();
			if (key.compareTo(max) > 0){
				max = key;
			}
		}
		assertEquals(max, c);

		assertTrue(l.containsKey(c));
		assertEquals(oldSize, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void get_test(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		if (l.isEmpty()) return;
		Object e = getElementFrom(l);
		int oldSize = l.size();
		Object c = l.get(e);
		assertTrue(l.containsValue(c));
		assertTrue(l.containsKey(e));

		assertEquals(oldSize, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void empty_test(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		if (l.isEmpty()) return;
			int oldSize = l.size();
			boolean result = l.isEmpty();
			assertEquals(result,false);
			assertTrue(oldSize>0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void non_empty_test(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		if (!l.isEmpty()) return;
		int oldSize = l.size();
		boolean result = l.isEmpty();
		assertEquals(result,true);
		assertTrue(oldSize==0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void first_key_test(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK())
			return;

		if (l.isEmpty()) return;
		int oldSize = l.size();
		Object c = l.firstKey();

		//Check minumium.
		Comparable min = (Comparable) l.firstKey();
		Iterator iterator
				= l.keySet().iterator();
		while (iterator.hasNext()) {
			Comparable key = (Comparable) iterator.next();
			if (key.compareTo(min) > 0){
				min = key;
			}

		}

		assertTrue(l.containsKey(c));
		assertEquals(oldSize, l.size());
		assertTrue(l.containsKey(c));
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void toString_test(Object o) {
		TreeMap l = (TreeMap) o;
		if (l.isEmpty()) return;
		int oldSize = l.size();
		String c = l.toString();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void comparator_test(Object o) {
		TreeMap l = (TreeMap) o;
		if(!l.repOK()) return;
		if (l.isEmpty()) return;
		l.comparator();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void constructor_test(Object o) {
		TreeMap t = new TreeMap();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void constructor_all_test(Object o) {
		TreeMap t = new TreeMap();
	}


}
