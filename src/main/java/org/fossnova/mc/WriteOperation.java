package org.fossnova.mc;

public interface WriteOperation extends Operation {
    ServiceBuilder addService();
    void removeService(ServiceController controller);
    void finish(CompletionListener<WriteOperation> op);
}
