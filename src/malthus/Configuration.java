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
		if(classLoader == null)
			classLoader = Configuration.class.getClassLoader();
	}


	private static final Map<ClassLoader, Map< String, WeakReference<Class<?>> >> CACHE_CLASS = new
		WeakHashMap<ClassLoader, Map< String, WeakReference<Class<?>> >>();


	private static final Map<String, String> defaultProperties = parse(CONFIGURATION_FILENAME);


	private boolean usingDefault;
	private final Map<String, String> properties;

	
	public Configuration()
	{
		this.usingDefault = true;
		this.properties = null;
	}


	public Configuration(String fileName)
	{
		this.properties = parse(fileName);
		this.usingDefault = false;
	}


	private static Map<String, String> parse(String fileName)
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		Map<String, String> map = new HashMap<String, String>();
		try
		{
			builder = builderFactory.newDocumentBuilder();
		}
		catch(ParserConfigurationException e)
		{
			// TODO: use logging engine instead
			e.printStackTrace();
			return null;
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
				String value = node.getTextContent().trim();

				map.put(name, value);
			}
		}
		catch(Exception e)
		{
			// TODO: use logging engine instead
			e.printStackTrace();
			return null;
		}


		return map;
	}


	private String get(String name)
	{
		return this.usingDefault ? 
			Configuration.defaultProperties.get(name) : this.properties.get(name);
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


	public void set(String name, String value)
	{
		Map<String, String> setMap = (this.usingDefault) ? this.properties : Configuration.defaultProperties;
		setMap.put(name.trim().toLowerCase(), value.trim());
	}


	public void setInt(String name, int value)
	{
		this.set(name, value + "");
	}


	public void setDouble(String name, double value)
	{
		this.set(name, value + "");
	}


	public void setClass(Class<?> clazz)
	{
		this.setClass(clazz.getName(), clazz);
	}


	public void setClass(String name, Class<?> clazz)
	{
		this.set(name, clazz.getName());	
	}


	public void setClass(String className)
	{
		try
		{
			Class<?> clazz = Class.forName(className, true, classLoader);
			this.setClass(clazz);
		}
		catch(ClassNotFoundException e)
		{
			// TODO: Use logging engine instead
			e.printStackTrace();
		}
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
