package com.deodio.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;


public class VOUtils {
	public VOUtils() {
	}

	public static String getClassType(Class c) {
		String typeName = c.getSimpleName();

		if (typeName.equals("String") || typeName.equals("Date")) {
			return typeName;
		} else if (typeName.equals("BigDecimal") || typeName.equals("Decimal")
				|| typeName.equals("Double")|| typeName.equals("Long")|| typeName.equals("Float")) {
			return "Number";
		} else if (typeName.equals("Integer")|| typeName.equals("Byte")) {
			return "int";
		} else if (typeName.equals("Boolean")) {
			return "boolean";
		} else {
			return "*";
		}

	}

	public static String repeat(String c, int count) {
		String temp = "";
		for (int i = 0; i < count; i++) {
			temp += c;
		}

		return temp;
	}


	public static void generateAsFile(String pojoName, String packageName,
			String folder) throws ClassNotFoundException, IOException {
		Class c = Class.forName(pojoName);
		Field[] fields = c.getDeclaredFields();

		File f = new File(folder + c.getSimpleName() + ".as");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		if (StringUtils.isEmpty(packageName)) {
			bw.write("package " + c.getPackage().getName() + "\n{\n");
		} else {
			bw.write("package " + packageName + "\n{\n");
		}
		
		bw.write(repeat(" ", 4) + "[RemoteClass(alias=\""+pojoName+"\")]\n");
		bw.write(repeat(" ", 4) + "public class " + c.getSimpleName() + "\n");
		bw.write(repeat(" ", 4) + "{\n");

		for (int i = 0; i < fields.length; i++) {
			Class fieldType = fields[i].getType();
			String typeName = getClassType(fieldType);
			bw.write(repeat(" ", 8) + "public var " + fields[i].getName() + ":" + typeName + ";\n");
		}

		bw.write("\n\n\n");
		bw.write(repeat(" ", 8) + "public function " + c.getSimpleName() + "(){}\n\n");

		bw.write(repeat(" ", 4) + "}\n");
		bw.write("}");
		bw.close();
	}

	// 测试，写了个测试
	public static void main(String[] args) throws ClassNotFoundException,
			IOException {
		// TODO Auto-generated method stub
		String[] pojos = { 
				"com.deodio.elearning.persistence.model.customize.PresentationBo"
//				"com.deodio.elearning.persistence.model.PresentationSyncSlides",
//				"com.deodio.elearning.persistence.model.PresentationSyncPoints",
//				"com.deodio.elearning.persistence.model.PresentationSyncQuizs"
//				"com.deodio.elearning.persistence.model.LessonFinalRecord",
//				"com.deodio.elearning.persistence.model.LessonQuiz",
//				"com.deodio.elearning.persistence.model.LessonSyncMedia",
//				"com.deodio.elearning.persistence.model.LessonSyncPoints",
//				"com.deodio.elearning.persistence.model.LessonSyncRecord",
//				"com.deodio.elearning.persistence.model.LessonSyncSlides",
//				"com.deodio.elearning.persistence.model.Quiz",
//				"com.deodio.elearning.persistence.model.QuizItems",
//				"com.deodio.elearning.persistence.model.QuizResults",
//				"com.deodio.elearning.persistence.model.Role",
//				"com.deodio.elearning.persistence.model.RoleFuncRel",
//				"com.deodio.elearning.persistence.model.Template",
//				"com.deodio.elearning.persistence.model.User",
//				"com.deodio.elearning.persistence.model.UserCourseRel",
//				"com.deodio.elearning.persistence.model.UserGroup",
//				"com.deodio.elearning.persistence.model.UserGroupRel",
//				"com.deodio.elearning.persistence.model.UserLessonPlayback",
//				"com.deodio.elearning.persistence.model.UserQuiz",
//				"com.deodio.elearning.persistence.model.UserRoleRel"
//				"com.deodio.elearning.persistence.model.Media"
				};
		for (int i = 0; i < pojos.length; i++) {
			// Class c = Class.forName(pojos[i]);
			// System.out.println("registerClassAlias('"+pojos[i]+"',com.nstar.orderexpress.vo."+c.getSimpleName()+"VO);");
			VOUtils.generateAsFile(pojos[i], "com.deodio.elearning.domain", "/Volumes/Documents/Document/workspace/deodio-main/src/main/flex/com/deodio/elearning/domain/");
		}

	}

}