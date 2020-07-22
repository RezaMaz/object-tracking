package ir.ofoghkish.objecttracking.entity.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CarType {

    Passenger(1),
    Personal(2),
    Freight(3);

    private final Integer id;

}
