package edu.kis.powp.jobs2d.drivers.adapter;

import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.ILine;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.DrawerFeature;

public class LineDrawerAdapter extends DrawPanelController implements Job2dDriver {
    private int startX = 0, startY = 0;
    @Override
    public void setPosition(int i, int i1) {
        this.startX = i;
        this.startY = i1;
    }
    public LineDrawerAdapter(){
        super();
    }

    @Override
    public void operateTo(int i, int i1) {
        setPosition(i, i1);
        ILine line = LineFactory.getSpecialLine();
        line.setStartCoordinates(this.startX, this.startY);
        line.setEndCoordinates(i, i1);
        setPosition(i, i1);
        DrawerFeature.getDrawerController().drawLine(line);

    }

    @Override
    public String toString() {
        return "Line Drawer Adapter";
    }
}
