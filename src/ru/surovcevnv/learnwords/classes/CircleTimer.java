package ru.surovcevnv.learnwords.classes;

import javax.print.DocFlavor;
import java.awt.*;
import java.awt.geom.Arc2D;

public class CircleTimer {
    public static final float LINE_WIDTH = 10.0f;
    public static final int START_ANGLE = 90;
    //default values
    public static Color COLOR_OFF_DEFAULT = new Color(100, 100, 100);
    public static Color COLOR_ON_FULL_DEFAULT = new Color(0, 150, 0);
    public static Color COLOR_ON_EXPIRE_DEFAULT = new Color(150, 0, 0);
    public static float PERCENT_EXPIRE_DEFAULT = 0.3f;
    //    region timer properties
    private int duration;
    private boolean isStarted;
    private boolean isRunning;
    private long startTime;
    private long pauseDuration;
    private float percentExpire;
    //    endregion
//    region graphics properties
    private int curX;
    private int curY;
    private int width;
    private int height;
    //
    private Color colorOnFull;
    private Color colorOnExpire;
    private Color colorOff;
    //
    private boolean isVisible;

    //    endregion
    public CircleTimer(int centerX, int centerY, int width, int height, int durationMs) {
        init(centerX, centerY, width, height, durationMs, PERCENT_EXPIRE_DEFAULT, COLOR_ON_FULL_DEFAULT, COLOR_ON_EXPIRE_DEFAULT, COLOR_OFF_DEFAULT);
    }

    public CircleTimer(int centerX, int centerY, int width, int height, int durationMs, float percentExpire, Color colorOnFull, Color colorOnExpire, Color colorOff) {
        init(centerX, centerY, width, height, durationMs, percentExpire, colorOnFull, colorOnExpire, colorOff);
    }

    private void init(int centerX, int centerY, int width, int height, int durationMs, float percentExpire, Color colorOnFull, Color coloorOnExpire, Color colorOff) {
        this.curX = centerX;
        this.curY = centerY;
        this.width = width;
        this.height = height;
        this.duration = durationMs;
        this.percentExpire = percentExpire;
        this.isStarted = false;
        this.isRunning = false;
        this.colorOnFull = colorOnFull;
        this.colorOnExpire = coloorOnExpire;
        this.colorOff = colorOff;
    }

    public void start() {
        if (!isStarted) {
            isStarted = true;
            startTime = System.currentTimeMillis();
            pauseDuration = 0;
        }
        if ((!isRunning) && (pauseDuration > 0)) {
            startTime = System.currentTimeMillis() - pauseDuration;
            pauseDuration = 0;
        }
        isRunning = true;
    }

    public void pause() {
        if (isRunning) {
            isRunning = false;
            pauseDuration = System.currentTimeMillis() - startTime;
        }
    }

    public void stop() {
        isRunning = false;
        isStarted = false;
        pauseDuration = 0;
        startTime = 0;
    }

    public void restart() {
        stop();
        start();
    }

    public void reset(int duration) {
        stop();
        setDuration(duration);
        start();
    }

    public boolean setDuration(int durationMS) {
        if (isRunning) return false;
        if (durationMS <= 0) return false;
        this.duration = durationMS;
        return true;
    }

    public String getInfo() {
        return "startTime=" + startTime + ", isRunning=" + isRunning + ", isStarted=" + isStarted + ", percentPassed=" + getPercentPassed();
    }

    private float getPercentPassed() {
        float percentPassed = 0;
        if (isStarted) {
            if (isRunning) {
                long nowTime = System.currentTimeMillis();
                long millisPassed = nowTime - startTime;
                percentPassed = (float) millisPassed / (float) duration;
            } else {
                percentPassed = (float) pauseDuration / (float) duration;
            }
        } else {
            percentPassed = 0;
        }
        if (percentPassed > 1) {
            percentPassed = 1;
        }
        return percentPassed;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //
        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();
        Object oldAntialiasingValue = g2d.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
        //
        g2d.setStroke(new BasicStroke(LINE_WIDTH));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        float percentPassed = getPercentPassed();
        if ((!isStarted) || (!isRunning)) {
            g2d.setColor(colorOff);
        } else {
            if (percentPassed > (1.0f - percentExpire)) {
                g2d.setColor(colorOnExpire);
            } else {
                g2d.setColor(colorOnFull);
            }
        }
        Shape s = new Arc2D.Float(curX - width / 2, curY - height / 2, width, height, START_ANGLE, (int) (360 * (1.0f - percentPassed)), Arc2D.OPEN);
        g2d.draw(s);//drawArc(curX - width / 2, curY - height / 2, width, height, 0, (int) (360 * (1 - percentPassed)));
        //
        g2d.setColor(oldColor);
        g2d.setStroke(oldStroke);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldAntialiasingValue);
        //
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}