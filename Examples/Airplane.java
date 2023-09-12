public class Airplane {
    // attributes - instance variables
    private int wingspan;
    private String tailNumber;
    private int fuelLevel; // 0-100
    private int numPilots;
    private double[] position; // lat long
    private static int numPlanes = 0;
  
    // constructor
    Airplane(int wingspan, String tailNumber, int fuelLevel, int numPilots, double[] position) {
      this.wingspan = wingspan;
      this.tailNumber = tailNumber;
      this.fuelLevel = fuelLevel;
      this.numPilots = numPilots;
      this.position = position;
  
      numPlanes++;
  
    }
  
    public int getWingspan() {
      return wingspan;
    }
  
    public String getTailNumber() {
      return tailNumber;
    }
  
    public int getFuelLevel() {
      return fuelLevel;
    }
  
    public void setFuelLevel(int fuelLevel) {
      this.fuelLevel = fuelLevel;
    }
  
    public int getNumPilots() {
      return numPilots;
    }
  
    public void setNumPilots(int numPilots) {
      this.numPilots = numPilots;
    }
  
    public double[] getPosition() {
      return position;
    }
  
    public void setPosition(double[] position) {
      this.position = position;
    }
  
    public static int getNumPlanes() {
      return numPlanes;
    }
  
    public static void setNumPlanes(int numPlanes) {
      Airplane.numPlanes = numPlanes;
    }
  
    public void fly() {
      System.out.println("Weeeee, we're flying!!!");
    }
  
    public void fuelLeak() {
      System.out.println("Oh no!");
      this.fuelLevel = 0;
    }
  
    public void crash() {
      this.numPilots = 0;
      numPlanes--;
      this.fuelLevel = 0;
      this.tailNumber = ":(";
      this.wingspan /= 2;
    }
  
    @Override
    public boolean equals(Object o) {
      if (o == this)
        return true;
      if (!(o instanceof Airplane)) {
        return false;
      }
      Airplane plane = (Airplane) o;
      return this.tailNumber.equals(plane.tailNumber) && this.wingspan == plane.wingspan;
    }
  
    @Override
    public String toString() {
      return "tailNumber = " + getTailNumber() +
      ", wingspan = " + getWingspan();
    }
  
  }