// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package net.pega.intellij.modeler;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.function.Supplier;

public final class PegaBundle extends DynamicBundle {
	@NonNls
	private static final String BUNDLE = "messages.PegaBundle";
	private static final PegaBundle INSTANCE = new PegaBundle();

	private PegaBundle() {
		super(BUNDLE);
	}

	public static String getHelp(String page) {
		final Locale aDefault = Locale.getDefault();
		final ClassLoader classLoader = PegaBundle.class.getClassLoader();
		InputStream resourceAsStream = classLoader.getResourceAsStream("i18n/" + page + "_" + aDefault.toString() + ".html");
		if (resourceAsStream == null) {
			resourceAsStream = classLoader.getResourceAsStream("i18n/" + page + ".html");
		}
		if (resourceAsStream != null) {
			final byte[] bytes;
			try {
				bytes = resourceAsStream.readAllBytes();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return new String(bytes);
		}
		return page+" help not found.";
	}

	@NotNull
	public static @Nls String message(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, Object @NotNull ... params) {
		return INSTANCE.getMessage(key, params);
	}

	@NotNull
	public static Supplier<@Nls String> messagePointer(@NotNull @PropertyKey(resourceBundle = BUNDLE) String key, Object @NotNull ... params) {
		return INSTANCE.getLazyMessage(key, params);
	}
}
