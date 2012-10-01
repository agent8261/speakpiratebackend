package com.speakpirate.server;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ibm.icu.util.TimeZone;
import com.speakpirate.LexiconEndpoint;
import com.speakpirate.client.WebFrontSrvc;
import com.speakpirate.shared.Lexicon;

public class WebFrontSrvcImpl extends RemoteServiceServlet implements WebFrontSrvc
{
	private static final long serialVersionUID = -6542638378184734904L;
	private LexiconEndpoint lexiEndpt = new LexiconEndpoint();
	
	@Override
	public List<Lexicon> listLexicon()
	{  return lexiEndpt.listLexicon();  }

	@Override
	public Lexicon getLexicon(String engText)
	{  return lexiEndpt.getLexicon(engText);  }

	@Override
	public void insertLexicon(Lexicon lexicon)
	{  lexiEndpt.insertLexicon(lexicon);  }

	@Override
	public void updateLexicon(Lexicon lexicon)
	{  lexiEndpt.updateLexicon(lexicon);  }

	@Override
	public void removeLexicon(String engText)
	{  lexiEndpt.removeLexicon(engText);  }
	
	public void addLexicon(String engText, String pText)
	{ lexiEndpt.insertLexicon(new Lexicon(engText, pText));  }
}
