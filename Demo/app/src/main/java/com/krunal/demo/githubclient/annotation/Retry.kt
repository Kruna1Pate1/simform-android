package com.krunal.demo.githubclient.annotation

@Target(AnnotationTarget.FUNCTION)
annotation class Retry(val max: Int = 3)