package io.github.jtsshieh.persistentstorage.queried;

import io.github.jtsshieh.persistentstorage.PersistentStorage;
import io.github.jtsshieh.persistentstorage.Properties;

public class QueriedStorageController<T> extends PersistentStorage<T>
{
    public boolean initialize()
    {
        if (StorageMethod == null) return false;
        StorageMethod.initialize();
        return true;
    }

    public boolean initialize(Properties properties)
    {
        if (StorageMethod == null) return false;
        ((QueriedStorageMethod<T>)StorageMethod).initialize(properties);
        return true;
    }

    public <B> void setValue(String query, B value)
    {
        ((QueriedStorageMethod<T>)StorageMethod).setValue(query, value);
    }

    public <B> B getValue(String query)
    {
        return ((QueriedStorageMethod<T>)StorageMethod).getValue(query);
    }

    public <B> void insertArray(String query, B item, int index)
    {
        ((QueriedStorageMethod<T>)StorageMethod).insertArray(query, item, index);
    }

    public <B> void pushArray(String query, B item)
    {
        ((QueriedStorageMethod<T>)StorageMethod).pushArray(query, item);
    }

    public void removeAtArray(String query, int index)
    {
        ((QueriedStorageMethod<T>)StorageMethod).removeAtArray(query, index);
    }

    public <B> void removeArray(String query, B item)
    {
        ((QueriedStorageMethod<T>)StorageMethod).removeArray(query, item);
    }
}
