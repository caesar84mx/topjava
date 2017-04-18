package ru.javawebinar.topjava.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by caesar-84 on 4/18/17.
 */
public class MealServiceRule implements TestRule {
    private static final Logger LOG = LoggerFactory.getLogger(MealServiceRule.class);
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long start = System.currentTimeMillis();
                base.evaluate();
                LOG.info("Execution time: " + (System.currentTimeMillis() - start) + " ms");
            }
        };
    }
}
