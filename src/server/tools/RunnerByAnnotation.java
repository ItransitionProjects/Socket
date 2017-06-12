package server.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import server.tools.xml.parsers.UsedParses;

public class RunnerByAnnotation {

	public void run(Class<?> clazz,String annotationName)  {
		Method[] methods = clazz.getMethods();

		for (Method method : methods) {
			//System.out.println(new String( method.getName()));
			
			if (method.isAnnotationPresent(UsedParses.class)) {
				// Получаем доступ к атрибутам
				UsedParses test = method.getAnnotation(UsedParses.class);
				//System.out.println(new String("Val = "+test.value()));
				if(annotationName.equals(test.value())){
					try {
						method.invoke(null);
						break;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				}
			}
		
	}	
}
