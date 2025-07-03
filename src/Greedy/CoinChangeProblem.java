package Greedy;

import java.util.Arrays;

public class CoinChangeProblem {
	public static void main(String []args) {
		int coins[] = {1,2,5,10,20,50,100,1000};
		int amount = 2035;
		System.out.println("Coin available: "+Arrays.toString(coins));
		System.out.println("Target Amount "+amount);
		CoinChange.coinChangeProblem(coins, amount);
		
	}
}

class CoinChange{
	public static void coinChangeProblem(int coins[], int amount) {
		
		//Sort the coins
		Arrays.sort(coins);
		int index = coins.length-1;
		while(true) {
			int coinValue = coins[index];
			int maxValueChosenFromCoin = (amount/coinValue) * coinValue ; //In java divide return int 2035/1000 = 2
			index--;
			if(maxValueChosenFromCoin >0) {
				System.out.println("Coin Value:" + coinValue +" taken count: "+ amount/coinValue);
				amount = amount -maxValueChosenFromCoin;
			}
			if(amount == 0) {
				break;
			}
		}
		
	}
}


