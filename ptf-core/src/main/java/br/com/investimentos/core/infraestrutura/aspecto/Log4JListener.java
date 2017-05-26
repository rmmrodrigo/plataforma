package br.com.investimentos.core.infraestrutura.aspecto;

import java.lang.reflect.Field;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class Log4JListener implements TypeListener{
	public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
		for (Field field : typeLiteral.getRawType().getDeclaredFields()) {
			if (field.getType() == Logger.class && field.isAnnotationPresent(InjectLog.class)) {
				typeEncounter.register(new Log4JMembersInjector<T>(field));
			}
		}
	}

	private class Log4JMembersInjector<T> implements MembersInjector<T>{
		private Field field;
		private Logger logger;

		Log4JMembersInjector(Field field) {
			this.field = field;
			this.logger = LogManager.getLogger(field.getDeclaringClass());
			field.setAccessible(true);
		}

		public void injectMembers(T t) {
			try {
				field.set(t, logger);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}
}