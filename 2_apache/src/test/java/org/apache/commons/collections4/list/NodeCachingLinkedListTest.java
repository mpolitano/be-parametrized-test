package org.apache.commons.collections4.list;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.rules.ExpectedException;
import utils.TestHarness;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class NodeCachingLinkedListTest extends TestHarness {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList_put(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		Object e = getInt(-1000,1000);
		if (!n.contains(e)) {
			int oldSize = n.size();
			boolean result=n.add(e);
			assertTrue(n.contains(e));
			assertTrue(result);
			assertTrue(n.size()== oldSize +1);
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList_put_parameters(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		Object e = getInt(-1000,1000);
		Object f= getElementFrom(n);
		if (!n.contains(e)) {
			int oldSize = n.size();
			int index = n.indexOf(f);
			n.add(index, e);
			assertTrue(n.size() == oldSize+1);
			assertTrue(n.contains(e));
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementstBelongToList_put(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		Object e = getElementFrom(n);
			int oldSize = n.size();
			boolean result=n.add(e);
			assertTrue(n.contains(e));
			assertTrue(result);
			assertTrue(n.size()== oldSize +1);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementstBelongToList_putFirst(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		Object e = getElementFrom(n);
		int oldSize = n.size();
		boolean result = n.addFirst(e);
		assertTrue(n.contains(e));
		assertTrue(n.size()== oldSize +1);
		assertTrue(result);

	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementstBelongToList_putLast(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		Object e = getElementFrom(n);
		int oldSize = n.size();
		boolean result = n.addLast(e);
		assertTrue(n.contains(e));
		assertTrue(n.size()== oldSize +1);
		assertTrue(result);

	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList_putFirst(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		Object e = getInt(-1000,1000);
		if (!n.contains(e)) {
			int oldSize = n.size();
			n.addFirst(e);
			assertTrue(n.contains(e));
			assertTrue(n.size()== oldSize +1);
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementstBelongToList_remove(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		int e = getInt(0, n.size()-1);
		Object objToRemode = n.get(e);
		if (n.contains(objToRemode)) {
			int oldSize = n.size();
			boolean result = n.remove(objToRemode);
			assertTrue(oldSize - 1 == n.size());
			assertTrue(result);

		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList_remove(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object e = getInt(-1000, 1000);
		if (!n.contains(e)) {
			int oldSize = n.size();
			boolean result=n.remove(e);
			assertFalse(n.contains(e));
			assertTrue(n.size() == oldSize);
			assertFalse(result);

		}
	}




	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementstBelongToList_remove_index(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		int e = getInt(0, n.size()-1);
		Object objToRemode = n.get(e);
		if (n.contains(objToRemode)) {
			int oldSize = n.size();
			Object result = n.remove(e);
			assertTrue(oldSize - 1 == n.size());
			assertTrue(objToRemode == result);

		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsBelongToList_remove_First(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object objToRemode = n.get(0);
		int oldSize = n.size();
		Object result =n.removeFirst();
		assertTrue(result == objToRemode);
		assertTrue(oldSize-1 == n.size());

	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList_remove_First(Object o) {
		NodeCachingLinkedList l = (NodeCachingLinkedList) o;
		if (l.isEmpty()) { //e<size or e>size
			assertThrows(NoSuchElementException.class, () -> {
				l.removeFirst();
			});
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsBelongToList_remove_last(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object objToRemode = n.get(n.size()-1);
		int oldSize = n.size();
		Object result =n.removeLast();
		assertTrue(result == objToRemode);
		assertTrue(oldSize-1 == n.size());

	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void elementsDoesNotBelongToList_remove_last(Object o) {
		NodeCachingLinkedList l = (NodeCachingLinkedList) o;
		if (l.isEmpty()) { //e<size or e>size
			assertThrows(NoSuchElementException.class, () -> {
				l.removeLast();
			});
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void getNodeFromCache_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		int oldSize = n.size();
		Object result = n.getNodeFromCache();
		assertTrue(!n.contains(result));
		assertTrue(n.size() == oldSize);
		assertNull(result);


	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void createNode_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if(n.isEmpty()) return;
		int oldSize = n.size();
		Object i = getInt(0,n.size()-1);
		Object result = n.createNode(i);
		assertTrue(n.size() == oldSize);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
		public void clear_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		int oldSize = n.size();
		n.clear();
		assertTrue(n.size() == 0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void get_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		int oldSize = n.size();
		int i = getInt(0,n.size()-1);
		Object result = n.get(i);
		assertTrue(n.size() == oldSize);
		assertTrue(n.contains(result));
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void getMaximumCacheSize_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		Integer b = n.getMaximumCacheSize();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
		public void ListIterator_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		org.apache.commons.collections4.ListIterator i = n.listIterator();
		org.apache.commons.collections4.ListIterator j = n.listIterator(0);
		assertTrue(i != null);
		assertTrue(j != null);

	}

	@ParameterizedTest
	@MethodSource("readObjects")
		public void set_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object e = getElementFrom(n);
		int oldSize = n.size();
		int indexBef = n.indexOf(e);
		Object objBef = n.get(indexBef);
		Object result=n.set(indexBef, e);
		assertTrue(n.size() == oldSize);
		assertTrue(result == objBef);
		assertTrue(n.get(indexBef) == e);

		assertTrue(n.contains(e));
	}


	@ParameterizedTest
	@MethodSource("readObjects")
	public void lastIndex_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object value = getElementFrom(n);
		Integer index = n.indexOf(value);
		assertTrue((n.contains(value)));
		assertTrue(index>=0);

	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void lastIndexInvalid_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object value = getInt(-1000,1000);
		if(!n.contains(value)) {
			Integer index = n.indexOf(value);
			assertTrue(index==-1);
		}

	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void getFirst_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object b = n.getFirst();
		assertTrue(n.contains(b));
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void getFirstEmpty_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) {
			assertThrows(NoSuchElementException.class, () -> {
				n.getFirst();
			});
		}
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void getLast_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object b = n.getLast();
		assertTrue(n.contains(b));
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void getLasttEmpty_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) {
			assertThrows(NoSuchElementException.class, () -> {
				n.getLast();
			});
		}
	}

	@Test
	public void constructorTest() {
		NodeCachingLinkedList n = new NodeCachingLinkedList(2);
		assertTrue(n.getMaximumCacheSize() == 2);
		NodeCachingLinkedList l = new NodeCachingLinkedList();
		assertTrue(l.getMaximumCacheSize() == 10);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void addCollection_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		int oldSize = n.size();
		boolean res = n.addAll(new LinkedList()); //TODO: change for a good collection
		assertEquals(oldSize, n.size());

	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void removeAll_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		n.removeAllNodes();
		assertTrue(n.size()==0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void removeAllColl_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		int oldSize = n.size();
		n.removeAll(new LinkedList()); //TODO: change for a good collection
		assertTrue(n.size()==oldSize);
	}

//	@ParameterizedTest
//	@MethodSource("readObjects")
//	public void retainAll_test(Object o) {
////		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
//		if (n.isEmpty()) return;
//		int oldSize = n.size();
//		n.retainAll(new LinkedList()); //TODO: change for a good collection
//		assertTrue(n.size()==oldSize);
//	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void subList_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;

		n.subList(0, n.size()-1); //TODO: change for a good collection
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void containsAll_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;

		boolean result = n.containsAll(new LinkedList()); //TODO: change for a good collection
		assertTrue(result);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void setMaximiumCache_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		int max = getInt(1,100);
		n.setMaximumCacheSize(max);
		assertTrue(n.getMaximumCacheSize() == max);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void toString_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		n.toString();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void iterator_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		n.iterator();
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void listIterator_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		n.listIterator();
		n.listIterator(n.size()-1);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void listIteratorExcpetion_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		assertThrows(IndexOutOfBoundsException.class, () -> {
			n.listIterator(100);
		});
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void lastIndexOf_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		if (n.isEmpty()) return;
		Object e = getElementFrom(n);
		int index = n.lastIndexOf(e);
		assertTrue(index >=0);
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void toArray_test(Object o) {
		NodeCachingLinkedList n = (NodeCachingLinkedList) o;
		Object[] array = n.toArray();
		Object[] array1 = n.toArray(array);

		assertTrue(array1.length == n.size());
	}

	@ParameterizedTest
	@MethodSource("readObjects")
	public void isCacheFull_test(Object o) {
		NodeCachingLinkedList n = new NodeCachingLinkedList();
		n.add(1);
		n.remove(0);
		boolean b = n.isCacheFull();
		assertFalse(b);
	}

	@Test
	public void isCacheFull_test_full() {
		NodeCachingLinkedList n = new NodeCachingLinkedList(1);
		n.add(1);
		n.remove(0);
		boolean b = n.isCacheFull();
		assertTrue(b);
	}

	@Test
	public void addNode_cachoeFull_test() {
		NodeCachingLinkedList n = new NodeCachingLinkedList(1);
		n.setMaximumCacheSize(-1);
		n.add(0);
	}

	@Test
	public void addNode_createNode_test() {
		NodeCachingLinkedList n = new NodeCachingLinkedList();
		org.apache.commons.collections4.list.AbstractLinkedList.Node node = n.createNode(-1);
		assertTrue(node.getValue().equals(new Integer(-1)));
	}

//
//	//org.apache.commons.collections4.list.NodeCachingLinkedList.isCacheFull()
//	@Test
//	public void isCacheFull_test() {
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			objIterator.addCountTest();
//
//			boolean b = ncl.isCacheFull();
//			assertTrue(ncl.repOK());
//
//
//		}
//	}
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.lastIndexOf(java.lang.Integer)
//	@Test
//	public void lastIndexOf_test() {
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			Random r = new Random();
//			int value = r.nextInt(literals);
//
//			objIterator.addCountTest();
//			int index = ncl.lastIndexOf(value);
//			assertTrue(ncl.repOK());
//			assertTrue((ncl.contains(value) && index >=0) ||  (!ncl.contains(value) && index ==-1) );
//
//			}
//	}
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.remove(int)
//	@Test
//	public void remove_index_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			Random r = new Random();
//			int index = r.nextInt(literals);
//			objIterator.addCountTest();
//			if(index > 0 && index < ncl.size()) {
//				int oldSize = ncl.size();
//				Object b = ncl.remove(index);
//				assertTrue(ncl.repOK());
//				assertTrue(ncl.size() == oldSize-1);
//
//			}
//
//		}
//	}
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.remove(java.lang.Integer)
//
//	@Test
//	public void remove_value_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			Random r = new Random();
//			int value = r.nextInt(literals);
//			objIterator.addCountTest();
//			int oldSize = ncl.size();
//			boolean b = ncl.remove(new Integer(value));
//			assertTrue(ncl.repOK());
//			assertTrue((b && ncl.size() == oldSize-1) || (!b && ncl.size() == oldSize));
//
//		}
//	}
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.removeFirst()
//	@Test
//	public void removeFirst_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			Random r = new Random();
//			int value = r.nextInt(literals);
//
//			objIterator.addCountTest();
//
//			if(ncl.size() > 0) {
//				int oldSize = ncl.size();
//				Object b = ncl.removeFirst();
//				assertTrue(ncl.repOK());
//				assertTrue(ncl.size() == oldSize-1);
//			}
//
//		}
//	}
//
//
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.removeLast()
//
//	@Test
//	public void removeLast_test() {
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//
//			objIterator.addCountTest();
//
//			if(ncl.size() > 0) {
//				int oldSize = ncl.size();
//				Object b = ncl.removeLast();
//				assertTrue(ncl.repOK());
//				assertTrue(ncl.size() == oldSize-1);
//			}
//
//
//		}
//	}
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.add(int,java.lang.Integer)
//	@Test
//	public void add_on_position_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			Random r = new Random();
//			int value = r.nextInt(literals);
//
//			int index = r.nextInt(literals);
//			objIterator.addCountTest();
//
//			if(index >0 && index <= ncl.size()) {
//				int oldSize = ncl.size();
//				ncl.add(index, value);
//				assertTrue(ncl.repOK());
//				assertTrue(ncl.size() == oldSize+1);
//			}
//
//		}
//	}
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.set(int,java.lang.Integer)
//	@Test
//	public void set_test() {
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			Random r = new Random();
//			int value = r.nextInt(literals);
//
//			int index = r.nextInt(literals);
//
//			objIterator.addCountTest();
//
//			if(index >0 && index < ncl.size()) {
//				int oldSize = ncl.size();
//				ncl.set(index, value);
//				assertTrue(ncl.repOK());
//				assertTrue(ncl.size() == oldSize);
//				assertTrue(ncl.contains(value));
//			}
//
//		}
//	}
//
//	//org.apache.commons.collections4.list.NodeCachingLinkedList.removeAllNodes()
//	@Test
//	public void removeAllNodes_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			objIterator.addCountTest();
//			ncl.removeAllNodes();
//			assertTrue(ncl.repOK());
//			assertTrue(ncl.isEmpty());
//
//		}
//	}
//
//
//
//	//org.apache.commons.collections4.list.NodeCachingLinkedList.setMaximumCacheSize(int)
//	@Test
//	public void setMaximumCacheSize_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			Random r = new Random();
//			int value = r.nextInt(literals);
//
//			objIterator.addCountTest();
//
//			ncl.setMaximumCacheSize(value);
//			assertTrue(ncl.repOK());
//
//		}
//	}
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.toArray()
//
//	@Test
//	public void toArray_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			objIterator.addCountTest();
//			int size = ncl.size();
//			Object [] a = ncl.toArray();
//			assertTrue(ncl.repOK());
//			assertTrue(a.length == size);
//
//		}
//	}
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.toArray([Ljava.lang.Integer;)
//
//	@Test
//	public void toArray2_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			objIterator.addCountTest();
//			int size = ncl.size();
//			Object [] a = new Object[ncl.size()];
//			a = ncl.toArray(a);
//			assertTrue(ncl.repOK());
//			assertTrue(a.length == size);
//
//		}
//	}
//
//	//org.apache.commons.collections4.list.NodeCachingLinkedList.shrinkCacheToMaximumSize()
//	@Test
//	public void shrinkCacheToMaximumSize_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			objIterator.addCountTest();
//			ncl.shrinkCacheToMaximumSize();
//			assertTrue(ncl.repOK());
//		}
//
//	}
//
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.iterator()
//	@Test
//	public void Iterator_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//
//			objIterator.addCountTest();
//			java.util.Iterator i = ncl.iterator();
//			assertTrue(ncl.repOK());
//
//		}
//	}
//
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.listIterator()
//	@Test
//	public void ListIterator_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			objIterator.addCountTest();
//			org.apache.commons.collections4.ListIterator i = ncl.listIterator();
//			assertTrue(ncl.repOK());
//			assertTrue(i != null);
//
//
//		}
//	}
//
//
//
//	//org.apache.commons.collections4.list.AbstractLinkedList.listIterator(int)
//	@Test
//	public void ListIterator_Int_test() {
//
//		List<Object> nclList = objIterator.getObjects();
//		ListIterator it = nclList.listIterator();
//		while(it.hasNext()) {
//			NodeCachingLinkedList ncl = (NodeCachingLinkedList)it.next();
//			Random r = new Random();
//			int i = r.nextInt(literals);
//
//			objIterator.addCountTest();
//			if(i<ncl.size()) {
//				org.apache.commons.collections4.ListIterator iter = ncl.listIterator(i);
//				assertTrue(ncl.repOK());
//				assertTrue(iter != null);
//
//			}
//		}
//	}


}
