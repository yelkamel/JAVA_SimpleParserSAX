import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserSAX extends DefaultHandler
{
	SAXParser sParser;
	char tmp = 0;
	char[][] tab = null;
	int h = 0;
	int l = 0;
	char def = 0;
	boolean val = false;
	boolean msg = false;

	public ParserSAX()
	{
		try
		{
			sParser = SAXParserFactory.newInstance().newSAXParser();
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
		} catch (SAXException e)
		{
			e.printStackTrace();
		}
	}

	public void parser() throws SAXException, IOException
	{
		sParser.parse(new InputSource(System.in), this);
	}

	@Override
	public void startElement(String uri, String localname, String qname,
			org.xml.sax.Attributes attr) throws SAXException
	{
		if (qname.equals("image") && msg == false && val == false)
		{
			l = Integer.parseInt(attr.getValue("X"));
			h = Integer.parseInt(attr.getValue("Y"));
			tab = new char[h][l];
			for (int i = 0; i < h; ++i)
				for (int j = 0; j < l; ++j)
					tab[i][j] = ' ';
		}

		if (qname.equals("default") && msg == false && val == false)
		{
			def = (char) Integer.parseInt(attr.getValue("value"));
		}

		if (qname.equals("value"))
		{
			val = true;
		}

		if (qname.equals("message"))
		{
			msg = true;
		}
		if (qname.equals("position") && msg == false && val == false)
		{
			int i = Integer.parseInt(attr.getValue("X"));
			int j = Integer.parseInt(attr.getValue("Y"));
			tab[j][i] = tmp;
		}

		super.startElement(uri, localname, qname, attr);
	}

	@Override
	public void endElement(String uri, String localname, String qname)
			throws SAXException
	{
		String res = new String("");
		if (qname.equals("image"))
		{
			for (int i = 0; i < h; i++)
			{
				for (int j = 0; j < l; j++)
				{
					if (tab[i][j] == ' ' && def != 0)
						res += def;
					else
						res += tab[i][j];
				}
				if (i < h - 1)
				res += System.getProperty("line.separator");
			}
			System.out.println(res);
			tab = null;
		}

		if (qname.equals("message"))
		{
			msg = false;
		}
		if (qname.equals("value"))
		{
			val = false;
		}
		if (qname.equals("char"))
			tmp = 0;

		super.endElement(uri, localname, qname);
	}
	
	public void characters(char[] ch, int start, int lenght)
	{

		if (msg)
		{
			System.out.println(new String(ch, start, lenght));
		}
		if (val)
		{
			tmp = (char) Integer.parseInt(new String(ch, start, lenght));
		}
	}

	public String readXML() throws IOException
	{
		String res = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String lu = "";

		while ((lu = in.readLine()) != null)
		{
			res += lu;
		}

		return res;
	}
}
