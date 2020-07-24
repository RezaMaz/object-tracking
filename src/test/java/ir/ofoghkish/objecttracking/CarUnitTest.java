package ir.ofoghkish.objecttracking;

import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;
import ir.ofoghkish.objecttracking.service.iservice.ICarService;
import ir.ofoghkish.objecttracking.service.service.Utility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarUnitTest {

    @Autowired
    ICarService iCarService;

    @Test
    public void testIsCoordinationOutlier() {
//        when(iCarService.list()).thenReturn(new int[]{24, 15, 3});
//        assertEquals(24, businessImpl.findTheGreatestFromAllData());
    }

    @Test
    public void testGetReducedPath() {
//        when(iCarService.list()).thenReturn(new int[]{15});
//        assertEquals(15, businessImpl.findTheGreatestFromAllData());
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
        c.setLatitude(new BigDecimal(1));
        c.setLongitude(new BigDecimal(5));
        d.setLatitude(new BigDecimal(5));
        d.setLongitude(new BigDecimal(1));

        Boolean aBoolean = Utility.IsIntersecting(a, b, c, d);
        System.out.println("");
//        when(iCarService.list()).thenReturn(new int[]{});
//        assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
    }
}
