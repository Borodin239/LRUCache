import org.junit.Test
import kotlin.test.assertEquals

class LRUCacheTest {

    @Test(expected = IllegalArgumentException::class)
    fun `Incorrect initialization`() {
        // data
        val illegalCapacity = -1

        // action
        LRUCache(illegalCapacity)
    }

    @Test
    fun `Simple test 1`() {
        // data
        val initialCapacity = 1
        val cache = LRUCache(initialCapacity)

        // action && assert
        cache.put(2, 1)
        assertEquals(actual = cache.get(2), expected = 1)
        cache.put(3, 2)
        assertEquals(actual = cache.get(2), expected = null)
        assertEquals(actual = cache.get(3), expected = 2)
    }

    @Test
    fun `Simple test 2`() {
        // data
        val initialCapacity = 2
        val cache = LRUCache(initialCapacity)

        // action && assert
        cache.put(1, 1)
        cache.put(2, 2)
        assertEquals(actual = cache.get(1), expected = 1)
        cache.put(3, 3)
        assertEquals(actual = cache.get(2), expected = null)
        cache.put(4, 4)
        assertEquals(actual = cache.get(1), expected = null)
        assertEquals(actual = cache.get(3), expected = 3)
        assertEquals(actual = cache.get(4), expected = 4)
    }

    @Test
    fun `Replace value`() {
        // data
        val initialCapacity = 2
        val cache = LRUCache(initialCapacity)

        // action && assert
        assertEquals(actual = cache.get(2), expected = null)
        cache.put(2, 6)
        assertEquals(actual = cache.get(1), expected = null)
        cache.put(1, 5)
        cache.put(1, 2)
        assertEquals(actual = cache.get(1), expected = 2)
        assertEquals(actual = cache.get(2), expected = 6)
    }

    @Test
    fun `Random test`() {
        // data
        val initialCapacity = 4
        val cache = LRUCache(initialCapacity)

        // action && assert
        cache.put(2, 6)
        cache.put(9, 54)
        cache.put(4, 6)
        cache.put(12, -41)
        cache.put(-18, Int.MAX_VALUE)
        assertEquals(actual = cache.get(4), expected = 6)
        assertEquals(actual = cache.get(2), expected = null)
        assertEquals(actual = cache.get(9), expected = 54)
        assertEquals(actual = cache.get(-18), expected = Int.MAX_VALUE)
        cache.put(Int.MIN_VALUE, 239)
        cache.put(-18, 366)
        assertEquals(actual = cache.get(12), expected = null)
        assertEquals(actual = cache.get(Int.MIN_VALUE), expected = 239)
        assertEquals(actual = cache.get(-18), expected = 366)
    }
}