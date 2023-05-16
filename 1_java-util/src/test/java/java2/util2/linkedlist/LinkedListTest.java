package java2.util2.linkedlist;


import static org.junit.jupiter.api.Assertions.*;

import java2.util2.NoSuchElementException;
import org.junit.jupiter.api.Test;

import utils.TestHarness;

public class LinkedListTest extends TestHarness {

	//ADDs. Always add element to list.
	@Test
	public void elementsDoesNotBelongToList() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				addInvalidTest();
				continue;
			}
			int e = getInt(-1000, 1000);
//		if (!l.contains(e)) {
			int oldSize = l.size();
			boolean res = l.add(e);
			assertTrue(res);
			assertTrue(l.contains(e));
			assertEquals(oldSize + 1, l.size());
		}
	}

	@Test
	public void elementsDoesAddTwoParametersNotBelongToList() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getObj(l.getFirst());
			int oldSize = l.size();
			l.add(l.size(), e);
			assertTrue(l.contains(e));
			assertEquals(oldSize + 1, l.size());
		}
	}

	@Test
	public void elementsDoesAddTwoParametersNotBelongToListRandom() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getObj(getElementFrom(l));
			int oldSize = l.size();
			l.add(l.size(), e);
			assertTrue(l.contains(e));
			assertEquals(oldSize + 1, l.size());
		}
	}

	@Test
	public void elementstDoesNotBelongToList_addLast() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int e = getInt(-1000, 1000);
			int oldSize = l.size();
			l.addLast(e);
			Object last = l.getLast();
			assertEquals(last, e);
			assertTrue(l.contains(e));
			assertEquals(oldSize + 1, l.size());
		}
	}

	@Test
	public void elementstDoesNotBelongToList_addFirst() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int e = getInt(-1000, 1000);
			int oldSize = l.size();
			l.addFirst(e);
			Object first = l.getFirst();
			assertEquals(first, e);
			assertTrue(l.contains(e));
			assertEquals(oldSize + 1, l.size());
		}
	}

//Always add, no necessary
//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void elementsBelongToList(Object o) {
//		LinkedList l = (LinkedList) o;
//		if (l.isEmpty()) return;
//		Object e = getElementFrom(l);
//		if (l.contains(e)) {
//			int oldSize = l.size();
//			boolean res = l.add(e);
//			assertTrue(res);
//			assertFalse(l.contains(e));
//			assertEquals(oldSize, l.size());
//		}
//	}
//
//	//Check collection. Get Collection?
	@Test
	public void nonEmptyList_addAll() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			LinkedList b = (LinkedList) getCollection();
			int oldsize = l.size();
			if(b.isEmpty()) continue;
			boolean res = l.addAll(b);
			assertTrue(res);
			assertEquals(b.size()+oldsize, l.size());
		}
	}

	@Test
	public void EmptyList_addAll() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			LinkedList e = (LinkedList) getCollection();
			if (!e.isEmpty()) continue;
			int oldSize = l.size();
			boolean res = l.addAll(e);
			assertFalse(res);
			assertEquals(oldSize, l.size());
		}
	}


	//REMOVEs. Same value in the list. Contains no check
	@Test
	public void elementsBelongToList_remove() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getElementFrom(l);
			if (l.contains(e)) {
				int oldSize = l.size();
				boolean res = l.remove(e);
				assertTrue(res);
				assertEquals(oldSize - 1, l.size());
			}
		}
	}

	@Test
	public void elementsDoesNotBelongToList_remove() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getInt(-1000, 1000);
			if (!l.contains(e)) {
				int oldSize = l.size();
				boolean res = l.remove(e);
				assertFalse(res);
//			assertFalse(l.contains(e)); //same values.
				assertEquals(oldSize, l.size());
			}
		}
	}

	@Test
	public void indexBelongToList_remove() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int e = getInt(0, l.size() - 1);
			Object objToRemode = l.get(e);
			if (l.contains(objToRemode)) {
				int oldSize = l.size();
				Object res = l.remove(e);
				assertEquals(res, objToRemode);
//			assertFalse(l.contains(objToRemode)); //objs repeat
				assertEquals(oldSize - 1, l.size());
			}
		}
	}

	@Test
	public void elementstBelongToList_removeFirst() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object first = l.get(0);
			Object res = l.removeFirst();
