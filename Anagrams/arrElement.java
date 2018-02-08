import java.util.Arrays;
import java.util.Comparator;

public class arrElement {
		private final String ori; // original string
		private final String ord; // string sorted by char

		public static final Comparator<arrElement> SORTEDCOMP = new SortedFormComparator();

		public arrElement(String original, String sorted){
			ori = original;
			ord = sorted;
		}

		public String original() {
			return ori;
		}

		public String sorted() {
			return ord;
		}

		// custom sorting method, to sort input strings by their sorted form
		private static class SortedFormComparator implements Comparator<arrElement> {
			public int compare(arrElement a, arrElement b){
				return a.ord.compareTo(b.ord);
			}
		}
}