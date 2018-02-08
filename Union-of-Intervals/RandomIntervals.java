/*
 * $ java-introcs RandomIntervals 5 .2
 * 0.2880413140073884 0.40944843012601034
 * 0.4914984213671654 0.5889065054275899
 * 0.296780018969031 0.3838288881068681
 * 0.5861809474564894 0.6931346451470255
 * 0.12464670036674895 0.18185849256668402
 * $ java-introcs RandomIntervals 100000 .0001 > 100000-.0001.in
 * $ java-introcs RandomIntervals 2000000 .00001 > 2000000-.00001.in
 * 
 */

public class RandomIntervals {

    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	double L = Double.parseDouble(args[1]);

	while (n-- > 0) {
	    double min = StdRandom.uniform(0, 1 - L);
	    double length = StdRandom.uniform(0, L);
	    StdOut.println(min + " " + (min + length));
	}
    }

}
