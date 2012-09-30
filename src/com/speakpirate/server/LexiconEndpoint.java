package com.speakpirate.server;

import com.speakpirate.PMF;
import com.speakpirate.shared.Lexicon;

import com.google.api.server.spi.config.Api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "lexiconendpoint")
public class LexiconEndpoint
{
	/**This method lists all the entities inserted in datastore. It uses HTTP GET
	 * method.
	 * 
	 * @return List of all entities persisted.*/
	@SuppressWarnings({ "cast", "unchecked" })
	public List<Lexicon> listLexicon()
	{
		PersistenceManager mgr = getPersistenceManager();
		List<Lexicon> result = new ArrayList<Lexicon>();
		try
		{
			Query query = mgr.newQuery(Lexicon.class);
			for (Object obj : (List<Object>) query.execute())
			{  result.add(((Lexicon) obj));  }
		} 
		finally
		{  mgr.close();  }
		return result;
	}

	/**This method gets the entity having primary key id. It uses HTTP GET method.
	 * @param id
	 *          the primary key of the java bean.
	 * @return The entity with primary key id.*/
	public Lexicon getLexicon(@Named("id") String id)
	{
		PersistenceManager mgr = getPersistenceManager();
		Lexicon lexicon = null;
		try
		{  lexicon = mgr.getObjectById(Lexicon.class, id);  }
		finally
		{  mgr.close();  }
		return lexicon;
	}

	/**This inserts the entity into App Engine datastore. It uses HTTP POST
	 * method.
	 * @param lexicon
	 *          the entity to be inserted.
	 * @return The inserted entity.*/
	public Lexicon insertLexicon(Lexicon lexicon)
	{
		PersistenceManager mgr = getPersistenceManager();
		try
		{  mgr.makePersistent(lexicon);  } 
		finally
		{  mgr.close();  }
		return lexicon;
	}

	/**This method is used for updating a entity. It uses HTTP PUT method.
	 * @param lexicon
	 *          the entity to be updated.
	 * @return The updated entity.*/
	public Lexicon updateLexicon(Lexicon lexicon)
	{
		PersistenceManager mgr = getPersistenceManager();
		try
		{  mgr.makePersistent(lexicon);  } 
		finally
		{  mgr.close();  }
		return lexicon;
	}

	/**This method removes the entity with primary key id. It uses HTTP DELETE
	 * method.
	 * @param id
	 *          the primary key of the entity to be deleted.
	 * @return The deleted entity.*/
	public Lexicon removeLexicon(@Named("id") String id)
	{
		PersistenceManager mgr = getPersistenceManager();
		Lexicon lexicon = null;
		try
		{
			lexicon = mgr.getObjectById(Lexicon.class, id);
			mgr.deletePersistent(lexicon);
		} 
		finally
		{  mgr.close();  }
		return lexicon;
	}

	private static PersistenceManager getPersistenceManager()
	{  return PMF.get().getPersistenceManager();  }
}
