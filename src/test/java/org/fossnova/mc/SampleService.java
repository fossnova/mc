package org.fossnova.mc;

@Provides(values = {
    @Value(name = SampleService.r1, type = String.class),
    @Value(name = SampleService.r2, type = String.class)
})
@Requires(values = {
    @Value( name = SampleService.p1, type = String.class),
    @Value( name = SampleService.p2, type = String.class)
})
@Mode(ServiceMode.LAZY) // default mode is active if not specified
public final class SampleService implements Service {

    public static final String p1 = "p1";
    public static final String p2 = "p2";
    public static final String r1 = "r1";
    public static final String r2 = "r2";

    @Override
    public void start(StartContext ctx) {
        ctx.provide(p1, "provides 1 value"); // type check is performed internally
        ctx.provide(p2, "provides 2 value"); // type check is performed internally
        // internal check is implemented that provided value is set up only once
        // internal check checks if all provided values are initialized - null is acceptable
        String d1 = ctx.require(r1);
        String d2 = ctx.require(r2);
        // do something with d1 & d2
    }

    @Override
    public void stop(StopContext ctx) {

    }
}
