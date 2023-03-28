package edu.kis.powp.jobs2d;

import edu.kis.legacy.drawer.panel.DefaultDrawerFrame;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.command.FigureFactoryQuadrangle;
import edu.kis.powp.jobs2d.drivers.adapter.DriverToDrawer;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawerAdapter;
import edu.kis.powp.jobs2d.events.SelectChangeVisibleOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionSecond;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionThird;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestJobs2dPatterns {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Setup test concerning preset figures in context.
     *
     * @param application Application context.
     */
    private static void setupPresetTests(Application application) {
        SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
                DriverFeature.getDriverManager());
        SelectTestFigureOptionSecond selectTestFigureOptionListener2 = new SelectTestFigureOptionSecond(
				DriverFeature.getDriverManager());
        SelectTestFigureOptionThird selectTestFigureOptionListener3 = new SelectTestFigureOptionThird(
				DriverFeature.getDriverManager(), FigureFactoryQuadrangle.class.getMethods()[0]);
        SelectTestFigureOptionThird selectTestFigureOptionListener4 = new SelectTestFigureOptionThird(
				DriverFeature.getDriverManager(), FigureFactoryQuadrangle.class.getMethods()[1]);
        SelectTestFigureOptionThird selectTestFigureOptionListener5 = new SelectTestFigureOptionThird(
				DriverFeature.getDriverManager(), FigureFactoryQuadrangle.class.getMethods()[2]);
        application.addTest("Figure Joe 1", selectTestFigureOptionListener);
        application.addTest("Figure Joe 2", selectTestFigureOptionListener2);
        application.addTest("Rectangle", selectTestFigureOptionListener3);
        application.addTest("Triangle", selectTestFigureOptionListener4);
        application.addTest("Square", selectTestFigureOptionListener5);

    }

    /**
     * Setup driver manager, and set default driver for application.
     *
     * @param application Application context.
     */
    private static void setupDrivers(Application application) {
        Job2dDriver loggerDriver = new LoggerDriver();
        DriverFeature.addDriver("Logger Driver", loggerDriver);
        DriverFeature.getDriverManager().setCurrentDriver(loggerDriver);

        Job2dDriver testDriver = new DriverToDrawer();
        DriverFeature.addDriver("Buggy Simulator", testDriver);

        LineDrawerAdapter lineDrawerAdapter = new LineDrawerAdapter();
        DriverFeature.addDriver("Line Drawer Adapter", lineDrawerAdapter);
        setupLineDrawer(application, lineDrawerAdapter);


        DriverFeature.updateDriverInfo();
    }

    /**
     * Auxiliary routines to enable using Buggy Simulator.
     *
     * @param application Application context.
     */
    private static void setupDefaultDrawerVisibilityManagement(Application application) {
        DefaultDrawerFrame defaultDrawerWindow = DefaultDrawerFrame.getDefaultDrawerFrame();
        application.addComponentMenuElementWithCheckBox(DrawPanelController.class, "Default Drawer Visibility",
                new SelectChangeVisibleOptionListener(defaultDrawerWindow), false);
        defaultDrawerWindow.setVisible(false);
    }

    /**
     * Setup menu for adjusting logging settings.
     *
     * @param application Application context.
     */
    private static void setupLogger(Application application) {
        application.addComponentMenu(Logger.class, "Logger", 0);
        application.addComponentMenuElement(Logger.class, "Clear log",
                (ActionEvent e) -> application.flushLoggerOutput());
        application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
        application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
        application.addComponentMenuElement(Logger.class, "Warning level",
                (ActionEvent e) -> logger.setLevel(Level.WARNING));
        application.addComponentMenuElement(Logger.class, "Severe level",
                (ActionEvent e) -> logger.setLevel(Level.SEVERE));
        application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
    }

    private static void setupLineDrawer(Application application, LineDrawerAdapter lineDrawer) {
        application.addComponentMenu(LineDrawerAdapter.class, "Line Drawer", 0);
        application.addComponentMenuElement(LineDrawerAdapter.class, "Basic Line", (ActionEvent e) -> lineDrawer.changeTypeOfLine(LineFactory.getBasicLine()));
        application.addComponentMenuElement(LineDrawerAdapter.class, "Dotted Line", (ActionEvent e) -> lineDrawer.changeTypeOfLine(LineFactory.getDottedLine()));
        application.addComponentMenuElement(LineDrawerAdapter.class, "Special Line", (ActionEvent e) -> lineDrawer.changeTypeOfLine(LineFactory.getSpecialLine()));
    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Application app = new Application("2d jobs Visio");
                DrawerFeature.setupDrawerPlugin(app);
                setupDefaultDrawerVisibilityManagement(app);

                DriverFeature.setupDriverPlugin(app);
                setupDrivers(app);
                setupPresetTests(app);
                setupLogger(app);

                app.setVisibility(true);
            }
        });
    }

}
