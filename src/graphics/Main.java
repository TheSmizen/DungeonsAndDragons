/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;
import java.io.FileNotFoundException;
/**
 *
 * @author p0073862
 */
public class Main {
    public static void main() throws FileNotFoundException{
        KModel model = new KModel();
        KView view = new KView(model);
        KController controller = new KController(model, view);
        view.setController(controller);
        model.addObserver(view);
        view.update(model, null);
    }
}
