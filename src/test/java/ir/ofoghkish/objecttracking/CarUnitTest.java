package ir.ofoghkish.objecttracking;

import ir.ofoghkish.objecttracking.entity.Coordination;
import ir.ofoghkish.objecttracking.service.dto.CarDTO;
import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;
import ir.ofoghkish.objecttracking.service.iservice.ICarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarUnitTest {

    @Autowired
    ICarService iCarService;

    @Test
    public void testIsCoordinationOutlier() {
        long now = System.currentTimeMillis();

        Coordination a = new Coordination();
        Coordination b = new Coordination();
        Coordination c = new Coordination();
        Coordination d = new Coordination();

        a.setLatitude(new BigDecimal(1));
        a.setLongitude(new BigDecimal(1));
        a.setCreatedDate(new Date(now - 6000));

        b.setLatitude(new BigDecimal(3));
        b.setLongitude(new BigDecimal(3));
        b.setCreatedDate(new Date(now - 4000));

        c.setLatitude(new BigDecimal(4));
        c.setLongitude(new BigDecimal(4));
        c.setCreatedDate(new Date(now - 3000));

        d.setLatitude(new BigDecimal(7));
        d.setLongitude(new BigDecimal(7));
        d.setCreatedDate(new Date(now - 2000));

        List<Coordination> coordinations = new ArrayList<>();
        coordinations.add(a);
        coordinations.add(b);
        coordinations.add(c);
        coordinations.add(d);

        assertEquals(true, iCarService.isCoordinationOutlier(coordinations, new BigDecimal(15), new BigDecimal(15)));
    }

    @Test
    public void testIsInterference() {

        CoordinationDTO.Info a = new CoordinationDTO.Info();
        CoordinationDTO.Info b = new CoordinationDTO.Info();
        CoordinationDTO.Info c = new CoordinationDTO.Info();
        CoordinationDTO.Info d = new CoordinationDTO.Info();

        a.setLatitude(new BigDecimal(1));
        a.setLongitude(new BigDecimal(1));

        b.setLatitude(new BigDecimal(3));
        b.setLongitude(new BigDecimal(3));

        c.setLatitude(new BigDecimal(5));
        c.setLongitude(new BigDecimal(5));

        d.setLatitude(new BigDecimal(9));
        d.setLongitude(new BigDecimal(9));

        List<CoordinationDTO.Info> firstCoordinations = new ArrayList<>();
        firstCoordinations.add(a);
        firstCoordinations.add(b);
        firstCoordinations.add(c);
        firstCoordinations.add(d);

        CoordinationDTO.Info a1 = new CoordinationDTO.Info();
        CoordinationDTO.Info b1 = new CoordinationDTO.Info();
        CoordinationDTO.Info c1 = new CoordinationDTO.Info();
        CoordinationDTO.Info d1 = new CoordinationDTO.Info();

        a1.setLatitude(new BigDecimal(0));
        a1.setLongitude(new BigDecimal(0));

        b1.setLatitude(new BigDecimal("0.5"));
        b1.setLongitude(new BigDecimal(5));

        c1.setLatitude(new BigDecimal(5));
        c1.setLongitude(new BigDecimal(1));

        d1.setLatitude(new BigDecimal(6));
        d1.setLongitude(new BigDecimal(10));

        List<CoordinationDTO.Info> secondCoordinations = new ArrayList<>();
        secondCoordinations.add(a1);
        secondCoordinations.add(b1);
        secondCoordinations.add(c1);
        secondCoordinations.add(d1);

        CarDTO.Info firstCar = new CarDTO.Info();
        firstCar.setCoordinations(firstCoordinations);
        CarDTO.Info secondCar = new CarDTO.Info();
        secondCar.setCoordinations(secondCoordinations);

        assertEquals(true, iCarService.isInterference(firstCar, secondCar));
    }
}
