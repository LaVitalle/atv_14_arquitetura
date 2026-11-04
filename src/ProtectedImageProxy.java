public class ProtectedImageProxy implements Image {

    private final Image image;
    private final boolean hasPermission;

    public ProtectedImageProxy(Image image, boolean hasPermission) {
        this.image = image;
        this.hasPermission = hasPermission;
    }

    @Override
    public void display() {
        if (!hasPermission) {
            System.out.println("Access denied: you do not have permission to view this image");
            return;
        }
        image.display();
    }
}
