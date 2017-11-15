package edu.ucla.library.libservices.pubstats.beans;

import java.util.Date;

public class StatsLine
{
  private String aggregateID;
  private int count;
  private int dataMonth;
  private int dataYear;
  private Date dateTime;
  private String logonID;
  private double timeSpent;
  private int patronCount;

  public StatsLine()
  {
    super();
  }

  public void setAggregateID( String aggregateID )
  {
    this.aggregateID = aggregateID;
  }

  public String getAggregateID()
  {
    return aggregateID;
  }

  public void setCount( int count )
  {
    this.count = count;
  }

  public int getCount()
  {
    return count;
  }

  public void setDataMonth( int dataMonth )
  {
    this.dataMonth = dataMonth;
  }

  public int getDataMonth()
  {
    return dataMonth;
  }

  public void setDataYear( int dataYear )
  {
    this.dataYear = dataYear;
  }

  public int getDataYear()
  {
    return dataYear;
  }

  public void setDateTime( Date dateTime )
  {
    this.dateTime = dateTime;
  }

  public Date getDateTime()
  {
    return dateTime;
  }

  public void setLogonID( String logonID )
  {
    this.logonID = logonID;
  }

  public String getLogonID()
  {
    return logonID;
  }

  public void setTimeSpent( double timeSpent )
  {
    this.timeSpent = timeSpent;
  }

  public double getTimeSpent()
  {
    return timeSpent;
  }

  public void setPatronCount( int patronCount )
  {
    this.patronCount = patronCount;
  }

  public int getPatronCount()
  {
    return patronCount;
  }
}
