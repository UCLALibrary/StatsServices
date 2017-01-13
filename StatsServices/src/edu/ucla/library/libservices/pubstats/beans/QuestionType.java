package edu.ucla.library.libservices.pubstats.beans;

public class QuestionType
{
  private String typeName;
  private String typeID;
  private String helpText;

  public void setTypeName( String typeName )
  {
    this.typeName = typeName;
  }

  public String getTypeName()
  {
    return typeName;
  }

  public void setTypeID( String typeID )
  {
    this.typeID = typeID;
  }

  public String getTypeID()
  {
    return typeID;
  }

  public void setHelpText( String helpText )
  {
    this.helpText = helpText;
  }

  public String getHelpText()
  {
    return helpText;
  }

  public QuestionType()
  {
    super();
  }
}
