package vn.de.lipo.webapp.utils;

public class Question {
	private int[] arr = {5, 1, 2, 4, 8, 2, 9, 3};
	
	private int[] arr2 = {1, 5, 22, 32, -5, -10, -4, 1};
	
	private static int size = 0;
	
	public static int number = 25;
	
	public int[] getArr() {
		return arr;
	}
	
	public int[] getArrTwo() {
		return arr2;
	}
	
	public int[] findSum(int[] arr, int[] arr2) {
		int[] sumArr = {0,0,0,0,0,0,0,0,0};
		int i = 0;
		int j = 7;
		while (i < 8 || j > -1) {
			int temp = arr[i] + arr2[j];
			sumArr[size] = temp;
			size++;
			if (temp < Question.number) {
				i++;
			}
			else if (temp > Question.number) {
				j--;
			}
			else {
				System.out.format("%d + %d = %d\n", arr[i], arr2[j], temp);
				break;
			}
			
			if (i == 8 || j == -1) {
				break;
			}
		}
		sumArr[size] = Question.number;
		size++;
		return sumArr;
	}
	
	public static void main(String[] args) {
		Question question = new Question();
		int[] sumArr = question.findSum(question.getArr(), question.getArrTwo());
		
		Test test = new Test();
		test.heapSort(sumArr, size);
		
		test.printArr(sumArr, size);
	}
}
