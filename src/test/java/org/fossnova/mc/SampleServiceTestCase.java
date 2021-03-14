package org.fossnova.mc;

public class SampleServiceTestCase {

    public void testInstallSampleService() {
        Container container = ContainerFactory.newContainer().install();

        WriteOperation writeOp = container.newWriteOperation(); // blocking writeOp creation
        container.newWriteOperation(new CompletionListener<>()); // asynchronous writeOp creation
        ServiceController sc = writeOp.addService(SampleService.class);
        writeOp.removeService(sc);
        writeOp.finish(); // blocking finish
        writeOp.finish(new CompletionListener<>()); // asynchronous finish
        ReadOperation readOp = container.newReadOperation(); // blocking readOp creation

        container.newReadOperation(new CompletionListener<>()); // asynchronous writeOp creation
        readOp.getControllers();
        readOp.finish(); // blocking finish
        readOp.finish(new CompletionListener<>()); // asynchronous finish
    }
}
