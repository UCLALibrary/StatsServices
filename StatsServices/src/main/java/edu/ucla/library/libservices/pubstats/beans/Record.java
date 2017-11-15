package edu.ucla.library.libservices.pubstats.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "Record" )
public class Record
{
  @XmlElement(name = "aggregateID")
  private String aggregateID;
  @XmlElement(name = "count")
  private int count;
  @XmlElement(name = "createdDT")
  private Date createdDT;
  @XmlElement(name = "logonID")
  private String logonID;
  @XmlElement(name = "timeSpent")
  private float timeSpent;
  @XmlElement(name = "patronCount")
  private int patronCount;
  @XmlElement(name = "referralText")
  private String referralText;
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

  public Record()
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

  public void setCreatedDT( Date createdDT )
  {
    this.createdDT = createdDT;
  }

  public Date getCreatedDT()
  {
    return createdDT;
  }

  public void setLogonID( String logonID )
  {
    this.logonID = logonID;
  }

  public String getLogonID()
  {
    return logonID;
  }

  public void setTimeSpent( float timeSpent )
  {
    this.timeSpent = timeSpent;
  }

  public float getTimeSpent()
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

  public void setReferralText( String referralText )
  {
    this.referralText = referralText;
  }

  public String getReferralText()
  {
    return referralText;
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
