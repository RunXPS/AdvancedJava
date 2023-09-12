import java.util.*;

public class AlgorKC {
    public static void main (String[] args) {
        
        // Question 3
        int[] a = {5,3,4,1,9,2,7,8,0,6};
        int i = 0;
        int value = 0;
        int n = a.length;
        
        while (i < n && value != a[i]) {
            i++;
        }
        if (i == n) {
            System.out.print("Not found");
        }
        else {
            System.out.print("Found");
        }
        
        // Question 4
        int index = 0;
        int key = value;
        while (a[index] != key)
            index++;
        if (a[index] != key)
            index = -1;
        System.out.println(index);
    }
        /*
        // initialize array 1-100
        int[] arr = new int[100];
        for (int a = 0; a < arr.length; a++) {
        arr[a] = a + 1;
        }

        System.out.println("Choose a number between 1-100 (inclusive).");
        System.out.println("The computer will try to guess your number.");

        // sequential search
        
        System.out.println("\nAttempting to guess via sequential search:");
        int search = sequential(arr);
        System.out.println(search);
        
        // binary search
        System.out.println("\nAttempting to guess via binary search: ");
        int search2 = binary(arr);
        System.out.println(search2);
    }

    public static int binary(int[] arr) {
        Scanner sc = new Scanner(System.in);
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
        int mid = (start + end) / 2;
        System.out.print("Is your number " + arr[mid] + " (higher/lower/correct)? ");
        String response = sc.nextLine().toLowerCase();
        if (response.equals("correct")) {
            return arr[mid];
        } else if (response.equals("higher")) {
            start = mid + 1;
        } else if (response.equals("lower")) {
            end = mid - 1;
        } else {
            ; // do nothing
        }
        }
        return -1;
    }

    public static int sequential(int arr[]) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
        System.out.print("Is your number " + arr[i] + " (higher/lower/correct)? ");
        String response = sc.nextLine().toLowerCase();
        if (response.equals("correct")) {
            return arr[i];
        } else if (response.equals("higher")) {
            ; // increment, done by for loop, do nothing
        } else if (response.equals("lower")) {
            ; // increment, done by for loop, do nothing
        } else {
            i--; // unrecognized, don't increment
        }
        }
        return -1;
    }
    */
}
