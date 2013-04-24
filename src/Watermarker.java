
public class Watermarker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String watermark = "Hi";
		String targetText = "Watermark this";
		String watermarkedText = "";
		
		char[] targetTextArray = targetText.toCharArray();
		char[] watermarkArray = watermark.toCharArray();
		
		int c = 0;
		
		for(int i = 0; i < watermark.length(); i++)
		{
			  //convert the letter to a binary digit
			String watermarkCharAsBin = Integer.toBinaryString(watermarkArray[i]);
			for(int j = 0; j < watermarkCharAsBin.length(); j++)
			{
				//add a 1 or 0 to a letter in targetText, index corresponding to sig dig
				switch(watermarkCharAsBin.charAt(j))
				{
				case '1':
					targetTextArray[c] += 128;  
					break;
				case '0': // targetText will always be 0... but...
					if(targetTextArray[c] >= 128)
					{
						targetTextArray[c] -= 128;
					}
					break;
				}
				
				c++;
			}
		}
		
		watermarkedText = new String(targetTextArray);
		System.out.println(watermarkedText);
		
		StringBuilder readWatermarkSB = new StringBuilder();
		StringBuilder watermarkSB = new StringBuilder();
		char[] watermarkedTextArray = watermarkedText.toCharArray();
		c = 0;
		
		for(int i = 0; i < watermarkedTextArray.length; i++)
		{
			//System.out.println("Examining " + watermarkedTextArray[i]);
			if(watermarkedTextArray[i] >= 128)
			{
				//System.out.print("1");
				watermarkSB.append("1");
			}
			else
			{
				//System.out.print("0");
				watermarkSB.append("0");
			}
			if(watermarkSB.length() == 7)
			{
				//System.out.println(" Adding " + Integer.parseInt(watermarkSB.toString(), 2));
				readWatermarkSB.append((char)Integer.parseInt(watermarkSB.toString(), 2) );
				watermarkSB.setLength(0);
			}
		}
		
		System.out.println("Watermark read as: " + readWatermarkSB.toString());
		
	}

}
