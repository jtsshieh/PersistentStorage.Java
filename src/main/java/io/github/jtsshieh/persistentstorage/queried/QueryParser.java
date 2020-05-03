package io.github.jtsshieh.persistentstorage.queried;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A parser to parse queries
 */
public class QueryParser
{
    /**
     * Parses a query and then gets/adds/inserts an item
     *
     * @param query         The query
     * @param rootObject    The object to search/perform the operation on
     * @param value         The value to set
     * @param index         The index, if adding to an array -> -1, if pushing to an array -> -2
     * @param <T>           The type to parse the result as or the type of the value being set
     * @return              The object with updates performed/the object that was being searched for
     * @throws NoSuchFieldException     One of the fields in the query doesn't exist in the object
     * @throws IllegalAccessException   The class cannot be accessed
     */
    static <T> Object parseQuery(String query, Object rootObject, T value, int index) throws NoSuchFieldException, IllegalAccessException
    {
        // Split the query into parts
        List<String> queryParts = new ArrayList<>(Arrays.asList(query.split("\\.")));

        // Set the current working object to the root object
        Object currentObject = rootObject;

        // Start an index counter
        int currentIndex = 1;

        // Loop through the query parts
        for(String queryPart : queryParts)
        {
            Class<?> type = currentObject.getClass();

            // Check if we are parsing an array
            if (queryPart.contains("["))
            {
                // Split the array string into 3 parts, array name, array number, blank
                List<String> ArrayParts = new ArrayList<>(Arrays.asList(queryPart.split("[\\[\\]]")));

                // Cast the array into an Iterator
                List<?> array = (List<?>) type.getField(ArrayParts.get((0))).get(currentObject);

                // Check if we are setting information
                if ((currentIndex == queryParts.size()) && (value != null))
                {
                    List<T> array2 = (List<T>)type.getField(ArrayParts.get((0))).get(currentObject);
                    // Set the value in the list
                    array2.set(Integer.parseInt(ArrayParts.get(1)), value);

                    // Put this list into the object
                    type.getField(ArrayParts.get(0)).set(currentObject, array2);

                    // Return the object with the update
                    return rootObject;
                }

                // Set the current working object to the value in the array
                currentObject = array.get(Integer.parseInt(ArrayParts.get(1)));

            }
            // We are parsing an object
            else
            {
                // Get information about the field
                 Field fieldInfo = type.getField(queryPart);

                // Check if we are setting information or adding to an array
                if (currentIndex == queryParts.size())
                {

                    // Check if we are adding to an array
                    if (index != -1)
                    {
                        List<T> List = (List<T>)fieldInfo.get(currentObject);

                        // Check if we are pushing to an array
                        if (index == -2)
                        {
                            // Push to the array
                            List.add(value);
                        }
                        // Check if we are inserting to the array
                        else
                        {
                            // Insert to the array
                            List.add(index, value);
                        }

                        // Update the array in the object
                        fieldInfo.set(currentObject, List);

                        // Return the object with the update
                        return rootObject;
                    }
                    else if (value != null)
                    {
                        // Set the field value
                        fieldInfo.set(currentObject, value);

                        // Return the object with the update
                        return rootObject;
                    }
                }

                // Set the current working object ot the value in the field
                currentObject = fieldInfo.get(currentObject);
            }
            currentIndex++;
        }

        // If we are not setting an object, return the current working object
        return currentObject;
    }

    /**
     * Gets a value that is filtered using a query
     *
     * @param query         The query
     * @param rootObject    The object to search for the result in
     * @param <T>           The type to parse the result as
     * @return              The value the query yielded, parsed into the type specified in T
     * @throws NoSuchFieldException     One of the fields in the query doesn't exist in the object
     * @throws IllegalAccessException   The class cannot be accessed
     */
    public static <T> T getValue(String query, Object rootObject) throws NoSuchFieldException, IllegalAccessException
    {
        return (T) parseQuery(query, rootObject, null, -1);
    }

    /**
     * Sets a value that is filtered using a query
     *
     * @param query         The query
     * @param rootObject    The object to set the value on
     * @param value         The value to set
     * @param <T>           The type of the value that is being set
     * @return              The object with the value set
     * @throws NoSuchFieldException     One of the fields in the query doesn't exist in the object
     * @throws IllegalAccessException   The class cannot be accessed
     */
    public static <T> Object setValue(String query, Object rootObject, T value) throws NoSuchFieldException, IllegalAccessException
    {
        return parseQuery(query, rootObject, value, -1);
    }

    /**
     * Inserts an item into an array
     *
     * @param query         The query selecting an array
     * @param rootObject    The object to set the value on
     * @param item          The item being inserted into the array
     * @param index         The index to insert the item into
     * @param <T>           The type of the value being set
     * @return              The object with the item inserted
     * @throws NoSuchFieldException     One of the fields in the query doesn't exist in the object
     * @throws IllegalAccessException   The class cannot be accessed
     */
    public static <T> Object insertArray(String query, Object rootObject, T item, int index)throws NoSuchFieldException, IllegalAccessException
    {
        return parseQuery(query, rootObject, item, index);
    }

    /**
     * Pushes an item into an array
     *
     * @param query         The query selecting an array
     * @param rootObject    The object to set the value on
     * @param item          The item being pushed into the array
     * @param <T>           The type of the value being set
     * @return              The object with the item pushed
     * @throws NoSuchFieldException     One of the fields in the query doesn't exist in the object
     * @throws IllegalAccessException   The class cannot be accessed
     */
    public static <T> Object pushArray(String query, Object rootObject, T item) throws NoSuchFieldException, IllegalAccessException
    {
        return parseQuery(query, rootObject, item, -2);
    }

    /**
     * Removes an item from an array by its index
     *
     * @param query         The query selecting an array
     * @param rootObject    The object to remove the value on
     * @param index         The index of the item that needs to be removed
     * @param <T>           The type of the value being removed
     * @return              The object with the item removed
     * @throws NoSuchFieldException     One of the fields in the query doesn't exist in the object
     * @throws IllegalAccessException   The class cannot be accessed
     */
    public static <T> Object removeAtArray(String query, Object rootObject, int index) throws NoSuchFieldException, IllegalAccessException
    {
        List<T> list = QueryParser.getValue(query, rootObject);
        list.remove(index);
        return setValue(query, rootObject, list);
    }

    /**
     * Removes an item from an array
     *
     * @param query         The query selecting an array
     * @param rootObject    The object to remove the value on
     * @param item          The item that needs to be removed
     * @param <T>           The type of the value being removed
     * @return              The object with the item removed
     * @throws NoSuchFieldException     One of the fields in the query doesn't exist in the object
     * @throws IllegalAccessException   The class cannot be accessed
     */
    public static <T> Object removeArray(String query, Object rootObject, T item) throws NoSuchFieldException, IllegalAccessException
    {
        List<T> list = QueryParser.getValue(query, rootObject);
        list.remove(item);
        return setValue(query, rootObject, list);
    }

}
