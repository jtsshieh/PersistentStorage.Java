package io.github.jtsshieh.persistentstorage;

/**
 * The base for a storage method
 * @param <T> The object being stored
 */
public interface StorageMethod<T>
{
    /**
     * Get the name of the storage method
     * @return The name of the storage method
     */
    String getName();

    /**
     * Initialize the storage method
     */
    void initialize();
}
