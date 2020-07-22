package ir.ofoghkish.objecttracking.entity.enumeration;

import javax.persistence.AttributeConverter;
import java.util.Arrays;

public abstract class Converter {

    public static class CarTypeConverter implements AttributeConverter<CarType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(CarType carType) {
            if (carType == null)
                return null;
            return carType.getId();
        }

        @Override
        public CarType convertToEntityAttribute(Integer integer) {
            return Arrays.stream(CarType.values()).filter(carType -> carType.getId().equals(integer)).findFirst().orElse(null);
        }
    }

}
