public class Main {
    public static void main(String[] args) {

        double[] pos = { 30.0, 90.0 };
        Airplane a = new Airplane(40, "ABC", 80, 2, pos);
        System.out.println(a.getTailNumber());
    
        a.setFuelLevel(60);
    
        System.out.println(a.getFuelLevel());
    
        a.fly();
    
        System.out.println(Airplane.getNumPlanes());
    
        a.crash();
    
        System.out.println(Airplane.getNumPlanes());
        System.out.println(a.getTailNumber());
    
    
    
        Airplane b = new Airplane(64, "BigBoy", 90, 4, pos);
        Airplane c = new Airplane(64, "BigBoy", 90, 4, pos);
    
        System.out.println(a.equals(b));
        System.out.println(c.equals(b));
    
        System.out.println(a);
    
        Cessna d = new Cessna(2000, 0.9, "5", 98, pos);
    
        d.setFuelMixtureLevel(3.7);


      }
}
