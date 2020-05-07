package io.github.jtsshieh.persistentstorage.cached;

import io.github.jtsshieh.persistentstorage.PersistentStorage;
import io.github.jtsshieh.persistentstorage.Properties;

/**
 * A Cached Persistent Storage Controller
 *
 * @param <T> The object being stored
 */
public class CachedStorageController<T> extends PersistentStorage<T>
{
    /**
     * The Current Cache Object
     */
    public T currentCache;

    /**
     * Initializes the Selected Cached Persistent Storage Method
     *
     * @return  A boolean of whether the operation completed successfully or failed
     */
    public boolean initialize()
    {
        if (StorageMethod == null) return false;
        StorageMethod.initialize();
        ((CachedStorageMethod<T>)StorageMethod).updateCache();
        currentCache = ((CachedStorageMethod<T>)StorageMethod).getCache();
        return true;
    }

    /**
     * Initializes the Selected Cached Persistent Storage Method with Properties
     *
     * @param properties        The Properties object to pass in
     * @see                     Properties
     * @return                  A boolean of whether the operation completed successfully or failed
     */
    public boolean initialize(Properties properties)
    {
        if (StorageMethod == null) return false;
        ((CachedStorageMethod<T>)StorageMethod).initialize(properties);
        ((CachedStorageMethod<T>)StorageMethod).updateCache();
        currentCache = ((CachedStorageMethod<T>)StorageMethod).getCache();
        return true;
    }

    /**
     * Saves the Cache into the Persistent Storage
     */
    public void saveState()
    {
        ((CachedStorageMethod<T>)StorageMethod).setCache(currentCache);
        ((CachedStorageMethod<T>)StorageMethod).saveState();
    }

    /**
     * Updates the Cache with the contents of the Persistent Storage
     */
    public void updateCache()
    {
        ((CachedStorageMethod<T>)StorageMethod).updateCache();
        currentCache = ((CachedStorageMethod<T>)StorageMethod).getCache();
    }

    /**
     * Saves and Updates the Cache
     */
    public void SaveUpdate()
    {
        saveState();
        updateCache();
    }
}
