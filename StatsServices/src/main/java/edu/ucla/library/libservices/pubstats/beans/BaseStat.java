package edu.ucla.library.libservices.pubstats.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType( XmlAccessType.FIELD )
public class BaseStat
{
  @XmlElement(name = "typeID")
  private String typeID;
  @XmlElement(name = "modeID")
  private String modeID;
  @XmlElement(name = "count")
  private int count;

  public BaseStat()
  {
    super();
  }

  public void setTypeID( String typeID )
  {
    this.typeID = typeID;
  }

  public String getTypeID()
  {
    return typeID;
  }

  public void setModeID( String modeID )
  {
    this.modeID = modeID;
  }

  public String getModeID()
  {
    return modeID;
  }

  public void setCount( int count )
  {
    this.count = count;
  }

  public int getCount()
  {
    return count;
  }

}
