package io.github.jtsshieh.persistentstorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersistentStorage<T>
{
    public List<StorageMethod<T>> StorageMethods = new ArrayList<>();

    public StorageMethod<T> StorageMethod;

    public PersistentStorage(StorageMethod<T>... storageMethods)
    {
        this.StorageMethods.addAll(Arrays.asList(storageMethods));
    }

    public void addStorageMethod(StorageMethod<T> storageMethod)
    {
        StorageMethods.add(storageMethod);
    }

    public void selectStorageMethod(StorageMethod<T> storageMethod)
    {
        if (StorageMethods.contains(storageMethod)) this.StorageMethod = storageMethod;
    }

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
