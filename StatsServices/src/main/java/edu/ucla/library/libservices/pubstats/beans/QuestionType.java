package edu.ucla.library.libservices.pubstats.beans;

public class QuestionType
{
  private String type;
  private String typeID;
  private String helpText;

  public void setType( String type )
  {
    this.type = type;
  }

  public String getType()
  {
    return type;
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
