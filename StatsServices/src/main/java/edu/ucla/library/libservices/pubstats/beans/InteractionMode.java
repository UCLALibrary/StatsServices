package edu.ucla.library.libservices.pubstats.beans;

public class InteractionMode
{
  private String modeType;
  private String modeID;

  public void setModeType( String modeType )
  {
    this.modeType = modeType;
  }

  public String getModeType()
  {
    return modeType;
  }

  public void setModeID( String modeID )
  {
    this.modeID = modeID;
  }

  public String getModeID()
  {
    return modeID;
  }

  public InteractionMode()
  {
    super();
  }
}
