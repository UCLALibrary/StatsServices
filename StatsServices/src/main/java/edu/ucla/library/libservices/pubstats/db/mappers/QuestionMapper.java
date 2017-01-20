package edu.ucla.library.libservices.pubstats.db.mappers;

import edu.ucla.library.libservices.pubstats.beans.QuestionType;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class QuestionMapper
  implements RowMapper
{
  public QuestionMapper()
  {
    super();
  }

  @Override
  public Object mapRow( ResultSet rs, int i )
    throws SQLException
  {
    QuestionType bean;
    bean = new QuestionType();
    bean.setHelpText( rs.getString( "HelpText" ) );
    bean.setType( rs.getString( "QuestionType" ) );
    bean.setTypeID( rs.getString( "TypeID" ) );
    return bean;
  }
}
