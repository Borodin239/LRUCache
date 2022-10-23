class LRULinkedList(private val capacity: Int) {
    /**
     * Link to the first node of the list.
     */
    private var head: Node?

    /**
     * Link to the last node of the list. Required for fast removal
     * of the last node.
     */
    private var tail: Node?

    /**
     * Current list size.
     */
    private var size: Int

    init {
        head = null
        tail = null
        size = 0
    }

    private fun isEmpty(): Boolean = size == 0
    private fun isFull(): Boolean = size == capacity

    fun add(value: Int, key: Int): Pair<Node, Int?> {
        val newNode = Node(value, key)
        var removed: Int? = null

        if (isFull()) {
            removed = removeLast()
            assert(!isFull())
        }
        if (isEmpty()) {
            assert(head == null)
            assert(tail == null)
            head = newNode
            tail = newNode
        } else {
            head!!.prev = newNode
            newNode.next = head
            head = newNode
        }
        size++
        assert(capacity >= size)
        return Pair(newNode, removed)
    }

    private fun removeLast(): Int {
        assert(tail != null)
        val result = tail!!.key

        if (head == tail) {
            assert(size == 1)
            tail = null
            head = null
        } else {
            assert(tail!!.prev != null)
            val tmp = tail!!.prev
            tail!!.prev = null
            tail = tmp
            tail!!.next = null
        }

        size--
        assert(size >= 0)
        return result
    }

    /**
     * Move node to the top of the list
     */
    fun moveForvard(node: Node) {
        if (node == head) return

        assert(node.prev != null)
        node.prev!!.next = node.next

        if (tail == node) {
            tail = node.prev
        } else {
            assert(node.next != null)
            node.next!!.prev = node.prev
        }

        assert(head != null)
        head!!.prev = node
        node.prev = null
        node.next = head
        head = node
    }
}