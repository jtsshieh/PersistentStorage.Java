package io.github.jtsshieh.persistentstorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The base for a persistent storage controller
 * @param <T>   The object being stored
 */
public class PersistentStorage<T>
{
    /**
     * List of Storage Methods
     */
    public List<StorageMethod<T>> StorageMethods = new ArrayList<>();

    /**
     * The selected storage method
     */
    public StorageMethod<T> StorageMethod;

    /**
     * Initialize a new Persistent Storage Controller
     *
     * @param storageMethods An array of storage methods to add
     */
    public PersistentStorage(StorageMethod<T>... storageMethods)
    {
        this.StorageMethods.addAll(Arrays.asList(storageMethods));
    }

    /**
     * Add a storage method
     *
     * @param storageMethod The Storage Method to add
     */
    public void addStorageMethod(StorageMethod<T> storageMethod)
    {
        StorageMethods.add(storageMethod);
    }

    /**
     * Selects a storage method by its instance, if it exists
     * @param storageMethod The Storage Method instance to select
     */
    public void selectStorageMethod(StorageMethod<T> storageMethod)
    {
        if (StorageMethods.contains(storageMethod)) this.StorageMethod = storageMethod;
    }

    /**
     * Selects a storage method by its name, if it exists
     * @param storageMethodName The Name of the Storage Method to select
     */
    public void selectStorageMethod(String storageMethodName)
    {
        for(StorageMethod<T> StorageMethod : StorageMethods)
        {
            if(StorageMethod.getName().equals(storageMethodName))
            {
                this.StorageMethod = StorageMethod;
            }
        }
    }
}
