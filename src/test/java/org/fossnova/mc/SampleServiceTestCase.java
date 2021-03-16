package org.fossnova.mc;

import java.util.HashMap;
import java.util.Map;

public class SampleServiceTestCase {

    private static ContainerController containerController;
    private static Deployment deployment;

    public static void setUp() {
        containerController = ContainerFactory.newContainer().finish();
        deployment = new Deployment();
    }

    public static void tearDown() {
        deployment = null;
        containerController.shutdown();
        containerController = null;
    }

    public void testUsecase1() {
        Container container = containerController.getContainer();

        WriteOperation writeOp = container.newWriteOperation(); // blocking writeOp creation
        container.newWriteOperation(new CompletionListener<>()); // asynchronous writeOp creation
        ServiceBuilder sb = writeOp.addService();
        sb.requires("a");
        sb.provides("b");
        sb.setMode(ServiceMode.ACTIVE);
        sb.setInstance(new SampleService());
        deployment.addController(SampleService.class, sb.install());
        writeOp.finish(); // blocking finish
        writeOp.finish(new CompletionListener<>()); // asynchronous finish
    }

    public void testUsecase2() {
        Container container = containerController.getContainer();

        ReadOperation readOp = container.newReadOperation(); // blocking readOp creation
        container.newReadOperation(new CompletionListener<>()); // asynchronous writeOp creation
        ServiceController sc = deployment.getController(SampleService.class);

        System.out.println("Missing values: " + sc.misses());
        System.out.println("Required values: " + sc.requires());
        System.out.println("Provided values: " + sc.provides());

        System.out.println("Service mode: " + sc.mode());
        System.out.println("Service state: " + sc.state());
        System.out.println("Failure reason: " + sc.reason());

        readOp.finish(); // blocking finish
        readOp.finish(new CompletionListener<>()); // asynchronous finish
    }

    public void testUsecase3() {
        Container container = containerController.getContainer();

        ReadOperation readOp = container.newReadOperation(); // blocking readOp creation
        container.newReadOperation(new CompletionListener<>()); // asynchronous writeOp creation
        readOp.getControllers(); // dump container state
        readOp.finish(); // blocking finish
        readOp.finish(new CompletionListener<>()); // asynchronous finish
    }

    private static class Deployment {

        private Map<Class<? extends Service>, ServiceController> mapping = new HashMap<>();

        private void addController(Class<? extends Service> service, ServiceController controller) {
            mapping.put(service, controller);
        }

        private ServiceController getController(Class<? extends Service> service) {
            return mapping.get(service);
        }
    }
}
