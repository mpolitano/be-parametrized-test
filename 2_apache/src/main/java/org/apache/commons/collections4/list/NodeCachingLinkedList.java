/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


/**
 * A <code>List</code> implementation that stores a cache of internal Node objects
 * in an effort to reduce wasteful object creation.
 * <p>
 * A linked list creates one Node for each item of data added. This can result in
 * a lot of object creation and garbage collection. This implementation seeks to
 * avoid that by maintaining a store of cached nodes.
 * <p>
 * This implementation is suitable for long-lived lists where both add and remove
 * are used. Short-lived lists, or lists which only grow will have worse performance
 * using this class.
 * <p>
 * <b>Note that this implementation is not synchronized.</b>
 *
 * @since 3.0
 * @version $Id: NodeCachingLinkedList.java 1533984 2013-10-20 21:12:51Z tn $
 */
public class NodeCachingLinkedList extends AbstractLinkedList  implements Serializable {

	/** Serialization version */
	private static final long serialVersionUID = 1L;

	/**
	 * The default value for {@link #maximumCacheSize}.
	 */
	private static final int DEFAULT_MAXIMUM_CACHE_SIZE = 10;

	/**
	 * The first cached node, or <code>null</code> if no nodes are cached.
	 * Cached nodes are stored in a singly-linked list with
	 * <code>next</code> pointing to the next element.
	 */
	private  Node firstCachedNode;

	/**
	 * The size of the cache.
	 */
	private  int cacheSize;

	/**
	 * The maximum size of the cache.
	 */
	private int maximumCacheSize;

	//-----------------------------------------------------------------------
	/**
	 * Constructor that creates.
	 */
	public NodeCachingLinkedList() {
		this(DEFAULT_MAXIMUM_CACHE_SIZE);
	}

	/**
	 * Constructor that copies the specified collection
	 *
	 * @param coll  the collection to copy
	 */
//    public NodeCachingLinkedList(final java.util.Collection<Integer>  coll) {
//        super(coll);
//        this.maximumCacheSize = DEFAULT_MAXIMUM_CACHE_SIZE;
//    }

	/**
	 * Constructor that species the maximum cache size.
	 *
	 * @param maximumCacheSize  the maximum cache size
	 */
	public NodeCachingLinkedList(final int maximumCacheSize) {
		super();
		if(maximumCacheSize >= 0)
			this.maximumCacheSize = maximumCacheSize;
		init();  // must call init() as use super();
	}

	//-----------------------------------------------------------------------
	/**
	 * Gets the maximum size of the cache.
	 *
	 * @return the maximum cache size
	 */
	public int getMaximumCacheSize() {
		return maximumCacheSize;
	}

	/**
	 * Sets the maximum size of the cache.
	 *
	 * @param maximumCacheSize  the new maximum cache size
	 */
	public void setMaximumCacheSize(final int maximumCacheSize) {
		if(maximumCacheSize > 0){ //Agregada.
			this.maximumCacheSize = maximumCacheSize;
			shrinkCacheToMaximumSize();
		}

	}

	/**
	 * Reduce the size of the cache to the maximum, if necessary.
	 */
	public void shrinkCacheToMaximumSize() {
		// Rich Dougherty: This could be more efficient.
		while (cacheSize > maximumCacheSize) {
			getNodeFromCache();
		}
	}

	/**
	 * Gets a node from the cache. If a node is returned, then the value of
	 * {@link #cacheSize} is decreased accordingly. The node that is returned
	 * will have <code>null</code> values for next, previous and element.
	 *
	 * @return a node, or <code>null</code> if there are no nodes in the cache.
	 */
	public Node getNodeFromCache() {
		if (cacheSize == 0) {
			return null;
		}
		final Node cachedNode = firstCachedNode;
		firstCachedNode = cachedNode.next;
		cachedNode.next = null; // This should be changed anyway, but defensively
		// set it to null.
		cacheSize--;
		return cachedNode;
	}

	/**
	 * Checks whether the cache is full.
	 *
	 * @return true if the cache is full
	 */
	public boolean isCacheFull() {
		return cacheSize >= maximumCacheSize;
	}

