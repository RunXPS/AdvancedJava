public class Cessna extends Airplane {
    private int motorRPM;
    private double fuelMixtureLevel;

    Cessna(int motorRPM, double fuelMixtureLevel, String tailNumber, int fuelLevel, double[] position) {
        super(36, tailNumber, fuelLevel, 1, position);
        this.motorRPM = motorRPM;
        this.fuelMixtureLevel = fuelMixtureLevel;
    }

    @Override
    public void fly(){
        System.out.println("We are flying a Cessna");
    }

    public int getMotorRPM() {
        return motorRPM;
    }
    public void setMotorRPM(int motorRPM) {
        this.motorRPM = motorRPM;
    }
    public double getFuelMixtureLevel() {
        return fuelMixtureLevel;
    }
    public void setFuelMixtureLevel(double fuelMixtureLevel) {
        this.fuelMixtureLevel = fuelMixtureLevel;
    }


    
}
