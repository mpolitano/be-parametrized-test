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

public class HashSetTest extends TestHarness {

	 @Rule
	  public final ExpectedException exception = ExpectedException.none();

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  { addInvalidTest(); return; } //Maybe need filter one time before. Is better to Randoop
		int e = getInt(-1000, 1000);
		int k = getInt(-1000, 1000);
		if (!l.contains(e)) {
			int oldSize = l.size();
			boolean res = l.add(e);
			assertTrue(res);
			assertEquals(oldSize + 1, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsBelongToList(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		Object e = getElementFrom(l);
		int oldSize = l.size();
		boolean res = l.add(e);
		assertTrue(l.contains(e));
		assertEquals(oldSize , l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void clear(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
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
	public void noElem_containsValue(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		Object e = getInt(-1000,1000);
		if(!l.contains(e)) {
			int oldSize = l.size();
			assertFalse(l.contains(e));
			assertEquals(oldSize, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void belong_remove(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		Object e = getElementFrom(l);
		int oldSize = l.size();
		boolean c = l.remove(e);
		assertTrue(c);
		assertEquals(oldSize -1, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void noEleme_remove(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		Object e = getInt(-1000,1000);
		if(!l.contains(e)) {
			int oldSize = l.size();
			Object c = l.remove(e);
			assertEquals(c,false);
			assertFalse(l.contains(e));
			assertEquals(oldSize, l.size());
		}
	}

//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void last_key_test(Object o) {
//		HashSet l = (HashSet) o;
//		if (l.isEmpty()) return;
//		int oldSize = l.size();
//		Object c = l.;
//		assertTrue(l.contains(c));
//		assertEquals(oldSize, l.size());
//	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void empty_test(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		int oldSize = l.size();
			boolean result = l.isEmpty();
			assertEquals(result,false);
			assertTrue(oldSize>0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void non_empty_test(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		int oldSize = l.size();
		boolean result = l.isEmpty();
		assertEquals(result,true);
		assertTrue(oldSize==0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void first_key_test(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		int oldSize = l.size();
		Object c = l.clone();
		assertEquals(l,c);
		assertEquals(oldSize, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void toString_test(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		int oldSize = l.size();
		String c = l.toString();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void hashCode_test(Object o) {
		HashSet l = (HashSet) o;
		if(!l.repOK())  return;
		if (l.isEmpty()) return;
		l.hashCode();
	}

	@Test
	public void constructor_test() {
		HashSet t = new HashSet();
	}

	@Test
	public void constructor_all_test() {
		HashSet t = new HashSet(10);
		t = new HashSet(0);
	}

	@Test
	public void elementsDoesNotBelongToList_remove_First() {
			assertThrows(IllegalArgumentException.class, () -> {
				HashSet t = new HashSet(-1);
				t = new HashSet(-1,10);

			});
		}
}

