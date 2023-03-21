package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DrawerFeature;

public class LineDrawerAdapter extends DrawPanelController implements Job2dDriver {
    private int startX = 0, startY = 0;
    private ILine lineDefault = LineFactory.getSpecialLine();
    @Override
    public void setPosition(int x, int y) {
        this.startX = x;
        this.startY = y;
    }
    public LineDrawerAdapter(){
        super();
    }

    @Override
    public void operateTo(int x, int y) {


        lineDefault.setStartCoordinates(this.startX, this.startY);
        lineDefault.setEndCoordinates(x, y);
        setPosition(x, y);
        DrawerFeature.getDrawerController().drawLine(lineDefault);

    }

    @Override
    public String toString() {
        return "Line Drawer Adapter";
    }
    public void changeTypeOfLine(ILine line){
        this.lineDefault = line;
    }
}
