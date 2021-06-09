package com.projetointegrador.model.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author RafaelRodrigues1
 */
public class LongDate {
    
    public static Long getLongDate(LocalDate localdate){
        Long data = localdate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return data;
    }
    
    public static Long getLongDate(LocalDateTime localdateTime){
        Long data = localdateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        return data;
    }
}
