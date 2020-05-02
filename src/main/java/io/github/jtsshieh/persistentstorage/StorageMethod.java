package io.github.jtsshieh.persistentstorage;

public interface StorageMethod<T>
{

    String getName();

    String setName();

    void initialize();
}
