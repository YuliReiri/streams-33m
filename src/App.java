import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.print.Collation;

public class App {
    public static void main(String[] args) throws Exception {

        List<Integer> li = Arrays.asList( 1, 2, 3, 4, 5, 6, 7);
        int res = 0;

        for ( Integer i : li ) {
            if ( i % 2 != 0 ) {
                res = res + i;
            }
        }
        System.out.println(res);

       // res = li.stream().filter( p -> p % 2 != 0).reduce( (y1, y2 ) -> y1 +y2 ).orElse( 0 );

       //res = li.stream().reduce( (y1, y2 ) -> y1+y2 ).orElse( 0 );

       //res = li.stream().reduce(3, (y1, y2 ) -> y1+y2 );
       System.out.println( res );

       List<Integer> lie = Arrays.asList( );
       Optional<Integer> i1 = lie.stream().reduce( (y1, y2 ) -> y1+y2 );

        // System.out.println( i1.orElse(0) );

        String str = "YU-ty";
        Predicate<String> pS1 = s -> s.split("-").length < 3;
        Predicate<String> pS2 = s-> s.startsWith("X");

        Predicate<String> pS3 = pS1.and( pS2 );

      ///  System.out.println( str.split("-").length);
        System.out.println( pS1.test( str ) );
        System.out.println( pS2.test( str ) );
        System.out.println( pS3.test( str ) );

        List<String> il2 = Arrays.asList( "dd", "zs", "TR", "18YU", "10t", "3342", "23r2r3", "dsfdsfsdf");
        List<String> resI2 = il2.stream().sorted().collect( Collectors.toList() );
        System.out.println(resI2);

        List<String> resI3 = il2.stream().sorted( (s1,s2) -> s1.length() - s2.length()  ).collect( Collectors.toList() );
        System.out.println(resI3);

        String resI4 = il2.stream().sorted( (s1,s2) -> s1.length() - s2.length()  ).reduce((s1,s2) -> s1 +":" + s2  ).orElse("");
        System.out.println(resI4);

        Integer resI5 = il2.stream().sorted( 
            (s1,s2) -> s1.length() - s2.length()  ).map( s3 -> s3.length() ).reduce((s1,s2) -> s1 + s2  ).orElse(0);

        System.out.println(resI5);

        Stream<Integer> sI2 = Stream.of( 1, 10, 15, 2,3,5,6 ); 

        List<Integer> resL =  sI2.limit(2).collect( Collectors.toList() );

        System.out.println(resL);

        //Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        // this is won't work because stream are already was colosed in line 62 with method colllect
        //List<Integer> resL1 =  sI2.skip( 3 ).collect(Collectors.toList());
        //System.out.println(resL1);

        Stream<Integer> sI3 = Stream.of( 1,2,3,5,6 ); 
        List<Integer> resL1 =  sI3.skip( 2 ).collect(Collectors.toList());
        System.out.println( resL1 );

        Stream<Integer> sI4 = Stream.of( 1,2,3,5,6,2,6,8,3,5,6 ); 

        List<Integer> resL2 =  sI4.skip( 4 ).limit(4).collect(Collectors.toList());
        System.out.println(resL2);

        Stream<Integer> sI5 = Stream.of( 1,2,3,5,6,2,6,8,3,5,6 ); 
        List<Integer> resL3 = sI5.distinct().collect(Collectors.toList());

        System.out.println( resL3 );

        Stream<Integer> sI6 = Stream.of( 1,2,3,5,6,2,6,8,3,5,6 ); 
        sI6.peek( s -> System.out.print( s + "_") ).reduce((x1,x2) -> x1 + x2 );

    
        Stream<Integer> sI7 = Stream.of( 1,2,3,5,6,2,6,8,3,5,6 ); 

        Set<String> r7 = sI7.map( x -> Integer.toString(x*x) + ":" ).collect( Collectors.toSet());

        System.out.println( r7 );

        Stream<List<Integer>> rI8 = Stream.of( Arrays.asList(1,2,3),  
                        Arrays.asList(1,2,3,4,5,6), Arrays.asList(8,16,3, 4, 10));
 
        List<Integer> r8 = rI8.map( l2 -> {
            List<Integer> nL = new ArrayList<>();
            l2.forEach( r1-> nL.add(r1*r1));
            return nL;
        } ).flatMap( 
            (List<Integer>l) -> l.stream() ).sorted().distinct().collect(Collectors.toList());
        System.out.println( r8 );


        List<String> words = Arrays.asList( "a1", "b2", "g4", "u8", "u8", "a1", "g4", "u8", "a1", "a1");
    //Exception in thread "main" java.lang.IllegalStateException: Duplicate key u8 (attempted merging values 1 and 1)
       Map<String, Integer> res9 =  words.stream().collect(Collectors.toMap( (String x) -> x, (String x) -> 1,
            (y1, y2) -> y1+y2 ));

       System.out.println( res9 );

       System.out.println( words.stream().findFirst().get());
       System.out.println( words.stream().findAny().get());
       System.out.println( words.stream().anyMatch( p -> p.equals("bwew2") ));
       System.out.println( words.stream().anyMatch( p -> p.equals("b2") ));

       Stream<Integer> sI9 = Stream.of( 1,2,3,5,6,2,6,8,3,5,6,7 ); 
       System.out.println(sI9.filter( p -> p > 5).anyMatch( p -> p ==3));

       Stream<Integer> sI10 = Stream.of( 1,2,3,5,6,2,6,8,3,5,6,7 ); 
       System.out.println(sI10.anyMatch( p -> p ==3 ));

       Stream<Integer> sI11 = Stream.of( 1,2,3,5,6,2,6,8,3,5,6,7 ); 
       System.out.println(sI11.allMatch( p -> p <30 ));

       Stream<Integer> sI12 = Stream.of( 1,2,3,5,6,2,6,8,3,5,6,7 ); 
       System.out.println( sI12.min( (x1,x2) -> -x1 +x2).get() );








    }
}
