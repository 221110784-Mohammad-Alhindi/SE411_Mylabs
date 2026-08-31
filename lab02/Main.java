import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Exercise 1
        String[] names = {"Mohammad", "Saud", "Faisal"};

        PrintableList<String> printableList =
                new PrintableList<>(names);

        System.out.println("Exercise 1:");
        printableList.printItems();


        // Exercise 2 - Integer
        NumberBox<Integer> integerBox = new NumberBox<>();

        integerBox.setItem(10);

        System.out.println("\nExercise 2 - Integer:");
        System.out.println("Item: " + integerBox.getItem());
        System.out.println("Addition: " + integerBox.add(5));


        // Exercise 2 - Double
        NumberBox<Double> doubleBox = new NumberBox<>();

        doubleBox.setItem(10.5);

        System.out.println("\nExercise 2 - Double:");
        System.out.println("Item: " + doubleBox.getItem());
        System.out.println("Addition: " + doubleBox.add(5.5));


        // Sum a list of Numbers
        List<Integer> integerNumbers =
                Arrays.asList(10, 20, 30, 40);

        System.out.println("\nSum of Integer list: "
                + NumberBox.sumNumbers(integerNumbers));


        List<Double> doubleNumbers =
                Arrays.asList(1.5, 2.5, 3.5);

        System.out.println("Sum of Double list: "
                + NumberBox.sumNumbers(doubleNumbers));
    
            // Exercise 3 - Pipeline

        PipeLine<String, String> pipeline =
                new PipeLine<>();

        PipeLine<String, String> upperCasePipeline =
                pipeline.addTransformer(
                        input -> input.toUpperCase()
                );

        PipeLine<String, Integer> lengthPipeline =
                upperCasePipeline.addTransformer(
                        input -> input.length()
                );

        Integer result = lengthPipeline.execute("hello");

        System.out.println("\nExercise 3 - Pipeline:");
        System.out.println("Result: " + result);

                // Exercise 4

        List<String> students =
                Arrays.asList("Mohammad", "Saud", "Faisal");

        System.out.println("\nExercise 4 - printList:");
        printList(students);


        List<Integer> numbers =
                Arrays.asList(10, 20, 30);

        System.out.println("\nExercise 4 - sumNumbers:");
        System.out.println("Sum: " + sumNumbers(numbers));
            
    }

public static void printList(List<?> list) {

    for (Object item : list) {
        System.out.println(item);
    }
}

public static double sumNumbers(List<? extends Number> numbers) {

    double sum = 0;

    for (Number number : numbers) {
        sum += number.doubleValue();
    }

    return sum;
}
}

    