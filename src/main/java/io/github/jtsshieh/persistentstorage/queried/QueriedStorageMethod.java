package io.github.jtsshieh.persistentstorage.queried;

import io.github.jtsshieh.persistentstorage.Properties;
import io.github.jtsshieh.persistentstorage.StorageMethod;

/**
 * The base for a Queried Storage Method
 *
 * @param <T> The object being stored
 */
public interface QueriedStorageMethod<T> extends StorageMethod<T>
{
    /**
     * Initialize the queried storage method with properties
     *
     * @param properties    The Properties object to pass in
     * @see                 Properties
     */
    void initialize(Properties properties);

    /**
     * Set a value
     *
     * @param query The query
     * @param value The value as a B
     * @param <B>   The type of the value being set
     */
    <B> void setValue(String query, B value);

    /**
     * Get a value
     * @param query The query
     * @param <B>   They type to parse the value as
     * @return      The value as a B
     */
    <B> B getValue(String query);

    /**
     * Inserts an item into an array
     *
     * @param query The query selecting an array
     * @param item  The item being inserted into the array
     * @param index The index to insert hte item into
     * @param <B>   The type of the value being set
     */
    <B> void insertArray(String query, B item, int index);

    /**
     * Push an item into an array
     * @param query The query selecting an array
     * @param item  The item being pushed into the array
     * @param <B>   The type of the value being set
     */
    <B> void pushArray(String query, B item);

    /**
     * Remove an item from an array by its index
     * @param query The query selecting an array
     * @param index The index of the item that needs to be removed
     */
    void removeAtArray(String query, int index);

    /**
     * Remove an item from an array
     * @param query The query selecting an array
     * @param item  The item that needs to be removed
     * @param <B>   The type of the value being set
     */
    <B> void removeArray(String query, B item);
}
