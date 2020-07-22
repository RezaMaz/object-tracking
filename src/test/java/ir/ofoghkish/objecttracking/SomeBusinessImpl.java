package ir.ofoghkish.objecttracking;

import ir.ofoghkish.objecttracking.service.iservice.ICarService;

public class SomeBusinessImpl {
    private ICarService iCarService;

    public SomeBusinessImpl(ICarService iCarService) {
        super();
        this.iCarService = iCarService;
    }
}
