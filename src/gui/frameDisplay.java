package gui;

public class frameDisplay {
    public frameDisplay(myFrame frameObject) {
        frameObject.getFrame().add(frameObject.getMenu());
        frameObject.getFrame().add(frameObject.getContainer());
        frameObject.getFrame().setVisible(true);
    }
}
