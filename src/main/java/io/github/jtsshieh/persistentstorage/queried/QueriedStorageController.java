package io.github.jtsshieh.persistentstorage.queried;

import io.github.jtsshieh.persistentstorage.PersistentStorage;
import io.github.jtsshieh.persistentstorage.Properties;

/**
 * A Queried Persistent Storage Controller
 *
 * @param <T> The object being stored
 */
public class QueriedStorageController<T> extends PersistentStorage<T>
{
    /**
     * Initialize the Selected queried Persistent Storage Method
     *
     * @return A boolean of whether the operation completed successfully or failed
     */
    public boolean initialize()
    {
        if (StorageMethod == null) return false;
        StorageMethod.initialize();
        return true;
    }

    /**
     * Initialize the Selected queried Persistent Storage Method with Properties
     *
     * @param properties    The Properties object to pass in
     * @see                 Properties
     * @return              A boolean of whether the operation completed successfully or failed
     */
    public boolean initialize(Properties properties)
    {
        if (StorageMethod == null) return false;
        ((QueriedStorageMethod<T>)StorageMethod).initialize(properties);
        return true;
    }

    /**
     * Set a value
     *
     * @param query The query
     * @param value The value to set the object as
     * @param <B>   The type of the value being set
     */
    public <B> void setValue(String query, B value)
    {
        ((QueriedStorageMethod<T>)StorageMethod).setValue(query, value);
    }

    /**
     * Get a value
     *
     * @param query The query
     * @param <B>   The type to parse the value as
     * @return      The value as a B
     */
    public <B> B getValue(String query)
    {
        return ((QueriedStorageMethod<T>)StorageMethod).getValue(query);
    }

    /**
     * Inserts an item into an array
     *
     * @param query The query selecting an array
     * @param item  The item being inserted into the array
     * @param index The index to insert the item into
     * @param <B>   The type of the value being set
     */
    public <B> void insertArray(String query, B item, int index)
    {
        ((QueriedStorageMethod<T>)StorageMethod).insertArray(query, item, index);
    }

    /**
     * Push an item into an array
     *
     * @param query The query selecting an array
     * @param item  The item being pushed into the array
     * @param <B>   The type of the value being set
     */
    public <B> void pushArray(String query, B item)
    {
        ((QueriedStorageMethod<T>)StorageMethod).pushArray(query, item);
    }

    /**
     * Remove an item from an array by its index
     *
     * @param query The query selecting an array
     * @param index The index of the item that needs to be removed
     */
    public void removeAtArray(String query, int index)
    {
        ((QueriedStorageMethod<T>)StorageMethod).removeAtArray(query, index);
    }

    /**
     * Remove an item from an array
     * @param query The query selecting an array
     * @param item  The item that needs to be removed
     * @param <B>   The type of the value being removed
     */
    public <B> void removeArray(String query, B item)
    {
        ((QueriedStorageMethod<T>)StorageMethod).removeArray(query, item);
    }
}
