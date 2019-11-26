/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyecto.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;



/**
 *
 * @author Worlan
 */
public class GeneradarId {
    public static String generar()
    {
        Calendar c1 = new GregorianCalendar();
        String id = ""+c1.get(Calendar.YEAR)+c1.get(Calendar.MONTH)+c1.get(Calendar.DAY_OF_MONTH)+c1.get(Calendar.HOUR_OF_DAY)+c1.get(Calendar.MINUTE)+c1.get(Calendar.SECOND)+c1.get(Calendar.MILLISECOND);
        return id;
    }
}
