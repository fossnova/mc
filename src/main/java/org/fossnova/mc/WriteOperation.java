package org.fossnova.mc;

public interface WriteOperation extends Operation {
    ServiceController addService(Class<? extends Service> clazz);
    void removeService(ServiceController controller);
    void finish(CompletionListener<WriteOperation> op);
}
