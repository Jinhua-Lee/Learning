package com.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class Reflection {
	public static void main(String[] args) {
		String name;
		if(args.length > 0) {
			name = args[0];
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("输入类名(如java.util.Date)：");
			name =sc.nextLine();
			sc.close();
		}

		try {
			// 打印类名和父类名
			Class<?> cl = Class.forName(name);
			Class<?> scl = cl.getSuperclass();
			String modifiers = Modifier.toString(cl.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.print("class " + name);
			if(scl != null && scl != Object.class) {
				System.out.print(" extends " + scl.getName());
			}
			System.out.print("\n{\n");
			printConstructors(cl);
			System.out.println();
			printMethods(cl);
			System.out.println();
			printFields(cl);
			System.out.println("}");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	  * 打印类的所有构造器
	  * @param cl a class
	  */
	public static void printConstructors(Class<?>  cl) {
		Constructor[] cons = cl.getDeclaredConstructors();

		for (Constructor c : cons) {
			String name = c.getName();
			System.out.print("	");
			String modifiers =Modifier.toString(c.getModifiers());
			if(modifiers.length() > 0) {
				System.out.print(modifiers + "(");
			}

			// 打印参数类型
			Class[] paramTypes = c.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				System.out.print(paramTypes[i].getName());
			}
			System.out.println(");");
		}
	}


	public static void printMethods(Class cl) {
		Method[] methods = cl.getDeclaredMethods();

		for(Method m: methods) {
			Class retType = m.getReturnType();
			String name = m.getName();

			System.out.print("	");
			String modifiers = Modifier.toString(m.getModifiers());
			if(modifiers.length() > 0) {
				System.out.print(modifiers + "	");
			}
			System.out.print(retType.getName() + " " + "(");

			Class<?>[] paramTypes = m.getParameterTypes();
			for (int i = 0; i < paramTypes.length; i++) {
				if (i > 0) {
					System.out.print(", ");
				}
				System.out.print(paramTypes[i].getName());
			}
			System.out.println(");");
		}
	}

	/**
	  * 打印类的所有属性
	  * @param cl a class
	  */
	public static void printFields(Class<?> cl) {
		Field[] fields = cl.getDeclaredFields();

		for(Field f: fields) {
			Class<?> type = f.getType();
			String name = f.getName();
			System.out.print("	");
			String modifiers  = Modifier.toString(f.getModifiers());
			if(modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			}
			System.out.println(type.getName() + " "+ name + ";");
		}
	}
}