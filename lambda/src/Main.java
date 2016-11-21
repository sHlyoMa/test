import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        System.out.println("ForEach test");
        String separator = ", ";
        Arrays.asList("a", "b", "c", "d").forEach(e -> System.out.print(e + separator));
        System.out.println("\nSort test");
        List<String> strings = Arrays.asList("a", "b", "a", "c", "a");
        strings.sort((e1, e2) -> e1.compareTo(e2));
        System.out.println(strings);


        System.out.println("Test static interface method ");
        System.out.println(Animal.create(Elephant::new).say());
        System.out.println(Animal.create(Mouse::new).say());

        List<Animal> animals = Arrays.asList(Animal.create(Elephant::new), Animal.create(Mouse::new), Animal.create(Elephant::new));

        List<Car> cars = Arrays.asList(Car.create(Car::new), Car.create(Car::new));
        cars.forEach(Car::run);
        cars.forEach(Car::stop);
        Car police = Car.create(Car::new);
        cars.forEach(police::follow);

        System.out.println(Thread.currentThread().getId());
        new Thread(() -> System.out.println("Run " + Thread.currentThread().getId())).start();

        Testable testable = () -> System.out.println("Testable");
        testable.method();

        Optional<String> s = Optional.ofNullable("reew").filter(s1 -> "reew".equals(s1));
        System.out.println(s.get());

        final Collection<Streams.Task> tasks = Arrays.asList(
                new Streams.Task(Streams.Status.OPEN, 5),
                new Streams.Task(Streams.Status.OPEN, 13),
                new Streams.Task(Streams.Status.OPEN, 33),
                new Streams.Task(Streams.Status.CLOSED, 8),
                new Streams.Task(Streams.Status.OPEN, 23),
                new Streams.Task(Streams.Status.CLOSED, 4)
        );
        List<Integer> collect = tasks.stream().filter(task -> task.getStatus() == Streams.Status.OPEN).map(task -> task.getPoints()).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(tasks.stream().filter(task -> task.getStatus() == Streams.Status.OPEN).mapToInt(task -> task.getPoints()).sum());

        System.out.println(tasks.parallelStream().map(Streams.Task::getPoints).reduce(0,Integer::sum));
        System.out.println(tasks.stream().map(task -> task.getPoints()).sorted().max(Integer::compareTo));
        System.out.println(tasks.stream().collect(Collectors.groupingBy(Streams.Task::getStatus)));
//        System.out.println((int x, int y) -> x + y);

//        () -> { for (int i = 0; i < 1000; i++) System.out.println("1"); }

        Consumer<String> ss = System.out::println;
        ss.accept("sdsaasdasdasdas");
    }
}

class Streams {
    public enum Status {
        OPEN, CLOSED
    }

    ;

    public static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }
}

class Car {
    public static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void run(final Car car) {
        System.out.println("Run Run Run");
    }

    public void follow(final Car car) {
        System.out.println("Follow the " + car.toString());
        System.out.println("Follow the " + car.toString());
    }

    public void stop() {
        System.out.println("Stop Stop");
    }
}

interface Animal {

    static Animal create(Supplier<Animal> supplier) {
        return supplier.get();
    }

    static void run() {
        System.out.println("Running");
    }

    String say();
}

class Elephant implements Animal {

    @Override
    public String say() {
        return "Yeahhhhh";
    }
}

class Mouse implements Animal {
    @Override
    public String say() {
        return "Pipipipipi";
    }
}


@FunctionalInterface
interface Testable {

    void method();

    default void defaultMethod() {
        System.out.println("Testable.defaultMethod()");
    }

    default void secondDefaultMethod() {
        System.out.println("Testable.secondDefaultMethod()");
    }
}
