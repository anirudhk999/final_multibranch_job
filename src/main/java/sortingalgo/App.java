package sortingalgo;
import java.io.FileWriter;
import java.io.IOException;
import java.math.*;
import java.lang.reflect.Array;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void bubbleSort(int[] arr, boolean reverse) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if ((!reverse && arr[j] > arr[j + 1]) || (reverse && arr[j] < arr[j + 1])) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
 
    public static void selectionSort(int[] arr, boolean reverse) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int extremeIndex = i;
            for (int j = i + 1; j < n; j++) {
                if ((!reverse && arr[j] < arr[extremeIndex]) || (reverse && arr[j] > arr[extremeIndex])) {
                    extremeIndex = j;
                }
            }
            int temp = arr[extremeIndex];
            arr[extremeIndex] = arr[i];
            arr[i] = temp;
        }
    }
 
    public static void insertionSort(int[] arr, boolean reverse) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && ((!reverse && arr[j] > key) || (reverse && arr[j] < key))) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
 
   
 
    
    // public static void writeListToFile(List<Double> list, String fileName) {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
    //         for (Double d : list) {
    //             writer.write(d.toString());
    //             writer.newLine();
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    public static void writeListToFile(List<Double> list, String directory, String fileName) {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it does not exist
        }
        
        File file = new File(dir, fileName);
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Double d : list) {
                writer.write(d.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 

    public static void sortAlgoStats()
    {
        String directory = "C:\\DEVOPS_ASSESMENT\\GRAPH";
        List<Double> bubbleSortAnalytics = new ArrayList<Double>();
        List<Double> selectionSortAnalytics = new ArrayList<Double>();
        List<Double> insertionSortAnalytics = new ArrayList<Double>();

        int MIN_LIST_LENGTH = 1000;
        int MAX_LIST_LENGTH = 10000;

        //bubbleSort
        for(int i = MIN_LIST_LENGTH; i < MAX_LIST_LENGTH; i = i + 1000)
        {
            List<Integer> numbers = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                numbers.add(j);
            }

            Collections.shuffle(numbers);
            int[] nums = new int[numbers.size()];
            for(int k = 0; k < numbers.size(); k++)
            {
                nums[k] = numbers.get(k);
            }
            
            double start = (System.nanoTime()) / 1000 ;
            bubbleSort(nums, false);
            double end = (System.nanoTime()) / 1000 ;
            double elapsedTime = end - start;
            bubbleSortAnalytics.add(elapsedTime);
        }

        System.out.println(bubbleSortAnalytics);

        //selection sort
        for(int i = MIN_LIST_LENGTH; i < MAX_LIST_LENGTH; i = i + 1000)
        {
            List<Integer> numbers = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                numbers.add(j);
            }

            Collections.shuffle(numbers);
            int[] nums = new int[numbers.size()];
            for(int k = 0; k < numbers.size(); k++)
            {
                nums[k] = numbers.get(k);
            }
            
            double start = (System.nanoTime()) / 1000 ;
            selectionSort(nums, false);
            double end = (System.nanoTime()) / 1000 ;
            double elapsedTime = end - start;
            selectionSortAnalytics.add(elapsedTime);
        }

        System.out.println(selectionSortAnalytics);

        //insertion sort
        for(int i = MIN_LIST_LENGTH; i < MAX_LIST_LENGTH; i = i + 1000)
        {
            List<Integer> numbers = new ArrayList<>();

            for (int j = 0; j < i; j++) {
                numbers.add(j);
            }

            Collections.shuffle(numbers);
            int[] nums = new int[numbers.size()];
            for(int k = 0; k < numbers.size(); k++)
            {
                nums[k] = numbers.get(k);
            }
            
            double start = (System.nanoTime()) / 1000 ;
            insertionSort(nums, false);
            double end = (System.nanoTime()) / 1000 ;
            double elapsedTime = end - start;
            insertionSortAnalytics.add(elapsedTime);
        }

        System.out.println(insertionSortAnalytics);
        writeListToFile(selectionSortAnalytics, directory, "selectionSort.txt");
        writeListToFile(insertionSortAnalytics, directory, "insertionSort.txt");
        writeListToFile(bubbleSortAnalytics, directory ,"bubbleSort.txt");
    }


    
    
  
    public static void main(String[] args) {
        sortAlgoStats();
        // Scanner scanner = new Scanner(System.in);
        
        // System.out.println("Enter the numbers to sort (space-separated):");
        // String input = scanner.nextLine();
        // String[] numbers = input.split(" ");
        // int[] arr = new int[numbers.length];
        // for (int i = 0; i < numbers.length; i++) {
        //     arr[i] = Integer.parseInt(numbers[i]);
        // }
 
        // System.out.println("Choose sorting algorithm:");
        // System.out.println("1. Bubble Sort");
        // System.out.println("2. Selection Sort");
        // System.out.println("3. Insertion Sort");
        // System.out.println("4. Merge Sort");
        // System.out.println("5. Quick Sort");
        // int choice = scanner.nextInt();
 
        // System.out.println("Sort in descending order? (true/false):");
        // boolean reverse = scanner.nextBoolean();
 
        // long startTime = System.nanoTime();
 
        // switch (choice) {
        //     case 1:
        //         bubbleSort(arr, reverse);
        //         break;
        //     case 2:
        //         selectionSort(arr, reverse);
        //         break;
        //     case 3:
        //         insertionSort(arr, reverse);
        //         break;
        //     case 4:
        //         mergeSort(arr, reverse);
        //         break;
        //     case 5:
        //         quickSort(arr, reverse);
        //         break;
        //     default:
        //         System.out.println("Invalid choice");
        //         return;
        // }
 
        // long endTime = System.nanoTime();
        // long duration = (endTime - startTime) / 1000000;  // Convert to milliseconds
 
        // System.out.println("Sorted array:");
        // System.out.println(Arrays.toString(arr));
        // System.out.println("Time taken: " + duration + " ms");
 
        // scanner.close();
    }
}
