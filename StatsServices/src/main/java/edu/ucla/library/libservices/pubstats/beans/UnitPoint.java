package edu.ucla.library.libservices.pubstats.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType( XmlAccessType.FIELD )
public class UnitPoint
{
  @XmlElement(name = "unitPointID")
  private String unitPointID;
  @XmlElement(name = "unitName")
  private String unitName;
  @XmlElement(name = "servicePoint")
  private String servicePoint;

  public void setUnitPointID( String unitPointID )
  {
    this.unitPointID = unitPointID;
  }

  public String getUnitPointID()
  {
    return unitPointID;
  }

  public void setUnitName( String unitName )
  {
    this.unitName = unitName;
  }

  public String getUnitName()
  {
    return unitName;
  }

  public void setServicePoint( String servicePoint )
  {
    this.servicePoint = servicePoint;
  }

  public String getServicePoint()
  {
    return servicePoint;
  }

  public UnitPoint()
  {
    super();
  }
}
