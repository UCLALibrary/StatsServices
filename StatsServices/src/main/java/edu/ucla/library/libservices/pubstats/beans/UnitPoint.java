package edu.ucla.library.libservices.pubstats.beans;

import java.util.List;

public class UnitPoint
{
  private String unitPointID;
  private String unitName;
  private String servicePoint;
  private List<InteractionMode> modes;
  private List<QuestionType> types;

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

  public void setModes( List<InteractionMode> modes )
  {
    this.modes = modes;
  }

  public List<InteractionMode> getModes()
  {
    return modes;
  }

  public void setTypes( List<QuestionType> types )
  {
    this.types = types;
  }

  public List<QuestionType> getTypes()
  {
    return types;
  }

  public UnitPoint()
  {
    super();
  }
}
