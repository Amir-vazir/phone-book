import java.util.ResourceBundle;

public class BundleManager {
    private static ResourceBundle bundle;

    public static void setBundle (ResourceBundle bundle){
        BundleManager.bundle = bundle;

    }

    public static String getMessage (String key){
      return   bundle.getString(key);
    }


}
