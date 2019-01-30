package test;

import java.util.Random;
import java.util.UUID;

/**
 * @author Leon Zeng
 * @since 2019/1/30 10:15
 */
public class MyPojo {
  private final static Random random = new Random();
  private int anInt = random.nextInt();
  private long aLong = random.nextLong();
  private float aFloat = random.nextFloat();
  private double aDouble = random.nextDouble();
  private String string = UUID.randomUUID().toString();
  private byte[] bytes = new byte[100];

  public MyPojo() {
    random.nextBytes(bytes);
  }

  public int getAnInt() {
    return this.anInt;
  }

  public long getALong() {
    return this.aLong;
  }

  public float getAFloat() {
    return this.aFloat;
  }

  public double getADouble() {
    return this.aDouble;
  }

  public String getString() {
    return this.string;
  }

  public byte[] getBytes() {
    return this.bytes;
  }

  public void setAnInt(int anInt) {
    this.anInt = anInt;
  }

  public void setALong(long aLong) {
    this.aLong = aLong;
  }

  public void setAFloat(float aFloat) {
    this.aFloat = aFloat;
  }

  public void setADouble(double aDouble) {
    this.aDouble = aDouble;
  }

  public void setString(String string) {
    this.string = string;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof MyPojo)) return false;
    final MyPojo other = (MyPojo) o;
    if (this.getAnInt() != other.getAnInt()) return false;
    if (this.getALong() != other.getALong()) return false;
    if (Float.compare(this.getAFloat(), other.getAFloat()) != 0) return false;
    if (Double.compare(this.getADouble(), other.getADouble()) != 0) return false;
    final Object this$string = this.getString();
    final Object other$string = other.getString();
    if (this$string == null ? other$string != null : !this$string.equals(other$string))
      return false;
    if (!java.util.Arrays.equals(this.getBytes(), other.getBytes())) return false;
    return true;
  }
}
