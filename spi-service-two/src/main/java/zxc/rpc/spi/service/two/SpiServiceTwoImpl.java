package zxc.rpc.spi.service.two;

import zxc.rpc.spi.service.SpiInterfaceService;

public class SpiServiceTwoImpl implements SpiInterfaceService {
    @Override
    public String hello() {
        System.out.println("SpiService: SpiServiceTwoImpl");
        return "SpiService: SpiServiceTwoImpl";
    }
}
