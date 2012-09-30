package com.speakpirate.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.speakpirate.shared.Lexicon;

public interface WebFrontSrvcAsync
{

	void getLexicon(String engText, AsyncCallback<Lexicon> callback);

	void insertLexicon(Lexicon lexicon, AsyncCallback<Void> callback);

	void listLexicon(AsyncCallback<List<Lexicon>> callback);

	void removeLexicon(String engText, AsyncCallback<Void> callback);

	void updateLexicon(Lexicon lexicon, AsyncCallback<Void> callback);

	void addLexicon(String engText, String pText, AsyncCallback<Void> callback);

}
