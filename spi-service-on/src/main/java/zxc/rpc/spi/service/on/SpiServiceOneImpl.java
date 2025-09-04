package zxc.rpc.spi.service.on;

import zxc.rpc.spi.service.SpiInterfaceService;

public class SpiServiceOneImpl implements SpiInterfaceService {
    @Override
    public String hello() {
        System.out.println("SpiService: SpiServiceOneImpl");
        return "SpiService: SpiServiceOneImpl";
    }
}
