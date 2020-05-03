package io.github.jtsshieh.persistentstorage.cached;

import io.github.jtsshieh.persistentstorage.Properties;
import io.github.jtsshieh.persistentstorage.StorageMethod;

/**
 * The base for a Cached Storage Method
 *
 * @param <T> The object being stored
 */
public interface CachedStorageMethod<T> extends StorageMethod<T>
{
    /**
     * Gets the Current Cache of the Storage Method
     *
     * @return The Current Cache
     */
    T getCache();

    /**
     * Sets the Cache
     *
     * @param cacheObject The CacheObject to set the cache as
     */
    void setCache(T cacheObject);

    /**
     * Saves the stored cache into the Persistent Storage
     */
    void saveState();

    /**
     * Gets the data from the Persistent Storage and dumps it to the Cache
     */
    void updateCache();

    /**
     * Initialize the Cached StorageMethod with Properties
     *
     * @param properties    The Properties object to pass in
     * @see                 Properties
     */
    void initialize(Properties properties);
}
