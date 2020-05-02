package io.github.jtsshieh.persistentstorage.queried;

import io.github.jtsshieh.persistentstorage.Properties;
import io.github.jtsshieh.persistentstorage.StorageMethod;

public interface QueriedStorageMethod<T> extends StorageMethod<T>
{
    void initialize(Properties properties);

    <B> void setValue(String query, B value);
    
    <B> B getValue(String query);

    <B> void insertArray(String query, B item, int index);

    <B> void pushArray(String query, B item);

    void removeAtArray(String query, int index);

    <B> void removeArray(String query, B item);
}
