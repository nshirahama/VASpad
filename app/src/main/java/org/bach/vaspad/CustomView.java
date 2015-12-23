package org.bach.vaspad;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by naruki on 16/7/15.
 */
public class CustomView extends View {
    private final float MARGIN = 100;
    private float x, y, canvasWidth, canvasHeight;
    private Paint paint;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public CustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        x = -10;
        y = -10;
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
    }

    /**
     * Implement this method to handle touch screen motion events.
     * <p/>
     * If this method is used to detect click actions, it is recommended that
     * the actions be performed by implementing and calling
     * {@link #performClick()}. This will ensure consistent system behavior,
     * including:
     * <ul>
     * <li>obeying click sound preferences
     * <li>dispatching OnClickListener calls
     * <li>handling {@link AccessibilityNodeInfo#ACTION_CLICK ACTION_CLICK} when
     * accessibility features are enabled
     * </ul>
     *
     * @param event The motion event.
     * @return True if the event was handled, false otherwise.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float min = MARGIN;
        float max = canvasWidth - MARGIN;
        x = event.getX();
        if (x < min) {
            x = min;
        } else if (x > max) {
            x = max;
        }
        Log.d("Touch", "X: " + x + ",Y:" + y);
        return super.onTouchEvent(event);
    }

    /**
     * Implement this to do your drawing.
     *
     * @param canvas the canvas on which the background will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        drawInit(canvas);
        drawLine(canvas);
        invalidate();
    }

    private void drawInit(Canvas canvas) {
        canvasWidth = (float) canvas.getWidth();
        canvasHeight = (float) canvas.getHeight();
        canvas.drawLine(MARGIN, canvasHeight / 2, canvasWidth - MARGIN, canvasHeight / 2, paint);
    }

    private void drawLine(Canvas canvas) {
        canvas.drawLine(x, canvasHeight / 2 - 50, x, canvasHeight / 2 + 50, paint);
    }

    public float getNormalizedX() {
        Log.d("canvas", String.valueOf(canvasWidth));
        return (x - MARGIN) / (canvasWidth - 2 * MARGIN);
    }
}
