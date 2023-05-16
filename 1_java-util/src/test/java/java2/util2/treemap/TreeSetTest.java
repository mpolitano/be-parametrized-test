package java2.util2.treemap;

import java2.util2.Comparator;
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

	@Test
	public void elementsDoesNotBelongToList() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				addInvalidTest();
				continue;
			} //Maybe need filter one time before. Is better to Randoop
			Object e = getObj(l.first());
			int k = getInt(-1000, 1000);
			if (!l.contains(e)) {
				int oldSize = l.size();
				boolean res = l.add(e);
				assertTrue(res);
				assertEquals(oldSize + 1, l.size());
			}
		}
	}

	//ADDs. Always add element to list.
	@Test
	public void elementsBelongToList() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object e = l.first();
			boolean res = l.add(e);
			assertFalse(res);
			assertTrue(l.contains(e));
			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void clear() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			l.clear();
			assertEquals(l.size(), 0);
		}
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

	@Test
	public void noElem_containsValue() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getObj(l.first());
			if (!l.contains(e)) {
				int oldSize = l.size();
				assertFalse(l.contains(e));
				assertEquals(oldSize, l.size());
			}
		}
	}

	@Test
	public void belong_remove() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = l.first();
			int oldSize = l.size();
			Object c = l.remove(e);
			assertFalse(l.contains(e));
			assertEquals(oldSize - 1, l.size());
		}
	}

	@Test
	public void noEleme_remove() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getObj(l.first());
			if (!l.contains(e)) {
				int oldSize = l.size();
				Object c = l.remove(e);
				assertEquals(c, false);
				assertFalse(l.contains(e));
				assertEquals(oldSize, l.size());
			}
		}
	}

	@Test
	public void last_key_test() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object c = l.last();
			assertTrue(l.contains(c));
			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void empty_test() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			boolean result = l.isEmpty();
			assertEquals(result, false);
			assertTrue(oldSize > 0);
		}
	}

	@Test
	public void non_empty_test() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			boolean result = l.isEmpty();
			assertEquals(result, true);
			assertTrue(oldSize == 0);
		}
	}

	@Test
	public void first_key_test() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object c = l.first();
			assertTrue(l.contains(c));
			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void toString_test() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			String c = l.toString();
			assertTrue(c.length()>0);
		}
	}

	@Test
	public void comparator_test() {
		for (Object o: readObjects2()) {
			countTest();
			TreeSet l = (TreeSet) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			l.comparator();
		}
	}

	@Test
	public void constructor_test() {
		TreeSet t = new TreeSet();
	}

	@Test
	public void constructor_all_test() {
		TreeSet t = new TreeSet(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return 0;
			}
		});
	}


}
