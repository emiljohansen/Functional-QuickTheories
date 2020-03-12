package dk.sdu.mmmi.funcprog.examples.mergesort;

import org.junit.Test;
import org.quicktheories.WithQuickTheories;

import java.util.Arrays;

public class MergeSortTest implements WithQuickTheories {

    public int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;
        }

        int[] a = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] b = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

        a = mergeSort(a);
        b = mergeSort(b);

        return merge(a, b);
    }

    public int[] merge(int[] a, int[] b) {
        int[] mergedarray = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length || j < b.length) {
            if (a[i] > a[j]) {
                mergedarray[k] = b[j];
                k++;
            } else {
                mergedarray[k] = a[i];
                k++;
            }

            i++;
            j++;
        }

        while (i < a.length) {
            mergedarray[k] = a[i];
            i++;
            k++;
        }

        while (j < b.length) {
            mergedarray[k] = b[j];
            j++;
            k++;
        }

        return mergedarray;
    }

    @Test
    public void sortingTest() {

    }

}
