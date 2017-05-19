package edu.ucla.library.libservices.pubstats.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType( XmlAccessType.FIELD )
public class Unit
{
  @XmlElement(name = "unitID")
  private String unitID;
  @XmlElement(name = "unitName")
  private String unitName;

  public Unit()
  {
    super();
  }

  public void setUnitID( String unitID )
  {
    this.unitID = unitID;
  }

  public String getUnitID()
  {
    return unitID;
  }

  public void setUnitName( String unitName )
  {
    this.unitName = unitName;
  }

  public String getUnitName()
  {
    return unitName;
  }
}
