package io.github.jtsshieh.persistentstorage.cached;

import io.github.jtsshieh.persistentstorage.PersistentStorage;
import io.github.jtsshieh.persistentstorage.Properties;

public class CachedStorageController<T> extends PersistentStorage<T>
{
    public T currentCache;

    public boolean initialize()
    {
        if (StorageMethod == null) return false;
        StorageMethod.initialize();
        ((CachedStorageMethod<T>)StorageMethod).updateCache();
        currentCache = ((CachedStorageMethod<T>)StorageMethod).getCache();
        return true;
    }

    public boolean initialize(Properties properties)
    {
        if (StorageMethod == null) return false;
        ((CachedStorageMethod<T>)StorageMethod).initialize(properties);
        ((CachedStorageMethod<T>)StorageMethod).updateCache();
        currentCache = ((CachedStorageMethod<T>)StorageMethod).getCache();
        return true;
    }

    public void saveState()
    {
        ((CachedStorageMethod<T>)StorageMethod).setCache(currentCache);
        ((CachedStorageMethod<T>)StorageMethod).saveState();
    }

    public void updateCache()
    {
        ((CachedStorageMethod<T>)StorageMethod).updateCache();
        currentCache = ((CachedStorageMethod<T>)StorageMethod).getCache();
    }

    public void SaveUpdate()
    {
        saveState();
        updateCache();
    }
}
