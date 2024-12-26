import java.util.Arrays;
/**
 *
 * @param <K>       the data type for keys in the map.
 * @param <V>       the data type for values in the map.
 */
public class TreeMap<K extends Comparable<K>, V> implements TreeMapInterface<K, V> {
    /**
     * Root of the binary search tree.
     */
    private TreeMapNode<K, V> root;
    /**
     * The number of key-value mappings in this map.
     */

    private int size;
    /**
     * Initializes an empty TreeMap.
     */
    public TreeMap() {
        root = null;
        size = 0;
    }
    /**
     * Retrieves the number of key/value pair elements managed by the map
     * @return      number of elements
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Clears the existing tree, removing any and all existing key/value pairs.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    /**
     * Retrieves the corresponding value for the specified key.
     * @param key       key of interest.
     * @return          value corresponding to the specified key, or null if the key is not found.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException( "Key must not be null" );
        }
        TreeMapNode<K, V> targetNode = findNode( key, root);
        if (targetNode != null) {
            return targetNode.value;
        }
        return null;
    }
    private TreeMapNode<K, V> findNode(K key, TreeMapNode<K, V> node) {
        if (node == null) {
            return null;
        } else if (key.compareTo( node.key ) == 0) {
            return node;
        } else if (key.compareTo( node.key ) < 0) {
            return findNode( key, node.left );
        } else {
            return findNode( key, node.right );
        }
    }
    /**
     * Adds a key/value pair to the tree map.
     * @param key       the key. If not already in the tree, the key/value pair is added.  If already in the
     *                  tree, the existing value is replaced with the one specified here.
     * @param value     the value in the key/value pair.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException( "key must not be null" );
        }
        root = putNode( key, value, root );
    }
    private TreeMapNode<K, V> putNode(K key, V value, TreeMapNode<K, V> node) {
        if (node == null) {
            TreeMapNode<K, V> targetNode = new TreeMapNode<K, V>( key, value );
            node = targetNode;
            size++;
        } else if (key.compareTo( node.key ) <= 0) {
            node.left = putNode( key, value, node.left );
        } else {
            node.right = putNode( key, value, node.right );
        }
        return node;
    }
    /**
     * Checks the tree to see if it contains the specified key.
     * @param key       the key to search for.
     * @return          true, if the key is in the tree map; false, if not.
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        return keyExistsInTree(key, root);
    }
    private boolean keyExistsInTree(K key, TreeMapNode<K, V> node) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return keyExistsInTree(key, node.left);
        } else {
            return keyExistsInTree(key, node.right);
        }
    }
    /**
     * Retrieves an array of key data from the map, in order
     * @param array to fill in.  If smaller than the map's size, a new array will be created.  If larger than the
     *              map's size, data will be filled in from index 0, with a null reference just after the copied-in data.
     *              This parameter must not be null.
     * @return      a reference to the filled-in array; may be a different array than the one passed in.
     */
    @Override
    public K[] toKeyArray(K[] array) {
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        }
        fillKeyArray(root, array, 0);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }
    private int fillKeyArray(TreeMapNode<K, V> root, K[] array, int index) {
        if (root != null) {
            index = fillKeyArray(root.left, array, index);
            array[index] = root.key;
            index++;
            index = fillKeyArray(root.right, array, index);
        }
        return index;
    }
    /**
     * Retrieves an array of value data from the map, in key order
     * @param array to fill in.  If smaller than the map's size, a new array will be created.  If larger than the
     *              map's size, data will be filled in from index 0, with a null reference just after the copied-in data.
     *              This parameter must not be null.
     * @return      a reference to the filled-in array; may be a different array than the one passed in.
     */
    @Override
    public V[] toValueArray(V[] array) {
        if (array.length < size) {
            array = Arrays.copyOf(array, size);
        }
        fillValueArray(root, array, 0);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }
    private int fillValueArray(TreeMapNode<K, V> root, V[] array, int index) {
        if (root != null) {
            index = fillValueArray(root.left, array, index);
            array[index] = root.value;
            index++;
            index = fillValueArray(root.right, array, index);
        }
        return index;
    }
    /**
     * A static inner class that defines the nodes of the binary search tree.
     * Each node has a key, a value, and references to its left and right children.
     *
     * @param <K> the type of the key
     * @param <V> the type of the value
     */
    private static class TreeMapNode<K, V> {
        public K key;
        public V value;
        public TreeMapNode<K, V> left;
        public TreeMapNode<K, V> right;
        public TreeMapNode(K key, V value) {
            this(key, value, null, null);
        }
        public TreeMapNode(K key, V value, TreeMapNode<K, V> left, TreeMapNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
