# predicate practice

priceRangeQuery takes 2 float arguments representing
the minimum and maximum price of the query. The Predicate
it returns checks whether or not a Beer object's price
instance variable is both greater than or equal to the 
min price, and less than or equal to the max.

countryQuery takes a string argument representing the name
of a country. The returned predicate tests a Beer object's
country instance variable for equality against the string.

The beerQuery function takes a List of Beers and a Predicate
argument. It streams the List argument and uses filter() with
the Predicate to check for Beer objects that pass the test.
It then collects those elements to a new List<Beer> and returns
the new List.

BeerFest's main() method is set up to parse 3 command line
arguments. If 3 arguments are not provided, it prints a usage
String to stdout. The first argument is used to call beerQuery
and filter a List of Beers for those whose country matches the
first argument. The next 2 arguments are converted to floats
and used to check the same original List of Beers for those
that are in the price range. Results are printed to stdout.
