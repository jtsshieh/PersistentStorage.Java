package io.github.jtsshieh.persistentstorage.cached;

import io.github.jtsshieh.persistentstorage.Properties;
import io.github.jtsshieh.persistentstorage.StorageMethod;

public interface CachedStorageMethod<T> extends StorageMethod<T>
{
    T getCache();

    void setCache(T cacheObject);

    void saveState();

    void updateCache();

    void initialize(Properties properties);
}
