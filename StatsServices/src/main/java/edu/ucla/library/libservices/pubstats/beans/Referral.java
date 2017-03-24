package edu.ucla.library.libservices.pubstats.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType( XmlAccessType.FIELD )
public class Referral
{
  @XmlElement(name = "text")
  private String text;

  public Referral()
  {
    super();
  }

  public void setText( String text )
  {
    this.text = text;
  }

  public String getText()
  {
    return text;
  }
}
