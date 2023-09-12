class AlgoExp { 
    public static void main () {    
        // Test Loop    
        for (int i = 0; i < 100000; i++) {  
            if (prime(i) != primeOptimized(i)) { 
                System.out.print("failed: ");   
                System.out.println(i);  
            }   
        }   
    }   

    public static boolean prime(int n) {   
        for (int i = 2; i < n; i++) {   
            if (n % i == 0) {   
                return false;   
            }
        }   
        return true;    
    }   

    public static boolean primeOptimized (int n) {  
        // Class implementation
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        final int m = (int)Math.sqrt(n);
        for (int i = 3; i <= m; i+= 2) {    
            if (n % i == 0) {   
                return false;   
            }   
        }   
        return true;    
        /*
        // Dr. D'Adrgino's implementation
        for (int i = 2; i < n / (i-1); i ++) {
            if (n % i == 0) {   
                return false;   
            }   
        }   
        return true;    
        */
    }   

    public static int leastDivisor (int n) {
        int i = 2;
        while (n % i != 0) {
            i++;
        }
        return i;
    }
}   