package persistence.entity.comparators;

import java.util.Comparator;

import persistence.entity.Faculty;

public class FacultyCountComparator implements Comparator<Faculty>{
	@Override
	public int compare(Faculty o1, Faculty o2) {
		return -Integer.compare(o1.getCount(), o2.getCount());
	}
}
