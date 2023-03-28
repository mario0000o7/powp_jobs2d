package edu.kis.powp.jobs2d.events;

import edu.kis.powp.command.ComplexCommand;
import edu.kis.powp.command.FigureFactoryQuadrangle;
import edu.kis.powp.jobs2d.drivers.DriverManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class SelectTestFigureOptionThird implements ActionListener {
    final private DriverManager driverManager;
    final private Method method;

    public SelectTestFigureOptionThird(DriverManager driverManager,Method method) {
        this.driverManager = driverManager;
        this.method = method;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ComplexCommand commands= (ComplexCommand) method.invoke(FigureFactoryQuadrangle.class,driverManager.getCurrentDriver());
            commands.execute(driverManager.getCurrentDriver());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
