package aoc2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;

public class Day03
{
	public static int solveP1()
	{
		try
		{
			int result = 0;
			int[][] batteries = Files.readAllLines(Path.of("inputs/aoc2025/day03.txt"))
					.stream()
					.map(line -> line.chars()
							.map(c -> c - '0')
							.toArray())
					.toArray(int[][]::new);

			int battery;
			int tensDigit; // max
			int onesDigit; // max after tensDigit ("nextMax")
			for (int row = 0; row < batteries.length; row++)
			{
				tensDigit = 0;
				onesDigit = 0;
				for (int col = 0; col < batteries[0].length; col++)
				{
					battery = batteries[row][col];
					if (battery > tensDigit)
					{
						if (col + 1 >= batteries[0].length)
						{
							onesDigit = battery;
							continue;
						}
						tensDigit = battery;
						onesDigit = batteries[row][col + 1];
					}
					else if (battery == tensDigit)
					{
						onesDigit = battery;
					}
					else if (battery > onesDigit)
					{
						onesDigit = battery;
					}
				}

				result += onesDigit + (10 * tensDigit);
			}
			
			return result;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return -1;
		}
	}


	public static long solveP2()
	{
		try
		{
			long result = 0;
			int[][] batteries = Files.readAllLines(Path.of("inputs/aoc2025/day03.txt"))
					.stream()
					.map(line -> line.chars()
							.map(c -> c - '0')
							.toArray())
					.toArray(int[][]::new);
			
			for (int row = 0; row < batteries.length; row++)
			{
				result += maxJoltageP2(batteries[row]);
			}
			
			return result;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	
	private static long maxJoltageP2(int[] row)
	{
		int k = 12;
		int toRemove = row.length - k;
		
		Deque<Integer> stack = new ArrayDeque<>();
		for (int digit : row)
		{
			while (!stack.isEmpty() && toRemove > 0 && stack.peekLast() < digit)
			{
				stack.pollLast();
				toRemove--;
			}
			
			stack.addLast(digit);
		}
		
		long joltage = 0;
		for (int i = 0; i < k; i++)
		{
			joltage = joltage * 10 + stack.pollFirst();
		}
		
		return joltage;
	}


//	public static long solveP2()
//	{
//		try
//		{
//			long result = 0;
//			int[][] batteries = Files.readAllLines(Path.of("inputs/aoc2025/day03.txt"))
//					.stream()
//					.map(line -> line.chars()
//							.map(c -> c - '0')
//							.toArray())
//					.toArray(int[][]::new);
//
//			int[] digits;
//			for (int row = 0; row < batteries.length; row++)
//			{
//				digits = new int[12];
//				for (int col = 0; col < batteries[row].length; col++)
//				{
//					for (int i = 0; i < digits.length && col != digits[i]; i++)
//					{
//						if (batteries[row][col] > batteries[row][digits[i]] && col + (11 - i) < batteries[row].length)
//						{
//							for (int j = 0; i+j < digits.length; j++)
//							{
//								digits[i + j] = col + j;
//							}
//							break;
//						}
//					}
//				}
//
//				long joltage = 0;
//				for (int i = 0; i < digits.length; i++)
//				{
//					joltage += batteries[row][digits[11 - i]] * Math.pow(10, i);
//				}
//				
//				result += joltage;
//				System.out.printf("%3d. %d%n", row+1, joltage);
//			}
//			
//			return result;
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//			return -1;
//		}
//	}
}