	/**
	 * Adds a node to the cache, if the cache isn't full.
	 * The node's contents are cleared to so they can be garbage collected.
	 *
	 * @param node  the node to add to the cache
	 */
	private void addNodeToCache(final Node node, Object value) {
		if (isCacheFull()) {
			// don't cache the node.
			return;
		}
		// clear the node's contents and add it to the cache.
		final Node nextCachedNode = firstCachedNode;
		node.previous = null;
		node.next = nextCachedNode;
		 node.setValue(null);
//		node.setValue(value);
		firstCachedNode = node;
		cacheSize++;
	}

	//-----------------------------------------------------------------------
	/**
	 * Creates a new node, either by reusing one from the cache or creating
	 * a new one.
	 *
	 * @param value  value of the new node
	 * @return the newly created node
	 */
	@Override
	public Node createNode(final Object value) {
		final Node cachedNode = getNodeFromCache();
		if (cachedNode == null) {
			return super.createNode(value);
		}
		cachedNode.setValue(value);
		return cachedNode;
	}

	/**
	 * Removes the node from the list, storing it in the cache for reuse
	 * if the cache is not yet full.
	 *
	 * @param node  the node to remove
	 */
	@Override
	public void removeNode(final Node node) {
		super.removeNode(node);
		addNodeToCache(node, node.getValue());

	}

	/**
	 * Removes all the nodes from the list, storing as many as required in the
	 * cache for reuse.
	 *
	 */
	@Override
	public void removeAllNodes() {
		// Add the removed nodes to the cache, then remove the rest.
		// We can add them to the cache before removing them, since
		// {@link AbstractLinkedList.removeAllNodes()} removes the
		// nodes by removing references directly from {@link #header}.
		final int numberOfNodesToCache = Math.min(size, maximumCacheSize - cacheSize);
		Node node = header.next;
		for (int currentIndex = 0; currentIndex < numberOfNodesToCache; currentIndex++) {
			final Node oldNode = node;
			Object value = node.getValue();
			node = node.next;
			addNodeToCache(oldNode, value);
		}
		super.removeAllNodes();
	}

	//-----------------------------------------------------------------------
	/**
	 * Serializes the data held in this object to the stream specified.
	 */
	private void writeObject(final ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		doWriteObject(out);
	}

	/**
	 * Deserializes the data held in this object to the stream specified.
	 */
	private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		doReadObject(in);
	}


	/*    @Override
		public String toString() {
			final StringBuilder buf = new StringBuilder();
			Node node = header.next;
			buf.append('[');
			int i = 0;
			while (i < super.size()) {
				buf.append(node.getValue());
				node = node.next;
				i++;
				if (i < super.size())
					buf.append(",");
			}
			buf.append("]");

			buf.append("-");

			Node nodeC = firstCachedNode;
			buf.append('[');
			i=0;
			while (i < cacheSize) {
				buf.append(nodeC.getValue());
				nodeC = nodeC.next;
				i++;
				if (i < cacheSize)
					buf.append(",");
			}
			buf.append("]");
			buf.append("-" +this.cacheSize);
			buf.append("-" +maximumCacheSize);
			buf.append("-" +modCount);

			return buf.toString();
	//        return super.toString();
		}
		*/
	public boolean repOK(){

		if (this.header == null)
			return false;

		if (this.header.next == null)
			return false;

		if (this.header.previous == null)
			return false;

		if (this.cacheSize > this.maximumCacheSize)
			return false;

		//if (this.DEFAULT_MAXIMUM_CACHE_SIZE != 20)
		//  return false;

		if (this.size < 0)
			return false;

		int cyclicSize = 0;

		Node n = this.header;
		do
		{
			cyclicSize++;

			if (n.previous == null)
				return false;

			if (n.previous.next != n)
				return false;

			if (n.next == null)
				return false;

			if (n.next.previous != n)
				return false;


			if (n != null) {
				n = n.next;
			}
		} while (n != this.header && n != null);

		if (n == null)
			return false;

		if (this.size != cyclicSize - 1)
			return false;

		int acyclicSize = 0;
		Node m = this.firstCachedNode;

		java.util.Set<Node> visited = new java.util.HashSet<Node>();
		visited.add(this.firstCachedNode);

		while (m != null)
		{
			acyclicSize++;

			if (m.previous != null)
				return false;

			 if (m.value != null)
			 return false;

			m = m.next;

			if (!visited.add(m))
				return false;

		}

		if (this.cacheSize != acyclicSize) {
			return false;
		}

		return true;
	}

}

