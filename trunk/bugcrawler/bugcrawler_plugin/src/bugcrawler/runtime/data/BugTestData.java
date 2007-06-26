package bugcrawler.runtime.data;

import java.util.Calendar;
import java.util.Date;

public class BugTestData {
	
	public static Object[] objects;
	
	public static Object[] getTestData(){
		
		Calendar cal = Calendar.getInstance();
		cal.set(2007, 11, 15);
		Project proj = new Project("Bugcrawler", "Tobias", new Date());
		new Bug("#3", "Keine Selektionsmöglichkeiten für die Projekte", "Other", "0.1 aplha", "0.1",
				"task","normal","Tobias",cal.getTime(),Priority.Highest,proj);
		
		cal.set(2006, 11, 12);
		new Bug("#1", "Das Wizard wird nicht angezeigt", "Wizard", "0.1 aplha", "0.1",
				"defect","normal","Jörn",cal.getTime(),Priority.High,proj);

		cal.set(2006, 11, 11);
		new Bug("#6", "PreferencesMenü soll für Projektauswahlen erweitert werden.", "Preferences", "0.1 aplha", "0.1",
				"task","normal","Tobias",cal.getTime(),Priority.High,proj);		
		
		new Bug("#4", "Keine Filtermöglichkeiten", "Filters", "0.1 aplha", "0.1",
				"task","normal","Tobias",new Date(),Priority.Medium,proj);
					
		new Bug("#5", "TreeTable kann nicht sortiert werden, sachter Jörn", "Tree", "0.1 aplha", "0.1",
				"task","normal","Tobias",new Date(),Priority.Low,proj);
	
		new Bug("#2", "Der Tree soll nur ein Projekt anzeigen!1 :SSS", "Tree", "0.1 aplha", "0.1",
				"task","normal","Jörn",new Date(),Priority.Lowest,proj).setDescription("Hier meinte der Jörn" +
						", dass wir den Tree nur auf einen Projekt-Datensatz anpassen müssen und nicht auf mehrere" +
						"- obs zwingend ein Bug ist fraglich! ;D");
		
		Project proj2 = new Project("Intension", "Tobias", new Date());
		new Bug("#3", "The Datamodel of the Browser is not working correctly", "Browser", "none", "none",
				"defect","major","Tobias",new Date(),Priority.High,proj2);
		
		new Bug("#6", "The chat making troubles in Server-Client-Communication", "Chat", "none", "none",
				"defect","critical","Tobias" ,new Date(),Priority.High,proj2);
		
		if(objects == null){
			objects = new Object[] { proj, proj2 };
		}
		return objects;
	}

}
