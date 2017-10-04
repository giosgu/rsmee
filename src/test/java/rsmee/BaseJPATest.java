package rsmee;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class BaseJPATest {

	protected static String POM = "pom.xml"; 
	protected static String COMMONS_LANG_3 = "org.apache.commons:commons-lang3";
	
    protected static WebArchive createDeployment(String deploymentUnitName) {
    	return ShrinkWrap.create(WebArchive.class, deploymentUnitName)
        .addAsResource("persistence-test.xml", "META-INF/persistence.xml")
        .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
        .addAsWebInfResource("rsmtst2-ds.xml")
        .addClass(BaseJPATest.class)
		.addPackages(true, "model")
		.addPackages(true, "listener")
		.addPackages(true, "dao");
    }

}
