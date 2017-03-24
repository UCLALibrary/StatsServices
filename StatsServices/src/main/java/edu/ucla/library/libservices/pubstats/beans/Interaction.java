package edu.ucla.library.libservices.pubstats.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType( XmlAccessType.FIELD )
public class Interaction
{
  @XmlElement(name = "topic")
  private String topic;
  @XmlElement(name = "departmentID")
  private int departmentID;
  @XmlElement(name = "course")
  private String course;
  @XmlElement(name = "staffFeedback")
  private String staffFeedback;
  @XmlElement(name = "patronType")
  private int patronType;
  @XmlElement(name = "patronFeedback")
  private String patronFeedback;

  public Interaction()
  {
    super();
  }

  public void setTopic( String topic )
  {
    this.topic = topic;
  }

  public String getTopic()
  {
    return topic;
  }

  public void setDepartmentID( int departmentID )
  {
    this.departmentID = departmentID;
  }

  public int getDepartmentID()
  {
    return departmentID;
  }

  public void setCourse( String course )
  {
    this.course = course;
  }

  public String getCourse()
  {
    return course;
  }

  public void setStaffFeedback( String staffFeedback )
  {
    this.staffFeedback = staffFeedback;
  }

  public String getStaffFeedback()
  {
    return staffFeedback;
  }

  public void setPatronType( int patronType )
  {
    this.patronType = patronType;
  }

  public int getPatronType()
  {
    return patronType;
  }

  public void setPatronFeedback( String patronFeedback )
  {
    this.patronFeedback = patronFeedback;
  }

  public String getPatronFeedback()
  {
    return patronFeedback;
  }
}
