package ua.ithillel.reflect;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.reflect.anno.MyAnnotation;
import ua.ithillel.reflect.calc.CalculatorTest;
import ua.ithillel.reflect.drink.client.CocktailDbClient;
import ua.ithillel.reflect.drink.client.DrinkClient;
import ua.ithillel.reflect.drink.exception.DrinkClientException;
import ua.ithillel.reflect.drink.model.Drink;
import ua.ithillel.reflect.drink.proxy.CacheHandler;
import ua.ithillel.reflect.drink.service.DrinkService;
import ua.ithillel.reflect.drink.service.DrinkServiceDefault;
import ua.ithillel.reflect.shapes.Circle;
import ua.ithillel.reflect.shapes.Shape;
import ua.ithillel.reflect.shapes.Square;
import ua.ithillel.reflect.test.TestRunner;
import ua.ithillel.reflect.vehicle.Car;
import ua.ithillel.reflect.vehicle.Vehicle;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
//        TestRunner.start(CalculatorTest.class);


        // FIXME: Proxy Example
        DrinkClient client = new CocktailDbClient(HttpClient.newHttpClient(), new ObjectMapper());

        CacheHandler cacheHandler = new CacheHandler(client);

        DrinkClient proxyClient = (DrinkClient) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class<?>[]{
                DrinkClient.class
        }, cacheHandler);

        DrinkService service = new DrinkServiceDefault(proxyClient);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of a drink you'd like to know recipe of:");
        System.out.println("Or type '-exit' to exit");
        while (scanner.hasNext()) {
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("-exit")) {
                break;
            }

            try {
                Drink drinkByName = service.getDrinkByName(name);

                System.out.println("Here are the ingredients you're gonna need:");
                for (String ingredient : drinkByName.getIngredients()) {
                    System.out.printf("- %s%n",ingredient);
                }

                System.out.println();
                System.out.println("How to prepare: ");
                System.out.println(drinkByName.getInstruction());
                System.out.println();


                System.out.println("Enter the name of a drink you'd like to know recipe of:");
                System.out.println("Or type '-exit' to exit");
            } catch (DrinkClientException e) {
                System.out.println("Couldn't find the recipe :( Try again:");
            }

        }

//        Drink cubaLibre = service.getDrinkByName("Cuba libre");
//        Drink margarita = service.getDrinkByName("margarita");
//         cubaLibre = service.getDrinkByName("Cuba libre");
//        Drink bloodyMary = service.getDrinkByName("Bloody mary");


        // FIXME: Reflection

        try {
            Vehicle vehicle = new Car("AAi76t786t876t9");
            Object o = vehicle;

            // if class is available
            Class<Car> carClass = Car.class;

            // by any object
            Class<?> aClass = o.getClass();

            // by class name
            Class<?> forName = Class.forName("ua.ithillel.reflect.vehicle.Car");


            String className = carClass.getName();
            String simpleName = carClass.getSimpleName();
            System.out.println(className);
            System.out.println(simpleName);

            Package aPackage = carClass.getPackage();
            System.out.println(aPackage.getName());

            int modifiers = carClass.getModifiers(); // 17
            // 0001 0001 = 17
            System.out.println("Car modifiers: " + modifiers);
            System.out.println("public: " +  Modifier.isPublic(modifiers));
            // 0001 0001 = 17 & 1 = 1 true

            System.out.println("final: " +  Modifier.isFinal(modifiers));
            // 0001 0001 = 17 & 16 = 1 true
            // 0001 0000 - true
            System.out.println("static: " +  Modifier.isStatic(modifiers));
            // 0001 0001 = 17 & 16 = 1 true
            // 0000 1000 = 0 false
            System.out.println("private: " +  Modifier.isPrivate(modifiers));

            Field[] fields = carClass.getDeclaredFields();
            for (Field field :
                    fields) {
                System.out.printf("Field: %s %d%n", field.getName(), field.getModifiers());
            }

//            Method[] methods = carClass.getMethods();
            Method[] methods = carClass.getDeclaredMethods();
            for (Method method :
                    methods) {
                String name = method.getName();
                int methodModifiers = method.getModifiers();
                int parameterCount = method.getParameterCount();
                Class<?>[] parameterTypes = method.getParameterTypes();
                Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                for (Annotation anno :
                        declaredAnnotations) {
                    System.out.println(anno);

                }

                System.out.printf("Method: %s %d %d %s %s%n",
                        name, methodModifiers, parameterCount, Arrays.asList(parameterTypes), Arrays.toString(declaredAnnotations));

            }

            Method setSeatsMethod = carClass.getMethod("setSeats", int.class);
            MyAnnotation annotation = setSeatsMethod.getAnnotation(MyAnnotation.class);
            int count = annotation.count();

            System.out.println(setSeatsMethod);
            setSeatsMethod.invoke(vehicle, count);

            Method repairMethod = carClass.getDeclaredMethod("repair");
            repairMethod.setAccessible(true);
            repairMethod.invoke(vehicle);

            Constructor<?>[] constructors = carClass.getConstructors();
            for (Constructor<?> constructor :
                    constructors) {
                System.out.printf("Constructor: %s%n", Arrays.toString(constructor.getParameterTypes()));
            }

            Constructor<Car> nonDefaultConstructor = carClass.getConstructor(String.class);
            Car car = nonDefaultConstructor.newInstance("UA45362781929");



            System.out.println();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }

//        Shape s = new Square();
//        Shape c = new Circle();
//
//        c.draw();
//        s.draw();
    }
}
