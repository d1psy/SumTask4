package persistence.entity.comparators;

import java.util.Comparator;

import persistence.entity.Faculty;;

/**
 * @author Golbutsov
 * Used to sort faculties by their names
 *
 */
public class FacultyNameComparator implements Comparator<Faculty> {

	@Override
	public int compare(Faculty o1, Faculty o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
