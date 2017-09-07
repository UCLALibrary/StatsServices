package edu.ucla.library.libservices.pubstats.beans;

import edu.ucla.library.libservices.pubstats.util.DateAdapter;

//import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "Submission" )
public class Submission
{
  @XmlElement(name = "unitID")
  private String unitID; 
  @XmlElement(name = "pointID")
  private String pointID;
  @XmlElement(name = "dateTime")
  @XmlJavaTypeAdapter( DateAdapter.class )
  private Date dateTime;
  @XmlElement(name = "operator")
  private String operator;
  @XmlElement(name = "timeSpent")
  private double timeSpent;
  @XmlElement(name = "detailed")
  private boolean detailed;
  @XmlElement(name = "stats")
  private List<BaseStat> stats;
  @XmlElement(name = "referral", required = false )
  private Referral referral;
  @XmlElement(name = "interaction", required = false )
  private Interaction interaction;

  public Submission()
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

  public void setPointID( String pointID )
  {
    this.pointID = pointID;
  }

  public String getPointID()
  {
    return pointID;
  }

  public void setDateTime( Date dateTime )
  {
    this.dateTime = dateTime;
  }

  public Date getDateTime()
  {
    return dateTime;
  }

  public void setOperator( String operator )
  {
    this.operator = operator;
  }

  public String getOperator()
  {
    return operator;
  }

  public void setTimeSpent( double timeSpent )
  {
    this.timeSpent = timeSpent;
  }

  public double getTimeSpent()
  {
    return timeSpent;
  }

  public void setStats( List<BaseStat> stats )
  {
    this.stats = stats;
  }

  public List<BaseStat> getStats()
  {
    return stats;
  }

  public void setReferral( Referral referral )
  {
    this.referral = referral;
  }

  public Referral getReferral()
  {
    return referral;
  }

  public void setInteraction( Interaction interaction )
  {
    this.interaction = interaction;
  }

  public Interaction getInteraction()
  {
    return interaction;
  }

  public void setDetailed( boolean detailed )
  {
    this.detailed = detailed;
  }

  public boolean isDetailed()
  {
    return detailed;
  }
}
