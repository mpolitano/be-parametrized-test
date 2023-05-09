package java2.util2.treemap;

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

public class TreeSetTest extends TestHarness {

	 @Rule
	  public final ExpectedException exception = ExpectedException.none();

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList(Object o) {
		TreeSet l = (TreeSet) o;
		if(l.isEmpty()) return;
		if(!l.repOK())  { addInvalidTest(); return; } //Maybe need filter one time before. Is better to Randoop
		Object e = getObj(l.first());
		int k = getInt(-1000, 1000);
		if (!l.contains(e)) {
			int oldSize = l.size();
			boolean res = l.add(e);
			assertTrue(res);
			assertEquals(oldSize + 1, l.size());
		}
	}

	//ADDs. Always add element to list.
	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsBelongToList(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		int oldSize = l.size();
		Object e = l.first();
		boolean res = l.add(e);
		assertFalse(res);
		assertTrue(l.contains(e));
		assertEquals(oldSize , l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void clear(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
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
	public void noElem_containsValue(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		Object e = getObj(l.first());
		if(!l.contains(e)) {
			int oldSize = l.size();
			assertFalse(l.contains(e));
			assertEquals(oldSize, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void belong_remove(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		Object e = l.first();
		int oldSize = l.size();
		Object c = l.remove(e);
		assertFalse(l.contains(e));
		assertEquals(oldSize -1, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void noEleme_remove(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		Object e = getObj(l.first());
		if(!l.contains(e)) {
			int oldSize = l.size();
			Object c = l.remove(e);
			assertEquals(c,false);
			assertFalse(l.contains(e));
			assertEquals(oldSize, l.size());
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void last_key_test(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		int oldSize = l.size();
		Object c = l.last();
		assertTrue(l.contains(c));
		assertEquals(oldSize, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void empty_test(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		int oldSize = l.size();
			boolean result = l.isEmpty();
			assertEquals(result,false);
			assertTrue(oldSize>0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void non_empty_test(Object o) {
		TreeSet l = (TreeSet) o;
		if (!l.isEmpty()) return;
		if(!l.repOK()) return;
		int oldSize = l.size();
		boolean result = l.isEmpty();
		assertEquals(result,true);
		assertTrue(oldSize==0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void first_key_test(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		int oldSize = l.size();
		Object c = l.first();
		assertTrue(l.contains(c));
		assertEquals(oldSize, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void toString_test(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		int oldSize = l.size();
		String c = l.toString();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void comparator_test(Object o) {
		TreeSet l = (TreeSet) o;
		if (l.isEmpty()) return;
		if(!l.repOK()) return;
		l.comparator();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void constructor_test(Object o) {
		TreeSet t = new TreeSet();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void constructor_all_test(Object o) {
		TreeSet t = new TreeSet();
	}


}
