package vn.de.lipo.webapp.utils;

public class Test {
	private int[] arr = {5, 1, 2, 4, 8, 2, 9, 3};
	
	public int[] getArr() {
		return arr;
	}
	
	public void setArr(int[] arr) {
		this.arr = arr;
	}
	
	public void printArr(int[] arr, int n) {
		for (int i = 0; i < n; i++) {
			System.out.format("%d ", arr[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		System.out.println("Array before sorting: ");
		test.printArr(test.getArr(), 8);
		test.heapSort(test.getArr(), 8);
		System.out.println("After: ");
		test.printArr(test.getArr(), 8);
	}
	
	public void heapSort(int arr[], int n) {
		buildHeap(arr, n); // first we need to build our binary max heap
		
		// after receiving the max heap, we use a loop, starting from n (rightmost node) to swap
		// swap, then continue heapifying with size-- (because the rightmost after swap is largest)
		
		for (int i = n - 1; i >= 0; i--) {
			swap(arr, 0, i);
			heapify(arr, 0, i-1);
		}
	}
	
	public void buildHeap(int arr[], int n) {
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(arr, i, n);
		}
	}
	
	public void heapify(int arr[], int i, int n) {
		int L = 2*i + 1;
		int R = 2*i + 2;
		int max = i;
		if ( L < n && arr[i] < arr[L]) {
			max = L;
		}
		if (R < n && arr[max] < arr[R]) {
			max = R;
		}
		if (max != i) {
			swap(arr, max, i);
			heapify(arr, max, n);
		}
	}
	
	public void swap(int arr[], int i, int j) {
		int temp = 0;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		setArr(arr);
	}
}


