package io.github.jtsshieh.persistentstorage;

/**
 * The base for a storage method
 * @param <T> The object being stored
 */
public interface StorageMethod<T>
{
    /**
     * Gets the name of the storage method
     * @return The name of the storage method
     */
    String getName();

    /**
     * Initializes the storage method
     */
    void initialize();
}
