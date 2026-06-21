package me.mioclient.clickgui.util;

/**
 * Replicates the smoothing model of Mio's Class_0031 / Class_0585 / Class_0055:
 * each frame the current value advances toward target by a speed-scaled factor.
 * speed is "units per second" — a speed of 2.5 reaches half-way in ~0.28s.
 */
public final class Animation {
   private float current;
   private float target;
   private long lastMs = System.currentTimeMillis();
   public final float speed;

   public Animation(float speed) { this.speed = speed; }
   public Animation(float speed, float initial) { this.speed = speed; this.current = initial; this.target = initial; }

   public void target(float v) { this.target = v; }
   public void target(boolean v) { this.target = v ? 1F : 0F; }
   public void snap(float v) { this.current = v; this.target = v; }
   public float value() { return this.current; }

   public float tick() {
      long now = System.currentTimeMillis();
      float dt = Math.min(0.1F, (now - this.lastMs) / 1000F);
      this.lastMs = now;
      if (this.current == this.target) return this.current;
      float k = 1F - (float) Math.pow(0.001F, dt * this.speed);
      this.current += (this.target - this.current) * k;
      if (Math.abs(this.target - this.current) < 0.001F) this.current = this.target;
      return this.current;
   }
}
