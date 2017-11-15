package edu.ucla.library.libservices.pubstats.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType( XmlAccessType.FIELD )
public class Department
{
  @XmlElement(name = "DepartmentID")
  private int departmentID; 
  @XmlElement(name = "DepartmentName")
  private String departmentName;

  public Department()
  {
    super();
  }

  public void setDepartmentID( int departmentID )
  {
    this.departmentID = departmentID;
  }

  public int getDepartmentID()
  {
    return departmentID;
  }

  public void setDepartmentName( String departmentName )
  {
    this.departmentName = departmentName;
  }

  public String getDepartmentName()
  {
    return departmentName;
  }
}
/*
select DepartmentID, Department from SIA.dbo.DepartmentLookup
 */