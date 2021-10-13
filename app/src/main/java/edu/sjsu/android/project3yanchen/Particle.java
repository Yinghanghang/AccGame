package edu.sjsu.android.project3yanchen;

public class Particle {
    private static final float COR = 0.7f;

    public float mPosX;
    public float mPosY;
    private float mVelX;
    private float mVelY;

    /**
     * Calculate displacement of the particle along the X and Y axis
     * @param x acceleration of x-axis
     * @param y acceleration of x-axis
     * @param z acceleration of x-axis
     * @param timestamp timestamp of the sensor event
     */
    public void updatePosition(float x, float y, float z, long timestamp) {
        float dt = (System.nanoTime() - timestamp) / 1000000000.0f;
        mVelX += -x * dt;
        mVelY += -y * dt;
        mPosX += mVelX * dt;
        mPosY += mVelY * dt;
    }

    /**
     * Adds a bounce effect when it collides with the boundary
     * @param horizontalBound horizontal bound of the screen
     * @param verticalBound vertical bound of the screen
     */
    public void resolveCollisionWithBounds(float horizontalBound, float verticalBound) {
        if (mPosX > horizontalBound) {
            mPosX = horizontalBound;
            mVelX = -mVelX * COR;
        } else if (mPosX < -horizontalBound) {
            mPosX = -horizontalBound;
            mVelX = -mVelX * COR;
        }
        if (mPosY > verticalBound) {
            mPosY = verticalBound;
            mVelY = -mVelY * COR;
        } else if (mPosY < -verticalBound) {
            mPosY = -verticalBound;
            mVelY = -mVelY * COR;
        }
    }
}

