package aoc2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day02
{
	public static long solveP1()
	{
		long result = 0;
		
		try
		{
			String[] ranges = Files.readAllLines(Path.of("inputs/aoc2025/day02.txt"))
					.get(0)
					.split(",");
			
			for (String range : ranges)
			{
				int dashIndex = range.indexOf('-');
				long start = Long.parseLong(range.substring(0, dashIndex));
				long end = Long.parseLong(range.substring(dashIndex  + 1));
				
				// Example: id=123123 -> digits=[3,2,1,3,2,1] -> if firstSection=123 == secondSection=123, result += id;
				for (long id = start; id < end + 1; id++)
				{
					List<Long> reversedDigits = new ArrayList<>(10);
					long idCopy = id;
					while (idCopy > 0)
					{
						reversedDigits.add(idCopy % 10);
						idCopy /= 10;
					}

					if (reversedDigits.size() % 2 == 1)
					{
						continue;
					}
					
					int secondIndex = reversedDigits.size() / 2;
					long firstSection = 0;
					long secondSection = 0;
					for (int i = 0; i < secondIndex; i++)
					{
						firstSection += reversedDigits.get(i) * (long)Math.pow(10, i);
						secondSection += reversedDigits.get(secondIndex + i) * (long)Math.pow(10, i);
					}
					
					if (firstSection == secondSection)
					{
						result += id;
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}


	// Example: id=123123 -> 111111? 121212? 123123! -> result += id;
	public static long solveP2()
	{
		long result = 0;
		
		try
		{
			String[] ranges = Files.readAllLines(Path.of("inputs/aoc2025/day02.txt"))
					.get(0)
					.split(",");
			
			for (String range : ranges)
			{
				int dashIndex = range.indexOf('-');
				long start = Long.parseLong(range.substring(0, dashIndex));
				long end = Long.parseLong(range.substring(dashIndex  + 1));
				
				for (long id = start; id < end + 1; id++)
				{
					List<Long> reversedDigits = new ArrayList<>(10);
					long idCopy = id;
					while (idCopy > 0)
					{
						reversedDigits.add(idCopy % 10);
						idCopy /= 10;
					}
					
					long patternSection = 0;
					long patternNumber;
					int idSize = reversedDigits.size();
					for (int i = 0; i < idSize >>> 1; i++)
					{
						patternSection *= 10;
						patternSection += reversedDigits.get(idSize - 1 - i);
						if (idSize % (i+1) != 0)
						{
							continue;
						}
						
						patternNumber = patternSection;
						for (int j = i; j < idSize - 1 - i; j += i+1)
						{
							patternNumber *= (long)Math.pow(10, i+1);
							patternNumber += patternSection;
						}
						
						if (patternNumber == id)
						{
							result += id;
							break;
						}
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return -1;
		}
		
		return result;
	}
}
