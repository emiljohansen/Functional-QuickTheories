package dk.sdu.mmmi.funcprog.examples.mergesort;

import org.junit.Test;
import org.quicktheories.WithQuickTheories;

import java.util.Arrays;

public class MergeSortTest implements WithQuickTheories {

    public Integer[] mergeSort(Integer[] arr) {
        if (arr.length == 1) {
            return arr;
        }

        Integer[] a = Arrays.copyOfRange(arr, 0, arr.length / 2);
        Integer[] b = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

        a = mergeSort(a);
        b = mergeSort(b);

        return merge(a, b);
    }

    public Integer[] merge(Integer[] a, Integer[] b) {
        Integer[] mergedarray = new Integer[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] > b[j]) {
                mergedarray[k] = b[j];
                k++;
                j++;
            } else {
                mergedarray[k] = a[i];
                k++;
                i++;
            }
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

    public boolean isSorted(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    @Test
    public void sortingTest() {
        qt()
                .forAll(arrays().ofIntegers(integers().allPositive()).withLengthBetween(1, 1000))
                .check(l1 -> isSorted(mergeSort(l1)));
    }

}
