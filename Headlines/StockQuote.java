/******************************************************************************
 *  Compilation:  javac StockQuote.java
 *  Execution:    java StockQuote symbol
 *  Dependencies: In.java, StdOut.java
 *
 *  Print the stock price of the stock with the given symbol.
 *  Screen scrapes https://finance.yahoo.com to get the current price
 *  and associated information.
 *
 *  Warning: if Yahoo updates the format of their page,
 *           this program also needs to be updated.
 *
 * $ java-introcs StockQuote GOOG
 * 922.9
 * Alphabet Inc.
 * Thu Aug 10 11:40:07 UTC 2017 
 * $ java-introcs StockQuote GOOG source.GOOG.html 
 * 922.9
 * Alphabet Inc.
 * Thu Aug 10 11:40:07 UTC 2017 
 * $ java-introcs StockQuote AAPL
 * 161.06
 * Apple Inc.
 * Thu Aug 10 11:40:29 UTC 2017 
 * $ java-introcs StockQuote AAPL source.AAPL.html 
 * 161.06
 * Apple Inc.
 * Thu Aug 10 11:40:29 UTC 2017 
 * $ java-introcs StockQuote IBM
 * 141.77
 * International Business Machines
 * Thu Aug 10 11:40:43 UTC 2017 
 * $ java-introcs StockQuote MSFT
 * 72.47
 * Microsoft Corporation
 * Thu Aug 10 11:40:48 UTC 2017 
 *
 ******************************************************************************/

public class StockQuote {

    // Given symbol, get HTML
    private static String readHTML(String symbol) {
        In page = new In("https://finance.yahoo.com/quote/" + symbol);
        String html = page.readAll();
        if (html.contains("<title></title>")) return null;
        else return html;
    }

    // Given symbol, get current stock price.
    public static double priceOf(String symbol) {
        String html = readHTML(symbol);
	   return priceOf(symbol, html);
    }

    // Given symbol and html source, get current stock price.
    public static double priceOf(String symbol, String html) {
        int p     = html.indexOf("currentPrice", 0);      // "currentPrice" index
        int from  = html.indexOf("raw", p) + 4;           // "raw" index
        int to    = html.indexOf(",", from);              // "," index
        String price = html.substring(from + 1, to);
        // remove any comma separators
        return Double.parseDouble(price.replaceAll(",", ""));
    }

    // Given symbol, get current stock name.
    public static String nameOf(String symbol, String html) {
	int p    = html.indexOf("Summary for ", 0);
        int from = html.indexOf("for ", p);
        int to   = html.indexOf(" -", from);
        String name = html.substring(from + 4, to);
        return name;
    }

    // Given symbol, get current date.
    public static String dateOf(String symbol, String html) {
        int p    = html.indexOf("adx.bf1.yahoo.com", 0);
        int from = html.indexOf(" ", p);
        int to   = html.indexOf("-->", from);
        String date = html.substring(from + 1, to);
        return date;
    }

    public static void main(String[] args) {
        String symbol = args[0];
	String html;
	if (args.length > 1) {
	    In in = new In(args[1]);
	    html = in.readAll();
	} else 
	    html = readHTML(symbol);
	Out out = new Out("source." + symbol + ".html");
	out.print(html);
        if (html == null) StdOut.println("Invalid symbol: " + symbol);
        else {
            StdOut.println(priceOf(symbol, html));
            StdOut.println(nameOf(symbol, html));
            StdOut.println(dateOf(symbol, html));
        }
    }

}