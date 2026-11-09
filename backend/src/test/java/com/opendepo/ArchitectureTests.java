/*
 * SPDX-License-Identifier: MIT
 */
package com.opendepo;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.opendepo", importOptions = ImportOption.DoNotIncludeTests.class)
class ArchitectureTests {

    @ArchTest
    static final ArchRule domainMustNotDependOnFrameworks = noClasses()
            .that()
            .resideInAPackage("com.opendepo..domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                    "org.springframework..", "jakarta.persistence..", "org.hibernate..", "org.springframework.web..")
            .allowEmptyShould(true);

    @ArchTest
    static final ArchRule domainMustNotDependOnOuterLayers = noClasses()
            .that()
            .resideInAPackage("com.opendepo..domain..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage(
                    "com.opendepo..application..", "com.opendepo..infrastructure..", "com.opendepo..presentation..")
            .allowEmptyShould(true);

    @ArchTest
    static final ArchRule applicationMustNotDependOnPresentationOrInfrastructure = noClasses()
            .that()
            .resideInAPackage("com.opendepo..application..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("com.opendepo..presentation..", "com.opendepo..infrastructure..")
            .allowEmptyShould(true);
}
