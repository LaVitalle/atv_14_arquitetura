import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Image> gallery = new ArrayList<>();

        gallery.add(new ImageProxy("forest.jpg"));
        gallery.add(new ImageProxy("beach.jpg"));
        gallery.add(new ImageProxy("mountain.jpg"));

        System.out.println("Gallery created");

        System.out.println("\n=== Displaying the first image twice ===");
        gallery.get(0).display();
        gallery.get(0).display();

        System.out.println("\n=== Displaying the second image ===");
        gallery.get(1).display();

        System.out.println("\n(The third image was never displayed, so it was never loaded)");

        System.out.println("\n=== Protected images ===");
        Image denied = new ProtectedImageProxy(new ImageProxy("secret.jpg"), false);
        Image allowed = new ProtectedImageProxy(new ImageProxy("private.jpg"), true);

        System.out.println("Trying without permission:");
        denied.display();

        System.out.println("Trying with permission:");
        allowed.display();
    }
}
