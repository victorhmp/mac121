import java.util.Arrays;
import java.util.Comparator;

public class Anagrams  {
	
	// function returns each string sorted and lowercase
	public static String lowerSort(String s1){
		s1 = s1.toLowerCase();
		char[] s1AsChar = s1.toCharArray();
		Arrays.sort(s1AsChar);

		s1 = new String(s1AsChar);

		return s1;
	}

	public static void main(String[] args) {
		String[] in = StdIn.readAllLines();
		arrElement[] final_arr = new arrElement[in.length];

		for(int i=0;i<final_arr.length;i++){
			// this is the array where the final result will be
			final_arr[i] = new arrElement (in[i], lowerSort(in[i]));
		}

		Arrays.sort(final_arr, arrElement.SORTEDCOMP);

		boolean isAnagram = false;

		for(int i=0;i<final_arr.length - 1;i++){
			if(final_arr[i].sorted().compareTo(final_arr[i+1].sorted())==0){
				StdOut.print("* " + final_arr[i].original() + " " + final_arr[i+1].original());
				isAnagram = true;
				while(isAnagram){
					i++;
					if(final_arr[i].sorted().compareTo(final_arr[i+1].sorted())!=0){
						isAnagram = false;
						StdOut.print("\n");
						break;
					}
					StdOut.print(" " + final_arr[i+1].original());
				}
			}
		}
	}
	/* function to return if any 2 given strings are anagrams
	
	public static boolean isAnagram(String s1, String s2){
		if(s1.length()==s2.length()){

			s1.toLowerCase();
			s2.toLowerCase();

			char[] s1AsChar = s1.toCharArray();
			char[] s2AsChar = s2.toCharArray();

			Arrays.sort(s1AsChar);
			Arrays.sort(s2AsChar);

			return Arrays.equals(s1AsChar, s2AsChar);
		}

		return false;
	}

	*/
}