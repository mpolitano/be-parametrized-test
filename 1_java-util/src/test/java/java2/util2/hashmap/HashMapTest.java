package java2.util2.hashmap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;

import java2.util2.Collection;
import java2.util2.NoSuchElementException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;

import utils.TestHarness;

public class HashMapTest extends TestHarness {

	 @Rule

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList(Object o) {
		HashMap l = (HashMap) o;
		 if(l.isEmpty()) return;
		 if(!l.repOK())  { addInvalidTest(); return; } //Maybe need filter one time before. Is better to Randoop
		 Object e = getInt(-1000,1000);
		int k = getInt(-1000, 1000);
		if (!l.containsKey(e)) {
			int oldSize = l.size();
			Object res = l.put(e,k);
			assertNull(res);
			assertTrue(l.containsKey(e));
			assertEquals(oldSize + 1, l.size());
		}
	}

	//ADDs. Always add element to list.
	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsBelongToList(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  {return; }
		Object e = getElementFrom(l);
		Object k = getInt(-1000, 1000);
		if(l.containsKey(e)) {
			int oldSize = l.size();
			Object res = l.put(e, k);
			assertTrue(l.containsKey(e));
			assertEquals(oldSize, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void clear(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
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
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
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
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
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
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		Object e = getInt(-1000,1000);
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
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		int oldSize = l.size();
//		Object c = l.lastKey();
//		assertTrue(l.containsKey(c));
		assertEquals(oldSize, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void get_test(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
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
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		int oldSize = l.size();
		boolean result = l.isEmpty();
		assertEquals(result,false);
		assertTrue(oldSize>0);
	}


	@ParameterizedTest
	@MethodSource("readObjects")
	public void first_key_test(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		int oldSize = l.size();
//		Object c = l.firstKey();
//		assertTrue(l.containsKey(c));
		assertEquals(oldSize, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void toString_test(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		int oldSize = l.size();
		String c = l.toString();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void values_test(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		int oldSize = l.size();
		Collection c = l.values();

	}
	@ParameterizedTest
	@MethodSource("readObjects")
	public void comparator_test(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		//		l.comparator();
	}

	@Test
	public void constructor_test() {
		HashMap t = new HashMap();
	}

	@Test
	public void constructor_test2() {
		HashMap t = new HashMap(10,10);
	}

	@Test
	public void constructor_all_test() {
		HashMap t = new HashMap(10);
		t = new HashMap(100);
	}

	@Test
	public void constructor_negative() {
		assertThrows(IllegalArgumentException.class, () -> {
			HashMap t = new HashMap(-1);
		});
	}

}
