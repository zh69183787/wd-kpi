/**
 * Created with IntelliJ IDEA.
 * User: zhoushun
 * Date: 2015/4/2
 * Time: 15:26
 * To change this template use File | Settings | File Templates.
 */
public class QuickSort {
    public static void main(String[] args){
        int[] arr = {33,1,55,66,2,24,5,54};
        quicksort(arr,0,arr.length-1);
        for(int i : arr){
            System.out.println(i + ",");
        }
    }

    public static int getMiddle(int[] arr,int low,int high){
        int pivot = arr[low];
        while(low < high){
            while(low < high && arr[high] >= pivot){
                high --;
            }
            arr[low] = arr[high];
            while(low < high && arr[low] <= pivot){
                low ++;
            }
            arr[high] = arr[low];
        }

        return low;
    }

    public static void quicksort(int[] arr,int low,int high){
        if(low < high){
            int middle = getMiddle(arr,low,high);
            quicksort(arr,low,middle);
            quicksort(arr,middle+1,high);
        }
    }
}
