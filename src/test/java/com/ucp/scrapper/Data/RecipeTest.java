package com.ucp.scrapper.Data;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;


public class RecipeTest {

    Logger logger = Logger.getLogger("RecipeTest");

    @BeforeClass
    public void recipe() {
        logger.info("Testing Recipe");
    }

    //TODO
    @Test
    public void recipeTest() throws Exception {
        try {

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}