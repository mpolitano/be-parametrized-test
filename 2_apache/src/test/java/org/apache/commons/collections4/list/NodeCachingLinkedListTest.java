	package org.apache.commons.collections4.list;

import org.apache.commons.collections4.List;
import org.junit.jupiter.api.Test;
import utils.TestHarness;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class NodeCachingLinkedListTest extends TestHarness {

	@Test
	public void elementsDoesNotBelongToList_put() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				addInvalidTest();
				continue;
			}
			Object e = getInt(-1000, 1000);
			if (!n.contains(e)) {
				int oldSize = n.size();

				boolean result = n.add(e);
				assertTrue(n.contains(e));
				assertTrue(result);
				assertTrue(n.size() == oldSize + 1);
			}
		}
	}

	@Test
	public void elementsDoesNotBelongToList_put_parameters() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object e = getInt(-1000, 1000);
			Object f = getElementFrom(n);
			if (!n.contains(e)) {
				int oldSize = n.size();
				int index = n.indexOf(f);
				n.add(index, e);
				assertTrue(n.size() == oldSize + 1);
				assertTrue(n.contains(e));
			}
		}
	}

	@Test
	public void elementstBelongToList_put() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object e = getElementFrom(n);
			int oldSize = n.size();
			boolean result = n.add(e);
			assertTrue(n.contains(e));
			assertTrue(result);
			assertTrue(n.size() == oldSize + 1);
		}
	}

	@Test
	public void elementstBelongToList_putFirst() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object e = getElementFrom(n);
			int oldSize = n.size();
			boolean result = n.addFirst(e);
			assertTrue(n.contains(e));
			assertTrue(n.size() == oldSize + 1);
			assertTrue(result);
		}

	}

	@Test
	public void elementstBelongToList_putLast() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object e = getElementFrom(n);
			int oldSize = n.size();
			boolean result = n.addLast(e);
			assertTrue(n.contains(e));
			assertTrue(n.get(n.size()-1) == e);

			assertTrue(n.size() == oldSize + 1);
			assertTrue(result);
		}

	}

	@Test
	public void elementsDoesNotBelongToList_putFirst() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object e = getInt(-1000, 1000);
			if (!n.contains(e)) {
				int oldSize = n.size();
				n.addFirst(e);
				assertTrue(n.contains(e));
				assertTrue(n.size() == oldSize + 1);
			}
		}
	}

	@Test
	public void elementstBelongToList_remove() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int e = getInt(0, n.size() - 1);
			Object objToRemode = n.get(e);
			if (n.contains(objToRemode)) {
				int oldSize = n.size();
				boolean result = n.remove(objToRemode);
				assertTrue(oldSize - 1 == n.size());
				assertTrue(result);

			}
		}
	}

	@Test
	public void elementsDoesNotBelongToList_remove() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object e = getInt(-1000, 1000);
			if (!n.contains(e)) {
				int oldSize = n.size();
				boolean result = n.remove(e);
				assertFalse(n.contains(e));
				assertTrue(n.size() == oldSize);
				assertFalse(result);

			}
		}
	}

	@Test
	public void elementsDoesNotBelongToList_remove_index() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int e = getInt(0, n.size()-1);
			int oldSize = n.size();

			Object objToRemode = n.get(e);
			Object result = n.remove(e);
//			assertFalse(n.contains(objToRemode));
			assertTrue(n.size()  == oldSize -1);
			assertEquals(result,objToRemode);
		}
	}


	@Test
	public void elementstBelongToList_remove_index() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int e = getInt(0, n.size() - 1);
			Object objToRemode = n.get(e);
			if (n.contains(objToRemode)) {
				int oldSize = n.size();
				Object result = n.remove(e);
				assertTrue(oldSize - 1 == n.size());
				assertTrue(objToRemode == result);

			}
		}
	}

	@Test
	public void elementsBelongToList_remove_First() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object objToRemode = n.get(0);
			int oldSize = n.size();
			Object result = n.removeFirst();
			assertTrue(result == objToRemode);
			assertTrue(oldSize - 1 == n.size());
		}

	}

	@Test
	public void elementsDoesNotBelongToList_remove_First() {
		for (Object o : readObjects2()) {
			countTest();
			NodeCachingLinkedList l = (NodeCachingLinkedList) o;
			if (!l.repOK()) {
				continue;
			}
			if (l.isEmpty()) { //e<size or e>size
				assertThrows(NoSuchElementException.class, () -> {
					l.removeFirst();
				});
			}
		}
	}

	@Test
	public void elementsBelongToList_remove_last() {
			for (Object o: readObjects2()) {
				countTest();
				NodeCachingLinkedList n = (NodeCachingLinkedList) o;
				if (n.isEmpty()) continue;
				if (!n.repOK()) {
					continue;
				}
				Object objToRemode = n.get(n.size() - 1);
				int oldSize = n.size();
				Object result = n.removeLast();
				assertTrue(result == objToRemode);
				assertTrue(oldSize - 1 == n.size());
			}

	}

	@Test
	public void elementsDoesNotBelongToList_remove_last() {
			for (Object o: readObjects2()) {
				countTest();
				NodeCachingLinkedList l = (NodeCachingLinkedList) o;
				if (!l.repOK()) {
					continue;
				}
				if (l.isEmpty()) { //e<size or e>size
					assertThrows(NoSuchElementException.class, () -> {
						l.removeLast();
					});
				}
			}
	}

	@Test
	public void getNodeFromCache_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
