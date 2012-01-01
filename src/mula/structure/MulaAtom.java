package mula.structure;

import java.util.HashMap;

public final class MulaAtom extends MulaSubstance {
	String meaning;
	
	public String getMeaning()
	{
		return this.meaning;
	}
	
	private MulaAtom(String meaning)
	{
		this.meaning = meaning;
	}
	
	static HashMap<String, MulaAtom> usedAtoms = new HashMap<String, MulaAtom>();
	public static MulaAtom get(String meaning) {
		if(!usedAtoms.containsKey(meaning)) {
			usedAtoms.put(meaning, new MulaAtom(meaning));
		}
		return usedAtoms.get(meaning);
	}
	
	@Override
	public String toString()
	{
		return this.getMeaning();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MulaAtom)) {
			return false;
		}
		
		MulaAtom atom = (MulaAtom)obj;
		
		if(!(atom.getMeaning().equals(this.getMeaning()))) {
			return false;
		}
		
		return true;
	}
}
