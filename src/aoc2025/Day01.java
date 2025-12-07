package aoc2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day01
{
	public static int solveP1()
	{
		try
		{
			List<String> lines = Files.readAllLines(Path.of("inputs/aoc2025/day01.txt"));

			int count = 0;
			int dial = 50;
			int magnitude;
			for (String line : lines)
			{
				magnitude = Integer.parseInt(line.substring(1));
				dial = (line.charAt(0) == 'L') ? dial - magnitude : dial + magnitude;

				if (dial % 100 == 0)
				{
					count++;
				}
			}

			return count;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public static int solveP2()
	{
		try
		{
			List<String> lines = Files.readAllLines(Path.of("inputs/aoc2025/day01.txt"));

			int count = 0;
			int dial = 50;
			for (String line : lines)
			{
				int magnitude = Integer.parseInt(line.substring(1));
				int direction = line.charAt(0);
				int delta = (direction == 'L') ? -magnitude : magnitude;
				
				// First add cycles.
				count += magnitude / 100;
				
				// Then add remainder.
				int newDial = dial + (delta % 100);
				if (dial == 0)
				{
					dial = (newDial + 100) % 100;
				}
				else if (newDial == 0)
				{
					count++;
					dial = newDial;
				}
				else if (newDial > 99)
				{
					count++;
					dial = newDial - 100;
				}
				else if (newDial < 0)
				{
					count++;
					dial = newDial + 100;
				}
				else
				{
					dial = newDial;
				}
			}
			
			return count;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return -1;
		}
	}
}
