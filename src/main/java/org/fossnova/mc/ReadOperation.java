package org.fossnova.mc;

public interface ReadOperation extends Operation {
    /**
     * Returns value associaged with given property name inside container.
     * @param name name of the value
     * @param <T> value instance
     * @return value instance
     */
    <T> T getValue(String name);
    void finish(CompletionListener<ReadOperation> op);
}