//		assertFalse(l.contains(first)); //same items
			assertEquals(oldSize - 1, l.size());
			assertEquals(res, first);
		}
	}

	@Test
	public void elementstBelongToList_removeLast() {
		for (Object o : readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object first = l.get(l.size() - 1);
			Object res = l.removeLast();
	//		assertFalse(l.contains(first)); //same items
			assertEquals(oldSize - 1, l.size());
			assertEquals(res, first);
		}
	}


	@Test
	public void emptyList_getFirst() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;

			if (!l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
				assertThrows(NoSuchElementException.class, () -> {
					l.getFirst();
				});
		}
	}

	@Test
	public void emptyList_get() {
		for (Object o : readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int e = getInt(-1000, 1000);
			if (e < 0 || e >= l.size()) { //e<size or e>size
				assertThrows(IndexOutOfBoundsException.class, () -> {
					l.get(e);
				});
			}
		}
	}

	@Test
	public void NonEmptyList_getFirst() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object res = l.getFirst();
			Object first = l.get(0);
			assertEquals(oldSize, l.size());
			assertEquals(res, first);
		}
	}

	@Test
	public void emptyList_getLast() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			if (l.isEmpty()) {
				assertThrows(NoSuchElementException.class, () -> {
					l.getLast();
				});
			}
		}
	}


	@Test
	public void NonEmptyList_getLastt() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object res = l.getLast();
			Object last = l.get(l.size() - 1);
			assertEquals(oldSize, l.size());
			assertEquals(res, last);
		}

	}

	@Test
	public void NonEmptyList_clear() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			l.clear();
			assertEquals(l.size(), 0);
		}

	}

	@Test
	public void indexBelongToList_add() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int pos = getInt(0, l.size() - 1);
			Integer objToAdd = new Integer (-1);
			if (!l.contains(objToAdd)) {
				int oldSize = l.size();
				l.add(pos, objToAdd);
				assertEquals(l.get(pos), objToAdd);
				assertTrue(l.contains(objToAdd));
				assertEquals(oldSize + 1, l.size());
			}
		}
	}

	@Test
	public void indexDoesNotBelongToList_add() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int e = getInt(-1000, -1);
			if (l.isEmpty() || e < l.size() || e >= l.size()) {
				assertThrows(IndexOutOfBoundsException.class, () -> {
					l.add(e, new Integer(1));
				});
			}
		}
	}


	@Test
	public void elementsBelongToList_set() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int e = getInt(0, l.size() - 1);
			Object objToset = getElementFrom(l);
			Object objToRemplace = l.get(e);
			int oldSize = l.size();
			Object oldValue = l.set(e, o);
			assertEquals(oldValue, objToRemplace);
//		assertFalse(l.contains(objToRemplace));
			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void elementsDoesBelongToList_indexOf() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int e = getInt(-1000, 1000);
			if (!l.contains(e)) {
				int res = l.indexOf(e);
				assertTrue(res == -1);
				assertFalse(l.contains(e));
			}
		}
	}



	@Test
	public void elementsBelongToList_indexOf() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getElementFrom(l);
			if (l.contains(e)) {
				int res = l.indexOf(e);
				assertTrue(res >= 0);
//			assertNotEquals(res,-1);

				assertTrue(l.contains(e));
			}
		}
	}


	@Test
	public void elementsDoesBelongToList_lastindexOff() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getInt(-1000, 1000);
			if (!l.contains(e)) {
				int res = l.lastIndexOf(e);
				assertEquals(res, -1);
				assertFalse(l.contains(e));
			}
		}
	}

	@Test
	public void elementsBelongToList_lastindexOff() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getElementFrom(l);
			if (l.contains(e)) {
				int res = l.lastIndexOf(e);
				assertTrue(res >= 0);
				assertNotEquals(res, -1);
				assertTrue(l.contains(e));
			}
		}
	}

	@Test
	public void testClone() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			LinkedList res = (LinkedList) l.clone();
			assertEquals(res.size(), l.size());
		}
	}

	@Test
	public void testToarray() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList l = (LinkedList) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object[] res = l.toArray();
			assertEquals(res.length, l.size());
			assertEquals(res.length, l.size());

		}
	}

	@Test
	public void constructorTest() {
		LinkedList l = new LinkedList();
		assertTrue(l.size()==0);
	}

	@Test
	public void constructorCollectionTest() {
		for (Object o: readObjects2()) {
			countTest();
			LinkedList e = (LinkedList) getCollection();
			if (e.isEmpty()) return;
			if (!e.repOK()) return;
			LinkedList l = new LinkedList(e);
			assertTrue(l.size() == e.size());
		}
		}


}
