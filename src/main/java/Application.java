import controller.KioskController;
import repository.KioskRepository;
import service.KioskService;

public class Application {
    public static void main(String[] args) {
        KioskController controller = new KioskController(service());
        controller.run();
    }

    public static KioskService service() {
        return new KioskService(kioskRepository());
    }

    public static KioskRepository kioskRepository() {
        return new KioskRepository();
    }
}

