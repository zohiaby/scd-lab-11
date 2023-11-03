

import java.util.ArrayList;


public class AssignRootsBO {
	
	private AssignRootsDAO assRoot = new AssignRootsDAO();
	
	public void assignRootsToVerse() {
		
		ArrayList<String> tokens = assRoot.getTokensFromDB();
		ArrayList<String> roots = assRoot.getRootsFromDB();
		
		for(String roo : roots) {
			for(String too : tokens) {
				assRoot.assignRootsToVerse(roo, too);
			}
		}
	}
	
	
	public void displayRootsAssign() {
		assRoot.displayRootsWithTokens();
	}
}
