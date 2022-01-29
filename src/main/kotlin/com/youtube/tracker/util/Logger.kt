package com.youtube.tracker.util

import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

fun loggerFor(clazz: KClass<*>) = LoggerFactory.getLogger(clazz.java)