//			if()
			if (!n.repOK()) {
				continue;
			}
			int oldSize = n.size();
			Object result = n.getNodeFromCache();
			assertTrue(!n.contains(result));
			assertTrue(n.size() == oldSize);
//			assertNull( ((AbstractLinkedList.Node) result).getValue());
		}


	}

	@Test
	public void createNode_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int oldSize = n.size();
			Object i = getInt(0, n.size() - 1);
			Object result = n.createNode(i);
			assertTrue(n.size() == oldSize);
		}
	}

	@Test
	public void clear_test() {
	for (Object o: readObjects2()) {
		countTest();
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) continue;
		if (!n.repOK()) {
			continue;
		}
		int oldSize = n.size();
		n.clear();
		assertTrue(n.size() == 0);
	}
	}

	@Test
	public void get_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int oldSize = n.size();
			int i = getInt(0, n.size() - 1);
			Object result = n.get(i);
			assertTrue(n.size() == oldSize);
			assertTrue(n.contains(result));
		}
	}

	@Test
	public void getMaximumCacheSize_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Integer b = n.getMaximumCacheSize();
		}
	}

	@Test
	public void ListIterator_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			org.apache.commons.collections4.ListIterator i = n.listIterator();
			org.apache.commons.collections4.ListIterator j = n.listIterator(0);
			assertTrue(i != null);
			assertTrue(j != null);
		}

	}

	@Test
	public void set_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object e = getElementFrom(n);
			int oldSize = n.size();
			int indexBef = n.indexOf(e);
			Object objBef = n.get(indexBef);
			Object result = n.set(indexBef, e);
			assertTrue(n.size() == oldSize);
			assertTrue(result == objBef);
			assertTrue(n.get(indexBef) == e);

			assertTrue(n.contains(e));
		}
	}

	@Test
	public void lastIndex_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object value = getElementFrom(n);
			Integer index = n.indexOf(value);
			assertTrue((n.contains(value)));
			assertTrue(index >= 0);
		}

	}

	@Test
	public void lastIndexInvalid_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object value = getInt(-1000, 1000);
			if (!n.contains(value)) {
				Integer index = n.lastIndexOf(value);
				assertTrue(index == -1);
			}
		}

	}




	@Test
	public void getFirst_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object b = n.getFirst();
			assertTrue(n.contains(b));
		}
	}

	@Test
	public void getFirstEmpty_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (!n.repOK()) {
				continue;
			}
			if (n.isEmpty()) {
				assertThrows(NoSuchElementException.class, () -> {
					n.getFirst();
				});
			}
		}
	}

	@Test
	public void getLast_test() {
		for (Object o : readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object b = n.getLast();
			assertTrue(n.contains(b));
		}
	}

	@Test
	public void getLasttEmpty_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (!n.repOK()) {
				continue;
			}
			if (n.isEmpty()) {
				assertThrows(NoSuchElementException.class, () -> {
					n.getLast();
				});
			}
		}
	}

	@Test
	public void constructorTest() {
		NodeCachingLinkedList n = new NodeCachingLinkedList(2);
		assertTrue(n.getMaximumCacheSize() == 2);
		assertTrue(n.header != null);
		NodeCachingLinkedList l = new NodeCachingLinkedList();
		assertTrue(l.getMaximumCacheSize() == 10);
	}

	@Test
	public void constructor2Test() {
		NodeCachingLinkedList n = new NodeCachingLinkedList(-2);
//		int oldMax = n.getMaximumCacheSize();
		assertTrue(n.getMaximumCacheSize() >= 0);
		NodeCachingLinkedList n1 = new NodeCachingLinkedList(0);
//		int oldMax = n.getMaximumCacheSize();
		assertTrue(n1.getMaximumCacheSize() == 0);
	}

	@Test
	public void addCollection_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int oldSize = n.size();
			Collection coll = (Collection) getCollection();
			if (coll.equals(n)) continue;
			boolean res = n.addAll(coll); //TODO: change for a good collection
			assertEquals(oldSize+coll.size(), n.size());
			assertTrue(res);

		}

	}

	@Test
	public void removeAll_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			n.removeAllNodes();
			assertTrue(n.size() == 0);
		}
	}

	@Test
	public void removeAllColl_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int oldSize = n.size();
			Collection coll = (Collection) getCollection();
			n.removeAll(coll); //TODO: change for a good collection
			assertTrue(n.size() <= oldSize);
		}
	}

	@Test
	public void retainAll_test() {
		for (Object o : readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int oldSize = n.size();
			Collection coll = (Collection) getCollection();
			n.retainAll(coll); //TODO: change for a good collection
			assertTrue(n.size() <= oldSize);
		}
	}

	@Test
	public void subList_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}

			List result = n.subList(0, n.size() - 1); //TODO: change for a good collection
			assertNotNull(result);
		}
	}

	@Test
	public void subListError_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (!n.repOK()) {
				continue;
			}
			if (n.isEmpty()) continue;
			if (n.isEmpty()) {
				assertThrows(IndexOutOfBoundsException.class, () -> {
					n.subList(-1,0);
				});
			}
		}
	}

	@Test
	public void subListErro2r_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (!n.repOK()) {
				continue;
			}
			if (n.isEmpty()) continue;
			if (n.isEmpty()) {
				assertThrows(IndexOutOfBoundsException.class, () -> {
					n.subList(0,n.size()+1);
				});
			}
		}
	}

	@Test
	public void subListError3_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (!n.repOK()) {
				continue;
			}
			if (n.isEmpty()) continue;
			if (n.isEmpty()) {
				assertThrows(IllegalArgumentException.class, () -> {
					n.subList(0,-1);
				});
			}
		}
	}

	@Test
	public void containsAll_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}

			boolean result = n.containsAll(new LinkedList()); //TODO: change for a good collection
			assertTrue(!result); //CHAnge
		}
	}

	@Test
	public void setMaximiumCache_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			int max = getInt(1, 100);
			n.setMaximumCacheSize(max);
			assertTrue(n.getMaximumCacheSize() == max);
		}
	}

	@Test
	public void setMaximiumCacheMin_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
