/* Adapted from code in "Java Programming", Chapter 20
   by Yakov Fain
 */
 package cscie55.hw7.problem1;
 
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BeerFest provides a Beer class and member functions
 * for checking a List of Beers for country of origin
 * and price range using Streams and Predicates.
 *
 * Takes 3 arguments from cmd line - country, min price,
 * and max price.
 */
public class BeerFest {
    public  static class Beer {
	    public final String name;
	    public final String country;
	    private float price;

        public Beer(String name, String country,float price){
            this.name=name;
            this.country=country;
            this.price=price;
        }
	
        public String toString(){
            return "Country: " + country +  " Name: " + name + ", price: " + price;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }

    // returns a List of Beers from provided List passing the Predicate
    public static List<Beer> beerQuery(List<Beer> beerList, Predicate <Beer> predicate) {
        List<Beer> result = beerList.stream().filter(predicate).collect(Collectors.toList());
        return result;
    }

    static List<Beer> loadCellar(){
        List<Beer> beerStock = new ArrayList<>();

        beerStock.add(new Beer("Stella", "Belgium", 7.75f));
        beerStock.add(new Beer("Sam Adams", "USA", 7.00f));
        beerStock.add(new Beer("Obolon", "Ukraine", 4.00f));
        beerStock.add(new Beer("Bud Light", "USA", 5.00f));
        beerStock.add(new Beer("Yuengling", "USA", 5.50f));
        beerStock.add(new Beer("Leffe Blonde", "Belgium", 8.75f));
        beerStock.add(new Beer("Chimay Blue", "Belgium", 10.00f));
        beerStock.add(new Beer("Brooklyn Lager", "USA", 8.25f));

        return beerStock;
    }

    // returns a Predicate<Beer> that tests a Beer for price in the min and max
    static Predicate<Beer> priceRangeQuery(float min, float max) {
        return (beer) -> beer.getPrice() >= min && beer.getPrice() <= max;
    }

    // returns a Predicate<Beer> that tests a Beer for country matching the country arguement
    static Predicate<Beer> countryQuery(String country) {
        return  (beer) -> beer.country == country;
    }

    // takes 3 cmd line arguments and runs priceRangeQuery and countryQuery on the List
    // returned by loadCellar(). Prints results to stdout. Prints a usage string to
    // stdout if 3 arguments are not provided.
    public static void main(String argv[]) {
        if (argv.length == 3) {
            List<Beer> beerList = loadCellar();

            String country = argv[0];
            System.out.println("Beers from " + country + ":");
            beerQuery(beerList, countryQuery("USA")).forEach(System.out::println);

            float min = Float.parseFloat(argv[1]);
            float max = Float.parseFloat(argv[2]);
            System.out.println("Beers between $" + argv[1] + " and $" + argv[2] + ":");
            beerQuery(beerList, priceRangeQuery(min, max)).forEach(System.out::println);
        }
        else {
            System.out.println("USAGE: [COUNTRY] [MAX PRICE] [MIN PRICE]");
        }
    }
}
