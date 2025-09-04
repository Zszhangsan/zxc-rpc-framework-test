package zxc.rpc.spi.app;

import zxc.rpc.spi.service.SpiInterfaceService;
import zxc.rpc.spi.service.on.SpiServiceOneImpl;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiService {
    public static void main(String[] args) {
        ServiceLoader<SpiInterfaceService> load = ServiceLoader.load(SpiInterfaceService.class);
        SpiServiceOneImpl spiServiceOne = new SpiServiceOneImpl();


        for (SpiInterfaceService next : load) {
            next.hello();
        }
    }
}
