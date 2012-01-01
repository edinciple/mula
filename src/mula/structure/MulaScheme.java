package mula.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MulaScheme {
	static final String VARIABLE = "variable";
	static final String REPEAT = "repeat";
	
	public MulaScheme(MulaList schemeList) {
		this.schemeList = schemeList;
	}
	
	MulaList schemeList;
	
	public MulaList getSchemeList() {
		return this.schemeList;
	}
	
	public Map<MulaAtom, List<MulaSubstance>> match(MulaList source) throws Exception {
		return matchList(source, this.getSchemeList());
	}
	
	private static Map<MulaAtom, List<MulaSubstance>> matchList(MulaList source, MulaList scheme) throws Exception {
		HashMap<MulaAtom, List<MulaSubstance>> variables = new HashMap<MulaAtom, List<MulaSubstance>>();
		
		for(int schemeIndex = 0, sourceIndex = 0; schemeIndex < scheme.size();) {
			MulaSubstance schemeSubstance = scheme.get(schemeIndex);
			MulaSubstance sourceSubstance = source.get(sourceIndex);
			
			if(isSymbol(schemeSubstance)) {
				if(sourceSubstance.equals(schemeSubstance)) {
					schemeIndex ++;
					sourceIndex ++;
				} else {
					return null;
				}
			} else if (isVariable(schemeSubstance)) {
				addVariable(getVariableName(schemeSubstance), sourceSubstance, variables);
				
				schemeIndex ++;
				sourceIndex ++;
			} else if (isRepeat(schemeSubstance)) {
				if(!(schemeIndex == scheme.size() - 1)) {
					throw new Exception("Expect repeat atom at the end of the list");
				}
				
				MulaSubstance lastSchemeSubstance = scheme.get(schemeIndex - 1);
				
				if(isRepeat(lastSchemeSubstance)) {
					throw new Exception(String.format("Unexpected repect atom at {0} of the scheme list {1}", schemeIndex - 1, scheme.toString()));
				}
				
				if(isVariable(lastSchemeSubstance)) {
					addVariable(getVariableName(lastSchemeSubstance), sourceSubstance, variables);
				} else if (isSymbol(lastSchemeSubstance)) {
					if(!lastSchemeSubstance.equals(sourceSubstance)) {
						return null;
					}
				} else {
					Map<MulaAtom, List<MulaSubstance>> subVariableCollection = matchList((MulaList)sourceSubstance, (MulaList)lastSchemeSubstance);
					if(subVariableCollection == null) {
						return null;
					} else {
						combineVariableCollections(subVariableCollection, variables);
					}
				}
				sourceIndex ++;
				
			} else {
				Map<MulaAtom, List<MulaSubstance>> subVariableCollection = matchList((MulaList)sourceSubstance, (MulaList)schemeSubstance);
				if(subVariableCollection == null) {
					return null;
				} else {
					combineVariableCollections(subVariableCollection, variables);
				}
				
				schemeIndex ++;
				sourceIndex ++;
			}
		}
		
		return variables;
	}
	
	private static void addVariable(MulaAtom key, MulaSubstance value, Map<MulaAtom, List<MulaSubstance>> collection) {
		if(!collection.containsKey(key)) {
			collection.put(key, new ArrayList<MulaSubstance>());
		}
		
		collection.get(key).add(value);
	}
	
	private static void combineVariableCollections(Map<MulaAtom, List<MulaSubstance>> subVariableCollection, 
			Map<MulaAtom, List<MulaSubstance>> mainVariableCollection) {
		for(MulaAtom subKey:subVariableCollection.keySet()) {
			for(MulaSubstance subSubstance:subVariableCollection.get(subKey)) {
				addVariable(subKey, subSubstance, mainVariableCollection);
			}
		}
	}
	
	private static boolean isSymbol(MulaSubstance substance) throws Exception {
		if(isRepeat(substance)) {
			return false;
		}
		
		if(!(substance instanceof MulaAtom)) {
			return false;
		}
		
		return true;
	}
	
	private static boolean isVariable(MulaSubstance substance) throws Exception {
		if(!(substance instanceof MulaList)) {
			return false;
		}
		
		MulaList list = (MulaList)substance;
		
		if(!(list.get(0).equals(MulaAtom.get(VARIABLE)))) {
			return false;
		}
		
		if(!(list.size() == 2 && list.get(1) instanceof MulaAtom)) {
			throw new Exception("The second item of variable expected Atom");
		}
		
		return true;
	}
	
	private static MulaAtom getVariableName(MulaSubstance substance) throws Exception {
		if(!isVariable(substance)) {
			throw new Exception("Expected variable");
		}
		
		return (MulaAtom)((MulaList)substance).get(1);
	}
	
	private static boolean isRepeat(MulaSubstance substance) {
		if(!(substance instanceof MulaAtom)) {
			return false;
		}
		
		return ((MulaAtom)substance).equals(MulaAtom.get(REPEAT));
	}
}
