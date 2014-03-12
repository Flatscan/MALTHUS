package malthus.util;

import java.lang.reflect.Constructor;


public class ReflectiveUtils
{
	private static final Class<?>[] EMPTY_ARRAY = new Class[]{};
	
	public static <T> T newInstance(Class<T> clazz)
	{
		T ret = null;
		try
		{
			Constructor<T> constructor = clazz.getDeclaredConstructor(EMPTY_ARRAY);
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
	
	public static <T> T newInstance(Class<T> clazz, Class<?>[] parameterTypes, Object[] parameters)
	{
		T ret = null;
		try
		{
			Constructor<T> constructor = clazz.getDeclaredConstructor(parameterTypes);
			constructor.setAccessible(true);
			ret = (T) constructor.newInstance(parameters);
		}
		catch(Exception e)
		{
			// TODO: Use logging engine instead
			e.printStackTrace();
		}
		
		return ret;
	}
}
