package edu.ucla.library.libservices.pubstats.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateExtractor
{

  public DateExtractor( )
  {
  }

  public static int getCalendarPart( Date source, int part )
  {
    GregorianCalendar converter;
    converter = new GregorianCalendar();
    converter.setTime( source );
    return ( part == Calendar.MONTH ? converter.get( part ) + 1 : converter.get( part ) );
  }
}
