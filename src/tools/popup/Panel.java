package tools.popup;

import java.util.ArrayList;
import java.util.List;

public class Panel {
    private List<Component> components;

    public Panel() {
        components = new ArrayList<Component>();
    }

    public List<Component> getComponents() {
        return components;
    }

    public void addComponent(Component component) {
        components.add(component);
    }
}
