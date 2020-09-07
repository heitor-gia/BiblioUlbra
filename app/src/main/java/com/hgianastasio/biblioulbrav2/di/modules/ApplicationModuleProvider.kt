package com.hgianastasio.biblioulbrav2.di.modules

import com.hgianastasio.biblioulbrav2.di.ModuleProvider
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

/**
 * Created by heitorgianastasio on 4/29/17.
 */

object ApplicationModuleProvider : ModuleProvider( {
    single { Executors.newFixedThreadPool(15) as ThreadPoolExecutor }
    factory { UseCaseExecutor() }
})