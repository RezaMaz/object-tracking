package ir.ofoghkish.objecttracking.service.service;

import ir.ofoghkish.objecttracking.service.dto.CoordinationDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utility {
    //    https://gamedev.stackexchange.com/questions/26004/how-to-detect-2d-line-on-line-collision
    public static Boolean IsIntersecting(CoordinationDTO.Info a, CoordinationDTO.Info b, CoordinationDTO.Info c, CoordinationDTO.Info d) {

        BigDecimal ua = BigDecimal.ZERO;
        BigDecimal ub;

        BigDecimal denominator =
                ((d.getLongitude().subtract(c.getLongitude())).multiply((b.getLatitude().subtract(a.getLatitude()))))
                        .subtract(((d.getLatitude().subtract(c.getLatitude())).multiply((b.getLongitude().subtract(a.getLongitude())))));

        if (denominator.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal numerator1 = ((d.getLatitude().subtract(c.getLatitude())).multiply((a.getLongitude().subtract(c.getLongitude()))))
                    .subtract(((d.getLongitude().subtract(c.getLongitude())).multiply((a.getLatitude().subtract(c.getLatitude())))));

            BigDecimal numerator2 = ((b.getLatitude().subtract(a.getLatitude())).multiply((a.getLongitude().subtract(c.getLongitude()))))
                    .subtract(((b.getLongitude().subtract(a.getLongitude())).multiply((a.getLatitude().subtract(c.getLatitude())))));

            ua = numerator1.divide(denominator, 10, RoundingMode.HALF_UP);
            ub = numerator2.divide(denominator, 10, RoundingMode.HALF_UP);

            if (ua.compareTo(BigDecimal.ZERO) < 0 || ua.compareTo(BigDecimal.ONE) > 0 || ub.compareTo(BigDecimal.ZERO) < 0 || ub.compareTo(BigDecimal.ONE) > 1)
                ua = BigDecimal.ZERO;
        }

        return ua.compareTo(BigDecimal.ZERO) != 0;
    }
}
