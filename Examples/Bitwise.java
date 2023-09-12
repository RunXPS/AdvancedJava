public class Bitwise {
    public static void main(String[] args) {
        System.out.println(lower("AM I SCREAMING?"));
        System.out.println(upper("i am not screaming!"));
        System.out.println(invertCase("what ABOUT now?"));
    }

    public static String lower(String s) {
        // convert from String to char array
        char[] c = new char[s.length()];
        s.getChars(0, s.length(), c, 0);

        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 0x41 && c[i] <= 0x5A)
                c[i] = (char) (c[i] | 0b00100000);
        }
        // convert from char array to String
        return new String(c);
    }

    public static String upper(String s) {
        // convert from String to char array
        char[] c = new char[s.length()];
        s.getChars(0, s.length(), c, 0);

        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 0x61 && c[i] <= 0x7A)
                c[i] = (char) (c[i] & 0b11011111);
        }
        // convert from char array to String
        return new String(c);
    }

    public static String invertCase(String s) {
        // convert from String to char array
        char[] c = new char[s.length()];
        s.getChars(0, s.length(), c, 0);

        for (int i = 0; i < c.length; i++) {
            if (c[i] >= 0x41 && c[i] <= 0x5A)
                c[i] = (char) (c[i] | 0b00100000);
            else if (c[i] >= 0x61 && c[i] <= 0x7A)
                c[i] = (char) (c[i] & 0b11011111);
        }
        // convert from char array to String
        return new String(c);
    }

}
