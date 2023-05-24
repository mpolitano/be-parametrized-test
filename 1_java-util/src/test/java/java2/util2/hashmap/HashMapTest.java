package java2.util2.hashmap;

import java2.util2.Collection;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utils.TestHarness;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest extends TestHarness {

	@Test
	public void elementsDoesNotBelongToList() {
		for (Object o: readObjects2()) {
			countTest();
			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				addInvalidTest();
				continue;
			} //Maybe need filter one time before. Is better to Randoop
			Object e = getInt(-1000, 1000);
			int k = getInt(-1000, 1000);
			if (!l.containsKey(e)) {
				int oldSize = l.size();
				Object res = l.put(e, k);
				assertNull(res);
				assertTrue(l.containsKey(e));
				assertEquals(oldSize + 1, l.size());
			}
		}
	}

	//ADDs. Always add element to list.
	@Test
	public void elementsBelongToList() {
		for (Object o: readObjects2()) {
			countTest();
			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) {
				continue;
			}
			Object e = getElementFrom(l);
			Object k = getInt(-1000, 1000);
			if (l.containsKey(e)) {
				int oldSize = l.size();
				if (l.get(e)==null) continue;
				Object z = l.get(e);
				Object res = l.put(e, k);
				assertNotNull(res);
				assertTrue(l.containsKey(e));
				assertEquals(oldSize, l.size());
			}
		}
	}

	@Test
	public void clear() {
		for (Object o: readObjects2()) {
			countTest();
			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			l.clear();
			assertEquals(l.size(), 0);
		}
	}

	@Test
	public void get_no_contains() {
		for (Object o: readObjects2()) {
			countTest();
			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			Object k = getObj(getElementFrom(l));
			if (!l.containsKey(k)) {
				Object c = l.get(k);
				assertEquals(c, null);
			}
		}
	}

//	"Este simple test mata unos brach de tipo"
//	@Test
//	public void get_noTest_contains() {
//		for (Object o: readObjects2()) {
//			countTest();
//			HashMap l = (HashMap) o;
//			if (l.isEmpty()) continue;
//			if (!l.repOK()) continue;
////			Object k = getInt(-1000, 1000);
//			if (!l.containsKey(0)) {
//				Object c = l.get(0);
//				assertEquals(c, null);
//			}
//		}
//	}

	@Test
	public void getEntry_no_contains() {
		for (Object o: readObjects2()) {
			countTest();
			HashMap l = (HashMap) o;
			if (l.isEmpty()) return;
			if (!l.repOK()) return;
			Object k = getInt(-1000, 1000);
			if (!l.containsKey(k)) {
				Object c = l.getEntry(k);
				assertEquals(c, null);
			}
		}
	}

	@Test
	public void getEntry_contains() {
		for (Object o: readObjects2()) {
			countTest();
			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			Object k = getElementFrom(l);
			if (l.containsKey(k)) {
				HashMap.Entry c = l.getEntry(k);
				Object value = c.getValue();
				assertNotEquals(c, null);
				assertEquals(c.getKey(), k);
				assertEquals(c.getKey(), k);
				assertEquals(value, l.get(k));
			}
		}
	}

	@Test
	public void containsValue() {
		for (Object o: readObjects2()) {
			countTest();
			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			Object e = getElementFromValues(l);
			boolean c = l.containsValue(e);
			assertFalse(c);
		}
	}

	@Test
	public void noEleme_containsValue() {
		for (Object o: readObjects2()) {
			countTest();
			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
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
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
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
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			Object e = getInt(-1000, 1000);
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
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			int oldSize = l.size();
//		Object c = l.lastKey();
//		assertTrue(l.containsKey(c));
			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void get_test() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
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
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			int oldSize = l.size();
			boolean result = l.isEmpty();
			assertEquals(result, false);
			assertTrue(oldSize > 0);
		}
	}


	@Test
	public void first_key_test() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			int oldSize = l.size();
//		Object c = l.firstKey();
//		assertTrue(l.containsKey(c));
			assertEquals(oldSize, l.size());
		}
	}

	@Test
	public void toString_test() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			String c = l.toString();
			assertTrue(c.length() > 0);
		}
	}

	@Test
	public void toString_test_empty() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (!l.isEmpty()) continue;
			if (!l.repOK()) continue;
			String c = l.toString();
			assertTrue(c.length() == 2);//"{}"
		}
	}

	@Test
	public void values_test() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			int oldSize = l.size();
			Collection c = l.values();
		}

	}

	@Test
	public void hashCodTeste() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			int oldSize = l.size();
			int c = l.hashCode();
//		assertTrue(c==l.hashCode());
		}

	}

	@Test
	public void putAll() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			int oldSize = l.size();
			l.putAll((HashMap) l);
			assertTrue(l.size() >= oldSize);
		}

	}

	public void comparator_test() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			//		l.comparator();
		}
	}

	@Test
	public void constructor_test() {
		countTest();
		HashMap t = new HashMap();
	}

	@Test
	public void equals_test() {
		countTest();

		HashMap t = new HashMap();
		assertTrue(t.equals(t));
	}

	@Test
	public void equals_false_test() {
		countTest();

		HashMap t = new HashMap();
		assertFalse(t.equals(new Object()));
	}

	@Test
	public void constructor_test2() {
		countTest();

		HashMap t = new HashMap(10,10);
	}


	@Test
	public void constructor_all_test() {
		countTest();

		HashMap t = new HashMap(10);
		t = new HashMap(100);
	}

	@Test
	public void constructor_negative() {
		countTest();

		assertThrows(IllegalArgumentException.class, () -> {
			HashMap t = new HashMap(-1);
		});
	}

	@Test
	public void maskNull() {
		for (Object o: readObjects2()) {
			countTest();

			HashMap l = (HashMap) o;
			if (l.isEmpty()) continue;
			if (!l.repOK()) continue;
			int oldSize = l.size();
			Object c = l.maskNull(l);
			assertEquals(l, c);
		}
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
		countTest();

		assertThrows(IllegalArgumentException.class, () -> {
			HashMap t = new HashMap(10,-1);
		});
	}

}