//			int max = getInt(1, 100);
			int oldMax = n.getMaximumCacheSize();
			n.setMaximumCacheSize(-1);
			assertTrue(n.getMaximumCacheSize() == oldMax);
		}
	}

	@Test
	public void toString_test() {
		for (Object o : readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			n.toString();
		}
	}

	@Test
	public void iterator_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			n.iterator();
		}
	}

	@Test
	public void listIterator_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			n.listIterator();
			n.listIterator(n.size() - 1);
		}
	}

	@Test
	public void listIteratorExcpetion_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			assertThrows(IndexOutOfBoundsException.class, () -> {
				n.listIterator(100);
			});
		}
	}

	@Test
	public void lastIndexOf_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object e = getElementFrom(n);
			int index = n.lastIndexOf(e);
			assertTrue(index >= 0);
		}
	}

	@Test
	public void toArray_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Object[] array = n.toArray();
			assertTrue(array.length == n.size());
		}
	}

	@Test
	public void toArray2_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isEmpty()) continue;
			if (!n.repOK()) {
				continue;
			}
			Collection e = (Collection) getCollection();
//			Object[] array = e.toArray();
			Object[] array1 = n.toArray(e.toArray());
			assertTrue(array1.length >= e.size());
		}
	}

//	@Test
//	public void isCacheFull_test() {
//		for (Object o: readObjects2()) {
//			countTest();
//			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
//			if (n.isEmpty()) continue;
//			if (!n.repOK()) {
//				continue;
//			}
//			n.add(1);
//			n.remove(0);
//			boolean b = n.isCacheFull();
//			assertFalse(b);
//		}
//	}

//	@Test
//	public void isCacheFull_test_full() {
//		for (Object o: readObjects2()) {
//			countTest();
//			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
//			if (n.isEmpty()) continue;
//			if (!n.repOK()) {
//				continue;
//			}
//			n.add(1);
//			n.remove(0);
//			boolean b = n.isCacheFull();
//			assertTrue(b);
//		}
//	}

//	Agregue una linea al metodo sino tiene error
	@Test
	public void setMaximumCacheSizeNegative_test() {
		NodeCachingLinkedList n = new NodeCachingLinkedList(1);
		n.setMaximumCacheSize(-1);
		assertTrue(n.getMaximumCacheSize()>0);
	}

	@Test
	public void addNode_createNode_test() {
		NodeCachingLinkedList n = new NodeCachingLinkedList();
		org.apache.commons.collections4.list.AbstractLinkedList.Node node = n.createNode(-1);
		assertTrue(node.getValue().equals(new Integer(-1)));
	}

	@Test
	public void addCacheFull() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (!n.isCacheFull()) continue;
			if (n.isEmpty()) continue;

			if (!n.repOK()) {
				continue;
			}
			n.remove(0);
			assertTrue(n.isCacheFull());
		}
	}

	@Test
	public void add1CacheFull() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isCacheFull()) continue;
			if (n.isEmpty()) continue;

			if (!n.repOK()) {
				continue;
			}
			n.remove(0);
			assertTrue(n.getNodeFromCache().value == null);

//			assertTrue(n.size);

		}
	}

	@Test
	public void isCacheFull_test() {
		for (Object o: readObjects2()) {
			countTest();
			NodeCachingLinkedList n = (NodeCachingLinkedList) o;
			if (n.isCacheFull()) continue;
			if (n.isEmpty()) continue;

			if (!n.repOK()) {
				continue;
			}
			if (n.getMaximumCacheSize() != 0) continue;
			boolean b = n.isCacheFull();
			assertTrue(b);
		}
	}
//

}

//}
