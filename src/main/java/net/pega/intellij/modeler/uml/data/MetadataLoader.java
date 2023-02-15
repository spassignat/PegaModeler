package net.pega.intellij.modeler.uml.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.pega.intellij.modeler.uml.Context;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

class MetadataLoader {
	private final Map<String, MClass> classMap = new HashMap<>();
	Context context = null;

	public MetadataLoader(Context context) {
		this.context = context;
	}

	void analyseClass(MClass mClass) {
		if (Arrays.stream(context.getState().highestClasses).noneMatch(ac -> Objects.equals(ac, mClass.name))) {
			final MClass mClass1 = getMClass(mClass.name);
			analyseInheritance(mClass1);
			analyseProperties(mClass1);
			mClass1.analyzed = true;
			boolean pendingClasses = true;
			int mxc = 100;
			while (pendingClasses && mxc-- > 0) {
				getClasses().stream().filter(c -> !c.analyzed).forEach(d -> {
					analyseClass(d);
				});
				pendingClasses = getClasses().stream().anyMatch(c -> !c.analyzed);
			}
		}
	}

	Collection<MClass> getClasses() {
		return new ArrayList<>(classMap.values());
	}

	private void analyseInheritance(MClass mClass) {
		final HttpGet get = context.createRequest("/class/" + mClass.name);
		context.log("analyseClass: " + mClass.name);
//		System.out.println(context.getState());
		final InputStream content;
		try (CloseableHttpResponse response = context.execute(get)) {
			context.log(response.getStatusLine().toString());
			context.log(System.getProperty("line.separator"));
			content = response.getEntity().getContent();
			final InputStreamReader reader = new InputStreamReader(content);
			JsonArray pxResults = JsonParser.parseReader(reader).getAsJsonArray();
			for (int i = 0; i < pxResults.size(); i++) {
				final JsonElement jsonElement = pxResults.get(i);
				final JsonObject jsonElement1 = (JsonObject) jsonElement;
				final String pyClassType = jsonElement1.get("pyClassType").getAsString();
				final String pyClassName = jsonElement1.get("pyDerivesFrom").getAsString();
				final boolean pyClassInheritance = jsonElement1.get("pyClassInheritance").getAsBoolean();
				mClass.abs = "Abstract".equals(pyClassType);
				mClass.inheritance = pyClassInheritance;
				MClass declaringClass = getMClass(pyClassName);
				if (declaringClass != null) {
					mClass.parent = declaringClass.name;
				}
				context.log("  - " + jsonElement);
			}
		} catch (Exception e) {
			context.log(e.toString());
		}
	}

	private void analyseProperties(MClass mClass) {
		final HttpGet get = context.createRequest("/properties/" + mClass.name);
		context.log("analyseproperties: " + mClass.name);
		final InputStream content;
		try (CloseableHttpResponse response = context.execute(get)) {
			context.log(response.getStatusLine().toString());
			context.log(System.getProperty("line.separator"));
			content = response.getEntity().getContent();
			final InputStreamReader reader = new InputStreamReader(content);
			JsonArray pxResults = JsonParser.parseReader(reader).getAsJsonArray();
			for (int i = 0; i < pxResults.size(); i++) {
				final JsonElement jsonElement = pxResults.get(i);
				context.log("    - " + jsonElement);
				registerProperty((JsonObject) jsonElement);
			}
		} catch (Exception e) {
			context.log(e.toString());
		}
	}

	private MClass getMClass(String pyPageClass) {
		if (Arrays.stream(context.getState().highestClasses).noneMatch(ac -> Objects.equals(ac, pyPageClass))) {
			if (!classMap.containsKey(pyPageClass)) {
				classMap.put(pyPageClass, new MClass(pyPageClass));
			}
			return classMap.get(pyPageClass);
		}
		return null;
	}

	private void registerProperty(JsonObject jsonElement) {
		final MProperty pyProperty = new MProperty();
		/*
		the name of the property
		 */
		final String pyPropertyName = jsonElement.get("pyPropertyName").getAsString();
		/*
		return Page or String
		 */
		String pyPropertyMode = jsonElement.get("pyPropertyMode").getAsString();
		/*
		containing class
		 */
		String pyClassName = jsonElement.get("pyClassName").getAsString();
		String pyStringType = jsonElement.get("pyStringType").getAsString();
		boolean pyIsReference = jsonElement.get("pyIsReference").getAsBoolean();
		/**
		 * return "" or the class of the field
		 */
		String pyPageClass = jsonElement.get("pyPageClass").getAsString();
		MClass declaringClass = getMClass(pyClassName);
		if (declaringClass!=null) {
			pyProperty.page = pyPropertyMode.startsWith("Page");
			pyProperty.reference = pyIsReference;
			pyProperty.list = pyPropertyMode.equals("PageList");
			pyProperty.name = pyPropertyName;
			if (pyProperty.page) {
				MClass fieldType = getMClass(pyPageClass);
				pyProperty.type = fieldType.name;
			} else {
				pyProperty.type = pyPropertyMode;
			}
			declaringClass.properties.put(pyPropertyName, pyProperty);
		}
	}
}
