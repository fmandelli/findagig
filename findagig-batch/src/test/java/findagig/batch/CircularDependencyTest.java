package findagig.batch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

/**
 * @author diego.costa on 25.10.20
 **/


@AnalyzeClasses(packages = "findagig", importOptions = {ImportOption.DoNotIncludeTests.class})
public class CircularDependencyTest {

    @ArchTest
    static final ArchRule noCycleDependencies =
            slices().matching("findagig.batch..(**)..")
            .should()
            .beFreeOfCycles();


}
