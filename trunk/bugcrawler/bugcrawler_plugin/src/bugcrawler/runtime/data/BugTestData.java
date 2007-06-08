package bugcrawler.runtime.data;

import java.util.Date;

public class BugTestData {
	public static Object[] getTestData(){
		Project proj = new Project("Bugcrawler", "Tobias", new Date());
		new Bug("#3", "Keine Selektionsmöglichkeiten für die Projekte", "Other", "0.1 aplha", "0.1",
				"task","normal","Tobias",new Date(),Priority.Highest,proj);
		
		new Bug("#1", "Das Wizard wird nicht angezeigt", "Wizard", "0.1 aplha", "0.1",
				"defect","normal","Tobias",new Date(),Priority.High,proj);

		new Bug("#6", "PreferencesMenü soll für Projektauswahlen erweitert werden.", "Preferences", "0.1 aplha", "0.1",
				"task","normal","Tobias",new Date(),Priority.High,proj);		
		
		new Bug("#4", "Keine Filtermöglichkeiten", "Filters", "0.1 aplha", "0.1",
				"task","normal","Tobias",new Date(),Priority.Medium,proj);
					
		new Bug("#5", "TreeTable kann nicht sortiert werden", "Tree", "0.1 aplha", "0.1",
				"task","normal","Tobias",new Date(),Priority.Low,proj);
	
		new Bug("#2", "Der Tree soll nur ein Projekt anzeigen!1 :SSS", "Tree", "0.1 aplha", "0.1",
				"task","normal","Jörn",new Date(),Priority.Lowest,proj);
		
		Project proj2 = new Project("Intension", "Tobias", new Date());
		new Bug("#3", "The Datamodel of the Browser is not working correctly", "Browser", "none", "none",
				"defect","major","Tobias",new Date(),Priority.High,proj2);
		
		new Bug("#6", "The chat making troubles in Server-Client-Communication", "Chat", "none", "none",
				"defect","critical","Tobias" ,new Date(),Priority.High,proj2);
		
		return new Object[] { proj, proj2 };
	}

}
