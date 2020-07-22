package ir.ofoghkish.objecttracking;

import ir.ofoghkish.objecttracking.service.iservice.ICarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockAnnotationTest {

    @Mock
    ICarService iCarService;

    @InjectMocks
    SomeBusinessImpl businessImpl;

    @Test
    public void testFindTheGreatestFromAllData() {
//        when(iCarService.list()).thenReturn(new int[]{24, 15, 3});
//        assertEquals(24, businessImpl.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllData_ForOneValue() {
//        when(iCarService.list()).thenReturn(new int[]{15});
//        assertEquals(15, businessImpl.findTheGreatestFromAllData());
    }

    @Test
    public void testFindTheGreatestFromAllData_NoValues() {
//        when(iCarService.list()).thenReturn(new int[]{});
//        assertEquals(Integer.MIN_VALUE, businessImpl.findTheGreatestFromAllData());
    }
}
