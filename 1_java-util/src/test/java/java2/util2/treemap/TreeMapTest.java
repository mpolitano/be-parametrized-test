package java2.util2.treemap;

import java2.util2.Comparator;
import java2.util2.Iterator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.TestHarness;

public class TreeMapTest extends TestHarness {

	@Test
	public void elementsDoesNotBelongToList() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				addInvalidTest();
				continue;
			} //Maybe need filter one time before. Is better to Randoop
			Object e = getObj(l.firstKey());
			int k = getInt(-1000, 1000);
			if (!l.containsKey(e)) {
				int oldSize = l.size();
				Object res = l.put(e, k);
				assertNull(res);
				assertEquals(oldSize + 1, l.size());
			}
		}
	}

	@Test
	public void elementsBelongToList() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getElementFrom(l);
			Object k = getInt(-1000, 1000);
			int oldSize = l.size();
			Object res = l.put(e, k);
			assertTrue(l.containsKey(e));
			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void clear() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
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
	public void noEleme_containsValue() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getInt(-1000, 1000);
			if (!l.containsValue(e)) {
				int oldSize = l.size();
				assertFalse(l.containsValue(e));
				assertEquals(oldSize, l.size());
			}
		}
	}

	@Test
	public void Belong_remove() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getElementFrom(l);
			if (l.containsKey(e)) {
				int oldSize = l.size();
				Object c = l.remove(e);
				assertFalse(l.containsKey(e));
				assertEquals(oldSize - 1, l.size());
			}
		}
	}

	@Test
	public void noEleme_remove() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getObj(l.firstKey());
			if (!l.containsKey(e)) {
				int oldSize = l.size();
				Object c = l.remove(e);
				assertEquals(c, null);
				assertFalse(l.containsKey(e));
				assertEquals(oldSize, l.size());
			}
		}
	}

	@Test
	public void last_key_test() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object c = l.lastKey();

			//Check maximium.
			Comparable max = (Comparable) l.firstKey();
			Iterator iterator
					= l.keySet().iterator();
			while (iterator.hasNext()) {
				Comparable key = (Comparable) iterator.next();
				if (key.compareTo(max) > 0) {
					max = key;
				}
			}
			assertEquals(max, c);

			assertTrue(l.containsKey(c));
			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void get_test() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getElementFrom(l);
			int oldSize = l.size();
			Object c = l.get(e);
			assertTrue(l.containsValue(c));
			assertTrue(l.containsKey(e));

			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void empty_test() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
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
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (!l.isEmpty()) continue;
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
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			Object c = l.firstKey();

			//Check minumium.
			Comparable min = (Comparable) l.firstKey();
			Iterator iterator
					= l.keySet().iterator();
			while (iterator.hasNext()) {
				Comparable key = (Comparable) iterator.next();
				if (key.compareTo(min) > 0) {
					min = key;
				}

			}

			assertTrue(l.containsKey(c));
			assertEquals(oldSize, l.size());
			assertTrue(l.containsKey(c));
		}
	}

	@Test
	public void toString_test() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			int oldSize = l.size();
			String c = l.toString();
		}
	}

	@Test
	public void comparator_test() {
		for (Object o : readObjects2()) {
			countTest();
			TreeMap l = (TreeMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			l.comparator();
		}
	}

	@Test
	public void constructor_test() {
		TreeMap t = new TreeMap();
	}

	@Test
	public void constructor_all_test() {
		TreeMap t = new TreeMap(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return 0;
			}
		});
	}
}
