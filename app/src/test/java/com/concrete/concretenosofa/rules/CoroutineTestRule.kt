package com.concrete.concretenosofa.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CoroutineTestRule : TestRule {

    private val dispacher = TestCoroutineDispatcher()
    private val score = TestCoroutineScope(dispacher)


    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement(){
            override fun evaluate() {
                Dispatchers.setMain(dispacher)
                base?.evaluate()
                Dispatchers.resetMain()
                score.cleanupTestCoroutines()
            }
        }
    }
}