package edu.ucla.library.libservices.pubstats.util;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter
  extends XmlAdapter<String, Date>
{
  private static final SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm" ); //2017-09-01 14:15

  public DateAdapter()
  {
    super();
  }

  @Override
  public Date unmarshal( String string )
    throws Exception
  {
    return df.parse( string );
  }

  @Override
  public String marshal( Date localDateTime )
    throws Exception
  {
    return df.format( localDateTime );
  }
}
