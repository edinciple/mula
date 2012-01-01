package mula.structure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public final class MulaList extends MulaSubstance implements List<MulaSubstance> {
	ArrayList<MulaSubstance> subItems = new ArrayList<MulaSubstance>();

	@Override
	public boolean add(MulaSubstance arg0) {
		return subItems.add(arg0);
	}

	@Override
	public void add(int arg0, MulaSubstance arg1) {
		subItems.add(arg0, arg1);
	}

	@Override
	public boolean addAll(Collection<? extends MulaSubstance> arg0) {
		return subItems.addAll(arg0);
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends MulaSubstance> arg1) {
		return subItems.addAll(arg0, arg1);
	}

	@Override
	public void clear() {
		subItems.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		return subItems.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return subItems.containsAll(arg0);
	}

	@Override
	public MulaSubstance get(int arg0) {
		return subItems.get(arg0);
	}

	@Override
	public int indexOf(Object arg0) {
		return subItems.indexOf(arg0);
	}

	@Override
	public boolean isEmpty() {
		return subItems.isEmpty();
	}

	@Override
	public Iterator<MulaSubstance> iterator() {
		return subItems.iterator();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		return subItems.lastIndexOf(arg0);
	}

	@Override
	public ListIterator<MulaSubstance> listIterator() {
		return subItems.listIterator();
	}

	@Override
	public ListIterator<MulaSubstance> listIterator(int arg0) {
		return subItems.listIterator(arg0);
	}

	@Override
	public boolean remove(Object arg0) {
		return subItems.remove(arg0);
	}

	@Override
	public MulaSubstance remove(int arg0) {
		return subItems.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return subItems.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return subItems.retainAll(arg0);
	}

	@Override
	public MulaSubstance set(int arg0, MulaSubstance arg1) {
		return subItems.set(arg0, arg1);
	}

	@Override
	public int size() {
		return subItems.size();
	}

	@Override
	public List<MulaSubstance> subList(int arg0, int arg1) {
		return subItems.subList(arg0, arg1);
	}

	@Override
	public Object[] toArray() {
		return subItems.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return subItems.toArray(arg0);
	}
	
	@Override
	public String toString() {
		String result = "(";
		for(int i = 0;i<this.size();i++) {
			result += this.get(i).toString();
			if(i != this.size() - 1) {
				result += " ";
			}
		}
		result += ")";
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MulaList)) {
			return false;
		}
		
		MulaList list = (MulaList)obj;
		
		if(!(list.size() == this.size())) {
			return false;
		}
		
		for(int i = 0;i<this.size();i++) {
			if(!(list.get(i).equals(this.get(i)))) {
				return false;
			}
		}
		
		return true;
	}
}
