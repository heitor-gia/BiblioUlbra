package com.hgianastasio.biblioulbrav2.di

import org.koin.core.KoinApplication
import org.koin.dsl.module

fun KoinApplication.modules(vararg moduleProvider: ModuleProvider) {
    modules(
        listOf(*moduleProvider)
            .map { module(moduleDeclaration = it.moduleFactory) }
    )
}
