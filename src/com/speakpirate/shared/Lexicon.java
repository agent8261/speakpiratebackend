package com.speakpirate.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Lexicon implements Serializable
{	
	private static final long serialVersionUID = 6101563891163528070L;
	@PrimaryKey
	public String engText;
	public String pirateText;
	
	public Lexicon(String _engText, String _pirateText)
	{ engText = _engText; pirateText = _pirateText; }
	
	public Lexicon() { }	
}