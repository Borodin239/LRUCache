class LRUCache(capacity: Int) {
    private val queue: LRULinkedList
    private val map: HashMap<Int, Node>

    init {
        if (capacity < 1) throw IllegalArgumentException("Capacity ($capacity) less than 1 doesn't make sense")

        queue = LRULinkedList(capacity)
        map = HashMap()
    }

    private fun getNode(key: Int): Node? {
        val curNode = map[key] ?: return null
        queue.moveForvard(curNode)
        return curNode
    }

    fun get(key: Int): Int? {
        return getNode(key)?.value
    }

    fun put(key: Int, value: Int) {
        val curNode = getNode(key)
        if (curNode != null) {
            curNode.value = value
            return
        }
        val (node, removed) = queue.add(value, key)
        map.remove(removed)
        map[key] = node
    }
}