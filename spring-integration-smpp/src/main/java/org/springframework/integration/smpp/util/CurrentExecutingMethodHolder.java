/* Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.integration.smpp.util;

import org.springframework.core.NamedThreadLocal;

import java.lang.reflect.Method;

/**
 * A place to stash the currently executing method for advised classes. This provides the equivalent of JavaScript's arity property,
 * and it's probably cheaper to implement than throwing an {@link Exception} and parsing its stack trace for the method whence the
 * exception was thrown.
 *
 * @author Josh Long
 * @since 1.0
 */
abstract public class CurrentExecutingMethodHolder {

	public static ThreadLocal<Method> methodThreadLocal = new NamedThreadLocal<Method>("methodThreadLocal");

	/**
	 * returns the currently executing thread local-bound method
	 *
	 * @return the currently executing method
	 */
	public static Method getCurrentlyExecutingMethod() {
		return methodThreadLocal.get();
	}

	/**
	 * stash the currently execution method
	 *
	 * @param m the method in the throes of execution
	 */
	public static void setCurrentlyExecutingMethod(Method m) {
		removeMethod();
		methodThreadLocal.set(m);
	}

	/**
	 * the thread local method.
	 */
	public static void removeMethod() {
		methodThreadLocal.remove();
	}
}
