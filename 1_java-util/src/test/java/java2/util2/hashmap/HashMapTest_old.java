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

public class HashMapTest_old extends TestHarness {

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
			assertNotNull(res);
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

	@ParameterizedTest
	@MethodSource("readObjects")
	public void get_no_contains(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		Object k = getInt(-1000, 1000);
		if(!l.containsKey(k)) {
			Object c = l.get(k);
			assertEquals(c,null);
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void getEntry_no_contains(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		Object k = getInt(-1000, 1000);
		if(!l.containsKey(k)) {
			Object c = l.getEntry(k);
			assertEquals(c,null);
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void getEntry_contains(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		Object k = getElementFrom(l);
		if(l.containsKey(k)) {
			HashMap.Entry c = l.getEntry(k);
			Object value=c.getValue();
			assertNotEquals(c,null);
			assertEquals(c.getKey(),k);
			assertEquals(c.getKey(),k);
			assertEquals(value,l.get(k));


		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void containsValue(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		Object e = getElementFromValues(l);
		boolean c = l.containsValue(e);
		assertFalse(c);
	}

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
		String c = l.toString();
		assertTrue(c.length()>0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void toString_test_empty(Object o) {
		HashMap l = (HashMap) o;
		if (!l.isEmpty()) return;
		if(!l.repOK())  return;
		String c = l.toString();
		assertTrue(c.length()==2);//"{}"
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
	public void hashCode(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		int oldSize = l.size();
		int c = l.hashCode();
//		assertTrue(c==l.hashCode());

	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void putAll(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		int oldSize = l.size();
		l.putAll((HashMap)l);
		assertTrue(l.size()>=oldSize);

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
	public void equals_test() {
		HashMap t = new HashMap();
		assertTrue(t.equals(t));
	}

	@Test
	public void equals_false_test() {
		HashMap t = new HashMap();
		assertFalse(t.equals(new Object()));
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

	@ParameterizedTest
	@MethodSource("readObjects")
	public void maskNull(Object o) {
		HashMap l = (HashMap) o;
		if (l.isEmpty()) return;
		if(!l.repOK())  return;
		int oldSize = l.size();
		Object c=l.maskNull(l);
		assertEquals(l,c);
	}

//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void eq_test(Object o) {
//		HashMap l = (HashMap) o;
//		if (l.isEmpty()) return;
//		if(!l.repOK())  return;
//		int oldSize = l.size();
//		Object c=l.eq(l,l);
//		assertEquals(l,c);
//	}

	@Test
	public void constructor_negative_two_arguments() {
		assertThrows(IllegalArgumentException.class, () -> {
			HashMap t = new HashMap(10,-1);
		});
	}

}
