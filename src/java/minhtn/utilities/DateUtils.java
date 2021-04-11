/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtn.utilities;

import java.io.Serializable;
import java.sql.Date;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/**
 *
 * @author minhv
 */
public class DateUtils implements Serializable {

    public static Date getCurrentDate() {
        LocalDate date = new LocalDate(DateTimeZone.UTC).now();
        return new Date(date.toDateTimeAtStartOfDay().getMillis());
    }
}
