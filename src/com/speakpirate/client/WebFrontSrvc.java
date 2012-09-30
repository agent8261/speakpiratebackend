package com.speakpirate.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.speakpirate.shared.Lexicon;

@RemoteServiceRelativePath("webfrontend")
public interface WebFrontSrvc extends RemoteService
{
	public List<Lexicon> listLexicon();
	
	public Lexicon getLexicon(String engText);
	
	public void addLexicon(String engText, String pText);
	
	public void insertLexicon(Lexicon lexicon);
	
	public void updateLexicon(Lexicon lexicon);
	
	public void removeLexicon(String engText);
}
