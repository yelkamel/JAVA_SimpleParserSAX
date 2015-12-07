import java.io.IOException;

import org.xml.sax.SAXException;

/**
 * 
 */

/**
 * @author el-kam_y
 *
 */
public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ParserSAX p = new ParserSAX();
		
		try
		{
			p.parser();
		} catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
