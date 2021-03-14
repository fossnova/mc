package org.fossnova.mc;

import java.util.Collection;

public interface ReadOperation extends Operation {
    Collection<ServiceController> getControllers();
    void finish(CompletionListener<ReadOperation> op);
}
