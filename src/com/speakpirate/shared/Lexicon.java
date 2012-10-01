package com.speakpirate.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Lexicon implements Serializable
{	
	private static final long serialVersionUID = 6101563891163528070L;
	@PrimaryKey
	private String engText;
	private String pirateText;
	
	public Lexicon(String _engText, String _pirateText)
	{ engText = _engText; pirateText = _pirateText; }
	
	public Lexicon() { }
	
	public String getengText()
	{  return engText;  }
	
	public String getpirateText()
	{  return pirateText;  }
	
	public void setengText(String _engText)
	{ engText = _engText;  }
	
	public void setpirateText(String _pirateText)
	{  pirateText = _pirateText;  }	
}