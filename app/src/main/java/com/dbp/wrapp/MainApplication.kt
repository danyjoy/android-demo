package com.dbp.wrapp

import android.app.Application
import com.dbp.wrapp.database.EmpDao
import com.dbp.wrapp.database.EmpDatabase
import com.dbp.wrapp.repositories.EmpRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        import(androidXModule(this@MainApplication))
        bind() from singleton { EmpDatabase(instance())}
    }

}