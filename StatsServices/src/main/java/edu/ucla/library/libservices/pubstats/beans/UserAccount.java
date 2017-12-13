package edu.ucla.library.libservices.pubstats.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType( XmlAccessType.FIELD )
public class UserAccount
{
  @XmlElement(name = "LogonID")
  private String logonID;
  @XmlElement(name = "Name")
  private String name;
  @XmlElement(name = "Unit")
  private String unit;

 public UserAccount()
  {
    super();
  }

  public void setLogonID( String LogonID )
  {
    this.logonID = LogonID;
  }

  public String getLogonID()
  {
    return logonID;
  }

  public void setName( String Name )
  {
    this.name = Name;
  }

  public String getName()
  {
    return name;
  }

  public void setUnit( String Unit )
  {
    this.unit = Unit;
  }

  public String getUnit()
  {
    return unit;
  }
}
