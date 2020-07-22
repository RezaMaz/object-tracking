package ir.ofoghkish.objecttracking.entity.enumeration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CarType {

    Passenger(1), //AOP Converter
    Personal(2),
    Freight(3);

    private final Integer id;

}
