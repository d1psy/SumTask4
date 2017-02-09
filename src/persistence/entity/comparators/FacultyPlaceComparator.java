package persistence.entity.comparators;

import java.util.Comparator;

import persistence.entity.Faculty;

/**
 * @author Golbutsov
 * Used to sort faculties by their max place
 *
 */
public class FacultyPlaceComparator implements Comparator<Faculty> {

	@Override
	public int compare(Faculty o1, Faculty o2) {
		return -Integer.compare(o1.getMaxplace(), o2.getMaxplace());
	}

}