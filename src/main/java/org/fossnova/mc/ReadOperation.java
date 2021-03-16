package org.fossnova.mc;

public interface ReadOperation extends Operation {
    void finish(CompletionListener<ReadOperation> op);
}
