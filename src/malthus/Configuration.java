import java.io.FileInputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Configuration
{
	private static final String CONFIGURATION_FILENAME = "DefaultConfiguration.xml";
	private static final Class<?>[] EMPTY_ARRAY = new Class[]{};


	/* Multithreading Purpose */
	private ClassLoader classLoader;
	{
		classLoader = Thread.currentThread().getContextClassLoader();
	}


	private static final Map<ClassLoader, Map< String, WeakReference<Class<?>> >> CACHE_CLASS = new
		WeakHashMap<ClassLoader, Map< String, WeakReference<Class<?>> >>();


	private final Map<String, String> properties = new HashMap<String, String>();

	
	public Configuration()
	{
		this(true);
	}


	public Configuration(boolean usingDefault) 
	{
		this(CONFIGURATION_FILENAME, usingDefault);	
	}


	public Configuration(String fileName)
	{
		this(fileName, true);
	}


	public Configuration(String fileName, boolean usingDefault)
	{
		this.parse(fileName);	
	}


	private void parse(String fileName)
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try
		{
			builder = builderFactory.newDocumentBuilder();
		}
		catch(ParserConfigurationException e)
		{
			// TODO: use logging engine instead
			e.printStackTrace();
		}

		
		// Parsing the File
		try
		{
			Document doc = builder.parse(new FileInputStream(fileName));			
			NodeList properties = doc.getElementsByTagName("property");

			for(int i = 0; i < properties.getLength(); i++)
			{
				Node node = properties.item(i);
		
				String name = node.getAttributes().getNamedItem("name").getNodeValue()
								.trim().toLowerCase();
				String value = node.getTextContent()
								.trim();

				this.properties.put(name, value);
			}
		}
		catch(Exception e)
		{
			// TODO: use logging engine instead
			e.printStackTrace();
		}
	}


	public String get(String name)
	{
		return this.properties.get(name);
	}


	public int getInt(String name)
	{
		String val = this.get(name);
		return (val == null) ? null : Integer.parseInt(val);
	}


	public double getDouble(String name)
	{
		String val = this.get(name);
		return (val == null) ? null : Double.parseDouble(val);
	}


	public Class<?> getClass(String name)
	{
		String className = this.get(name);
		if(className == null)
			return null;

		Map<String, WeakReference<Class<?>>> classes = null;
		synchronized(CACHE_CLASS)
		{
			classes = CACHE_CLASS.get(classLoader);
			if(classes == null)
			{
				classes = new WeakHashMap<String, WeakReference<Class<?>>>();
				CACHE_CLASS.put(classLoader, classes);
			}
		}


		Class<?> clazz = null;
		WeakReference<Class<?>> classRef = classes.get(className);
		if(classRef != null)
		{
			clazz = classRef.get();
		}

		if(clazz == null)
		{
			try
			{
				clazz = Class.forName(className, true, classLoader);	
			}
			catch(ClassNotFoundException e)
			{
				// TODO: Use logging engine instead
				e.printStackTrace();
				return null;
			}

			classes.put(className, new WeakReference<Class<?>>(clazz));
		}

		return clazz;
	}


	@SuppressWarnings("unchecked")
	public <T> T newInstance(String name, Class<T> xface)
		throws ClassNotFoundException
	{
		Class<?> cl = this.getClass(name);
		if(cl == null)
			throw new ClassNotFoundException();

		T ret = null;
		try
		{
			Constructor<?> constructor = cl.getDeclaredConstructor(EMPTY_ARRAY);	
			constructor.setAccessible(true);
			ret = (T) constructor.newInstance();
		}
		catch(Exception e)
		{
			// TODO: Use logging engine instead
			e.printStackTrace();
		}

		return ret;
	}
 }
