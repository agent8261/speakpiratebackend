package com.speakpirate.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.speakpirate.shared.Lexicon;

public class WebFrontEnd implements EntryPoint
{	
	private TextBox engTxtBox = new TextBox();
	private TextBox pirateTxtBox = new TextBox();
	private FlexTable flexTable = new FlexTable();
	
	
	private ArrayList<String> flexIndex = new ArrayList<String>();
	private WebFrontSrvcAsync webfrontsrvc = GWT.create(WebFrontSrvc.class);
	final private String onFailErrorTxt = "Failed to connect to server";
	
	//===========================================================================
	// onModuleLoad()
	@Override
	public void onModuleLoad()
	{
		RootPanel rootPanel = RootPanel.get("MainPanel");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		rootPanel.add(verticalPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		horizontalPanel.add(verticalPanel_1);
		
		Label lblEnglish = new Label("English");
		verticalPanel_1.add(lblEnglish);
		verticalPanel_1.add(engTxtBox);
		
		VerticalPanel verticalPanel_2 = new VerticalPanel();
		horizontalPanel.add(verticalPanel_2);
		
		Label lblPirate = new Label("Pirate");
		verticalPanel_2.add(lblPirate);
		verticalPanel_2.add(pirateTxtBox);
		
		VerticalPanel verticalPanel_3 = new VerticalPanel();
		horizontalPanel.add(verticalPanel_3);
		horizontalPanel.setCellVerticalAlignment(verticalPanel_3, HasVerticalAlignment.ALIGN_BOTTOM);
		
		Button btnAdd = new Button("Add");
		verticalPanel_3.add(btnAdd);
		verticalPanel_3.setCellVerticalAlignment(btnAdd, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel_3.setCellHorizontalAlignment(btnAdd, HasHorizontalAlignment.ALIGN_CENTER);
		
		VerticalPanel verticalPanel_4 = new VerticalPanel();
		horizontalPanel.add(verticalPanel_4);
		horizontalPanel.setCellVerticalAlignment(verticalPanel_4, HasVerticalAlignment.ALIGN_BOTTOM);
		
		Button btnGetAll = new Button("Get All");
		verticalPanel_4.add(btnGetAll);
		horizontalPanel.setCellVerticalAlignment(btnGetAll, HasVerticalAlignment.ALIGN_BOTTOM);
		
		verticalPanel.add(flexTable);
		verticalPanel.setCellVerticalAlignment(flexTable, HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setCellHorizontalAlignment(flexTable, HasHorizontalAlignment.ALIGN_CENTER);
		
		btnAdd.addClickHandler(new AddClickHandler());
		btnGetAll.addClickHandler(new GetAllClickHandler());
	}
	
	//===========================================================================
	// True if the "msgText" is valid
	private static boolean CheckValidMsg(String msgText, TextBox txtBox)
	{
    if(!msgText.matches("^[a-z\\']{1,80}$")) 
    {
      Window.alert("'" + msgText + "' is not a valid symbol. Use only letters");
      txtBox.selectAll();
      return false;
    }
    if(msgText.equals(""))
    {
      Window.alert("Text Box is blank");
      txtBox.selectAll();
      return false;
    }
    txtBox.setText("");
    return true;
	}
	
	//===========================================================================	
	private void updateTable(Lexicon lexi)
	{
		String engText = lexi.getengText(), pirateText = lexi.getpirateText();
		if(flexIndex.contains(engText))
		{  return;  }
		int row = flexTable.getRowCount();
		flexIndex.add(engText);
		flexTable.setText(row, 0, engText);
		flexTable.setText(row, 1, pirateText);
		Button removeButton = new Button("x");
		removeButton.addClickHandler(new RemoveClickHandler(engText));
		flexTable.setWidget(row, 3, removeButton);
	}
	
	//---------------------------------------------------------------------------
	// 															   Handlers	
	//---------------------------------------------------------------------------
	
	//===========================================================================
	// Add button click handler
	private class AddClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{
			String engText = engTxtBox.getText().toLowerCase().trim();
			String pirateText = pirateTxtBox.getText().toLowerCase().trim();
			if(!CheckValidMsg(engText, engTxtBox)) {  return;  }
			if(!CheckValidMsg(pirateText, pirateTxtBox)) { return; }
			AsyncCallback<Void> callback = new AddCallback(engText, pirateText);
			webfrontsrvc.insertLexicon(new Lexicon(engText, pirateText), callback);			
			//webfrontsrvc.addLexicon(engText, pirateText, callback);
		}		
	}
	
	//===========================================================================
	private class GetAllClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event)
		{  webfrontsrvc.listLexicon(new GetAllCallback());  }
	}
	
	//===========================================================================
	private class RemoveClickHandler implements ClickHandler
	{
		final String engText;
		RemoveClickHandler(String _engText)
		{ engText = _engText; }
		
		@Override
		public void onClick(ClickEvent event)
		{  webfrontsrvc.removeLexicon(engText, new RemoveCallback(engText));  }		
	}
	
	//===========================================================================
	private class AddCallback implements AsyncCallback<Void>
	{
		private String eText, pText;
		
		public AddCallback(String _etext, String _ptext)
		{  eText = _etext; pText = _ptext;  }
		@Override
		public void onFailure(Throwable caught)
		{  Window.alert(onFailErrorTxt);  }
		@Override
		public void onSuccess(Void result)
		{ updateTable(new Lexicon(eText, pText));  }
	}
	
	//===========================================================================
	private class GetAllCallback implements AsyncCallback<List<Lexicon>>
	{
		@Override
		public void onFailure(Throwable caught)
		{ Window.alert(onFailErrorTxt); }
		@Override
		public void onSuccess(List<Lexicon> result)
		{
			Iterator<Lexicon> iter = result.iterator();
			while(iter.hasNext())
			{  updateTable(iter.next());  }
		}
	}
	
	//===========================================================================
	private class RemoveCallback implements AsyncCallback<Void>
	{
		final String engText;
		public RemoveCallback(String _engText)
		{ engText = _engText; }
		@Override
		public void onFailure(Throwable caught)
		{  Window.alert(onFailErrorTxt);  }
		@Override
		public void onSuccess(Void result)
		{
			int index = flexIndex.indexOf(engText);
			flexIndex.remove(index); flexTable.removeRow(index);
		}		
	}
}













