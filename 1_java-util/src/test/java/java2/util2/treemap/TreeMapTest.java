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

public class TreeMapTest extends TestHarness {

	 @Rule
	  public final ExpectedException exception = ExpectedException.none();

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList(Object o) {
		TreeMap l = (TreeMap) o;
		int e = getInt(-1000, 1000);
		int k = getInt(-1000, 1000);
		if (!l.containsKey(e)) {
			int oldSize = l.size();
			Object res = l.put(e,k);
//			assertTrue(res);
//			assertTrue(l.containsKey(res));
			assertEquals(oldSize + 1, l.size());
		}
	}

	//ADDs. Always add element to list.
	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsBelongToList(Object o) {
		TreeMap l = (TreeMap) o;
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
		TreeMap l = (TreeMap) o;
		if (l.isEmpty()) return;
		int oldSize = l.size();
		Object c = l.lastKey();
		assertTrue(l.containsKey(c));
		assertEquals(oldSize, l.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void get_test(Object o) {
		TreeMap l = (TreeMap) o;
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
		if (l.isEmpty()) return;
		int oldSize = l.size();
		Object c = l.firstKey();
		assertTrue(l.containsKey(c));
		assertEquals(oldSize, l.size());
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
//
//	//Check collection. Get Collection?
//	@Test
//	public void emptyList_addAll() {
//		LinkedList l = new LinkedList();
//		Collection b = new LinkedList();
//		boolean res = l.addAll(b);
//		assertFalse(res);
//		assertEquals(0, l.size());
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void nonEmptyList_addAll(Object o) {
//		LinkedList l = (LinkedList) o;
//		Collection e = (Collection) getCollection();
//		if (e.isEmpty()) return;
//		int oldSize = l.size();
//		boolean res = l.addAll(e);
//
//		assertTrue(res);
////		assertEquals(oldSize+e.size(), l.size());
//	}
//
//
//	//REMOVEs. Same value in the list. Contains no check
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementsBelongToList_remove(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		Object e = getElementFrom(l);
//		if (l.contains(e)) {
//			int oldSize = l.size();
//			boolean res = l.remove(e);
//			assertTrue(res);
//			assertEquals(oldSize -1, l.size());
//		}
//	}
//
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementsDoesNotBelongToList_remove(Object o) {
//		LinkedList l = (LinkedList) o;
//		Object e = getInt(-1000,1000);
//		if (!l.contains(e)) {
//			int oldSize = l.size();
//			boolean res = l.remove(e);
//			assertFalse(res);
////			assertFalse(l.contains(e)); //same values.
//			assertEquals(oldSize, l.size());
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void indexBelongToList_remove(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		int e = getInt(0, l.size()-1);
//		Object objToRemode = l.get(e);
//		if (l.contains(objToRemode)) {
//			int oldSize = l.size();
//			Object res = l.remove(e);
//			assertEquals(res,objToRemode);
////			assertFalse(l.contains(objToRemode)); //objs repeat
//			assertEquals(oldSize-1, l.size());
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementstBelongToList_removeFirst(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		int oldSize = l.size();
//		Object first = l.get(0);
//		Object res = l.removeFirst();
////		assertFalse(l.contains(first)); //same items
//		assertEquals(oldSize-1, l.size());
//		assertEquals(res, first);
//
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementstBelongToList_removeLast(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		int oldSize = l.size();
//		Object first = l.get(l.size()-1);
//		Object res = l.removeLast();
////		assertFalse(l.contains(first)); //same items
//		assertEquals(oldSize-1, l.size());
//		assertEquals(res, first);
//	}
//
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void emptyList_getFirst(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) { //e<size or e>size
//			assertThrows(NoSuchElementException.class, () -> {
//				l.getFirst();
//			});
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void emptyList_get(Object o) {
//		LinkedList l = (LinkedList) o;
//		int e = getInt(-1000, 1000);
//		if (e<0 || e>=l.size()) { //e<size or e>size
//			assertThrows(IndexOutOfBoundsException.class, () -> {
//				l.get(e);
//			});
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void NonEmptyList_getFirst(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		int oldSize = l.size();
//		Object res = l.getFirst();
//		Object first = l.get(0);
//		assertEquals(oldSize, l.size());
//		assertEquals(res, first);
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void emptyList_getLast(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) {
//			assertThrows(NoSuchElementException.class, () -> {
//				l.getLast();
//			});
//		}
//	}
//
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void NonEmptyList_getLastt(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		int oldSize = l.size();
//		Object res = l.getLast();
//		Object last = l.get(l.size()-1);
//		assertEquals(oldSize, l.size());
//		assertEquals(res, last);
//
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void NonEmptyList_clear(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		l.clear();
//		assertEquals(l.size(), 0);
//
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void indexBelongToList_add(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		int e = getInt(0, l.size()-1);
//		Object objToAdd = l.get(e);
//		if (!l.contains(e)) {
//			int oldSize = l.size();
//			l.add(e,objToAdd);
//			assertEquals(l.get(e),objToAdd);
//			assertTrue(l.contains(objToAdd));
//			assertEquals(oldSize + 1, l.size());
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void indexDoesNotBelongToList_add(Object o) {
//		LinkedList l = (LinkedList) o;
//		int e = getInt(-1000, -1);
//		if (l.isEmpty() || e<l.size() || e>=l.size()) {
//			assertThrows(IndexOutOfBoundsException.class, () -> {
//				l.add(e,new Integer(1));
//			});
//		}
//	}
//
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementsBelongToList_set(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		int e = getInt(0, l.size()-1);
//		Object objToset= getElementFrom(l);
//		Object objToRemplace = l.get(e);
//		int oldSize = l.size();
//		Object oldValue=l.set(e,o);
//		assertEquals(oldValue,objToRemplace);
////		assertFalse(l.contains(objToRemplace));
//		assertEquals(oldSize, l.size());
//
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementsDoesBelongToList_indexOf(Object o) {
//		LinkedList l = (LinkedList) o;
//		int e = getInt(-1000, 1000);
//		if(!l.contains(e)) {
//			int res = l.indexOf(e);
//			assertTrue(res==-1);
//			assertFalse(l.contains(e));
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementsBelongToList_indexOf(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		Object e = getElementFrom(l);
//		if(l.contains(e)) {
//			int res = l.indexOf(e);
//			assertTrue(res>=0);
////			assertNotEquals(res,-1);
//
//			assertTrue(l.contains(e));
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementsDoesBelongToList_lastindexOff(Object o) {
//		LinkedList l = (LinkedList) o;
//		Object e = getInt(-1000, 1000);
//		if(!l.contains(e)) {
//			int res = l.lastIndexOf(e);
//			assertEquals(res,-1);
//			assertFalse(l.contains(e));
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementsBelongToList_lastindexOff(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		Object e = getElementFrom(l);
//		if(l.contains(e)) {
//			int res = l.lastIndexOf(e);
//			assertTrue(res>=0);
//			assertNotEquals(res,-1);
//			assertTrue(l.contains(e));
//		}
//	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void testClone(Object o) {
//		LinkedList l = (LinkedList) o;
//		LinkedList res = (LinkedList)l.clone();
//		assertEquals(res.size(),l.size());
//	}
//
////	@Test
////	public void constructorTest() {
////		LinkedList l = new LinkedList();
////		assertTrue(l.size()==0);
////	}
//
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void constructorCollectionTest() {
//		Collection e = (Collection) getCollection();
//		LinkedList l = new LinkedList(e);
//		assertTrue(l.size()==e.size());
//	}
//

//?}
